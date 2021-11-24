package com.example.session05;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recycler = findViewById(R.id.recycler);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recycler.setLayoutManager(manager);

        MyAdapter adapter = new MyAdapter();
        recycler.setAdapter();

        Intent intent = new Intent(MainActivity.this, TestForegroundService.class);
        recycler.addOnItemTouchListener(new adapter.cl )


        Button bt1 = findViewById(R.id.btnStart);
        Button bt2 = findViewById(R.id.btnEnd);
        bt1.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       startForegroundService(intent);
                                   }
                               }
        );
        bt2.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       stopService(intent);

                                   }
                               }
        );




    }
}