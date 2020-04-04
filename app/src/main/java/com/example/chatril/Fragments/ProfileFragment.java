package com.example.chatril.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chatril.LoginActivity;
import com.example.chatril.MainActivity;
import com.example.chatril.Model.AvatarActivity;
import com.example.chatril.Model.User;
import com.example.chatril.R;
import com.example.chatril.RegisterActivity;
import com.example.chatril.StartActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.util.HashMap;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {


    ImageView profilepic;
    TextView username;
    EditText userstatus;

    Button button_logout;
    Button button_submitstatus;

    String selectedAvatar;

    DatabaseReference reference;

    FirebaseUser fbUser;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        profilepic = view.findViewById(R.id.profilepicture);

        username = view.findViewById(R.id.username);

        userstatus = view.findViewById(R.id.userstatus);

        button_logout = view.findViewById(R.id.button_logout);

        button_submitstatus = view.findViewById(R.id.button_submitstatus);

        fbUser = FirebaseAuth.getInstance().getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("Users").child(fbUser.getUid());


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                selectedAvatar = user.getImageURL();

                username.setText(user.getUsername());

                    switch (user.getImageURL()){

                        case "adam":

                            profilepic.setImageResource(R.drawable.adam);
                            break;

                        case "dylan":

                            profilepic.setImageResource(R.drawable.dylan);
                            break;

                        case "george":

                            profilepic.setImageResource(R.drawable.george);
                            break;

                        case "martha":

                            profilepic.setImageResource(R.drawable.martha);
                            break;

                        case "mike":

                            profilepic.setImageResource(R.drawable.mike);
                            break;

                        case "rachel":

                            profilepic.setImageResource(R.drawable.rachel);
                            break;

                        case "sandy":

                            profilepic.setImageResource(R.drawable.sandy);
                            break;

                        case "sarah":

                            profilepic.setImageResource(R.drawable.sarah);
                            break;

                        case "sid":

                            profilepic.setImageResource(R.drawable.sid);
                            break;

                        case "steve":

                            profilepic.setImageResource(R.drawable.steve);
                            break;

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });



        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseAuth.getInstance().signOut();

                Intent intent = new Intent(getActivity(), StartActivity.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(intent);

            }
        });


        profilepic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectAvatar();

            }
        });



        button_submitstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAvatar();


                reference = FirebaseDatabase.getInstance().getReference();

                reference.child("Users").child(fbUser.getUid()).child("imageURL").setValue(selectedAvatar);

                if (!userstatus.getText().toString().equals("")) {

                    reference.child("Users").child(fbUser.getUid()).child("userstatus").setValue(userstatus.getText().toString());

                    userstatus.setText("");


                }
            }
        });

        return view;


    }



    private void selectAvatar(){

        Intent intent = new Intent(getActivity(), AvatarActivity.class);


        startActivityForResult(intent, 69);


        //selectedAvatar = MainActivity.sendAvatar();
    }


    private void showAvatar(){

        switch (selectedAvatar){

            case "adam":

                profilepic.setImageResource(R.drawable.adam);
                break;

            case "dylan":

                profilepic.setImageResource(R.drawable.dylan);
                break;

            case "george":

                profilepic.setImageResource(R.drawable.george);
                break;

            case "martha":

                profilepic.setImageResource(R.drawable.martha);
                break;

            case "mike":

                profilepic.setImageResource(R.drawable.mike);
                break;

            case "rachel":

                profilepic.setImageResource(R.drawable.rachel);
                break;

            case "sandy":

                profilepic.setImageResource(R.drawable.sandy);
                break;

            case "sarah":

                profilepic.setImageResource(R.drawable.sarah);
                break;

            case "sid":

                profilepic.setImageResource(R.drawable.sid);
                break;

            case "steve":

                profilepic.setImageResource(R.drawable.steve);
                break;

        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(requestCode == 69){

            if(resultCode==RESULT_OK){

                String returnString = data.getStringExtra("avatar");

                selectedAvatar = returnString;

            }

        }

    }



}
