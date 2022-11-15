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
import com.number1.phonetic.model.Supplier;

import java.util.ArrayList;

public class SupplierAdapterH extends RecyclerView.Adapter<SupplierAdapterH.ViewHolder> {
    ArrayList<Supplier> suppliers;
    Context context;

    public SupplierAdapterH(ArrayList<Supplier> suppliers, MainActivity activity) {
        this.suppliers = suppliers;
        this.context = activity;
    }

    @NonNull
    @Override
    public SupplierAdapterH.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.supplier_list_v, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SupplierAdapterH.ViewHolder holder, int position) {
        final Supplier supplier = suppliers.get(position);
        holder.tv.setText(supplier.getName());
        holder.iv.setImageResource(R.drawable.ic_launcher_background);
    }

    @Override
    public int getItemCount() {
        if (suppliers.size() > 6) {
            return 6;
        } else {
            return suppliers.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.ivSupplierH);
            tv = itemView.findViewById(R.id.tvSupplierH);
        }
    }
}
