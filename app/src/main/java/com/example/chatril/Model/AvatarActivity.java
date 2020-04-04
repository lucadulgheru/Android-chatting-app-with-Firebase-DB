package com.example.chatril.Model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.chatril.R;

public class AvatarActivity extends AppCompatActivity {

    ImageView imageAdam, imageDylan, imageGeorge, imageMartha, imageMike, imageRachel, imageSandy, imageSarah, imageSid, imageSteve;


    private static String selectedAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar);

        imageAdam = findViewById(R.id.imageAdam);
        imageDylan = findViewById(R.id.imageDylan);
        imageGeorge = findViewById(R.id.imageGeorge);
        imageMartha = findViewById(R.id.imageMartha);
        imageMike = findViewById(R.id.imageMike);
        imageRachel = findViewById(R.id.imageRachel);
        imageSandy = findViewById(R.id.imageSandy);
        imageSarah = findViewById(R.id.imageSarah);
        imageSid = findViewById(R.id.imageSid);
        imageSteve = findViewById(R.id.imageSteve);


        imageAdam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.putExtra("avatar", "adam");


                setResult(RESULT_OK, intent);


                finish();

            }
        });

        imageDylan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.putExtra("avatar", "dylan");


                setResult(RESULT_OK, intent);


                finish();

            }
        });

        imageGeorge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.putExtra("avatar", "george");

                setResult(RESULT_OK, intent);

                finish();

            }
        });

        imageMartha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.putExtra("avatar", "martha");


                setResult(RESULT_OK, intent);

                finish();

            }
        });

        imageMike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.putExtra("avatar", "mike");


                setResult(RESULT_OK, intent);

                finish();

            }
        });

        imageRachel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.putExtra("avatar", "rachel");


                setResult(RESULT_OK, intent);
                finish();

            }
        });

        imageSandy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.putExtra("avatar", "sandy");


                setResult(RESULT_OK, intent);

                finish();

            }
        });

        imageSarah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.putExtra("avatar", "sarah");


                setResult(RESULT_OK, intent);

                finish();

            }
        });

        imageSid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.putExtra("avatar", "sid");


                setResult(RESULT_OK, intent);

                finish();

            }
        });

        imageSteve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.putExtra("avatar", "steve");


                setResult(RESULT_OK, intent);

                finish();

            }
        });


    }

    public static String getAvatar(){

        return selectedAvatar;

    }
}
