package com.number1.phonetic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button loginButton;
    EditText user, pass;
    String strUser, strPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.userEmail);
        pass = findViewById(R.id.userPass);

        loginButton = findViewById(R.id.logButton);
        loginButton.setOnClickListener(v -> {
            //if(Database.isOnline()) {
                verify();
           // } else {
            //    Toast.makeText(Login.this, "You're offline", Toast.LENGTH_SHORT).show();
            //}
        });
    }

    private void verify() {
        Database connection = new Database();
        strUser = user.getText().toString();
        strPass = pass.getText().toString();
        Database.LogIn(strUser, strPass);
        if (connection.getEstado()) {
            createNew();
            finish();
        } else {
            Toast.makeText(Login.this, "User or password incorrect", Toast.LENGTH_SHORT).show();
        }
    }

    private void createNew() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("user", strUser);
        startActivity(intent);
    }
}