package cat.tiki.tikirefresh.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.RelativeLayout

import cat.tiki.tikirefresh.R


/**
 * Created by Tikitoo on 2019-11-07.
 */

class TikiLoadMoreCircleFooter @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    protected var mLoadmoreProgressBarView: ProgressBar? = null
    protected var mLoadmoreLayout: RelativeLayout? = null

    init {
        init(context)
    }

    private fun init(context: Context) {
        View.inflate(context, R.layout.circle_loadmore_layout, this)
        initView(this)
    }

    private fun initView(rootView: View) {
        mLoadmoreProgressBarView =
            rootView.findViewById<View>(R.id.lib_widget_zz_circle_loadmore_progress_bar_view) as ProgressBar
        mLoadmoreLayout =
            rootView.findViewById<View>(R.id.lib_widget_zz_circle_loadmore_layout) as RelativeLayout
        gravity = Gravity.CENTER
    }

    fun startRefreshing() {
        if (mLoadmoreProgressBarView != null) {
            mLoadmoreProgressBarView!!.visibility = View.VISIBLE
        }
    }


    fun show() {
        startRefreshing()
    }

    fun stopRefreshing() {
        if (mLoadmoreProgressBarView != null) {
            mLoadmoreProgressBarView!!.visibility = View.INVISIBLE
        }
    }

    fun dismiss() {
        stopRefreshing()
    }
}
