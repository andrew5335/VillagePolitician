package politics.andrew.com.villagepolitician.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import politics.andrew.com.villagepolitician.R

class NewsViewActivity : AppCompatActivity() {

    var webView: WebView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_view)

        val newsIntent = intent
        val newsUrl = newsIntent.getStringExtra("newsUrl")

        webView = findViewById(R.id.webview)
        webView!!.webViewClient = object: WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView!!.loadUrl(newsUrl)
    }
}
