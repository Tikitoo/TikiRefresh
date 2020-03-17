package cat.tiki.tikirefresh.samples.jsoup

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.LiveData
import cat.tiki.common.extension.dip2px
import cat.tiki.tikiadapter.TikiBaseModel
import cat.tiki.tikirefresh.TikiBaseJsoupRefreshActivity
import cat.tiki.tikirefresh.extension.viewModel
import cat.tiki.tikirefresh.samples.R
import cat.tiki.tikirefresh.samples.boxcover.KotlinBoxCoverViewModel
import org.jsoup.nodes.Document

/**
 * Created by Yifa Liang on 2019-08-21.
 */
class TikiHtmlSampleActivity: TikiBaseJsoupRefreshActivity<KotlinBoxCoverViewModel>() {

    override val viewModel by viewModel<KotlinBoxCoverViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        onStartRefresh()
        enablePullRefresh(true)
        enableLoadMore(true)
        onFirstLoad()

        val textView = TextView(this)
        textView.layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, dip2px(60f))
        textView.text = "dfajsfladjsfldjsaf"
        textView.setOnClickListener {
            Toast.makeText(this, "fdasfdas", Toast.LENGTH_SHORT).show()
        }
//        topLayout.addView(textView)
    }

    override fun onSuccCallback(document: Document?) {
//        val allDataList : MutableList<TikiBaseModel> = arrayListOf()

        var parseResultList: MutableList<ArkSearchModel>? = mutableListOf()

        if (document != null) {

            // div id links_wrapper
            // div id links
            // div
            // div class result__body
            // result__title result__extras__url  in_result_banner result__snippet js-result-snippet

            try {
                val linkWrapperElement = document.getElementById("links_wrapper")
                val linkElement = linkWrapperElement.getElementById("links")
                var resultList = linkElement.children()

                parseResultList = resultList?.map {
                    val title = it.getElementsByClass("result__title")?.text()
                    val url = it.getElementsByClass("result__extras__url")?.text()
                    val banner = it.getElementsByClass("in_result_banner")?.text()
                    val content = it.getElementsByClass("result__snippet js-result-snippet")?.text()
                    var arkSearchModel = ArkSearchModel(LAYOUT_SEARCH_CONTENT, 1, title, url, banner, content)
                    arkSearchModel
                }?.toMutableList()
            } catch (e: Exception) {
            }

            /*parseResultList?.apply {
                allDataList = this
            }*/


            rvAdapter?.apply {
                registerItem(LAYOUT_SEARCH_CONTENT, ArkSearchItemVH())
                updateData(parseResultList!!)


            }
        }

//            System.out.println("title: " + parseResultList)


        Log.d("TikiHtmlSampleActivity", "body is no null: " + (document == null))
    }


    override fun createLiveData(): LiveData<Document>? {
        viewModel.loadUrl = "https://www.dogedoge.com/results?q=读书&p=%s"
        return viewModel.jsoupData
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




    companion object {
        const val LAYOUT_SEARCH_CONTENT = R.layout.item_search_content
    }
}











