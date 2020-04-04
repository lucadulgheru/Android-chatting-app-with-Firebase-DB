package com.example.chatril;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    EditText email, password;

    Button button_login;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);

        password = findViewById(R.id.password);


        button_login = findViewById(R.id.button_login);


        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str_email = email.getText().toString();

                String str_password = password.getText().toString();


                if(TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password)){


                    Toast.makeText(LoginActivity.this, "You cannot leave any field empty!", Toast.LENGTH_SHORT).show();

                }


                else{


                    auth.signInWithEmailAndPassword(str_email, str_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if(task.isSuccessful()){


                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                startActivity(intent);

                                finish();
                            }

                            else {


                                Toast.makeText(LoginActivity.this, "Authentication failed!", Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                }

            }
        });

    }
}
