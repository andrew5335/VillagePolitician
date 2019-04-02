package politics.andrew.com.villagepolitician.client

import android.app.Activity
import android.webkit.WebChromeClient
import android.webkit.WebView

/**
 * @File : WebChromeClt
 * @Date : 2019-04-02 오전 9:50
 * @Author : Andrew Kim
 * @Version : 1.0.0
 * @Description : 웹 크롬 클라이언트
**/
class WebChromeClt internal constructor(private val activity: Activity) : WebChromeClient() {

    override fun onProgressChanged(view: WebView?, progress: Int) {
        activity.setProgress(progress * 1000)
    }
}