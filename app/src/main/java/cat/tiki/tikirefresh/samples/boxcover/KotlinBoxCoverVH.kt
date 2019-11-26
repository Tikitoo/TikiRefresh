package cat.tiki.tikirefresh.samples.boxcover

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import cat.tiki.tikirefresh.samples.R
import cat.tiki.common.image.load
import cat.tiki.tikiadapter.TikiBaseVHImpl
import cat.tiki.tikirefresh.samples.utils.getImgUrl

/**
 * Created by Yifa Liang on 2019-08-20.
 */
class KotlinBoxCoverVH: TikiBaseVHImpl<KotlinBoxCover>() {

    override fun bindData(data: KotlinBoxCover, view: View) {
        val titleTv = view?.findViewById<TextView>(R.id.biz_show_item_boxcover_title_tv)
        val sloganTv = view?.findViewById<TextView>(R.id.biz_show_item_boxcover_slogan_tv)
        val topImg = view?.findViewById<ImageView>(R.id.biz_show_item_boxcover_top_img)

        data?.apply {
            titleTv?.text = name
            sloganTv?.text = slogan
            println("load url: " + headImg + imgWidth + "; " + imgHeight)
            topImg.layoutParams?.apply {
                width = imgWidth
                height = imgHeight
            }
            topImg?.load(getImgUrl(headImg), imgWidth, imgHeight)
        }
    }
}