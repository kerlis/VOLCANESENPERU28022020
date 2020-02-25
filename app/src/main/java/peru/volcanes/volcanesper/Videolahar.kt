package peru.volcanes.volcanesper

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class Videolahar : AppCompatActivity() {

    var tipodenotificacion_dat: String =""
    var tipodeevento_dat: String =""
    var fecha_dat: String =""
    var hora_dat: String =""
    var observacicones_dat: String =""
    var simulacro_dat: String =""
    var horautc_dat: String =""
    var volcan_dat: String =""
    var  sismogramaurls: String =""
     private var spinner: ProgressBar? = null
    internal var ShowOrHideWebViewInitialUse = "show"


    private lateinit var webView: WebView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videolahar)

       // sismogramaurls = "http://intranet.igp.gob.pe/fotos-tiempo-real/helicorde_ubinas.php"

        val i = this.intent
        tipodenotificacion_dat = i.extras!!.getString("TIPODENOTIFICACION")
        tipodeevento_dat = i.extras!!.getString("TIPODEEVENTO")
        fecha_dat = i.extras!!.getString("FECHA")
        hora_dat = i.extras!!.getString("HORA")
        horautc_dat = i.extras!!.getString("HORAUTC")
        observacicones_dat = i.extras!!.getString("OBSERVACIONES")
        simulacro_dat = i.extras!!.getString("SIMULACRO")
        volcan_dat = i.extras!!.getString("VOLCAN")

        /*
        webView = findViewById<View>(R.id.activity_main_webview) as WebView
        spinner = findViewById<View>(R.id.progressBar1) as ProgressBar
        webView.setWebViewClient(CustomWebViewClient())
        webView.getSettings().setJavaScriptEnabled(true)
        webView.getSettings().setDomStorageEnabled(true)
        webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER)
        webView.getSettings().setBuiltInZoomControls(true)

          webView.loadUrl(sismogramaurls)


*/



        webView = findViewById(R.id.webview)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url)
                return true
            }
        }
        webView.loadUrl("http://intranet.igp.gob.pe/fotos-tiempo-real/helicorde_ubinas.php")




    }

/*
    private inner class CustomWebViewClient : WebViewClient() {

        override fun onPageStarted(webview: WebView, url: String, favicon: Bitmap) {
            if (ShowOrHideWebViewInitialUse == "show") {
                webview.visibility = View.GONE
            }
        }

        override fun onPageFinished(view: WebView, url: String) {
            ShowOrHideWebViewInitialUse = "hide"
            spinner!!.visibility = View.GONE
            spinner!!.visibility =  View.GONE
            view.visibility = View.VISIBLE
            super.onPageFinished(view, url)
        }
    }
    */


}
