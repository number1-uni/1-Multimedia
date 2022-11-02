package com.number1.phonetic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setElevation(0);
        actionBar.setIcon(R.drawable.ic_simple_square);
        RecyclerView rvProducts = findViewById(R.id.rvProducts);
        rvProducts.setHasFixedSize(true);




        //TODO: Change actionBar color to AMOLED black in dark mode
        //TODO: Make the ActionBar text black in light mode, but keep it white in dark mode
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
        Intent intent = getIntent();
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
        LinearLayout productsScreen = (LinearLayout) findViewById(R.id.LinearLayoutProducts);
        LinearLayout providersScreen = (LinearLayout) findViewById(R.id.LinearLayoutProviders);
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
}