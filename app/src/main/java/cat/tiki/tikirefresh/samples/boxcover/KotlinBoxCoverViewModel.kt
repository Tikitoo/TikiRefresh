package cat.tiki.tikirefresh.samples.boxcover

import androidx.lifecycle.*
import cat.tiki.tikirefresh.TikiBaseViewModel

/**
 * T 做一个基类
 * Created by Yifa Liang on 2019-08-22.
 */
class KotlinBoxCoverViewModel : TikiBaseViewModel() {
    open var tagId: Int = 0
    val service = getService(SampleApi::class.java)

    val topicListModel = Transformations.switchMap(page) {
        service?.getTopicList(it, tagId)
    }


}