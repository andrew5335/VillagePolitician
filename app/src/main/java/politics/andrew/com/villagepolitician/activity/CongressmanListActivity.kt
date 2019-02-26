package politics.andrew.com.villagepolitician.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.widget.Adapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_congressman_list.*
import politics.andrew.com.villagepolitician.R
import politics.andrew.com.villagepolitician.adapter.CongressmanListAdapter
import politics.andrew.com.villagepolitician.interfacevo.Congressman
import politics.andrew.com.villagepolitician.service.ApiService

/**
 * @File : CongressmanListActivity.kt
 * @Date : 29/10/2018 10:39 PM
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 국회의원 정보 리스트를 보여준다
**/
class CongressmanListActivity : AppCompatActivity() {

    //var test1: String? = null
    private var handler: Handler? = null
    private var runnable: Runnable? = null
    private var apiService: ApiService? = null

    private var congressmanList = ArrayList<Congressman>()
    private var congressmanListAdapter: CongressmanListAdapter? = null
    private var page: Int = 0    // 페이지 번호
    private var per_page: Int = 30    // 한 페이지당 표시할 목록 수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congressman_list)

        congressmanListAdapter = CongressmanListAdapter(this, congressmanList)
        congressmanListView.adapter = congressmanListAdapter

        apiService = ApiService()

        val congressmanIntent = intent
        val apiKey: String = congressmanIntent.getStringExtra("apiKey")

        handler = Handler()
        /**
        Thread {
            Looper.prepare()
            //test1 = getCongressman(apiKey);
            //congressmanList = getCongressmanList(apiKey)
            getCongressmanList(apiKey)
            Looper.loop()
        }.start()
        **/

        Thread(Runnable {
            Looper.prepare()
            getCongressmanList(apiKey)
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

    /**
    * @File : getCongressmanList
    * @Date : 2019-02-26 오후 3:36
    * @Author : Andrew Kim
    * @Description : 국회의원 전체 리스트 가져오기
    **/
    fun getCongressmanList(apiKey: String) {
        //var congressmanList = ArrayList<Congressman>()
        var tmpCongressmanList = ArrayList<Congressman>()
        tmpCongressmanList = apiService!!.getContressmanList(page, per_page, apiKey)

        if(null != tmpCongressmanList) {
            for(item in tmpCongressmanList) {
                congressmanList!!.add(item)
            }
        }

        handler!!.post {
            congressmanListAdapter!!.notifyDataSetChanged()
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
}
