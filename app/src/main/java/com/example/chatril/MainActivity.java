package com.example.chatril;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.chatril.Fragments.ChatsFragment;
import com.example.chatril.Fragments.FriendsFragment;
import com.example.chatril.Fragments.ProfileFragment;
import com.example.chatril.Model.User;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    static String selectedAvatar;

    TextView textView;

    int position = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);



        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new ChatsFragment(), "Chats");
        viewPagerAdapter.addFragment(new FriendsFragment(), "Friends");
        viewPagerAdapter.addFragment(new ProfileFragment(), "My Profile");


        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);



    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {

        super.onActivityResult(requestCode,resultCode,data);


    }


    class ViewPagerAdapter extends FragmentPagerAdapter{


        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fm){

            super(fm);

            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();

        }


        @Override
        public Fragment getItem(int position) {

            return fragments.get(position);

        }

        @Override
        public int getCount() {

            return fragments.size();

        }

        public void addFragment(Fragment fragment, String title){

                fragments.add(fragment);

                titles.add(title);


        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {

            return titles.get(position);

        }
    }

    public static String sendAvatar(){

        return selectedAvatar;

    }

}


