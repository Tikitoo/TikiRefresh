package cat.tiki.tikirefresh.samples

import android.app.Application
import android.content.Context
import cat.tiki.common.extension.TikiCommon
import cat.tiki.tikirefresh.lifecycle.TikiRetrofitClient
import okhttp3.logging.HttpLoggingInterceptor
import android.R.attr.tag



/**
 * Created by Yifa Liang on 2019-10-23.
 */
class TikiContext: Application() {
    override fun onCreate() {
        super.onCreate()
        _context = this

        TikiRetrofitClient.setHost("https://api.zaozuo.com")
        TikiRetrofitClient.setInterceptor(TikiRefreshInterceptor())
        TikiCommon.context = this

    }

    companion object {
        var _context: Application? = null
        fun getContext(): Context {
            return _context!!
        }

    }

}