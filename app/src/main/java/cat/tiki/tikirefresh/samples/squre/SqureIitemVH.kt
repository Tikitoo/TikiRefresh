package cat.tiki.tikirefresh.samples.squre

import android.view.View
import cat.tiki.tikiadapter.TikiBaseVHImpl
import kotlinx.android.synthetic.main.item_square.view.*

class SquareItemVH : TikiBaseVHImpl<TikiSquare>() {

    override fun bindData(tikiSqure: TikiSquare, view: View) {
        val titleTv = view?.item_square_item_tv
        titleTv.text = tikiSqure?.title
    }

}
