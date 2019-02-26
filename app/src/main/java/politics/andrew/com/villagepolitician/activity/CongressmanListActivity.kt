package politics.andrew.com.villagepolitician.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import politics.andrew.com.villagepolitician.R
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

    private var congressmanList: List<Congressman>? = null
    private var page: Int = 0    // 페이지 번호
    private var per_page: Int = 30    // 한 페이지당 표시할 목록 수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congressman_list)

        apiService = ApiService()

        val congressmanIntent = intent
        val apiKey: String = congressmanIntent.getStringExtra("apiKey")

        handler = Handler()
        Thread {
            //test1 = getCongressman(apiKey);
            congressmanList = getCongressmanList(apiKey)
            handler?.post {
                //toastMessage(item.name_kr)
                viewCongressmanList(congressmanList!!)
            }

        }.start()

    }

    fun getCongressman(apiKey: String): String {
        //apiService = ApiService()
        val test2: String
        test2 = apiService!!.test(apiKey)
        return test2
    }

    fun getCongressmanList(apiKey: String): List<Congressman> {
        var congressmanList: List<Congressman>? = null
        congressmanList = apiService!!.getContressmanList(page, per_page, apiKey)

        return congressmanList
    }

    fun viewCongressmanList(congressmanList: List<Congressman>) {
        if(null != congressmanList) {
            val listSize: Int
            listSize = congressmanList.size
            val listSizeStr: String = listSize.toString()
            Toast.makeText(applicationContext, listSizeStr, Toast.LENGTH_LONG).show()
        }
    }

    fun toastMessage(str: String) {
        Toast.makeText(applicationContext, str, Toast.LENGTH_LONG).show()
    }
}
