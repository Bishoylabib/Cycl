package com.example.cycl;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
 import androidx.annotation.NonNull;

public class Signup extends AppCompatActivity {
    TextInputEditText password;
    TextInputEditText passwordc;
    TextInputEditText Email;
    private FirebaseAuth mAuth  = FirebaseAuth.getInstance();
    Button signup;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        view = findViewById(R.id.background1);
        view.setBackgroundColor(Color.rgb(249, 252, 255));
        password = findViewById(R.id.Passwordd);
        passwordc = findViewById(R.id.Passwordcc);
        Email = findViewById(R.id.Emaill);
        signup = findViewById(R.id.button2);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((!password.getText().toString().equals("") || !passwordc.getText().toString().equals("")||!Email.getText().toString().equals("") ) &&
                        password.getText().toString().equals(passwordc.getText().toString())){
                    mAuth.createUserWithEmailAndPassword(Email.getText().toString(),password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(Signup.this, "Signed Up Successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(Signup.this,MainActivity.class));
                                        finish();
                                    }
                                    else
                                    {
                                        Toast.makeText(Signup.this, "Something Wrong Happened", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
                else
                    Toast.makeText(Signup.this, "Error!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}