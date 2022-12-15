package com.example.eexport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eexport.Controller.Session;
import com.example.eexport.Model.productModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

public class ProductDetails extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    Session session;
    productModel productModel;

    ImageView productImage;
    TextView productName, productPrice, productDescription, productOwnerName, productOwnerEmail, productOwnerPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("products");

        session = new Session(this.getApplicationContext());
        session.checkLogin();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            String productCode = (String) bundle.get("productCode");

            productImage = findViewById(R.id.productImage_productDetails);
            productName = findViewById(R.id.productName_productDetails);
            productPrice = findViewById(R.id.productPrice_productDetails);
            productDescription = findViewById(R.id.productDescription_productDetails);
            productOwnerName = findViewById(R.id.productOwnerName_productDetails);
            productOwnerEmail = findViewById(R.id.productOwnerEmail_productDetails);
            productOwnerPhone = findViewById(R.id.productOwnerPhone_productDetails);
            getProductData(productCode);

        }
    }



    private void getProductData(String productCode){
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    for(DataSnapshot dataSnapshot : snapshot.getChildren()){

                        if(dataSnapshot.child("productCode").getValue(String.class).equals(productCode)){

                            productModel = new productModel();

                            productModel.setImageUrl(dataSnapshot.child("productImage").getValue(String.class));
                            productModel.setProductId(dataSnapshot.child("productCode").getValue(String.class));
                            productModel.setProductName(dataSnapshot.child("productName").getValue(String.class));
                            productModel.setProductPrice(dataSnapshot.child("productPrice").getValue(String.class));
                            productModel.setDescription(dataSnapshot.child("productDescription").getValue(String.class));
                            productModel.setOwnerName(dataSnapshot.child("userName").getValue(String.class));
                            productModel.setOwnerEmail(dataSnapshot.child("userEmail").getValue(String.class));
                            productModel.setOwnerPhone(dataSnapshot.child("userPhone").getValue(String.class));

                        }
                    }
                    productData();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void productData(){

        productName.setText("Product : "+productModel.getProductName());
        productPrice.setText("Price : "+productModel.getProductPrice());
        productDescription.setText("Description : "+productModel.getDescription());
        productOwnerName.setText("Seller : "+productModel.getOwnerName());
        productOwnerEmail.setText("Seller Email : "+productModel.getOwnerEmail());
        productOwnerPhone.setText("Seller Mobile : "+productModel.getOwnerPhone());
        Picasso.get().load(productModel.getImageUrl()).into(productImage);

    }
}