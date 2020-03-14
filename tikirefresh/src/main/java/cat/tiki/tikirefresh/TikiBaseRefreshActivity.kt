package cat.tiki.tikirefresh

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import cat.tiki.tikiadapter.TikiBaseModel
import cat.tiki.tikiadapter.TikiRvAdapter
import cat.tiki.tikirefresh.lifecycle.ApiErrorResponse
import cat.tiki.tikirefresh.lifecycle.ApiSuccessResponse
import cat.tiki.tikirefresh.lifecycle.TikiApiResponse
import cat.tiki.tikirefresh.lifecycle.TikiHttpCommon
import cat.tiki.tikirefresh.widget.TikiLoadMoreCircleFooter
import cat.tiki.tikirefresh.widget.TikiErrorView
import cat.tiki.tikirefresh.widget.TikiSmartRefreshLayout

/**
 * 通用的网络请求
 * 使用 LiveData 实现
 *
 * Created by Tikitoo on 2019-11-07.
 */
abstract class TikiBaseRefreshActivity<M: Any, VM: TikiBaseViewModel>: AppCompatActivity(), TikiSmartRefreshLayout.Callback {

    private lateinit var refreshRvLayout: TikiSmartRefreshLayout
    lateinit var dataList: MutableList<out TikiBaseModel>
    lateinit var rvAdapter: TikiRvAdapter<TikiBaseModel>
    open val viewModel: VM? = null
    lateinit var refreshType: TikiBaseViewModel.RefreshType
    //    var loadingView = biz_show_kotlin_base_loading_view
    private var loadingView: TikiLoadMoreCircleFooter? = null
    private var errorView: TikiErrorView? = null
    lateinit var topLayout: RelativeLayout
    var recyclerView: RecyclerView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_base)

        topLayout = findViewById<RelativeLayout>(R.id.kotlin_base_refresh_top_layout)
        refreshRvLayout = findViewById(R.id.kotlin_base_refresh_rv_layout)
        errorView = findViewById(R.id.kotlin_base_error_view)
        initCircleLoadingView()
        setRvAdapter()
        loadDataNet()
    }

    private fun setRvAdapter() {
        recyclerView = refreshRvLayout.getRecyclerView()
        dataList = arrayListOf()
        rvAdapter = TikiRvAdapter(
            applicationContext,
            dataList
        )
        recyclerView?.apply {
            adapter = rvAdapter
            rvAdapter.setRvConfig(false, this)
            adapter?.notifyDataSetChanged()

        }
        refreshRvLayout?.apply {
            setCallback(this@TikiBaseRefreshActivity)
            setPullToRefresh(true)
            enableLoadMore(false)
        }

    }

    protected fun enableLoadMore(enable: Boolean) {
        refreshRvLayout.enableLoadMore(enable)
    }

    protected fun enablePullRefresh(enable: Boolean) {
        refreshRvLayout.setPullToRefresh(enable)
    }


    fun loadDataNet() {
        createLiveData()?.observe(this, Observer {
            it?.apply {
                refreshType = viewModel?.refreshType!!
                when(refreshType) {
                    TikiBaseViewModel.RefreshType.LOADING -> {
                        dismissLoading()
                        refreshRvLayout.refreshComplete()
                        refreshRvLayout.loadMoreComplete()
                    }
                    TikiBaseViewModel.RefreshType.REFRESH -> {
                        refreshRvLayout.refreshComplete()
                        refreshRvLayout.loadMoreComplete()
                    }
                    TikiBaseViewModel.RefreshType.LOADMORE -> {
                        refreshRvLayout.loadMoreComplete()
                    }
                }


                refreshRvLayout.loadMoreComplete()
                if (it is ApiSuccessResponse<M>) {
                    it.body.apply {
                        onSuccCallback(it.body)
                    }
                    errorView?.visibility = View.GONE
//                    LogUtils.d("load data success.....")
                } else if(it is ApiErrorResponse<*>) {
                    // 设置错误数据
                    it.apply {
                        if (-1 == it.errorCode) {
                            setEmptyText(TikiHttpCommon.EMPTYTXT_FAILED)
                        } else if(it.errorCode >= 400 && it.errorCode < 500) {
                            setEmptyText(TikiHttpCommon.EMPTYTXT_400)
                        } else if(it.errorCode >= 500) {
                            setEmptyText(TikiHttpCommon.EMPTYTXT_500)
                        } else {
                            setEmptyText(TikiHttpCommon.EMPTYTXT_NO_DATA)
                        }
                    }
//                    LogUtils.e("load data failed.....")
                }
            }
        })
    }

    fun setEmptyText(emptyTxt: String) {
        errorView?.errorText(emptyTxt)
        errorView?.visibility = View.VISIBLE
    }

    fun updateData(showDataList: MutableList<out TikiBaseModel>) {
        rvAdapter?.apply {
            if (refreshType == TikiBaseViewModel.RefreshType.LOADMORE) {
                dataList?.addAll(showDataList as Collection<Nothing>)
            } else{
                dataList = showDataList
            }
            notifyDataSetChanged()
        }
    }

    override fun onPullDownBegin(currentPercent: Float) {
//        LogUtils.d()
    }

    open fun onFirstLoad() {
//        LogUtils.d()
        showLoading()
        viewModel?.loading()
    }

    open fun onStartRefresh() {
        refreshRvLayout.autoRefresh()
    }

    override fun onRefreshBegin() {
        println("onRefreshBegin........")
        viewModel?.refresh()
    }


    override fun onLoadMoreBegin() {
//        LogUtils.d()
        viewModel?.loadMore()
    }


    private fun initCircleLoadingView() {
        if (loadingView == null) {
            val layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
            loadingView = TikiLoadMoreCircleFooter(this)

            addContentView(loadingView, layoutParams)
            loadingView?.setVisibility(View.GONE)
        }
    }

    fun showLoading() {
        loadingView?.visibility = View.VISIBLE
        loadingView?.show()
    }

    fun dismissLoading() {
        loadingView?.visibility = View.GONE
        loadingView?.dismiss()
    }


    abstract fun onSuccCallback(body: M)
    abstract fun createLiveData(): LiveData<TikiApiResponse<M>>?


}