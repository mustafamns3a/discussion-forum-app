package com.example.mustafajarvis.myapplication;

/**
 * Created by Mustafa Jarvis on 13-04-2016.
 */

        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;
        import android.view.Window;
        import android.webkit.CookieSyncManager;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;

@SuppressLint("SetJavaScriptEnabled")
public class ForumActivity extends Activity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);

        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);


        setContentView(R.layout.forum);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);


        webView.getSettings().setSupportZoom(true);


        webView.getSettings().setBuiltInZoomControls(true);


        webView.loadUrl("http://discussion.vanillacommunity.com/");

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if(url.contains("discussion.vanillacommunity.com") == true) {
                    view.loadUrl(url);

                    super.onPageStarted(view, url, null);
                    findViewById(R.id.progressbar).setVisibility(View.VISIBLE);
                   
                } else {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                }
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {

                findViewById(R.id.progressbar).setVisibility(View.GONE);

                CookieSyncManager.getInstance().sync();
            }
        });
    }
    @Override
    public void onBackPressed() {

        if (webView.copyBackForwardList().getCurrentIndex() > 0) {
            webView.goBack();
        }
        else {

            super.onBackPressed();
        }
    }
}
