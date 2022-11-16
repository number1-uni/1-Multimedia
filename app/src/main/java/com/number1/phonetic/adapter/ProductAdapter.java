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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    ArrayList<Product> products;
    Context context;

    public ProductAdapter(ArrayList<Product> products, MainActivity activity) {
        this.products = products;
        this.context = activity;
    }

    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        final Product product = products.get(position);
        holder.txtPrIzena.setText(product.getName());
        holder.txtPrPrezioa.setText(String.valueOf(product.getPrice()) + "€");
        holder.imgProduct.setImageResource(R.drawable.ic_launcher_background);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.product_list_v, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView txtPrIzena;
        TextView txtPrPrezioa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduktua);
            txtPrIzena = itemView.findViewById(R.id.txtPrIzena);
            txtPrPrezioa = itemView.findViewById(R.id.txtPrPrezioa);
        }
    }
}
