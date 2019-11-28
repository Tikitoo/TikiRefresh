package cat.tiki.tikirefresh.samples.squre

import android.os.Bundle
import cat.tiki.tikiadapter.TikiBaseModel
import cat.tiki.tikiadapter.TikiRvAdapter
import cat.tiki.tikirefresh.TikiBaseRvActivity
import cat.tiki.tikirefresh.samples.R

/**
 * Created by Yifa Liang on 2019-11-28.
 */
class SquareActivity: TikiBaseRvActivity() {
    override fun onRegisterItem(rvAdapter: TikiRvAdapter<TikiBaseModel>) {
        rvAdapter?.registerItem(ITEM_SQUARE, SquareItemVH())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val squreList = mutableListOf<TikiSquare>()
        for (i in 0..10) {
            squreList.add(TikiSquare(ITEM_SQUARE, 2, "title: " + i))
        }
        updateData(squreList)
    }

    companion object {
        val ITEM_SQUARE = R.layout.item_square
    }
}