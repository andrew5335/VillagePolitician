package politics.andrew.com.villagepolitician.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.google.android.gms.common.util.Strings

import politics.andrew.com.villagepolitician.R
import politics.andrew.com.villagepolitician.interfacevo.CongressmanDetailXml
import politics.andrew.com.villagepolitician.service.XmlApiService
import java.util.*

/**
 * @File : CongressmanDetailActivity
 * @Date : 2019-03-06 오후 2:20
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 국회의원 상세정보 Activity
**/
class CongressmanDetailActivity : BaseActivity() {

    private var handler: Handler? = null
    private var runnable: Runnable? = null

    private var apiService: XmlApiService? = null

    private var congressmanDetailXml: CongressmanDetailXml = CongressmanDetailXml()

    private var progressBar: ProgressBar? = null

    private var apiKey: String = ""    // apikey 정보
    private var serviceUrl: String = ""    // 서비스 주소
    private var deptCd: Int = 0
    private var num: Int = 0
    private var page: Int = 1    // 페이지 번호
    private var per_page: Int = 10    // 한 페이지당 표시할 목록 수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congressman_detail)

        var policy: StrictMode.ThreadPolicy? = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        progressBar = findViewById(R.id.congressman_detail_progressbar)    // 진행상태 표시용 progress bar 생성

        apiService = XmlApiService()

        apiKey = getString(R.string.publicDataAuthKey)    // intent에서 필요한 데이터 확인
        serviceUrl = getString(R.string.congressmanDetailServiceUrl)    // 국회의원 리스트 서비스 주소

        var congressmanIntent = intent    // Activity 호출 시 넘겨받을 값이 담겨있는 intent 설정
        deptCd = congressmanIntent.getIntExtra("deptCd", 0)
        num = congressmanIntent.getIntExtra("num", 0)
        Toast.makeText(applicationContext, "DeptCd: " + deptCd + " / Num  : " + num, Toast.LENGTH_LONG).show()

        handler = Handler()

        Thread(Runnable {
            Looper.prepare()
            getCongressmanInfo(apiKey, serviceUrl, per_page, page, deptCd, num)    // 신규 thread에서 실행할 내용
            Looper.loop()
        }).start()
    }

    /**
     * @File : getCongressmanInfo
     * @Date : 2019-03-13 오후 1:56
     * @Author : Andrew Kim
     * @Description : 국회의원 1명에 대한 상세 정보 가져오기
    **/
    fun getCongressmanInfo(serviceKey: String, serviceUrl: String, numOfRows: Int, pageNo: Int, deptCd: Int, num: Int) {
        congressmanDetailXml = apiService!!.getCongressman(serviceKey, numOfRows, pageNo, serviceUrl, deptCd, num)

        handler!!.post {
            progressBar!!.visibility = View.INVISIBLE
            if(null != congressmanDetailXml && !Strings.isEmptyOrWhitespace(congressmanDetailXml.empNm)) {
                Toast.makeText(applicationContext, "Congressman HJ Name : " + congressmanDetailXml.hjNm, Toast.LENGTH_LONG).show()
            }
        }
    }
}
