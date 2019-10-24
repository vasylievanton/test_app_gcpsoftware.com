package com.test.test.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.test.test.R;

public class WebFragment extends Fragment {
    WebView myWebView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web, container, false);
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() == null) return;
        myWebView = view.findViewById(R.id.webview);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                Snackbar.make(view,"Downloading",Snackbar.LENGTH_SHORT);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Snackbar.make(view,"Download done",Snackbar.LENGTH_SHORT);
                super.onPageFinished(view, url);
            }
        });

        myWebView.loadUrl(getArguments().getString("p"));
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);


    }

    public static WebFragment newInstance(String url) {
        WebFragment webFragment = new WebFragment();
        Bundle arguments = new Bundle();
        arguments.putString("p", url);
        webFragment.setArguments(arguments);
        return webFragment;
    }
}