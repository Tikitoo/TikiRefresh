package cat.tiki.tikirefresh.samples.squre

import android.os.Bundle
import android.util.Log
import android.view.View
import cat.tiki.tikiadapter.TikiBaseModel
import cat.tiki.tikiadapter.TikiItemClickListener
import cat.tiki.tikiadapter.TikiRvAdapter
import cat.tiki.tikirefresh.TikiBaseRvActivity
import cat.tiki.tikirefresh.samples.R

/**
 * Created by Yifa Liang on 2019-11-28.
 */
class SquareActivity: TikiBaseRvActivity(), TikiItemClickListener {
    override fun onItemClick(view: View, position: Int) {
        Log.d("SquareActivity：",  " pos: " + position )
    }

    override fun onRegisterItem(rvAdapter: TikiRvAdapter<TikiBaseModel>) {
        rvAdapter?.registerItem(ITEM_SQUARE, SquareItemVH())
        rvAdapter?.setOnItemClick(this@SquareActivity)
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