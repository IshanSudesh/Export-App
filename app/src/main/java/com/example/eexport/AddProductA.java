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
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class AddProductA extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseStorage storage;

    ImageButton imageButton;
    EditText productName, productCode, productPrice, productDescription;
    Button addProduct;
    String userName,userEmail,userPhone;


    private static final int Gallery_Code = 1;
    Uri imageUrl=null;
    ProgressDialog progressDialog;

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product2);

        session = new Session(this.getApplicationContext());
        session.checkLogin();

        imageButton = findViewById(R.id.imageButton);
        productName = findViewById(R.id.productName);
        productCode = findViewById(R.id.productCode);
        productPrice = findViewById(R.id.productPrice);
        productDescription = findViewById(R.id.productDescription);
        addProduct = findViewById(R.id.addProductButton);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("products");
        storage = FirebaseStorage.getInstance();

        progressDialog = new ProgressDialog(this);

        getUserDetails();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Code);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == Gallery_Code && resultCode == RESULT_OK){

            imageUrl = data.getData();
            imageButton.setImageURI(imageUrl);
        }

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String productname = productName.getText().toString().trim();
                String productcode = productCode.getText().toString().trim();
                String productprice = productPrice.getText().toString().trim();
                String productdesc = productDescription.getText().toString().trim();


                if(!(productname.isEmpty() || productcode.isEmpty() || productprice.isEmpty() || productdesc.isEmpty())){

                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    StorageReference filepath = storage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                    String t = task.getResult().toString();
                                    DatabaseReference newPost = reference.push();

                                    newPost.child("productName").setValue(productname);
                                    newPost.child("productCode").setValue(productcode);
                                    newPost.child("productPrice").setValue(productprice);
                                    newPost.child("productDescription").setValue(productdesc);
                                    newPost.child("userName").setValue(userName);
                                    newPost.child("userEmail").setValue(userEmail);
                                    newPost.child("userPhone").setValue(userPhone);
                                    newPost.child("productImage").setValue(task.getResult().toString());

                                    Toast.makeText(AddProductA.this, "Product is Added", Toast.LENGTH_SHORT).show();

                                    progressDialog.dismiss();

                                }
                            });
                        }
                    });

                }else{

                    productName.setError("product name is required");
                    productCode.setError("code is required");
                    productPrice.setError("price is required");
                    productDescription.setError("description is required");

                    Toast.makeText(AddProductA.this, "Plese fill all the fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    private void getUserDetails() {

        FirebaseDatabase fdatabase;
        DatabaseReference ref;

        fdatabase = FirebaseDatabase.getInstance();
        ref = fdatabase.getReference("users");

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
                Log.d("Before first if","cc");
                if(snapshot.exists()){
                    String passwordDB = snapshot.child(phone).child("password").getValue(String.class);
                    Log.d("First if","Password "+passwordDB);
                    if(passwordDB.equals(password)){
                        Log.d("user profile","working right");
                         userName = snapshot.child(phone).child("username").getValue(String.class);
                         userEmail = snapshot.child(phone).child("email").getValue(String.class);
                         userPhone = snapshot.child(phone).child("phone").getValue(String.class);

                    }else{
                        Toast.makeText(AddProductA.this, "Password Is Not Match", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(AddProductA.this, "There is no user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }
}