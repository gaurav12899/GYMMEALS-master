package com.example.gymmeals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Underweight extends AppCompatActivity {

    Button Logout_btn, Call_us_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_underweight);


        Logout_btn = (Button) findViewById(R.id.logoutbtn);
        Call_us_btn = (Button) findViewById(R.id.Callus);

        Logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(Underweight.this, loginactivity.class);
                startActivity(i);
            }
        });
    }
}