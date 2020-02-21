package cat.tiki.tikirefresh.lifecycle

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


open class TikiRetrofitClient {

    fun getRetrofit(host: String): Retrofit {
        val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
//                .add(LocalDateTimeAdapter())
//                .add(MultipleFormatsDateAdapter())
                .build()

        val map = Types.newParameterizedType(Map::class.java, String::class.java, Any::class.java)
        val adapter = moshi.adapter<Map<String, Any>>(map)
        return Retrofit.Builder()
                .baseUrl(if (host != null)  host else HTTPS_API_HOST)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(TikiLiveDataCallAdapterFactory())
                .callFactory(okhttp?.build())
            .build()
    }



    companion object {
        private var HTTPS_API_HOST = ""
        private var interceptor: Interceptor? = null
        private var okhttp: OkHttpClient.Builder = getOkHttpClient()

        init {

        }
        open fun getOkhttp(): OkHttpClient.Builder {
            return okhttp
        }

        open fun setHost(host: String) {
            HTTPS_API_HOST = host
        }

        open fun setInterceptor(interceptor2: Interceptor) {
            interceptor = interceptor2
        }

        private fun getOkHttpClient(): OkHttpClient.Builder {
            var socketFactory: HttpsUtils.SSLParams
            var okHttpClient: OkHttpClient.Builder? = null
            try {
                socketFactory = HttpsUtils.getSslSocketFactory(null, null, null)
                OkHttpClient.Builder()
                    .addInterceptor(interceptor!!)
                    .sslSocketFactory(socketFactory.sSLSocketFactory!!, socketFactory.trustManager!!)
            } catch (e: Exception) {
            }

            if (okHttpClient == null) {
                okHttpClient = OkHttpClient.Builder()
            }

            return okHttpClient
        }
    }
}