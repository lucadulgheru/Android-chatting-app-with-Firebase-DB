package com.example.chatril.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatril.MessageActivity;
import com.example.chatril.Model.Chat;
import com.example.chatril.Model.User;
import com.example.chatril.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;


    private Context mContext;
    private List<Chat> mChat;
    private String imageurl;

    FirebaseUser fbUser;

    private String key = "Bar12345Bar12345";


    public MessageAdapter(Context mContext, List<Chat> mChat, String imageurl) {
        this.mContext = mContext;
        this.mChat = mChat;
        this.imageurl = imageurl;
    }

    public MessageAdapter(){

    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType == MSG_TYPE_RIGHT) {

            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);

            return new MessageAdapter.ViewHolder(view);

        }

        else{

            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);

            return new MessageAdapter.ViewHolder(view);

        }

    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {


        Chat chat = mChat.get(position);

        //holder.show_message.setText(chat.getMessage());


        holder.show_message.setText(chat.getMessage());

        switch (imageurl) {

            case "adam":

                holder.profilepicture.setImageResource(R.drawable.adam);
                break;

            case "dylan":

                holder.profilepicture.setImageResource(R.drawable.dylan);
                break;

            case "george":

                holder.profilepicture.setImageResource(R.drawable.george);
                break;

            case "martha":

                holder.profilepicture.setImageResource(R.drawable.martha);
                break;

            case "mike":

                holder.profilepicture.setImageResource(R.drawable.mike);
                break;

            case "rachel":

                holder.profilepicture.setImageResource(R.drawable.rachel);
                break;

            case "sandy":

                holder.profilepicture.setImageResource(R.drawable.sandy);
                break;

            case "sarah":

                holder.profilepicture.setImageResource(R.drawable.sarah);
                break;

            case "sid":

                holder.profilepicture.setImageResource(R.drawable.sid);
                break;

            case "steve":

                holder.profilepicture.setImageResource(R.drawable.steve);
                break;


        }

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView show_message;
        public ImageView profilepicture;


        public ViewHolder(View itemView){


            super(itemView);

            show_message = itemView.findViewById(R.id.show_message);
            profilepicture = itemView.findViewById(R.id.profilepicture);



        }


    }

    @Override
    public int getItemViewType(int position) {

        fbUser = FirebaseAuth.getInstance().getCurrentUser();

        if(mChat.get(position).getSender().equals(fbUser.getUid())){

            return MSG_TYPE_RIGHT;

        }

        else return MSG_TYPE_LEFT;

    }



}
