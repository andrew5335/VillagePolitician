package politics.andrew.com.villagepolitician.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import politics.andrew.com.villagepolitician.R

class NewsListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        val newsIntent = intent

        val name = newsIntent.getStringExtra("query");

        Toast.makeText(applicationContext, "Congressman Name for Search : " + name, Toast.LENGTH_LONG).show()
    }
}
