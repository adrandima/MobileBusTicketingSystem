package com.example.mobilebusticketingsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {

    EditText forgetPasswordEmail;
    EditText inputVerificationCode;
    Button sendVerificationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_password);

        forgetPasswordEmail = (EditText)findViewById(R.id.forgetPasswordEmail);
        sendVerificationButton = (Button) findViewById(R.id.SendVerificaionCode);

       // forgetPasswordEmail.setEnabled(false);

        sendVerificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(forgetPasswordEmail.getText().toString().length()==0) {
                    forgetPasswordEmail.requestFocus();
                    forgetPasswordEmail.setError("FIELD CANNOT BE EMPTY");
                }else{
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ForgetPassword.this);
                    alertDialogBuilder.setMessage("Type your verification code");
                    inputVerificationCode = new EditText(ForgetPassword.this);
                    alertDialogBuilder.setView(inputVerificationCode);

                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            String verificationCode = inputVerificationCode.getText().toString();
                            Toast.makeText(ForgetPassword.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                        }
                    });

                    alertDialogBuilder.setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });

    }
}
