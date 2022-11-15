package com.example.mymemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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




    }
}