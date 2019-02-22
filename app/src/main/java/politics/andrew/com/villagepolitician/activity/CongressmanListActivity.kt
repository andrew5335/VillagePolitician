package politics.andrew.com.villagepolitician.activity

import android.os.Bundle
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
class CongressmanListActivity(private var apiService: ApiService? = null) : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congressman_list)

        val congressmanIntent = intent
        val test: String = congressmanIntent.getStringExtra("apiKey")
        val test1 = apiService!!.test()
        Toast.makeText(applicationContext, test1, Toast.LENGTH_LONG).show()
    }
}
