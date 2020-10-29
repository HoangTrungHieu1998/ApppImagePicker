package com.example.apppimagepicker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.apppimagepicker.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    ActivityHomeBinding mBiding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBiding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(mBiding.getRoot());

        mBiding.buttonNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RandomActivity.class);
                startActivity(intent);
            }
        });
    }
}