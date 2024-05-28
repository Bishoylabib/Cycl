package com.example.cycl;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
public class MainActivity extends AppCompatActivity {


    Button login;
    Button signup;
    View view;
    private FirebaseAuth mAuth  = FirebaseAuth.getInstance();
    TextInputEditText email;
    TextInputLayout pass;
    TextInputEditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.Emailt);
        pass = findViewById(R.id.Password);
        password = findViewById(R.id.Passwordt);
        signup =findViewById(R.id.button2);
        view = findViewById(R.id.background1);
        view.setBackgroundColor(Color.rgb(249, 252, 255));
        login = findViewById(R.id.btL);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Signup.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((!password.getText().toString().equals("") || !email.getText().toString().equals(""))){
                    mAuth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        startActivity(new Intent(MainActivity.this,Homepage.class));
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(MainActivity.this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                    Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}