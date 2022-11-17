package com.example.mymemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    Button write_btn;
    private final int REQUEST_TEST = 200;

    RecyclerView recyclerView;
    MemoAdapter memoAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        write_btn = findViewById(R.id.write_btn);
        write_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), WriteActivity.class);
                startActivityForResult(i, REQUEST_TEST);
            }
        });


        //리사이클뷰 세팅
        LinearLayoutManager linearLayoutManager;
        recyclerView = findViewById(R.id.memo_rv);

        linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        memoAdapter = new MemoAdapter(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(memoAdapter);

        //저장된 키, 벨류 가져오기
        SharedPreferences prefb = getSharedPreferences("memo_contain", MODE_PRIVATE);

        Collection<?> col_val = prefb.getAll().values();
        Iterator<?> it_val = col_val.iterator();
        Collection<?> col_key = prefb.getAll().keySet();
        Iterator<?> it_key = col_key.iterator();

        // 키 벨류 값이 있을때 까지 반복
        while(it_val.hasNext() && it_key.hasNext() ){
            String key = (String) it_key.next();
            String value = (String) it_val.next();
            try{
                // String으로 된 value를 JSONObject로 변환
                JSONObject jsonObject = new JSONObject(value);
                String title = (String) jsonObject.getString("title");
                String content = (String) jsonObject.getString("content");

                memoAdapter.addItem(new MemoItem(key, title, content));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("MainActivity", "JSONObject : " + e);
            }

            memoAdapter.notifyDataSetChanged();

        }

    }
    @Override
        protected void onActivityResult(int requsetCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requsetCode, resultCode, data);

            if(requsetCode == REQUEST_TEST){
                if(resultCode == RESULT_OK){
                    Intent intent = getIntent();
                    String get_date = data.getStringExtra("date");
                    String get_title = data.getStringExtra("title");
                    String get_content = data.getStringExtra("content");

                    memoAdapter.addItem(new MemoItem(get_date, get_title, get_content));

                    memoAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "작성 되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "저장되지 않음", Toast.LENGTH_SHORT).show();
                }
            }


    }



}