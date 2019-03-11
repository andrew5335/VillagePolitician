package politics.andrew.com.villagepolitician.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Toast

import politics.andrew.com.villagepolitician.R

/**
 * @File : CongressmanDetailActivity
 * @Date : 2019-03-06 오후 2:20
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 국회의원 상세정보 Activity
**/
class CongressmanDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congressman_detail)

        var policy: StrictMode.ThreadPolicy? = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        var congressmanIntent = intent    // Activity 호출 시 넘겨받을 값이 담겨있는 intent 설정
        val deptCd = congressmanIntent.getIntExtra("deptCd", 0)
        val num = congressmanIntent.getIntExtra("num", 0)
        Toast.makeText(applicationContext, "DeptCd: " + deptCd + " / Num  : " + num, Toast.LENGTH_LONG).show()
    }
}
