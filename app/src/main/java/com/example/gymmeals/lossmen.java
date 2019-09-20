package com.example.gymmeals;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class lossmen extends AppCompatActivity {
    Spinner spin1,spin2,spin3;
    String data1[]={"OATS","NUTS","MILK","CHOCOLATE MILK"};
    String data2[]={"RICE+DAL/FISH","NUTS","SALAD","CURD"};
    String data3[]={"DAL","NUTS","SALAD","CURD"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gainmen);
        spin1=(Spinner)findViewById(R.id.spin1);
        spin2=(Spinner)findViewById(R.id.spin2);
        spin3=(Spinner)findViewById(R.id.spin3);

        ArrayAdapter adapter1=new ArrayAdapter(lossmen.this,android.R.layout.simple_list_item_1,data1);
        spin1.setAdapter(adapter1);
        ArrayAdapter adapter2=new ArrayAdapter(lossmen.this,android.R.layout.simple_list_item_1,data2);
        spin2.setAdapter(adapter2);
        ArrayAdapter adapter3=new ArrayAdapter(lossmen.this,android.R.layout.simple_list_item_1,data3);
        spin3.setAdapter(adapter3);


    }
}
