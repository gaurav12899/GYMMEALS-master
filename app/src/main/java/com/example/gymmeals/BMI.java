package com.example.gymmeals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class BMI extends AppCompatActivity {
    Button selectmeal,logout;
    TextView bmi_field,result_field;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        logout=(Button)findViewById(R.id.logout);
        selectmeal=(Button)findViewById(R.id.selectmeal);
        bmi_field=(TextView) findViewById(R.id.bmi);
        result_field=(TextView)findViewById(R.id.result);
        Intent intent=getIntent();
        String bmi=intent.getStringExtra("BMI");
        String result=intent.getStringExtra("RESULT");
        bmi_field.setText(bmi);
        result_field.setText(result);




        selectmeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=getIntent();
                String bmi=intent.getStringExtra("BMI");

                float BMI=Float.parseFloat(bmi);
                if (18.5 <= BMI && BMI <= 24.9) {

                    Intent i=new Intent(BMI.this,Normal_weight.class);
                    startActivity(i);

                } else if (BMI < 18.5) {

                    Intent i=new Intent(BMI.this,Underweight.class);
                    startActivity(i);
                } else if (BMI >= 25 && BMI <= 29.9) {

                    Intent i=new Intent(BMI.this,Overweighht.class);
                    startActivity(i);
                } else {

                    Intent i=new Intent(BMI.this,Overweighht.class);
                    startActivity(i);
                }

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent=new Intent(BMI.this,loginactivity.class);
                startActivity(intent);

            }
        });

    }
}
