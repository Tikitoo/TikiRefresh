package cat.tiki.tikirefresh.samples.fg

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import cat.tiki.common.extension.dip2px
import cat.tiki.tikiadapter.TikiBaseModel
import cat.tiki.tikiadapter.TikiItemClickListener
import cat.tiki.tikirefresh.TikiBaseRvRefreshFragment
import cat.tiki.tikirefresh.extension.viewModel
import cat.tiki.tikirefresh.lifecycle.TikiApiResponse
import cat.tiki.tikirefresh.samples.R
import cat.tiki.tikirefresh.samples.boxcover.*

/**
 * Created by Yifa Liang on 2020-02-21.
 */
class TikiFgRefreshSampleFragment: TikiBaseRvRefreshFragment<KotlinSubject, KotlinBoxCoverViewModel>(), TikiItemClickListener {

    override val viewModel by viewModel<KotlinBoxCoverViewModel>()

    override fun setup() {
        enablePullRefresh(true)
        enableLoadMore(true)
        onFirstLoad()

        rvAdapter?.registerItem(LAYOUT_BOXCOVER, KotlinBoxCoverVH())
        rvAdapter?.registerItem(LAYOUT_BOXHEADER, KotlinBoxHeaderVH())
        rvAdapter?.setOnItemClick(this@TikiFgRefreshSampleFragment)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onSuccCallback(body: KotlinSubject) {
        val allDataList : MutableList<TikiBaseModel> = arrayListOf()
        var kotlinBoxHeader: KotlinBoxHeader

        for ( i in 1..10) {
            kotlinBoxHeader = KotlinBoxHeader(LAYOUT_BOXHEADER, 1, "Hello" + i)
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
                    layoutId = LAYOUT_BOXCOVER
                    setImgParams(activity?.applicationContext!!)
                }
            }

            allDataList.addAll(boxCovers)

            rvAdapter?.apply {
                updateData(allDataList)
            }
        }

    }

    override fun onItemClick(view: View, position: Int) {

    }

    companion object {
        val LAYOUT_BOXCOVER = R.layout.biz_show_item_kotlin_boxcover
        val LAYOUT_BOXHEADER = R.layout.biz_show_item_kotlin_boxheader
    }

    override fun createLiveData(): LiveData<TikiApiResponse<KotlinSubject>>? {
        viewModel.tagId = 249
        return viewModel.topicListModel
    }




}