package com.example.mobilebusticketingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mobilebusticketingsystem.ApiManager.ApiConnector;
import com.example.mobilebusticketingsystem.ApiManager.IApiService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Header;

public class Login extends AppCompatActivity {
    EditText loginEmail;
    EditText loginPassword;
    Button loginButton;
    Button forgetButton;
    Button registration;
    IApiService apiService;

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
                    Retrofit retrofitClient = ApiConnector.getInstance();
                    apiService = retrofitClient.create(IApiService.class);


                  Call<JsonObject> call = apiService.loginUser("jaliya@gmail.com","jaliya@gmail.com");
                   // Call<JsonObject> call = apiService.loginUser(loginEmail.getText().toString(),loginPassword.getText().toString());


                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {


                            if(!response.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Login Fail", Toast.LENGTH_SHORT).show();
                               // return;
                            }else {


                                Headers headerList = response.headers();
                                String headerValue = headerList.get("auth-token");
                                System.out.println("Header Value*******************************"+headerValue);



                                try {
                                    JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                                    System.out.println("**********"+jsonObject.toString());
                                    // Toast.makeText(getApplicationContext(),jsonObject.toString(), Toast.LENGTH_SHORT).show();

                                    if(headerValue==null){
                                        Toast.makeText(getApplicationContext(), "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                                    }else{
                                        ApiConnector.AUTH = headerValue;
                                        Intent intent = new Intent(getApplicationContext(), Home.class);
                                        startActivity(intent);
                                        //System.out.println("**********"+jsonObject.toString());
                                       // Toast.makeText(getApplicationContext(), "Login Success"+jsonObject.getString("password"), Toast.LENGTH_SHORT).show();
                                    }

                                } catch (JSONException | NullPointerException e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            System.out.println("Fail :"+t.getMessage());
                            Toast.makeText(getApplicationContext(), "Fail :"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

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
