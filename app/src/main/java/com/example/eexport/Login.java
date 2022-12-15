package com.example.eexport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eexport.Controller.Session;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    EditText mobileNum, userPass;
    Button loginBtn;
    TextView registerLink;

    FirebaseDatabase database;
    DatabaseReference ref;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new Session(getApplicationContext());

        mobileNum = findViewById(R.id.mobileNumber);
        userPass = findViewById(R.id.userPassword);
        loginBtn = findViewById(R.id.loggingButton);
        registerLink = findViewById(R.id.registerLink);

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                ref = database.getReference("users");

                String phone = mobileNum.getText().toString();
                String password = userPass.getText().toString();

                Query checkQuery = ref.orderByChild("phone").equalTo(phone);

//                String phone1 = ref.child(phone).child("phone").toString();
//                Log.d("Phone","value :"+phone1);

            if(!(phone.isEmpty() || password.isEmpty())) {

                checkQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String passwordDB = snapshot.child(phone).child("password").getValue(String.class);
                            if (passwordDB.equals(password)) {

                                session.createLoginSession(phone, password);

                                Intent intent = new Intent(Login.this, Home.class);
                                startActivity(intent);

                            } else {
                                Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(Login.this, "No User ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }else{
                mobileNum.setError("mobile number required");
                userPass.setError("password required");
            }
            }
        });

    }
}