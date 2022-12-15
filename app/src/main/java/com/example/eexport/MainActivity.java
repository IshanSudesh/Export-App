package com.example.eexport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.eexport.Controller.Session;

public class MainActivity extends AppCompatActivity {

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        session = new Session(getApplicationContext());
        session.checkLogin();

        Intent intent = new Intent(MainActivity.this, Home.class);
        startActivity(intent);

    }
}