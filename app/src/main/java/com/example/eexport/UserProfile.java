package com.example.eexport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
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

import java.util.HashMap;

public class UserProfile extends AppCompatActivity {

    EditText userName, userEmail, userPass, userPhone;
    TextView userNameHeading;
    Button updateBtn;

    private String _userName, _userEmail, _userPass, _userPhone;

    Session session;
    FirebaseDatabase database;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        session = new Session(this.getApplicationContext());
        session.checkLogin();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("users");

        userNameHeading = findViewById(R.id.userProfile_userNameHeading);
        userName = findViewById(R.id.userProfile_userName);
        userEmail = findViewById(R.id.userProfile_userEmail);
        userPhone = findViewById(R.id.userProfile_userPhone);
        userPass = findViewById(R.id.userProfile_userPassword);
        updateBtn = findViewById(R.id.userProfile_updateButton);

//        showUserDetails();
        getUserDetails();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                UserUpdate(view);
//                Log.d("inside u`pdate","button");
                session.logOutUser();
            }
        });

    }

//    private void showUserDetails(){
//        Log.d("INSIDE THE showUserDetail","Working");
//        HashMap<String, String> session_user = session.getUserDetails();
//        String phone = session_user.get("phone");
//        String password = session_user.get("password");
//
//        Query checkUser = ref.orderByChild("phone").equalTo(phone);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if(snapshot.exists()){
//                    String passwordDB = snapshot.child(phone).child("phone").getValue(String.class);
//                    if (passwordDB.equals(password)){
//                        String namme = snapshot.child(phone).child("username").getValue(String.class);
//                        String email = snapshot.child(phone).child("email").getValue(String.class);
//                        String passw = snapshot.child(phone).child("password").getValue(String.class);
//
//                        Intent intent = new Intent(getApplicationContext(),UserProfile.class);
//
//                        intent.putExtra("name",namme);
//                        intent.putExtra("email",email);
//                        intent.putExtra("password",passw);
//
//                    }else{
//                        Toast.makeText(UserProfile.this, "Error getting 1", Toast.LENGTH_SHORT).show();
//                    }
//                }else{
//                    Toast.makeText(UserProfile.this, "Error getting 2", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//    }
//
//    private void getUserDetails(){
//        Log.d("INSIDE THE getUserDetails","Working");
//        Intent intent = getIntent();
//
//        Log.d("intent","value "+intent);
//        _userName = intent.getStringExtra("name");
//        _userEmail = intent.getStringExtra("email");
//        _userPass = intent.getStringExtra("password");
//
//        Log.d("User name","value "+_userName);
////
//        userName.setText(_userName);
//        userEmail.setText(_userEmail);
//        userPass.setText(_userPass);
//
//    }

    private void getUserDetails() {

        HashMap<String, String> session_user = session.getUserDetails();
        String phone = session_user.get("phone");
        String password = session_user.get("password");

//        Log.d("User Profile","Phone :"+phone);
//        Log.d("User Profile","Password :"+password);

//        userName.setText(phone);


        Query checkUser = ref.orderByChild("phone").equalTo(phone);


        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Log.d("Before first if","cc");
                if(snapshot.exists()){
                    String passwordDB = snapshot.child(phone).child("password").getValue(String.class);
//                    Log.d("First if","Password "+passwordDB);
                    if(passwordDB.equals(password)){
//                        Log.d("user profile","set data");

                        userNameHeading.setText(snapshot.child(phone).child("username").getValue(String.class));
                        userName.setText(snapshot.child(phone).child("username").getValue(String.class));
                        userEmail.setText(snapshot.child(phone).child("email").getValue(String.class));
                        userPhone.setText(phone);
                        userPass.setText(snapshot.child(phone).child("password").getValue(String.class));

                    }else{
                        Toast.makeText(UserProfile.this, "Error getting 1", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(UserProfile.this, "Error getting 2", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }

//    public void UserUpdate(View view){
//        if(usernameUpdate() || emailUpdate()){
//            Toast.makeText(this,"data update", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this,"data is not update", Toast.LENGTH_SHORT).show();
//        }
//        Log.d("inside user update","method");
//    }

//    private boolean usernameUpdate(){
//        if(_userName.equals(userName.getText().toString())){
//            ref.child(_userPhone).child("username").setValue(userName.getText().toString());
//            _userName = userName.getText().toString();
//            return true;
//        }else{
//            return false;
//        }
//    }

//    private boolean usernameUpdate() {
//        if (!_userName.equals(userName.getText().toString())) {
//            ref.child(_userPhone).child("username").setValue(userName.getText().toString());
////            _userName = userName.getText().toString();
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    private boolean emailUpdate(){
//        if(!_userEmail.equals(userEmail.getText().toString())){
//            ref.child(_userPhone).child("email").setValue(userEmail.getText().toString());
////            _userEmail = userEmail.getText().toString();
//            return true;
//        } else {
//            return false;
//        }
//    }

//    private boolean passwordUpdate(){
//        return true;
//    }

}