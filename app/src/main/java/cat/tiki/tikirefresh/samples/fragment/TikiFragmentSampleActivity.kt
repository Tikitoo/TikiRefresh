package cat.tiki.tikirefresh.samples.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import cat.tiki.tikirefresh.TikiBaseActivity
import cat.tiki.tikirefresh.samples.R
import kotlinx.android.synthetic.main.activity_fragment_sample.*

/**
 * Created by Yifa Liang on 2020-02-21.
 */
class TikiFragmentSampleActivity: TikiBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_sample)

        val fragmentSampleLayout = activity_fragment_sample_layout

        val isUserRefresh = intent.getBooleanExtra("isUseRefresh", false)
        val tempFragment: Fragment =
            if (isUserRefresh) TikiFgRefreshSampleFragment()
            else TikiFgSampleFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_fragment_sample_layout, tempFragment)
            .commitAllowingStateLoss()

    }
}