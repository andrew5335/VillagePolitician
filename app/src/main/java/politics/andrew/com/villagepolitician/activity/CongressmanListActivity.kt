package politics.andrew.com.villagepolitician.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AbsListView
import android.widget.Adapter
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_congressman_list.*
import politics.andrew.com.villagepolitician.R
import politics.andrew.com.villagepolitician.adapter.CongressmanListAdapter
import politics.andrew.com.villagepolitician.handler.BackPressCloseHandler
import politics.andrew.com.villagepolitician.interfacevo.Congressman
import politics.andrew.com.villagepolitician.service.ApiService

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
    private var runnable: Runnable? = null
    private var apiService: ApiService? = null

    private var apiKey: String = ""
    private var congressmanList = ArrayList<Congressman>()
    private var congressmanListAdapter: CongressmanListAdapter? = null
    private var page: Int = 0    // 페이지 번호
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

        progressBar = findViewById(R.id.congressman_list_progressbar)

        congressmanListAdapter = CongressmanListAdapter(this, congressmanList)
        congressmanListView.adapter = congressmanListAdapter
        congressmanListView.setOnScrollListener(this)

        apiService = ApiService()

        val congressmanIntent = intent
        //val apiKey: String = congressmanIntent.getStringExtra("apiKey")
        apiKey = congressmanIntent.getStringExtra("apiKey")
        sortQuery = "name_kr"
        sort  = "asc"
        queryWord = ""

        handler = Handler()

        Thread(Runnable {
            Looper.prepare()
            getCongressmanList(apiKey, sortQuery, sort, queryWord)
            Looper.loop()
        }).start()

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

    override fun onScrollStateChanged(absListView: AbsListView, scrollState: Int) {
        if(scrollState  == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag && mLockListView == false) {
            progressBar!!.visibility = View.VISIBLE
            congressmanListView.visibility = View.INVISIBLE
            getCongressmanList(apiKey, sortQuery, sort, queryWord)
        }
    }

    override fun onScroll(absListView: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        lastItemVisibleFlag  = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount)
    }

    /**
     * @File : getCongressmanList
     * @Date : 2019-02-26 오후 3:36
     * @Author : Andrew Kim
     * @Description : 국회의원 전체 리스트 가져오기
     **/
    fun getCongressmanList(apiKey: String, sortQuery: String, sort: String, queryWord: String) {
        mLockListView = true
        var tmpCongressmanList = ArrayList<Congressman>()
        tmpCongressmanList = apiService!!.getContressmanList(page, per_page, sortQuery, sort, queryWord, apiKey)

        if(null != tmpCongressmanList) {
            for(item in tmpCongressmanList) {
                congressmanList.add(item)
            }
        }

        handler!!.post {
            page = page +   30
            congressmanListAdapter!!.notifyDataSetChanged()
            progressBar!!.visibility = View.INVISIBLE
            congressmanListView.visibility = View.VISIBLE
            mLockListView = false
        }

        /**
        handler!!.postDelayed({
        congressmanListAdapter!!.notifyDataSetChanged()
        }, 500)
         **/
        //return congressmanList
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
