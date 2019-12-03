package cat.tiki.tikirefresh.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import androidx.recyclerview.widget.RecyclerView
import cat.tiki.tikirefresh.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader


/**
 * Created by Tikitoo on 2019-11-07.
 */
class TikiSmartRefreshLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr) {

    init {
        initView(context, attrs)
    }


    private lateinit var refreshRv: RecyclerView
    private lateinit var refreshLayout: SmartRefreshLayout


    private fun initView(context: Context, attrs: AttributeSet?) {
        val view = View.inflate(context, R.layout.lib_widget_smart_refresh_layout, this)
                refreshLayout = view.findViewById(R.id.lib_widget_refresh_view_frame)
                refreshRv = view.findViewById(R.id.lib_widget_refresh_rcv)

        refreshLayout?.apply {
            setOnRefreshListener {
                it.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                println("onRefreshBegin")
                callback?.onRefreshBegin()
            }
            setOnLoadMoreListener {
                it.finishLoadMore(2000/*,false*/);//传入false表示刷新失败
                callback?.onLoadMoreBegin()
            }

            /** Header & Footer */
            setRefreshHeader(ClassicsHeader(context))
            setRefreshFooter(ClassicsFooter(context).setDrawableSize(20f))

        }
    }

    fun setPullToRefresh(pullToRefresh: Boolean) {
        if (refreshLayout != null) {
            refreshLayout.setEnableRefresh(pullToRefresh)
        }
    }

    fun enableLoadMore() {
        // TODO: 2019-11-07  新的Footer

    }

    fun refreshComplete() {
//        libWidgetRefreshViewFooter.setLoadEnable(true)
        refreshLayout.finishRefresh()

    }

    fun loadMoreComplete() {
        refreshLayout.finishLoadMore()
//        libWidgetRefreshViewFooter.loadMoreComplete()
//        libWidgetRefreshPbLoadMore.stopRefreshing()
//        libWidgetRefreshPbLoadMore.setVisibility(View.INVISIBLE)
    }

    private var callback: Callback? = null


    open fun getRecyclerView():RecyclerView {
        return refreshRv
    }
    open fun setCallback(callback: Callback) {
        this.callback = callback
    }

    fun autoRefresh() {
        refreshLayout.autoRefresh()
    }

    interface Callback {
        /**
         * 开始下拉 自定的Header
         */
        fun onPullDownBegin(currentPercent: Float)

        /**
         * 开始刷新
         */
        fun onRefreshBegin()

        /**
         * 开始加载更多数据
         */
        fun onLoadMoreBegin()
    }

}