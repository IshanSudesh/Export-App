package com.example.eexport;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.eexport.Controller.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class AddProduct extends AppCompatActivity {

//    ImageButton imageButton;
//    EditText productName, productCode, productPrice, productDescription;
//    Button productButton;
//    String userName, userEmail, userPhone;
//
//    FirebaseDatabase database;
//    DatabaseReference ref;
//    FirebaseStorage storage;
//    Session session;
//
//    private static final int Gallery_Code=1;
//    Uri imageUrl = null;
//
//    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

//        session = new Session(getApplicationContext());
//        session.checkLogin();
//
//        imageButton = findViewById(R.id.addImageButton);
//        productName = findViewById(R.id.addProdcutName);
//        productCode = findViewById(R.id.addProductCode);
//        productPrice = findViewById(R.id.addProductPrice);
//        productDescription = findViewById(R.id.addProductDescription);
//        productButton = findViewById(R.id.addProductButton);
//
//        imageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                intent.setType("image/*");
//                startActivityForResult(intent,Gallery_Code);
//            }
//        });



//        productButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                getUserDetails();
//                addProduct(imageUrl);
////                Toast.makeText(AddProduct.this,"User Name "+userName,Toast.LENGTH_SHORT).show();
//                Log.d("User Name ",": "+userName);
////                Log.d("Image Url ",": "+imageUrl);
//            }
//        });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//                database = FirebaseDatabase.getInstance();
//                ref = database.getReference().child("products");
//                storage = FirebaseStorage.getInstance();
//
//        if (requestCode==Gallery_Code && requestCode==RESULT_OK){
//            imageUrl=data.getData();
//            imageButton.setImageURI(imageUrl);
//        }
//
//        productButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("Inside the add productModel ","Button");
//                final String name = productName.getText().toString().trim();
//                final  String code = productCode.getText().toString().trim();
//                final String price = productPrice.getText().toString().trim();
//                final String description = productDescription.getText().toString().trim();
//
//
//                if(!(name.isEmpty() && code.isEmpty() && price.isEmpty() && description.isEmpty())){

//                    Log.d("image url",":"+imageUrl);

//                    progressDialog.setTitle("Uploading....");
//                    progressDialog.show();

//                    StorageReference filePath = storage.getReference().child("productImage").child(imageUrl.getLastPathSegment());
//                    filePath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

//                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//
//                                @Override
//                                public void onComplete(@NonNull Task<Uri> task) {
//
//                                    Log.d("Product Name",":"+name);
//
//                                    String t  = task.getResult().toString();
//
//                                    DatabaseReference newPost = ref.push();
//
//                                    newPost.child("image").setValue(task.getResult().toString());
//                                    newPost.child("productname").setValue(name);
//                                    newPost.child("productcode").setValue(code);
//                                    newPost.child("productprice").setValue(price);
//                                    newPost.child("productdescription").setValue(description);
////                                  newPost.child("username").setValue(userName);
////                                  newPost.child("useremail").setValue(userEmail);
////                                  newPost.child("userphone").setValue(userPhone);
////                                    progressDialog.dismiss();
//
//                                }
//                            });
//                        }
//                    });
//
//                }else{
//                    Toast.makeText(AddProduct.this,"Check all the fields",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == Gallery_Code && requestCode == RESULT_OK) {
//            imageUrl = data.getData();
//            imageButton.setImageURI(imageUrl);
//            Log.d("Image Url ",": "+imageUrl);
//        }
//    }

//    private void addProduct(Uri uri){
//
//        database = FirebaseDatabase.getInstance();
//        ref = database.getReference().child("products");
//        storage = FirebaseStorage.getInstance();
//
//        final String name = productName.getText().toString().trim();
//        final  String code = productCode.getText().toString().trim();
//        final String price = productPrice.getText().toString().trim();
//        final String description = productDescription.getText().toString().trim();
//
//        progressDialog.setTitle("Uploading");
//        progressDialog.show();
//
//        StorageReference filepath = storage.getReference().child("productImage").child(imageUrl.getLastPathSegment());
//        filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
//                Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Uri> task) {
//
//                        String t = task.getResult().toString();
//
//                        DatabaseReference newPost = ref.push();
//
//                        newPost.child("image").setValue(task.getResult().toString());
//                        newPost.child("productname").setValue(name);
//                        newPost.child("productcode").setValue(code);
//                        newPost.child("productprice").setValue(price);
//                        newPost.child("productdescription").setValue(description);
////                        newPost.child("username").setValue(userName);
////                        newPost.child("useremail").setValue(userEmail);
////                        newPost.child("userphone").setValue(userPhone);
//
//                        progressDialog.dismiss();
//
//                        Toast.makeText(AddProduct.this, "Uploaded successful", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//    }

//    private void getUserDetails() {
//
//        DatabaseReference dbRef;
//
//        database = FirebaseDatabase.getInstance();
//        dbRef = database.getReference("users");
//
//
//        HashMap<String, String> session_user = session.getUserDetails();
//        String phone = session_user.get("phone");
//        String password = session_user.get("password");
//
////        Log.d("User Profile","Phone :"+phone);
////        Log.d("User Profile","Password :"+password);
//
////        userName.setText(phone);
//
//
//        Query checkUser = dbRef.orderByChild("phone").equalTo(phone);
//
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                Log.d("Before first if","cc");
//                if(snapshot.exists()){
//                    String passwordDB = snapshot.child(phone).child("password").getValue(String.class);
////                    Log.d("First if","Password "+passwordDB);
//                    if(passwordDB.equals(password)){
////                        Log.d("user profile","working right");
//                        userName = snapshot.child(phone).child("username").getValue(String.class);
//                        userEmail = snapshot.child(phone).child("email").getValue(String.class);
//                        userPhone = snapshot.child(phone).child("phone").getValue(String.class);
////                        Log.d("User Name ",": "+userName);
//
//                    }else{
//                        Toast.makeText(AddProduct.this, "Error getting 1", Toast.LENGTH_SHORT).show();
//                    }
//
//                }else{
//                    Toast.makeText(AddProduct.this, "Error getting 2", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//
//            }
//        });
//    }

}