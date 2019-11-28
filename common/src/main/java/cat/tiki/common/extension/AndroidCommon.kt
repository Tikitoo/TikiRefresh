package cat.tiki.common.extension

import android.widget.Toast

/**
 * Created by Yifa Liang on 2019-11-27.
 */
inline fun toast(str: CharSequence){
    Toast.makeText(TikiCommon.context, str, Toast.LENGTH_SHORT).show()
}