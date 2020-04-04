package com.example.chatril.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatril.MessageActivity;
import com.example.chatril.Model.User;
import com.example.chatril.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {


    private Context mContext;
    private List<User> mUsers;


    public UserAdapter(Context mContext, List<User> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);

        return new UserAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        final User user = mUsers.get(position);

        holder.username.setText(user.getUsername());

        holder.userstatus.setText(user.getUserstatus());

            switch (user.getImageURL()){

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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MessageActivity.class);

                intent.putExtra("userid", user.getId());

                mContext.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


            public TextView username;
            public TextView userstatus;
            public ImageView profilepicture;


            public ViewHolder(View itemView){


                    super(itemView);

                    username = itemView.findViewById(R.id.username);
                    userstatus = itemView.findViewById(R.id.userstatus);
                    profilepicture = itemView.findViewById(R.id.profilepicture);



            }


    }


}
