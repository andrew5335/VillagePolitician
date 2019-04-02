package politics.andrew.com.villagepolitician.activity

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import politics.andrew.com.villagepolitician.R
import politics.andrew.com.villagepolitician.handler.BackPressCloseHandler
import politics.andrew.com.villagepolitician.service.ApiService

/**
 * @File : MainActivity.kt
 * @Date : 2018. 10. 16. AM 10:57
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 메인화면 구성 Activity
**/
class MainActivity(private var apiService: ApiService? = null) : BaseActivity() {

    private var backPressCloseHandler: BackPressCloseHandler? = BackPressCloseHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var policy: StrictMode.ThreadPolicy? = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    /**
    fun getResult(str: String): String {
        val text: String = str
        val end: String = "You're Great !!!"
        return text + "-" + end
    }
    **/

    /**
     * @File : onCongressmanClicked
     * @Date : 2019-02-26 오후 3:34
     * @Author : Andrew Kim
     * @Description : 국회의원 목록 보기 버튼
    **/
    fun onCongressmanClicked(v: View) {
        val congressmanIntent = Intent(applicationContext, CongressmanListActivity::class.java)
        //congressmanIntent.putExtra("apiKey", getString(R.string.publicDataAuthKey))
        startActivity(congressmanIntent)
    }

    /**
     * @File : onBillListBtnClicked
     * @Date : 2019-04-02 오전 9:46
     * @Author : Andrew Kim
     * @Description : 의안정보 보기 버튼
    **/
    fun onBillListBtnClicked(v: View) {
        //Toast.makeText(applicationContext, "test2 clicked", Toast.LENGTH_LONG).show()
        val billIntent = Intent(applicationContext, BillListActivity::class.java)
        startActivity(billIntent)
    }

    /**
     * @File : onScheduleBtnClicked
     * @Date : 2019-04-02 오전 9:47
     * @Author : Andrew Kim
     * @Description : 의사일정 보기 버튼
    **/
    fun onSheduleListBtnClicked(v: View) {
        //Toast.makeText(applicationContext, "test3 clicked", Toast.LENGTH_LONG).show()
        val scheduleIntent = Intent(applicationContext, ScheduleListActivity::class.java)
        startActivity(scheduleIntent)
    }

    /**
     * @File : onBackPressed
     * @Date : 2019-02-28 오후 12:15
     * @Author : Andrew Kim
     * @Description : 서비스 종료 처리
     *                  뒤로가기 두번 클릭 시 앱 종료되도록 처리하기 위해 BackPresCloseHandler 적용
    **/
    override fun onBackPressed() {
        this.backPressCloseHandler!!.onBackPressed()
    }
}
