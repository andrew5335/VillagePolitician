package politics.andrew.com.villagepolitician.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.ProgressBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_news_list.*
import politics.andrew.com.villagepolitician.R
import politics.andrew.com.villagepolitician.adapter.NaverNewsListAdapter
import politics.andrew.com.villagepolitician.interfacevo.NaverNewsSearch
import politics.andrew.com.villagepolitician.service.ApiService

/**
 * @File : NewsListActivity.kt
 * @Date : 2019-04-02 오전 9:43
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 국회의원 연관 뉴스 목록
**/
class NewsListActivity : BaseActivity(), AbsListView.OnScrollListener {

    private var handler: Handler? = null

    private var apiService: ApiService? = null

    private var start: Int = 1    // 검색 시작 위치, page와 같음
    private var display: Int = 30    // 한 페이지 내 노출할 개수
    private var query: String = ""    // 검색어
    private var sort:String = ""    // 정렬 방식 - sim : 유사도순 / date : 일자순

    private var naverNewsInfo = NaverNewsSearch()
    private var naverNewsList = ArrayList<NaverNewsSearch.Items>()
    private var naverNewsListAdapter: NaverNewsListAdapter? = null

    private var progressBar: ProgressBar? = null
    private var lastItemVisibleFlag: Boolean = false
    private var mLockListView: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        var policy: StrictMode.ThreadPolicy? = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        setSupportActionBar(naverNewsToolBar)
        progressBar = naverNews_list_progressbar    // 진행상태 표시용 progress bar 생성

        val newsIntent = intent
        val name = newsIntent.getStringExtra("query")
        query = name + " 정치인"
        sort = "date"

        apiService = ApiService()

        naverNewsListAdapter = NaverNewsListAdapter(this, naverNewsList)
        naverNewsListView.adapter = naverNewsListAdapter
        naverNewsListView.setOnScrollListener(this)

        handler = Handler()

        Thread(Runnable {
            Looper.prepare()
            getNewsList()
            Looper.loop()
        }).start()

        naverNewsListView.setOnItemClickListener(itemClickListener())
    }

    /**
     * @File : itemClickListener
     * @Date : 2019-04-02 오전 9:45
     * @Author : Andrew Kim
     * @Description : 뉴스 목록 클릭 시 처리
    **/
    fun itemClickListener(): AdapterView.OnItemClickListener = object: AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            var item: NaverNewsSearch.Items = parent!!.getItemAtPosition(position) as NaverNewsSearch.Items
            val link = item.link

            if(null != link && !"".equals(link)) {
                val newsIntent = Intent(applicationContext, NewsViewActivity::class.java)
                newsIntent.putExtra("newsUrl", link)
                startActivity(newsIntent)
            }
        }
    }

    /**
     * @File : onScrollStateChanged
     * @Date : 2019-04-02 오전 9:45
     * @Author : Andrew Kim
     * @Description : 스크롤 변경 시 처리
    **/
    override fun onScrollStateChanged(absListView: AbsListView, scrollState: Int) {
        if(scrollState  == AbsListView.OnScrollListener.SCROLL_STATE_IDLE && lastItemVisibleFlag && mLockListView == false) {
            naverNews_list_progressbar.visibility = View.VISIBLE
            naverNewsListView.visibility = View.INVISIBLE
            getNewsList()
        }
    }

    /**
     * @File : onScroll
     * @Date : 2019-04-02 오전 9:46
     * @Author : Andrew Kim
     * @Description : 스크롤 중 처리
    **/
    override fun onScroll(absListView: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        lastItemVisibleFlag  = (totalItemCount > 0) && (firstVisibleItem + visibleItemCount >= totalItemCount)
    }

    /**
     * @File : getNewsList
     * @Date : 2019-04-02 오전 9:46
     * @Author : Andrew Kim
     * @Description : 뉴스 목록 가져오기
    **/
    fun getNewsList() {
        mLockListView = true
        var tmpNewsList = ArrayList<NaverNewsSearch.Items>()


        try {
            naverNewsInfo = apiService!!.getNaverNewsSearchList(query, display, start, sort)

            if(null != naverNewsInfo && 0 < naverNewsInfo.itemList.size) {
                tmpNewsList = naverNewsInfo.itemList
            }
        } catch(e: Exception) {
            Log.e("Error", "Naver News API Call Error : " + e.toString())
        }

        if(null != tmpNewsList && 0 < tmpNewsList.size) {
            for(item in tmpNewsList) {
                naverNewsList.add(item)
            }
        }

        handler!!.post {
            start = start + 1
            naverNewsListAdapter!!.notifyDataSetChanged()
            progressBar!!.visibility = View.INVISIBLE
            naverNewsListView.visibility = View.VISIBLE
            mLockListView = false
        }
    }
}
