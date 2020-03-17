package cat.tiki.tikirefresh.samples.jsoup

import android.os.Parcelable
import androidx.annotation.Keep
import cat.tiki.tikiadapter.TikiBaseModel
import kotlinx.android.parcel.Parcelize

/**
 * 日期   收盘价     涨跌幅     资金净流入   5日主力净额  大单(主力)(净额, 净占比)   中单(净额, 净占比)   小单(净额, 净占比)
 * Created by Yifa Liang on 2020-01-18.
 */
@Parcelize
@Keep
data class ArkSearchModel (
    override var layoutId: Int = 0,
    override var column: Int = 1,
    var title: String? = null,
    var url: String? = null,
    var banner: String? = null,
    var content: String? = null)
    : TikiBaseModel(column, layoutId), Parcelable {



}