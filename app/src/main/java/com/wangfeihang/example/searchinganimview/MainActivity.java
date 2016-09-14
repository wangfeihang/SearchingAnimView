package com.wangfeihang.example.searchinganimview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wangfeihang.searchinganimview.SearchingAnimView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SearchingAnimView searchingAnimView=(SearchingAnimView) findViewById(R.id.searchinganimview);
        findViewById(R.id.btn_start_searching_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchingAnimView.startAnimations();
            }
        });
        findViewById(R.id.btn_stop_searching_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchingAnimView.stopAnimations();
            }
        });
    }
}
