package com.example.gymmeals;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;

public class MainActivity extends AppCompatActivity {
    EditText username,name,password,phone;
    FirebaseAuth mAuth;
    Button signup;
    TextView already;
    DatabaseReference reff;
    member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FirebaseAuth mAuth ;
        mAuth=FirebaseAuth.getInstance();

        username=(EditText)findViewById(R.id.username);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone) ;
        password=(EditText)findViewById(R.id.password);
        signup=(Button) findViewById(R.id.signup);
        already=(TextView) findViewById(R.id.already);
        reff= FirebaseDatabase.getInstance().getReference().child("Member");
        member=new member();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long phone_no= Long.parseLong(phone.getText().toString().trim());
                member.setPhone(phone_no);
                member.setName(name.getText().toString());


                String email = username.getText().toString();
                String pwd = password.getText().toString();

                if (email.isEmpty()) {
                    username.setError("Please enter the email id");
                    username.requestFocus();

                } else if (pwd.isEmpty()) {
                    password.setError("Please enter the password");
                    password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Fields Are Empty", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Signup unsuccessful, Please try again later", Toast.LENGTH_SHORT).show();

                            } else {
                                reff.push().setValue(member);
                                Intent i = new Intent(MainActivity.this, home.class);
                                startActivity(i);

                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Error Occured",Toast.LENGTH_SHORT).show();
                }
            }

        });
        already.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i= new Intent(MainActivity.this,loginactivity.class);
            startActivity(i);
            }
        });

    }
}
