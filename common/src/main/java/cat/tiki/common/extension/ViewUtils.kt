package cat.tiki.common.extension

import android.util.TypedValue

/**
 * Created by Yifa Liang on 2019-08-29.
 */
inline fun dip2px(dpValue: Float): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dpValue,
        TikiCommon?.context?.getResources()?.getDisplayMetrics()
    ).toInt()

}


