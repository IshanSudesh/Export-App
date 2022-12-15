package com.example.eexport.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eexport.Model.productModel;
import com.example.eexport.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    List<productModel> productModelList;

    public ProductAdapter(Context context, List<productModel> productModelList) {
        this.context = context;
        this.productModelList = productModelList;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list, parent, false);
        //design row connectivity here
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

        // bind here

        productModel productModel = productModelList.get(position);
        holder.productName.setText("Product : "+productModel.getProductName());
        holder.productPrice.setText("Price : "+productModel.getProductPrice());

        String imageUri = null;
        imageUri = productModel.getImageUrl();
        Picasso.get().load(imageUri).into(holder.imageView); //set image here

    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //declare design here

        ImageView imageView;
        TextView productName, productPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.productDetails_productImage);
            productName = itemView.findViewById(R.id.productDetails_productName);
            productPrice = itemView.findViewById(R.id.productDetails_productPrice);


        }
    }
}
