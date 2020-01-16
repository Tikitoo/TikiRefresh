package cat.tiki.tikirefresh.samples

import okhttp3.Interceptor
import okhttp3.Response

class TikiRefreshInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newBuilder = request.newBuilder()
        newBuilder.header("user-agent", "iOS")
        return chain.proceed(request)
    }

}
