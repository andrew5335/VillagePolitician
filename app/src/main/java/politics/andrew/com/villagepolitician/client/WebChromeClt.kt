package politics.andrew.com.villagepolitician.client

import android.app.Activity
import android.webkit.WebChromeClient
import android.webkit.WebView

class WebChromeClt internal constructor(private val activity: Activity) : WebChromeClient() {

    override fun onProgressChanged(view: WebView?, progress: Int) {
        activity.setProgress(progress * 1000)
    }
}