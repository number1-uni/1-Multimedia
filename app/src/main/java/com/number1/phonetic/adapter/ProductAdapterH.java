package com.number1.phonetic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.number1.phonetic.MainActivity;
import com.number1.phonetic.R;
import com.number1.phonetic.model.Product;

import java.util.ArrayList;

public class ProductAdapterH extends RecyclerView.Adapter<ProductAdapterH.ViewHolder>{
    ArrayList<Product> products;
    Context context;

    public ProductAdapterH(ArrayList<Product> products, MainActivity activity) {
        this.products = products;
        this.context = activity;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapterH.ViewHolder holder, int position) {
        final Product product = products.get(position);
        holder.tvProductName.setText(product.getName());
        holder.tvProductPrice.setText(String.valueOf(product.getPrice()) + "€");
        holder.ivProductPicture.setImageResource(R.drawable.ic_launcher_background);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.product_list_h, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if(products.size() > 6){
            return 6;
        }else{
            return products.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProductPicture;
        TextView tvProductName;
        TextView tvProductPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProductPicture = itemView.findViewById(R.id.ivProductPicture);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
        }

    }
}
