package com.number1.phonetic;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setElevation(0);
        actionBar.setIcon(R.drawable.ic_simple_square);
        //TODO: Change icons and actionBar colors in dark mode
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
    }


    public void openHome(View view) {
        LinearLayout homescreen = (LinearLayout) findViewById(R.id.LinearLayoutHomescreen);
        ScrollView products = (ScrollView) findViewById(R.id.ScrollViewProducts);
        ScrollView providers = (ScrollView) findViewById(R.id.ScrollViewProviders);
        ImageButton btnHome = (ImageButton) findViewById(R.id.btnHome);
        ImageButton btnProducts = (ImageButton) findViewById(R.id.btnProducts);
        ImageButton btnProviders = (ImageButton) findViewById(R.id.btnProviders);
        homescreen.setVisibility(View.VISIBLE);
        products.setVisibility(View.GONE);
        providers.setVisibility(View.GONE);
        btnHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_round_home_24, getTheme()));
        btnProducts.setImageDrawable(getResources().getDrawable(R.drawable.ic_outline_shopping_cart_24, getTheme()));
        btnProviders.setImageDrawable(getResources().getDrawable(R.drawable.ic_outline_storefront_24, getTheme()));
    }

    public void openProducts(View view) {
        LinearLayout homescreen = (LinearLayout) findViewById(R.id.LinearLayoutHomescreen);
        ScrollView products = (ScrollView) findViewById(R.id.ScrollViewProducts);
        ScrollView providers = (ScrollView) findViewById(R.id.ScrollViewProviders);
        ImageButton btnHome = (ImageButton) findViewById(R.id.btnHome);
        ImageButton btnProducts = (ImageButton) findViewById(R.id.btnProducts);
        ImageButton btnProviders = (ImageButton) findViewById(R.id.btnProviders);
        //TODO: add providers
        homescreen.setVisibility(View.GONE);
        products.setVisibility(View.VISIBLE);
        providers.setVisibility(View.GONE);
        btnHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_outline_home_24, getTheme()));
        btnProducts.setImageDrawable(getResources().getDrawable(R.drawable.ic_round_shopping_cart_24, getTheme()));
        btnProviders.setImageDrawable(getResources().getDrawable(R.drawable.ic_outline_storefront_24, getTheme()));
    }

    public void openProviders(View view) {
        LinearLayout homescreen = (LinearLayout) findViewById(R.id.LinearLayoutHomescreen);
        ScrollView products = (ScrollView) findViewById(R.id.ScrollViewProducts);
        ScrollView providers = (ScrollView) findViewById(R.id.ScrollViewProviders);
        ImageButton btnHome = (ImageButton) findViewById(R.id.btnHome);
        ImageButton btnProducts = (ImageButton) findViewById(R.id.btnProducts);
        ImageButton btnProviders = (ImageButton) findViewById(R.id.btnProviders);
        homescreen.setVisibility(View.GONE);
        products.setVisibility(View.GONE);
        providers.setVisibility(View.VISIBLE);
        btnHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_outline_home_24, getTheme()));
        btnProducts.setImageDrawable(getResources().getDrawable(R.drawable.ic_outline_shopping_cart_24, getTheme()));
        btnProviders.setImageDrawable(getResources().getDrawable(R.drawable.ic_round_storefront_24, getTheme()));
    }
}