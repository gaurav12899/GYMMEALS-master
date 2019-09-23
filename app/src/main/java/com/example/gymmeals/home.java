package com.example.gymmeals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class home extends AppCompatActivity {
    Button skip,submit;
    EditText comments,age,weight,height;
    Spinner sex,healthissue;
    DatabaseReference details;
    userdetails userinfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        submit=(Button)findViewById(R.id.submit);
        skip=(Button)findViewById(R.id.skipbtn);
        height=(EditText)findViewById(R.id.height);
        weight=(EditText) findViewById(R.id.weight);
        age=(EditText)findViewById(R.id.age);
        comments=(EditText)findViewById(R.id.special);
        healthissue=(Spinner)findViewById(R.id.healthissue);
        sex=(Spinner)findViewById(R.id.sexspin);

        details= FirebaseDatabase.getInstance().getReference().child("Member Details");
        userinfo=new userdetails();





        List<String> list1 =new ArrayList<String>();
        list1.add("Male");
        list1.add("Female");
        list1.add("Others");

        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list1);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sex.setAdapter(arrayAdapter);
        sex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               sex.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        List<String> list2 =new ArrayList<String>();
        list2.add("none");
        list2.add("Diabetes");
        list2.add("Thyroid");
        list2.add("Arthritis");
        list2.add("Depression");
        list2.add("Obesity");
        list2.add("Asthama");
        list2.add("Others");

        ArrayAdapter<String> arrayAdapter2 =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list2);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        healthissue.setAdapter(arrayAdapter2);
        healthissue.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                healthissue.setSelection(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                userinfo.setHeight(height.getText().toString());
                userinfo.setAge(age.getText().toString());
                userinfo.setSpinnner(sex.getSelectedItem().toString());
                userinfo.setHealthissue(healthissue.getSelectedItem().toString().trim());
                userinfo.setWeight(weight.getText().toString());
                userinfo.setComments(comments.getText().toString());

                details.push().setValue(userinfo);

                float h = Float.parseFloat(height.getText().toString());
                float m = Float.parseFloat(weight.getText().toString());
                float hi= (float) (0.3084*h);
                float bmi=m/(hi*hi);
                String result;
                if (18.5 <= bmi && bmi <= 24.9) {
                    result = "Your BMI is normal";
                } else if (bmi < 18.5) {
                    result = "You are Underweight";
                } else if (bmi >= 25 && bmi <= 29.9) {
                    result = "You are Overweight";
                } else {
                    result = "You are in Obese range";
                }


                Intent i = new Intent(home.this,BMI.class);
                String Bmi= Float.toString(bmi);
                i.putExtra("BMI",Bmi);
                i.putExtra("RESULT",result);
                startActivity(i);

            }
        });



        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(home.this,Normal_weight.class);
                startActivity(i);
            }
        });
    }
}
