package com.mcas2.firstdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Looby extends AppCompatActivity {
String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_looby);
        Intent userRecibido = getIntent();
        username = userRecibido.getStringExtra("username");

    }
}