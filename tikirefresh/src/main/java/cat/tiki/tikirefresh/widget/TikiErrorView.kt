package cat.tiki.tikirefresh.widget

import android.content.Context
import android.graphics.Paint
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import cat.tiki.tikirefresh.R


/**
 * Created by Tikitoo on 2019-11-07.
 */
class TikiErrorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        initView(context)
    }

    protected var libWidgetIvImage: ImageView? = null
    protected var libWidgetTvText: TextView? = null
    protected var mTvLink: TextView? = null
    private var callback: Callback? = null
    private var mTipCallback: TipCallback? = null
    private var isClickGone = true



    private fun initView(context: Context) {
        val rootView = LayoutInflater.from(context).inflate(R.layout.base_errorview, this)
        libWidgetIvImage = rootView.findViewById<View>(R.id.lib_widget_iv_image) as ImageView
        libWidgetTvText = rootView.findViewById<View>(R.id.lib_widget_tv_text) as TextView
        mTvLink = rootView.findViewById<View>(R.id.lib_widget_tv_link) as TextView
        val paint = mTvLink!!.paint
        if (paint != null) {
            paint.flags = Paint.UNDERLINE_TEXT_FLAG //下划线
            paint.isAntiAlias = true//抗锯齿
        }
        setListener()
        orientation = LinearLayout.VERTICAL
    }

    fun setTvLinkStr(str: CharSequence) {
        if (mTvLink != null) {
            mTvLink!!.text = str
            if (TextUtils.isEmpty(str)) {
                mTvLink!!.visibility = View.INVISIBLE
            } else {
                mTvLink!!.visibility = View.VISIBLE
            }
        }
    }

    fun setShowTipView(flag: Boolean) {
        if (flag) {
            mTvLink!!.visibility = View.VISIBLE
            mTvLink!!.setOnClickListener {
                if (mTipCallback != null) {
                    mTipCallback!!.onClickListener()
                }
            }
        } else {
            mTvLink!!.visibility = View.GONE
        }
    }

    fun setClickGone(clickGone: Boolean) {
        isClickGone = clickGone
    }

    private fun setListener() {
        this.setOnClickListener {
            if (callback != null) {
                if (isClickGone) {
                    visibility = View.GONE
                }
                callback!!.onRetryClickListener()
            }
        }

    }

    fun callback(callback: Callback?): TikiErrorView {
        this.callback = callback
        return this
    }

    fun callback(callback: TipCallback?): TikiErrorView {
        this.mTipCallback = callback
        return this
    }


    fun errorImage(@DrawableRes res: Int): TikiErrorView {
        if (libWidgetIvImage != null) {
            libWidgetIvImage!!.setImageResource(res)
        }
        return this
    }

    fun errorText(text: String?): TikiErrorView {
        if (libWidgetTvText != null) {
            libWidgetTvText!!.text = text
        }
        return this
    }

    interface Callback {

        /**
         * 重试点击事件
         */
        fun onRetryClickListener()
    }

    interface TipCallback {

        /**
         * 重试点击事件
         */
        fun onClickListener()
    }
}
