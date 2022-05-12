package com.tracz.termometer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView

class BlocoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bloco)
        val myWebView: WebView = findViewById(R.id.webview)

        val webSettings: WebSettings = myWebView.getSettings()
        webSettings.javaScriptEnabled = true

        myWebView.loadUrl("http://44.228.65.254/site/Luminosidade.php")
    }
}
