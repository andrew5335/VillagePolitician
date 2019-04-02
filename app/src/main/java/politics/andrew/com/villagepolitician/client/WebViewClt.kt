package politics.andrew.com.villagepolitician.client

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.annotation.RequiresApi
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

/**
 * @File : WebViewClt
 * @Date : 2019-04-02 오전 9:51
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 웹 뷰 클라이언트
**/
class WebViewClt internal constructor(private val activity: Activity) : WebViewClient() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url: String = request?.url.toString()

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        activity.startActivity(intent)
        return true
    }

    override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {

        webView?.loadUrl(url)
        return true
    }

    override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
        Toast.makeText(activity, "Got Error! $error", Toast.LENGTH_SHORT).show()
    }

}