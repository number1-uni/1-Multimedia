package com.number1.phonetic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.number1.phonetic.adapter.ProductAdapter;
import com.number1.phonetic.adapter.ProductAdapterH;
import com.number1.phonetic.adapter.SupplierAdapter;
import com.number1.phonetic.adapter.SupplierAdapterH;
import com.number1.phonetic.model.Product;
import com.number1.phonetic.model.Supplier;

import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //TODO: SetOnClickListener on ImageButtons
        /*
        ImageButton btnProducts = (ImageButton) findViewById(R.id.btnProducts);
        ImageButton btnProviders = (ImageButton) findViewById(R.id.btnProviders);
        try {
            btnProducts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openProducts();
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
         */
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intent = getIntent();
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<Supplier> suppliers = new ArrayList<>();
        Database.grabProducts(products);
        Database.grabSuppliers(suppliers);
        createContent(products, suppliers);
    }

    public void openHome(View view) {
        open(view, "homescreen");
    }

    public void openProducts(View view) {
        open(view, "products");
    }

    public void openProviders(View view) {
        open(view, "providers");
    }

    public void open(View view, String name) {
        //Change icon colors
        ImageButton btnHome = (ImageButton) findViewById(R.id.btnHome);
        ImageButton btnProducts = (ImageButton) findViewById(R.id.btnProducts);
        ImageButton btnProviders = (ImageButton) findViewById(R.id.btnProviders);
        int nightModeFlags = view.getContext().getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK; //if dark mode is enabled
        if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) { //change icon colors to white
            btnHome.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_outline_home_white_24, getTheme()));
            btnProducts.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_outline_shopping_cart_white_24, getTheme()));
            btnProviders.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_outline_storefront_white_24, getTheme()));
        } else { //change all icons to black
            btnHome.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_outline_home_24, getTheme()));
            btnProducts.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_outline_shopping_cart_24, getTheme()));
            btnProviders.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_outline_storefront_24, getTheme()));
        }
        //Open the actual page and highlight its button
        LinearLayout homeScreen = (LinearLayout) findViewById(R.id.LinearLayoutHomescreen);
        LinearLayout productsScreen = (LinearLayout) findViewById(R.id.LinearLayoutProductsView);
        LinearLayout providersScreen = (LinearLayout) findViewById(R.id.LinearLayoutProvidersView);
        homeScreen.setVisibility(View.GONE);
        productsScreen.setVisibility(View.GONE);
        providersScreen.setVisibility(View.GONE);
        switch (name) {
            case "homescreen":
                btnHome.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_round_home_24, getTheme()));
                homeScreen.setVisibility(View.VISIBLE);
                break;
            case "products":
                btnProducts.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_round_shopping_cart_24, getTheme()));
                productsScreen.setVisibility(View.VISIBLE);
                break;
            case "providers":
                btnProviders.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_round_storefront_24, getTheme()));
                providersScreen.setVisibility(View.VISIBLE);
                break;
        }

    }

    public void createContent(ArrayList<Product> products, ArrayList<Supplier> suppliers) {
        try {
            RecyclerView rvProducts = findViewById(R.id.rvProducts);
            rvProducts.setLayoutManager(new LinearLayoutManager(this));
            rvProducts.setAdapter(new ProductAdapter(products, this));

            RecyclerView rvProductsH = findViewById(R.id.rvProductsH);
            rvProductsH.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            rvProductsH.setAdapter(new ProductAdapterH(products, this));

            RecyclerView rvSuppliers = findViewById(R.id.rvProviders);
            rvSuppliers.setLayoutManager(new LinearLayoutManager(this));
            rvSuppliers.setAdapter(new SupplierAdapter(suppliers, this));

            RecyclerView rvSuppliersH = findViewById(R.id.rvSuppliersH);
            rvSuppliersH.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            rvSuppliersH.setAdapter(new SupplierAdapterH(suppliers, this));

        } catch (Exception e) {
            Toast.makeText(this, "RecyclerView error", Toast.LENGTH_SHORT).show();
        }
    }
}