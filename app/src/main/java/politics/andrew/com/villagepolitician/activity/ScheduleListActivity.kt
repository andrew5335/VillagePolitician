package politics.andrew.com.villagepolitician.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import politics.andrew.com.villagepolitician.R

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_list)
    }
}
