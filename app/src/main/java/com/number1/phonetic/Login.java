package com.number1.phonetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
    Button loginButton;
    String user, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Database connection = new Database();

        user = findViewById(R.id.userText).toString();
        pass = findViewById(R.id.passText).toString();

        loginButton = findViewById(R.id.logButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //connection.LogIn(user, pass);
                createNew();
            }
        });
    }
    public void createNew(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}