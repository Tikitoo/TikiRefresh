package cat.tiki.tikirefresh.samples.squre

import android.os.Parcelable
import androidx.annotation.Keep
import cat.tiki.tikiadapter.TikiBaseModel
import kotlinx.android.parcel.Parcelize

/**
 * Created by Yifa Liang on 2019-11-28.
 */
@Parcelize
@Keep
data class TikiSquare(
    override var layoutId: Int = 0,
    override var column: Int = 1,
    val title: String): TikiBaseModel(column, layoutId), Parcelable {

}