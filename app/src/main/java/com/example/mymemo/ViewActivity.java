package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class ViewActivity extends AppCompatActivity {
    PreferenceManager pref;
    TextView view_title;
    TextView view_content;
    Button back_list_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        pref = new PreferenceManager();
        view_title = findViewById(R.id.view_title);
        view_content = findViewById(R.id.view_content);
        back_list_btn = findViewById(R.id.back_list_btn);

        Intent intent = getIntent();
        String key = intent.getStringExtra("key");
        String value = pref.getString(getApplication(), key);
        try{
            JSONObject jsonObject = new JSONObject(value);
            String title = (String) jsonObject.getString("title");
            String content = (String) jsonObject.getString("content");
            view_title.setText(title);
            view_content.setText(content);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // 뒤로 가기
        back_list_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}