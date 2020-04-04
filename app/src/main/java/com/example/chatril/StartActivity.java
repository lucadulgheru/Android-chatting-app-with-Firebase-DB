package com.example.chatril;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class StartActivity extends AppCompatActivity {

    Button button_login, button_register;

    FirebaseUser fbUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        fbUser = FirebaseAuth.getInstance().getCurrentUser();


        if(fbUser!=null){

            Intent intent = new Intent(StartActivity.this, MainActivity.class);

            startActivity(intent);

            finish();


        }

        button_login = findViewById(R.id.button_login);

        button_register = findViewById(R.id.button_register);


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(StartActivity.this, LoginActivity.class));

            }
        });


        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(StartActivity.this, RegisterActivity.class));

            }
        });


    }
}
