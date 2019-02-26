package politics.andrew.com.villagepolitician.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import politics.andrew.com.villagepolitician.R
import politics.andrew.com.villagepolitician.service.ApiService

/**
 * @File : MainActivity.kt
 * @Date : 2018. 10. 16. AM 10:57
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 메인화면 구성 Activity
**/
class MainActivity(private var apiService: ApiService? = null) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //apiService = ApiService()
        //val result: String = getResult("Hello Andrew !!!")
        //printStr(result)
        //test1.setOnClickListener { onTest1Clicked() }
        //test2.setOnClickListener { onTest2Clicked() }
        //test3.setOnClickListener { onTest3Clicked() }
    }

    //fun printStr(str: String) {
    //    helloWorld.text = str
    //}

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
        //Toast.makeText(applicationContext, "test1 clicked...", Toast.LENGTH_LONG).show()
        //val text: String = apiService!!.test()
        //Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show();
        val congressmanIntent = Intent(applicationContext, CongressmanListActivity::class.java)
        congressmanIntent.putExtra("apiKey", getString(R.string.apiKey))
        startActivity(congressmanIntent)
        finish()
    }

    fun onTest2Clicked(v: View) {
        Toast.makeText(applicationContext, "test2 clicked", Toast.LENGTH_LONG).show()
    }

    fun onTest3Clicked(v: View) {
        Toast.makeText(applicationContext, "test3 clicked", Toast.LENGTH_LONG).show()
    }
}
