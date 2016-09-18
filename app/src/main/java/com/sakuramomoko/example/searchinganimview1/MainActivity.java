package com.sakuramomoko.example.searchinganimview1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sakuramomoko.searchinganimview.SearchingAnimView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textViewState=(TextView)findViewById(R.id.tv_searching_state);
        final SearchingAnimView searchingAnimView=(SearchingAnimView) findViewById(R.id.searchinganimview);
        findViewById(R.id.btn_start_searching_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewState.setText("正在寻找");
                searchingAnimView.startAnimations();
            }
        });
        findViewById(R.id.btn_stop_searching_anim).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewState.setText("暂停寻找");
                searchingAnimView.stopAnimations();
            }
        });
    }
}
