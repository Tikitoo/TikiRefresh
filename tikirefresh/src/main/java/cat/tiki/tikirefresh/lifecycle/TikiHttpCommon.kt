package cat.tiki.tikirefresh.lifecycle

/**
 * Created by Yifa Liang on 2019-11-25.
 */
class TikiHttpCommon {
    enum class TikiNethType {
        FAILED,
        NO_DATA,
        SUCCESS,
        PARAMS_ERROR
    }

    companion object Common {
        val ERROR_FAILED = 10001
        val ERROR_EMPTY = 10001

        val EMPTYTXT_FAILED = "网络错误，请求数据为空"
        val EMPTYTXT_400 = "客户端请求错误"
        val EMPTYTXT_500 = "服务端错误，请稍后重试"
        val EMPTYTXT_NO_DATA = "请求数据为空"

    }
}