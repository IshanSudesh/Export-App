package com.example.eexport.Controller;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.eexport.Model.productModel;
import com.example.eexport.ProductDetails;
import com.example.eexport.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllProductsAdapter extends BaseAdapter {


    Context context;
    ArrayList<productModel> arrayList;
    productModel product;

    public AllProductsAdapter(Context context, ArrayList<productModel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    public int getCount() {
        return arrayList.size();
    }


    public Object getItem(int position) {
        return arrayList.get(position);
    }


    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.list_product, null);

        TextView productName = convertView.findViewById(R.id.productDetails_productName);
        TextView productPrice = convertView.findViewById(R.id.productDetails_productPrice);
        TextView sellerName = convertView.findViewById(R.id.productDetails_sellerName);
        ImageView imageView = convertView.findViewById(R.id.productDetails_productImage);
        LinearLayout linearLayout = convertView.findViewById(R.id.cardLayout);

        product = arrayList.get(position);

        productName.setText(String.valueOf("Product : "+product.getProductName()));
        productPrice.setText(String.valueOf("Price : "+product.getProductPrice()));
        sellerName.setText(String.valueOf("Seller : "+product.getOwnerName()));
        Picasso.get().load(product.getImageUrl()).into(imageView);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Code :", product.getProductId());
                Intent intent = new Intent(context,ProductDetails.class);
                intent.putExtra("productCode",product.getProductId());
                context.startActivity(intent);

            }
        });

//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("Inside linearlayout", product.getProductId());
//                Intent intent = new Intent(context, ProductDetails.class);
//                intent.putExtra("productCode", product.getProductId());
//                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivities(new Intent[]{intent});

//                Intent intent = new Intent();
//                intent.setClass(context,ProductDetails.class);
//                intent.setAction(ProductDetails.class.getName());
//                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
//                context.startActivities(intent);
//            }
//        });





        return convertView;
    }

}
