package com.example.gymmeals;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class chose extends AppCompatActivity {
    ListView chose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose);
        chose=(ListView)findViewById(R.id.chose);
        String[] values= new String[]{"MONDAY MEAL","TUESDAY MEAL","WEDNESDAY MEAL","THURSDAY MEAL","FRIDAY MEAL","SATURDAY MEAL"};
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,
                android.R.layout.activity_list_item,android.R.id.text1,values);
        chose.setAdapter(adapter);
        chose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    Intent myintent=new Intent(view.getContext(),gainmen.class);
                    startActivityForResult(myintent,0);
                }
                if (i==1){
                    Intent myintent=new Intent(view.getContext(),gainwomen.class);
                    startActivityForResult(myintent,1);
                }
                if (i==2){
                    Intent myintent=new Intent(view.getContext(),losswomen.class);
                    startActivityForResult(myintent,2);
                }
                if (i==3){
                    Intent myintent=new Intent(view.getContext(),lossmen.class);
                    startActivityForResult(myintent,3);
                }
                if (i==4){
                    Intent myintent=new Intent(view.getContext(),friday.class);
                    startActivityForResult(myintent,4);
                }
                if (i==5){
                    Intent myintent=new Intent(view.getContext(),saturday.class);
                    startActivityForResult(myintent,5);
                }


            }
        });





    }
}
