package com.example.mobilebusticketingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText loginEmail;
    EditText loginPassword;
    Button loginButton;
    Button forgetButton;
    Button registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        loginEmail = (EditText) findViewById(R.id.loginEmail);
        loginPassword = (EditText) findViewById(R.id.loginPassword);
        loginButton = (Button)findViewById(R.id.loginButton);
        forgetButton = (Button) findViewById(R.id.forgetPassword);
        registration = (Button)findViewById(R.id.registrationInLogin);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(loginEmail.getText().toString().length()==0) {
                    loginEmail.requestFocus();
                    loginEmail.setError("FIELD CANNOT BE EMPTY");
                }else if(loginPassword.getText().toString().length() == 0) {
                    loginPassword.requestFocus();
                    loginPassword.setError("FIELD CANNOT BE EMPTY");
                }else{

                }
            }
        });

        forgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgetPassword.class);
                startActivity(intent);
            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

    }
}
