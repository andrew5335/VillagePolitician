package politics.andrew.com.villagepolitician.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_news_view.*
import politics.andrew.com.villagepolitician.R
import politics.andrew.com.villagepolitician.client.WebChromeClt

class NewsViewActivity : AppCompatActivity() {

    var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_view)

        var policy: StrictMode.ThreadPolicy? = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        setSupportActionBar(newsViewToolBar)

        val newsIntent = intent
        val newsUrl = newsIntent.getStringExtra("newsUrl")

        webView = findViewById(R.id.webview)
        val webSettings = webView!!.settings

        webSettings.javaScriptEnabled = true
        webSettings.builtInZoomControls = true
        webView!!.webViewClient = object: WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        //webView!!.webViewClient = WebViewClt(this)
        webView!!.webChromeClient = WebChromeClt(this)
        webView!!.loadUrl(newsUrl)
    }
}
