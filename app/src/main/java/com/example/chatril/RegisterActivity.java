package com.example.chatril;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chatril.Model.AvatarActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {


    String selectedAvatar = "adam";

    String selectedStatus = "Available";

   EditText username, email, password, cpassword;

   ImageView profilepicture;

   Button button_register;

   FirebaseAuth auth;

   DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        cpassword = findViewById(R.id.cpassword);

        button_register = findViewById(R.id.button_register);

        profilepicture = findViewById(R.id.profilepicture);


        auth = FirebaseAuth.getInstance();

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str_username = username.getText().toString();
                String str_email = email.getText().toString();
                String str_password = password.getText().toString();
                String str_cpassword = cpassword.getText().toString();

                if(TextUtils.isEmpty(str_username) || TextUtils.isEmpty(str_email) || TextUtils.isEmpty(str_password) || TextUtils.isEmpty(str_cpassword)){

                        Toast.makeText(RegisterActivity.this, "You cannot leave any field empty!", Toast.LENGTH_SHORT).show();


                }

                else if (str_password.length() <  6){

                        Toast.makeText(RegisterActivity.this, "Choose a password with more than 6 characters!", Toast.LENGTH_SHORT).show();

                }

                else if (!str_password.equals(str_cpassword)){


                    Toast.makeText(RegisterActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT).show();

                }

                else {

                    registerAccount(str_username, str_email, str_password);

                }


            }
        });

        profilepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectAvatar();

            }
        });



    }


    private void registerAccount(final String username, String email, String password){


            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()){

                        FirebaseUser firebaseUser = auth.getCurrentUser();

                        assert firebaseUser != null;

                        String userid = firebaseUser.getUid();

                        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);


                        HashMap<String,String> hashMap = new HashMap<>();

                        hashMap.put("id", userid);
                        hashMap.put("username", username);
                        hashMap.put("userstatus", selectedStatus);
                        hashMap.put("imageURL", selectedAvatar);


                        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                                if(task.isSuccessful()){

                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);

                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                    startActivity(intent);

                                    finish();


                                }

                            }
                        });

                    }   else {


                        Toast.makeText(RegisterActivity.this, "You cannot register with this e-mail or password!", Toast.LENGTH_SHORT).show();

                    }

                }
            });

    }


    private void selectAvatar(){

        Intent intent = new Intent(RegisterActivity.this, AvatarActivity.class);


        startActivityForResult(intent, 69);


    }

    private void showAvatar(){

        switch (selectedAvatar){

            case "adam":

                profilepicture.setImageResource(R.drawable.adam);
                break;

            case "dylan":

                profilepicture.setImageResource(R.drawable.dylan);
                break;

            case "george":

                profilepicture.setImageResource(R.drawable.george);
                break;

            case "martha":

                profilepicture.setImageResource(R.drawable.martha);
                break;

            case "mike":

                profilepicture.setImageResource(R.drawable.mike);
                break;

            case "rachel":

                profilepicture.setImageResource(R.drawable.rachel);
                break;

            case "sandy":

                profilepicture.setImageResource(R.drawable.sandy);
                break;

            case "sarah":

                profilepicture.setImageResource(R.drawable.sarah);
                break;

            case "sid":

                profilepicture.setImageResource(R.drawable.sid);
                break;

            case "steve":

                profilepicture.setImageResource(R.drawable.steve);
                break;

        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 69){

            if(resultCode==RESULT_OK){

                String returnString = data.getStringExtra("avatar");


                selectedAvatar = returnString;


                showAvatar();

            }

        }

    }
}
