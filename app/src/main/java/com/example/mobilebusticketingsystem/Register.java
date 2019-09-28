package com.example.mobilebusticketingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mobilebusticketingsystem.Adapters.TravelListAdapter;
import com.example.mobilebusticketingsystem.ApiManager.ApiConnector;
import com.example.mobilebusticketingsystem.ApiManager.IApiService;
import com.example.mobilebusticketingsystem.Model.Passenger;
import com.example.mobilebusticketingsystem.Model.Travel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    IApiService apiService;

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
                /*if(firstName.getText().toString().length()==0) {
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
                }else{*/
                    //Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();

                    Retrofit retrofitClient = ApiConnector.getInstance();
                    apiService = retrofitClient.create(IApiService.class);


                  //  Call<JsonObject> call = apiService.registerUser(email.getText().toString(),firstName.getText().toString(),password.getText().toString());
                    Call<JsonObject> call = apiService.registerUser("abcd@gmail.com","nimal","q5555weds");


                    call.enqueue(new Callback<JsonObject>() {
                        @Override
                        public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Email Already Exist", Toast.LENGTH_SHORT).show();
                                Intent register = new Intent(Register.this, Register.class);
                                startActivityForResult(register, 0);

                                return;
                            }
                            try {
                                JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                                System.out.println(jsonObject.toString());
                               // Toast.makeText(getApplicationContext(),jsonObject.toString(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(getApplicationContext(),"Registration Success", Toast.LENGTH_SHORT).show();
                                Intent login = new Intent(Register.this, Login.class);
                                startActivityForResult(login, 0);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            String userDetails = response.body().toString();


                            if(userDetails == null){
                                Toast.makeText(getApplicationContext(),"Result is empty", Toast.LENGTH_SHORT).show();
                            }else{

                            }


                        }

                        @Override
                        public void onFailure(Call<JsonObject> call, Throwable t) {
                            System.out.println("Fail :"+t.getMessage());
                            Toast.makeText(getApplicationContext(), "Fail :"+t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });





                }

            //}
        });
    }
}







