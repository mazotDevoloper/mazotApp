package com.mazotapp.mazotapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.mazotapp.mazotapp.R;


public class MapActivity extends AppCompatActivity {
    private WebView view;
    String webLocation, stationName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        view  = findViewById(R.id.maps_webView);

        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new WebViewClient());

        Bundle stInfo = getIntent().getExtras();

        webLocation = stInfo.getString("stLocation");
        stationName = stInfo.getString("stationName");

        TextView tv_top = (TextView) findViewById(R.id.webMap_stationName_TV) ;

        tv_top.setText(stationName);

        view.loadUrl("https://www.google.com/maps/search/" + webLocation);

        ImageView reflesh = (ImageView) findViewById(R.id.webViewRefleshImv);

        reflesh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.loadUrl("https://www.google.com/maps/search/" + webLocation);
            }
        });

    }
}
