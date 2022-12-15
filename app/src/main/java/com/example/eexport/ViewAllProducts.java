package com.example.eexport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.eexport.Controller.AllProductsAdapter;
import com.example.eexport.Model.productModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class ViewAllProducts extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    ArrayList<productModel> productModelArrayList = new ArrayList<>();
    AllProductsAdapter allProductsAdapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_products);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child("products");

        getProductList();
    }

    private void displayProductList(){

        listView = findViewById(R.id.listviewId);
        allProductsAdapter = new AllProductsAdapter(this, productModelArrayList);
        listView.setAdapter(allProductsAdapter);
        allProductsAdapter.notifyDataSetChanged();

    }

    public void getProductList(){

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dss: snapshot.getChildren()){

                        productModel productModel = new productModel();

                        productModel.setProductName(dss.child("productName").getValue(String.class));
                        productModel.setProductPrice(dss.child("productPrice").getValue(String.class));
                        productModel.setImageUrl(dss.child("productImage").getValue(String.class));
                        productModel.setOwnerName(dss.child("userName").getValue(String.class));
                        productModel.setProductId(dss.child("productCode").getValue(String.class));

                        productModelArrayList.add(productModel);
                    }

                    if (!productModelArrayList.isEmpty()){
                        displayProductList();
                    }else{
                        Toast.makeText(ViewAllProducts.this, "No Products", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}