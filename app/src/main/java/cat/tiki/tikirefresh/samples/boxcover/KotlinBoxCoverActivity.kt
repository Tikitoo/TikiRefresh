package cat.tiki.tikirefresh.samples.boxcover

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.LiveData
import cat.tiki.tikirefresh.TikiBaseRefreshActivity
import cat.tiki.tikiadapter.TikiBaseModel
import cat.tiki.common.extension.dip2px
import cat.tiki.tikirefresh.lifecycle.TikiApiResponse
import cat.tiki.tikirefresh.extension.viewModel
import cat.tiki.tikirefresh.samples.R

/**
 * Created by Yifa Liang on 2019-08-21.
 */
class KotlinBoxCoverActivity: TikiBaseRefreshActivity<KotlinSubject, KotlinBoxCoverViewModel>() {

    override val viewModel by viewModel<KotlinBoxCoverViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        onStartRefresh()
        enablePullRefresh(false)
        enableLoadMore(true)
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
        viewModel.tagId = 249
        return viewModel.topicListModel
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_goods, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_clear -> {
                clearData()
                return true
            }
            R.id.action_no_network -> {
                return true
            }
        }
        return super.onOptionsItemSelected(item)

    }

    private fun clearData() {
        updateData(mutableListOf<TikiBaseModel>())
        setEmptyText("空内容")
    }

}











