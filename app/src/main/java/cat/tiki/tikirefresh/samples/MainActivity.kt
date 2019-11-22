package cat.tiki.tikirefresh.samples

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cat.tiki.tikirefresh.samples.boxcover.KotlinBoxCoverActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sampleRefreshTv = activity_main_refresh_sample_tv
        sampleRefreshTv.setOnClickListener {
            val cls = KotlinBoxCoverActivity::class.java as Class<Any>?
            val intent = Intent(this, cls)
            startActivity(intent)
        }
    }

}
