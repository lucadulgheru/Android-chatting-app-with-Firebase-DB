package com.example.chatril;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatril.AES.AES;
import com.example.chatril.Adapter.MessageAdapter;
import com.example.chatril.Model.Chat;
import com.example.chatril.Model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MessageActivity extends AppCompatActivity{


    ImageView profilepicture;
    TextView username;

    ImageButton button_send;
    EditText text_send;

    FirebaseUser fbUser;
    DatabaseReference reference;

    MessageAdapter messageAdapter;
    List<Chat> mChat;

    RecyclerView recyclerView;

    int index;

    final String secretKey = "1j3@id#i@$%mx3(*";

    Intent intent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });


        recyclerView = findViewById(R.id.recycler_view_messages);

        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        linearLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(linearLayoutManager);


        profilepicture = findViewById(R.id.profilepicture);
        username = findViewById(R.id.username);

        button_send = findViewById(R.id.button_send);
        text_send = findViewById(R.id.text_send);


        intent = getIntent();

        final String userid = intent.getStringExtra("userid");

        fbUser = FirebaseAuth.getInstance().getCurrentUser();


        button_send.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v){

                String msg = text_send.getText().toString();


                if(!msg.equals("")){

                    try {
                        sendMessage(fbUser.getUid(), userid, msg);
                    }

                    catch (Exception e){

                        e.printStackTrace();
                    }
                }

                else{

                    Toast.makeText(MessageActivity.this, "You have to type something!", Toast.LENGTH_SHORT).show();

                }

                text_send.setText("");

            }
        });


        reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);

                username.setText(user.getUsername());


                switch (user.getImageURL()) {

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

                    readMessages(fbUser.getUid(), userid, user.getImageURL());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendMessage(String sender, String receiver, String message) throws Exception{


        try {

            message = AES.encrypt(message, secretKey);

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

            HashMap<String, Object> hashMap = new HashMap<>();

            hashMap.put("sender", sender);
            hashMap.put("receiver", receiver);
            hashMap.put("message", message);

            reference.child("Chats").push().setValue(hashMap);

        }


        catch (Exception e){

            e.printStackTrace();
            throw new Exception(e);

        }

    }


    private void readMessages(final String myid, final String userid, final String imageurl){

        mChat = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chats");

        reference.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                mChat.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){


                    Chat chat = snapshot.getValue(Chat.class);

                    if(chat.getReceiver().equals(myid) && chat.getSender().equals(userid) || chat.getReceiver().equals(userid) && chat.getSender().equals(myid)){


                        try{

                            chat.setMessage(AES.decrypt(chat.getMessage(), secretKey));

                            InputStream is = getApplicationContext().getAssets().open("blacklist.txt");

                            BufferedReader br = new BufferedReader(new InputStreamReader(is));

                            String strLine;

                            String filteredMessage;

                            while ((strLine = br.readLine()) != null) {


                                try{

                                Pattern rx = Pattern.compile("\\b" + strLine + "\\b", Pattern.CASE_INSENSITIVE);
                                filteredMessage = rx.matcher(chat.getMessage()).replaceAll(new String(new char[strLine.length()]).replace('\0', '*'));

                                chat.setMessage(filteredMessage);

                                }

                                catch (Exception e){


                                }
                            }

                            is.close();
                            br.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                        mChat.add(chat);


                    }

                    messageAdapter = new MessageAdapter(MessageActivity.this, mChat, imageurl);

                    recyclerView.setAdapter(messageAdapter);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }



}
