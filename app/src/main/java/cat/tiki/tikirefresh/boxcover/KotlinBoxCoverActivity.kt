package cat.tiki.tikirefresh.boxcover

import android.os.Bundle
import androidx.lifecycle.LiveData
import cat.tiki.tikirefresh.TikiBaseRefreshActivity
import cat.tiki.tikiadapter.TikiBaseModel
import cat.tiki.common.extension.dip2px
import cat.tiki.tikirefresh.R
import cat.tiki.tikirefresh.TikiApiResponse
import cat.tiki.tikirefresh.extension.viewModel
/**
 * Created by Yifa Liang on 2019-08-21.
 */
class KotlinBoxCoverActivity: TikiBaseRefreshActivity<KotlinSubject, KotlinBoxCoverViewModel>() {

    override val viewModel by viewModel<KotlinBoxCoverViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onFirstLoad()
    }

    override fun onSuccCallback(body: KotlinSubject) {
        val allDataList : MutableList<TikiBaseModel> = arrayListOf()
        var kotlinBoxHeader: KotlinBoxHeader
        for ( i in 1..10) {
            kotlinBoxHeader = KotlinBoxHeader(R.layout.biz_show_item_kotlin_boxheader, 1, "Hello" + i)
            kotlinBoxHeader?.apply {

                column =  if(i % 3 == 0) 1 else 2
                rect.side = dip2px(15F)
                rect.center = dip2px(8F)
                rect.bottom = dip2px(15F)
            }
            allDataList.add(kotlinBoxHeader)
        }

        body?.data?.apply {
            for (boxCover in boxCovers) {
                boxCover?.apply {
                    margin = dip2px(40F)
                    column = 2
                    rect.side = dip2px(15F)
                    rect.center = dip2px(8F)
                    rect.bottom = dip2px(15F)
                    layoutId = R.layout.biz_show_item_kotlin_boxcover
                    setImgParams(applicationContext)
                }
            }

            allDataList.addAll(boxCovers)


            rvAdapter?.apply {
                registerItem(R.layout.biz_show_item_kotlin_boxcover, KotlinBoxCoverVH())
                registerItem(R.layout.biz_show_item_kotlin_boxheader, KotlinBoxHeaderVH())
                updateData(allDataList)
            }
        }
    }

    override fun createLiveData(): LiveData<TikiApiResponse<KotlinSubject>>? {
        return viewModel.topicListModel
    }

}










