package com.example.eexport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.eexport.Controller.Session;

public class Home extends AppCompatActivity {

    Button viewProducts,addNewProducts,userProfile,logout;

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        session = new Session(getApplicationContext());
        session.checkLogin();

        viewProducts = findViewById(R.id.viewAllProducts);
        addNewProducts = findViewById(R.id.addNewProducts);
        userProfile = findViewById(R.id.userProfile);
//        logout = findViewById(R.id.logOut);


        viewProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,ViewAllProducts.class);
                startActivity(intent);
            }
        });

        addNewProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,AddProductA.class);
                startActivity(intent);
            }
        });

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this,UserProfile.class);
                startActivity(intent);
            }
        });

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                session.logOutUser();
//            }
//        });
    }
}