package lech.gank.ui.activity

import android.net.http.SslError
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.*
import kotlinx.android.synthetic.main.activity_article_detail.*
import lech.gank.R
import lech.gank.utils.showProgress

class ArticleDetailActivity : AppCompatActivity() {

    var title:String =""
    var url:String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        initData()
        initView()
    }

    private fun initData() {
        title=intent.getStringExtra("desc")
        url=intent.getStringExtra("url")

    }

    private fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar!!.title=title
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        webView.settings.javaScriptEnabled =true
        webView.settings.useWideViewPort=true
        webView.setWebViewClient(object : WebViewClient(){

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view!!.loadUrl(url)
                return  true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                showProgress()
                super.onPageFinished(view, url)
            }

            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler!!.proceed()
            }
        })

        webView.setWebChromeClient(object  :WebChromeClient(){

        })

        webView.loadUrl(url)
    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }

    }
}
