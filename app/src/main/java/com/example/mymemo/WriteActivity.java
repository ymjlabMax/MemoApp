package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WriteActivity extends AppCompatActivity {

    PreferenceManager pref;
    Button back_btn;
    Button save_btn;
    EditText title;
    EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        pref = new PreferenceManager();
        back_btn = findViewById(R.id.back_btn);
        save_btn = findViewById(R.id.save_btn);

        title = findViewById(R.id.memo_title_edit);
        content = findViewById(R.id.memo_content_edit);


        /*뒤로 가기 버튼*/
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String edit_title = title.getText().toString();
                String edit_content = content.getText().toString();
                String save_form =  "{\"title\":\""+edit_title+"\",\"content\":\""+edit_content+"\"}";

                Long now = System.currentTimeMillis();
                Date mDate = new Date(now);
                SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String getTime = simpleDate.format(mDate).toString();

                Log.d("WriteActivity","제목 : "+edit_title+", 내용 : "+edit_content+", 현재시간 : "+getTime);
                PreferenceManager.setString(getApplication(), getTime, save_form);


                Intent intent = new Intent();
                intent.putExtra("date", getTime);
                intent.putExtra("title", edit_title);
                intent.putExtra("content", edit_content);
                setResult(RESULT_OK, intent);
                finish();
            }
        });




    }
}