package politics.andrew.com.villagepolitician.activity

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import politics.andrew.com.villagepolitician.R
import politics.andrew.com.villagepolitician.service.ApiService

/**
 * @File : CongressmanListActivity.kt
 * @Date : 29/10/2018 10:39 PM
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 국회의원 정보 리스트를 보여준다
**/
class CongressmanListActivity(private var apiService: ApiService? =  null) : AppCompatActivity() {

    var test1: String? = null
    private var handler: Handler? = null
    private var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congressman_list)

        val congressmanIntent = intent
        val apiKey: String = congressmanIntent.getStringExtra("apiKey")

        handler = Handler()
        Thread({
            test1 = getCongressman(apiKey);

            handler?.post{
                toastMessage(test1!!)
            }
        }).start()
    }

    fun getCongressman(apiKey: String): String {
        apiService = ApiService()
        val test2: String
        test2 = apiService!!.test(apiKey)
        return test2
    }

    fun toastMessage(str: String) {
        Toast.makeText(applicationContext, str, Toast.LENGTH_LONG).show()
    }
}
