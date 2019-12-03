package cat.tiki.tikirefresh

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cat.tiki.tikiadapter.TikiBaseModel
import cat.tiki.tikiadapter.TikiRvAdapter
import cat.tiki.tikirefresh.widget.TikiErrorView
import cat.tiki.tikirefresh.widget.TikiLoadMoreCircleFooter
import cat.tiki.tikirefresh.widget.TikiSmartRefreshLayout
import kotlinx.android.synthetic.main.lib_arch_fragment_kotlin_base.*

/**
 * Created by Yifa Liang on 2019-11-28.
 */
abstract class TikiBaseRvFragment: TikiBaseFragment() {
    private var refreshRvLayout: TikiSmartRefreshLayout? = null
    //    lateinit var refreshType: TikiBaseViewModel.RefreshType
    private var loadingView: TikiLoadMoreCircleFooter? = null
    private var errorView: TikiErrorView? = null
    var recyclerView: RecyclerView? = null


    lateinit var dataList: MutableList<out TikiBaseModel>
    lateinit var rvAdapter: TikiRvAdapter<TikiBaseModel>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.lib_arch_fragment_kotlin_base, container, false)



        refreshRvLayout = fragment_kotlin_base_refresh_rv_layout
        errorView = fragment_kotlin_base_error_view
        loadingView = fragment_kotlin_base_loading_view
        setRvAdapter()
        return rootView
    }

    private fun setRvAdapter() {
        recyclerView = refreshRvLayout?.getRecyclerView()
        dataList = mutableListOf()
        rvAdapter = TikiRvAdapter(
            activity?.applicationContext!!,
            dataList
        )
        recyclerView?.apply {
            adapter = rvAdapter
            rvAdapter.setRvConfig(false, this)
            onRegisterItem(rvAdapter)
            adapter?.notifyDataSetChanged()

        }

        refreshRvLayout?.setPullToRefresh(false)

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

    fun showLoading() {
        loadingView?.visibility = View.VISIBLE
        loadingView?.show()
    }

    fun dismissLoading() {
        loadingView?.visibility = View.GONE
        loadingView?.dismiss()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }
}