package politics.andrew.com.villagepolitician.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.actionbar_title.*
import kotlinx.android.synthetic.main.activity_congressman_list.*
import politics.andrew.com.villagepolitician.R
import politics.andrew.com.villagepolitician.adapter.CongressmanListAdapter
import politics.andrew.com.villagepolitician.adapter.PartySpinnerAdapter
import politics.andrew.com.villagepolitician.handler.BackPressCloseHandler
import politics.andrew.com.villagepolitician.interfacevo.Congressman
import politics.andrew.com.villagepolitician.interfacevo.CongressmanListXml
import politics.andrew.com.villagepolitician.interfacevo.Party
import politics.andrew.com.villagepolitician.service.ApiService
import politics.andrew.com.villagepolitician.service.XmlApiService

/**
 * @File : CongressmanListActivity.kt
 * @Date : 29/10/2018 10:39 PM
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 국회의원 정보 리스트를 보여준다
**/
class CongressmanListActivity : BaseActivity(), AbsListView.OnScrollListener  {

    //var test1: String? = null
    private var handler: Handler? = null
    private var spinnerHandler: Handler? = null

    private var apiService: XmlApiService? = null

    private var apiKey: String = ""    // apikey 정보
    private var serviceUrl: String = ""    // 국회의원 목록 정보 호출 서비스 주소
    private var partyServiceUrl: String = ""    // 정당 정보 호출 서비스 주소

    private var congressmanList = ArrayList<CongressmanListXml>()
    private var congressmanListAdapter: CongressmanListAdapter? = null
    private var partyList = ArrayList<Party>()
    private var spinnerArrayAdapter: ArrayAdapter<Party>? = null
    private var page: Int = 1    // 페이지 번호
    private var per_page: Int = 30    // 한 페이지당 표시할 목록 수
    private var sortQuery: String = ""    // 검색 조건절에 들어갈 DB의 컬럼명 - name_kr, party 등의 컬럼명을 넣어주면 됨
    private var sort: String = ""    // 정렬 조건 - asc, desc 둘 중 하나로 세팅. 기본값은 asc
    private var queryWord: String = ""    // 검색 키워드 - 검색 조건절의 값에 대응하는 검색어로서 정치인 이름이나 소속 정당명 등이 들어갈 수 있음

    private var progressBar: ProgressBar? = null
    private var lastItemVisibleFlag: Boolean = false
    private var mLockListView: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congressman_list)

        var policy: StrictMode.ThreadPolicy? = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        setSupportActionBar(congressmanToolBar)
        progressBar = findViewById(R.id.congressman_list_progressbar)    // 진행상태 표시용 progress bar 생성

        // API 호출 정보 설정
        apiService = XmlApiService()
        apiKey = getString(R.string.publicDataAuthKey)    // intent에서 필요한 데이터 확인
        serviceUrl = getString(R.string.congressmanListServiceUrl)    // 국회의원 리스트 서비스 주소
        sortQuery = "name_kr"
        sort  = "asc"
        queryWord = ""
        partyServiceUrl = getString(R.string.partyServiceUrl)    // 정당 정보 호출 서비스 주소

        // 국회의원 리스트 표시를 위한 Adapter 설정
        congressmanListAdapter = CongressmanListAdapter(this, congressmanList)
        congressmanListView.adapter = congressmanListAdapter
        congressmanListView.setOnScrollListener(this)

        // spinner 데이터 표시를 위한 Adapter 설정 및 데이터 생성
        spinnerArrayAdapter = PartySpinnerAdapter(this, R.layout.congressman_spinner_item, partyList)
        spinnerArrayAdapter!!.setDropDownViewResource(R.layout.congressman_spinner_item)
        congressman_list_spinner.adapter = spinnerArrayAdapter

        spinnerHandler = Handler()

        Thread(Runnable {
            Looper.prepare()
            getPartyInfo(apiKey)
            Looper.loop()
        }).start()

        handler = Handler()

        // API 서비스 호출을 위해 별도의ㅡ thread 생성
        // API 서비스와 같은 외부 데이터 접근을 main thread 에서 진행 시 오류 발생 되므로 별도의 thread 생성하여 외부 데이터 접근 처리
        Thread(Runnable {
            Looper.prepare()
            //getCongressmanList(apiKey, sortQuery, sort, queryWord)
            getCongressmanList(apiKey, per_page, page)    // 국회의원 목록 정보 호출
            Looper.loop()
        }).start()

        /**
        congressmanListView.onItemClickListener = object: AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                var item: Congressman = Congressman()
                item = parent.getItemAtPosition(position) as Congressman
                var no: String = item.no

                Toast.makeText(applicationContext, "Clicked Item No : " + no, Toast.LENGTH_LONG).show()
            }
        }
        **/
        congressmanListView.setOnItemClickListener(itemClickListener())    // 리스트 내 아이템 클릭 시 반응 처리를 위한 item click listener 설정

        // spinner item select 시 처리
        congressman_list_spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                congressman_list_spinner.prompt = "선택"
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var item: Party = Party()
                item = parent!!.getItemAtPosition(position) as Party
                val polyCd: Int = item.polyCd
                val polyNm: String = item.polyNm

                Toast.makeText(applicationContext, "Party Name & CD : " + polyNm + polyCd, Toast.LENGTH_LONG).show()
            }
        }
    }

    /**
     * @File : getCongressman
     * @Date : 2019-02-26 오후 3:35
     * @Author : Andrew Kim
     * @Description : 국회의원 1명의 정보 가져오기
    **/
    fun getCongressman(apiKey: String): String {
        //apiService = ApiService()
        val congressman: String
        congressman = apiService!!.test(apiKey)
        return congressman
    }

    /**
     * @File : itemClickListener
     * @Date : 2019-03-06 오후 2:20
     * @Author : Andrew Kim
     * @Description : 국회의원 리스트 아이템 클릭 시 상세 페이지 이동 처리를 위한 click listener
    **/
    fun itemClickListener(): AdapterView.OnItemClickListener = object: AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            var item: CongressmanListXml = CongressmanListXml()
            item = parent!!.getItemAtPosition(position) as CongressmanListXml
            var deptCd: Int = item.deptCd
            var num: Int = item.num
            var jpgLink: String = item.jpgLink

            val congressmanIntent = Intent(applicationContext, CongressmanDetailActivity::class.java)
            congressmanIntent.putExtra("deptCd", deptCd)
            congressmanIntent.putExtra("num", num)
            congressmanIntent.putExtra("jpgLink", jpgLink)
            startActivity(congressmanIntent)
        }
    }

    fun itemSelectedListener(): AdapterView.OnItemSelectedListener = object: AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        }
    }

    /**
     * @File : onScrollStateChanged
     * @Date : 2019-03-07 오후 4:41
     * @Author : Andrew Kim
     * @Description : 스크롤 상태 변경 시 처리
    **/
    override fun onScrollStateChanged(absListView: AbsListView, scrollState: Int) {
        if(scrollState  == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag && mLockListView == false) {
            congressman_list_progressbar.visibility = View.VISIBLE
            congressmanListView.visibility = View.INVISIBLE
            //etCongressmanList(apiKey, sortQuery, sort, queryWord)
            getCongressmanList(apiKey, per_page, page)
        }
    }

    /**
     * @File : onScroll
     * @Date : 2019-03-07 오후 4:42
     * @Author : Andrew Kim
     * @Description : 스크롤 시 처리
    **/
    override fun onScroll(absListView: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        lastItemVisibleFlag  = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount)
    }

    /**
     *
     *
     * @File : getCongressmanList
     * @Date : 2019-02-26 오후 3:36
     * @Author : Andrew Kim
     * @Description : 국회의원 전체 리스트 가져오기
     **/
    fun getCongressmanList(serviceKey: String, numOfRows: Int, pageNo: Int) {
        mLockListView = true
        var tmpCongressmanList = ArrayList<CongressmanListXml>()
        //tmpCongressmanList = apiService!!.getContressmanList(page, per_page, sortQuery, sort, queryWord, apiKey)
        try {
            tmpCongressmanList = apiService!!.getCongressmanList(serviceKey, numOfRows, pageNo, serviceUrl)
        } catch(e: Exception) {
            Log.e("Error", "Congressman List API Call Error : " + e.toString())
        }

        if(null != tmpCongressmanList) {
            for(item in tmpCongressmanList) {
                congressmanList.add(item)
            }
        }

        handler!!.post {
            page = page + 1
            congressmanListAdapter!!.notifyDataSetChanged()
            progressBar!!.visibility = View.INVISIBLE
            congressmanListView.visibility = View.VISIBLE
            mLockListView = false
        }

    }

    /**
     * @File : getPartyInfo
     * @Date : 2019-03-19 오후 2:54
     * @Author : Andrew Kim
     * @Description : 정당 정보 조회
    **/
    fun getPartyInfo(serviceKey: String){
        var tmpPartyList = ArrayList<Party>()

        try {
            tmpPartyList = apiService!!.getParty(serviceKey, partyServiceUrl)
        } catch(e: Exception) {
            Log.e("Error", "Party Info API Call Error : " + e.toString())
        }

        if(null != tmpPartyList) {
            for(item in tmpPartyList) {
                partyList.add(item)
            }
        }

        spinnerHandler!!.post{
            spinnerArrayAdapter!!.notifyDataSetChanged()
        }

    }

    /**
     * @File : viewCongressmanList
     * @Date : 2019-02-26 오후 3:36
     * @Author : Andrew Kim
     * @Description : 국회의원 리스트 보기
    **/
    fun viewCongressmanList(congressmanList: ArrayList<Congressman>) {
        if(null != congressmanList) {
            val listSize: Int
            listSize = congressmanList.size
            val listSizeStr: String = listSize.toString()
            Toast.makeText(applicationContext, listSizeStr, Toast.LENGTH_LONG).show()
        }
    }

    /**
     * @File : toastMessage
     * @Date : 2019-02-26 오후 3:37
     * @Author : Andrew Kim
     * @Description : 메시지 띄우기
    **/
    fun toastMessage(str: String) {
        Toast.makeText(applicationContext, str, Toast.LENGTH_LONG).show()
    }

    /**
     * @File : onBackPressed
     * @Date : 2019-02-28 오전 11:08
     * @Author : Andrew Kim
     * @Description : 뒤로 가기
    **/
    override fun onBackPressed() {
        super.onBackPressed()
    }
}
