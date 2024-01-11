package com.example.ultimateapp;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;



public class MainActivity extends Activity {
    final Activity activity = this;
    private boolean doubleBackToExitPressedOnce;

    boolean isActive = true;
    ProgressBar mProgress;
    WebAppInterface mWebAppInterface;


    int screenHeight;
    long sec = 5;
    WebView webView;
    WebView webView2;



    private static class WebAppInterface {
        public WebAppInterface(MainActivity mainActivity) {
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(1024);
        setContentView(R.layout.activity_main);
        this.mWebAppInterface = new WebAppInterface(this);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.mProgress = progressBar;
        progressBar.setVisibility(View.VISIBLE);
        WebView webView3 = (WebView) findViewById(R.id.webView);
        this.webView = webView3;
        webView3.clearHistory();
        this.webView.clearCache(true);
        this.webView.setClickable(true);
        this.webView.setFocusable(true);
        this.webView.setFocusableInTouchMode(true);
        WebSettings webSettings = this.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        this.webView.setWebChromeClient(new WebChromeClient() {

            public void onProgressChanged(WebView view, int newProgress) {
                MainActivity.this.mProgress.setProgress(newProgress);
                if (newProgress == 100) {
                    MainActivity.this.webView.loadUrl("javascript:_fully_loaded()");
                    MainActivity.this.webView.setVisibility(View.VISIBLE);
                    MainActivity.this.mProgress.setVisibility(View.GONE);
                }
            }
        });
        this.webView.setWebViewClient(new WebViewClient());
        this.webView.setWebChromeClient(new WebChromeClient() {

                @Override
                public void onPermissionRequest(final PermissionRequest request) {
                    request.grant(request.getResources());
                }



            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                new AlertDialog.Builder(view.getContext()).setTitle((CharSequence) "PAS N'(ou)T BLANC 2").setMessage((CharSequence) message).setPositiveButton((CharSequence) "OK", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create().show();
                result.confirm();
                return true;
            }

            public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
                new AlertDialog.Builder(view.getContext()).setTitle((CharSequence) "PAS N'(ou)T BLANC 2 " +
                        "2").setMessage((CharSequence) message).setNegativeButton((CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        result.cancel();
                    }
                }).setPositiveButton((CharSequence) "Yes", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                }).create().show();
                return true;
            }
        });
        this.webView.loadUrl("file:///android_asset/muestra 2.html");

    }

    public void onBackPressed() {
    }

}