package cat.tiki.tikirefresh.samples.fg

import android.os.Bundle
import android.view.View
import cat.tiki.tikiadapter.TikiBaseModel
import cat.tiki.tikiadapter.TikiItemClickListener
import cat.tiki.tikiadapter.TikiRvAdapter
import cat.tiki.tikirefresh.TikiBaseRvFragment
import cat.tiki.tikirefresh.samples.R
import cat.tiki.tikirefresh.samples.squre.SquareActivity
import cat.tiki.tikirefresh.samples.squre.SquareItemVH
import cat.tiki.tikirefresh.samples.squre.TikiSquare

/**
 * Created by Yifa Liang on 2020-02-21.
 */
class TikiFgSampleFragment: TikiBaseRvFragment(), TikiItemClickListener {
    override fun onRegisterItem(rvAdapter: TikiRvAdapter<TikiBaseModel>) {
        rvAdapter?.registerItem(SquareActivity.ITEM_SQUARE, SquareItemVH())
        rvAdapter?.setOnItemClick(this@TikiFgSampleFragment)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading()

        if (IS_USE_EMPTY) {
            dismissLoading()
            setEmptyText("数据为空，请重新加载")

        } else {
            recyclerView?.postDelayed({
                dismissLoading()
                val squreList = mutableListOf<TikiSquare>()
                for (i in 0..3) {
                    squreList.add(TikiSquare(ITEM_SQUARE, 1, "title: " + i))
                }
                updateData(squreList)
            }, 500L)
        }



    }

    override fun onItemClick(view: View, position: Int) {

    }

    companion object {
        val ITEM_SQUARE = R.layout.item_square
        const val IS_USE_EMPTY = false
    }
}