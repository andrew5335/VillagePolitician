package politics.andrew.com.villagepolitician.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.StrictMode
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.activity_schedule_list.*
import politics.andrew.com.villagepolitician.R
import politics.andrew.com.villagepolitician.service.XmlApiService

/**
 * @File : ScheduleListActivity
 * @Date : 2019-04-02 오전 9:54
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 의사일정 목록 구성 Activity
**/
class ScheduleListActivity : AppCompatActivity() {

    private var apiKey: String = ""    // apikey 정보
    private var serviceUrl: String = ""    // API 호출 주소 세팅용

    private var handler: Handler? = null
    private var runnable: Runnable? = null

    private var apiService: XmlApiService? = null

    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_list)

        var policy: StrictMode.ThreadPolicy? = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        setSupportActionBar(searchListToolBar)
    }
}
