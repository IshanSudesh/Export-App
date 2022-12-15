package com.example.eexport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eexport.Controller.Session;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText UserName, UserEmail, UserMobile, UserPass, UserRePass;
    Button RegisterButton;
    TextView loginPage;

    Session session;
    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        session = new Session(getApplicationContext());


        UserName = findViewById(R.id.userName);
        UserEmail = findViewById(R.id.userEmail);
        UserMobile = findViewById(R.id.userMobile);
        UserPass = findViewById(R.id.userPass);
        UserRePass = findViewById(R.id.userRePass);
        RegisterButton = findViewById(R.id.registerButton);
        loginPage = findViewById(R.id.loginPage);

        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });


        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                database = FirebaseDatabase.getInstance();
                ref = database.getReference();

                String userName = UserName.getText().toString();
                String userEmail = UserEmail.getText().toString();
                String userMobile = UserMobile.getText().toString();
                String password = UserPass.getText().toString();
                String rePassword = UserRePass.getText().toString();


                if(!(userName.isEmpty() || userEmail.isEmpty() || userMobile.isEmpty() || password.isEmpty() || rePassword.isEmpty())) {

                    if (password.equals(rePassword)) {

                        ref.child("users").child(userMobile).child("username").setValue(userName);
                        ref.child("users").child(userMobile).child("email").setValue(userEmail);
                        ref.child("users").child(userMobile).child("phone").setValue(userMobile);
                        ref.child("users").child(userMobile).child("password").setValue(password);

                        session.createLoginSession(userMobile, password);

                        Intent intent = new Intent(Register.this, MainActivity.class);
                        startActivity(intent);

                        Toast.makeText(Register.this, "User Registered", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Register.this, "Password not match", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    UserName.setError("username is required");
                    UserEmail.setError("email is required");
                    UserMobile.setError("mobile number is required");
                    UserPass.setError("password is required");
                    UserRePass.setError("re-password is required");

                    Toast.makeText(Register.this, "Plese fill all the fields", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}