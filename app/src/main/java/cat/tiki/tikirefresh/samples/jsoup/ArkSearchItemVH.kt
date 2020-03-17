package cat.tiki.tikirefresh.samples.jsoup

import android.view.View
import cat.tiki.tikiadapter.TikiBaseVHImpl
import kotlinx.android.synthetic.main.item_search_content.view.*

class ArkSearchItemVH : TikiBaseVHImpl<ArkSearchModel>() {
    override fun bindData(data: ArkSearchModel, view: View) {
        val titleTv = view?.item_search_content_title_tv
        val urlTv = view?.item_search_content_url_tv
        val bannerImg =view?.item_search_content_banner_tv
        val contentTv =view?.item_search_content_content_tv

        data?.apply {
            titleTv.text = title
            urlTv.text = url
            contentTv.text = content
        }
    }

}
