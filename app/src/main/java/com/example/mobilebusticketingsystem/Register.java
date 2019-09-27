package com.example.mobilebusticketingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilebusticketingsystem.Model.Passenger;

public class Register extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText nic;
    EditText contactNumber;
    EditText email;
    EditText password;
    EditText confirmPassword;
    Button registrationConfirm;

    Passenger passenger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        firstName = (EditText)findViewById(R.id.firstName);
        lastName = (EditText)findViewById(R.id.lastName);
        nic = (EditText)findViewById(R.id.NIC);
        contactNumber = (EditText)findViewById(R.id.contactNumber);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        confirmPassword = (EditText)findViewById(R.id.confirmPassword);
        registrationConfirm = (Button) findViewById(R.id.registrationConfirm);



        registrationConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(firstName.getText().toString().length()==0) {
                    firstName.requestFocus();
                    firstName.setError("FIELD CANNOT BE EMPTY");
                }else if(lastName.getText().toString().length() == 0) {
                    lastName.requestFocus();
                    lastName.setError("FIELD CANNOT BE EMPTY");
                }else if(nic.getText().toString().length() == 0) {
                    nic.requestFocus();
                    nic.setError("FIELD CANNOT BE EMPTY");
                }else if(nic.getText().toString().length() != 9) {
                    nic.requestFocus();
                    nic.setError("FIELD SHOULD BE 9");
                }else if(contactNumber.getText().toString().length() == 0) {
                    contactNumber.requestFocus();
                    contactNumber.setError("FIELD CANNOT BE EMPTY");
                }else if(contactNumber.getText().toString().length() != 10) {
                    contactNumber.requestFocus();
                    contactNumber.setError("Mobile Number should be 10");
                }else if(email.getText().toString().length() == 0) {
                    email.requestFocus();
                    email.setError("FIELD CANNOT BE EMPTY");
                }else if(password.getText().toString().length() == 0) {
                    password.requestFocus();
                    password.setError("PASSWORD CANNOT BE EMPTY");
                }else if(password.getText().toString().length() < 8) {
                    password.requestFocus();
                    password.setError("PASSWORD SHOULD BE GRATER THAN 8");
                }else if(!password.getText().toString().equals(confirmPassword.getText().toString())) {
                    confirmPassword.requestFocus();
                    confirmPassword.setError("PASSWORD SHOULD BE SAME");
                }else{
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}







