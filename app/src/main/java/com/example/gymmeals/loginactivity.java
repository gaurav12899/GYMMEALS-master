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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginactivity extends AppCompatActivity {


    EditText emailId,Password,phone;
    Button btnSignin;
    TextView wanttosingnup;
    FirebaseAuth mAuth;;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginactivity);



            mAuth=FirebaseAuth.getInstance();
            emailId=findViewById(R.id.loginid);
            phone=findViewById(R.id.phone);
            Password=findViewById(R.id.loginpassword);
            wanttosingnup=findViewById(R.id.wanttosignup);
            btnSignin=findViewById(R.id.btnlogin);
            mAuthStateListener =new FirebaseAuth.AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                    FirebaseUser mFirebaseUser =mAuth.getCurrentUser();

                    if ( mFirebaseUser !=null){
                        Toast.makeText(loginactivity.this,"you are logged in",Toast.LENGTH_SHORT).show();
                        Intent i =new Intent(loginactivity.this,home.class);
                        startActivity(i);

                    }
                    else{
                        Toast.makeText(loginactivity.this,"Please Login",Toast.LENGTH_SHORT).show();
                    }


                }
            };
            btnSignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email=emailId.getText().toString();
                    String password=Password.getText().toString();
                    if(email.isEmpty()){
                        emailId.setError("Please enter the email id");
                        emailId.requestFocus();
                    }
                    else if(password.isEmpty()){
                        Password.setError("Please enter you password");
                        Password.requestFocus();
                    }
                    else if(email.isEmpty() && password.isEmpty()){
                        Toast.makeText(loginactivity.this,"Fields are empty!!!",Toast.LENGTH_SHORT).show();
                    }
                    else if(!(email.isEmpty() && password.isEmpty())) {
                        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(loginactivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(loginactivity.this, "Login Error,Please Try again later", Toast.LENGTH_SHORT).show();
                                } else {
                                    Intent intToHome = new Intent(loginactivity.this, home.class);
                                    startActivity(intToHome);
                                }
                            }
                        });
                    }

                    else{
                        Toast.makeText(loginactivity.this,"Error occured!!!",Toast.LENGTH_SHORT).show();

                    }

                }
            });
            wanttosingnup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intSignUp=new Intent(loginactivity.this,MainActivity.class);
                    startActivity((intSignUp));
                }
            });
        }

        @Override
        protected void onStart() {
            super.onStart();
            mAuth.addAuthStateListener(mAuthStateListener);
        }

    }

