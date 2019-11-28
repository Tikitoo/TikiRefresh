package cat.tiki.tikirefresh

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.tiki.tikiadapter.TikiBaseModel
import cat.tiki.tikiadapter.TikiRvAdapter
import cat.tiki.tikirefresh.widget.TikiErrorView
import cat.tiki.tikirefresh.widget.TikiLoadMoreCircleFooter
import cat.tiki.tikirefresh.widget.TikiSmartRefreshLayout
import kotlinx.android.synthetic.main.lib_arch_activity_kotlin_base.*

/**
 * Created by Tikitoo on 2019-11-07.
 */
abstract class TikiBaseRvActivity: TikiBaseActivity() {

    private lateinit var refreshRvLayout: TikiSmartRefreshLayout
//    lateinit var refreshType: TikiBaseViewModel.RefreshType
    private var loadingView: TikiLoadMoreCircleFooter? = null
    private var errorView: TikiErrorView? = null
    var recyclerView: RecyclerView? = null

    lateinit var dataList: MutableList<out TikiBaseModel>
    lateinit var rvAdapter: TikiRvAdapter<TikiBaseModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lib_arch_activity_kotlin_base)

        refreshRvLayout = biz_show_kotlin_base_refresh_rv_layout
        errorView = biz_show_kotlin_base_error_view
        initCircleLoadingView()
        setRvAdapter()
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
            onRegisterItem(rvAdapter)
            adapter?.notifyDataSetChanged()

        }
//        refreshRvLayout.setCallback(this)
//        refreshRvLayout.setPullToRefresh(true)
//        refreshRvLayout.enableLoadMore()

    }

    abstract fun onRegisterItem(rvAdapter: TikiRvAdapter<TikiBaseModel>)


    fun setEmptyText(emptyTxt: String) {
        errorView?.errorText(emptyTxt)
        errorView?.visibility = View.VISIBLE
    }

    fun updateData(showDataList: MutableList<out TikiBaseModel>) {
        rvAdapter?.apply {
            dataList = showDataList
            notifyDataSetChanged()
        }
    }

    fun addData(showDataList: MutableList<out TikiBaseModel>) {
        rvAdapter?.apply {
            dataList?.addAll(showDataList as Collection<Nothing>)
            notifyDataSetChanged()
        }
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





}