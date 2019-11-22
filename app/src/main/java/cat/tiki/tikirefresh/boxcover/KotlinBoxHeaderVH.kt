package cat.tiki.tikirefresh.boxcover

import android.view.View
import android.widget.TextView
import cat.tiki.tikiadapter.TikiBaseVHImpl
import cat.tiki.tikirefresh.R

/**
 * Created by Yifa Liang on 2019-08-20.
 */
class KotlinBoxHeaderVH: TikiBaseVHImpl<KotlinBoxHeader>() {

    override fun bindData(data: KotlinBoxHeader, view: View) {
        val titleTv = view?.findViewById<TextView>(R.id.biz_show_item_boxcover_title_tv)

        data?.apply {

        }
    }
}