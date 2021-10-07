package com.example.fenleiyouwo.tabs;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.fenleiyouwo.R;
import androidx.appcompat.app.AppCompatActivity;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        //String url="https://mp.weixin.qq.com/s/w-R3Ee-JThB86g9qXRMfDw";

        String articleUrl = getIntent().getStringExtra("url");
        WebView webView=(WebView) findViewById((R.id.web));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(articleUrl);
    }
}
