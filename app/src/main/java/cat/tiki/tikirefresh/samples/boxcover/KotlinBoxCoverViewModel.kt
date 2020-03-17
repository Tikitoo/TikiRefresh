package cat.tiki.tikirefresh.samples.boxcover

import androidx.arch.core.util.Function
import androidx.lifecycle.*
import cat.tiki.tikirefresh.TikiBaseViewModel
import cat.tiki.tikirefresh.utils.TikiWebApiCommon
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * T 做一个基类
 * Created by Yifa Liang on 2019-08-22.
 */
class KotlinBoxCoverViewModel : TikiBaseViewModel() {
    open var tagId: Int = 0
    open var loadUrl: String = ""
    val service = getService(SampleApi::class.java)
//    val service2 = getService2("", SampleApi::class.java)

    val topicListModel = Transformations.switchMap(page) {
        service?.getTopicList(it, tagId)
    }

    val jsoupData = Transformations.switchMap(page) {
        loadJsoupData(loadUrl)
    }


    fun loadJsoupData(url: String): LiveData<Document>? {
        val jsupLiveData = MutableLiveData<Document>()
        Thread(Runnable {
            val loadUrl = String.format(url, page.value)
            var document: Document? = null
            try {
                document = Jsoup.connect(loadUrl)
                    .userAgent(TikiWebApiCommon.USER_AGENT_ANDROID)
                    .header("accept", "text/html")
                    .header("accept-encoding", "gzip")
//                .header("sec-fetch-mode", "same-origin")
//                .header("sec-fetch-user", "?1")
//                .header("upgrade-insecure-requests", "1")
                    .timeout(3000)
                    .get()
            } catch (e: Exception) {
                System.out.println("error: " + e.printStackTrace())
            }
            jsupLiveData.postValue(document)
        }).start()

        return jsupLiveData
    }

}