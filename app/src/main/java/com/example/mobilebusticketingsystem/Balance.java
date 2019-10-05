package com.example.mobilebusticketingsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Balance extends AppCompatActivity {
    IApiService apiService;
    String userId = "5d9064b26511891fbcdc4928";

    private String m_Text = "";
    TextView finalBalance;
    Button addCredit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.balance_details);

        finalBalance = (TextView) findViewById(R.id.balanceView);
        addCredit = (Button) findViewById(R.id.addCredit);

        getFinalBalance();
        addCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCreditForTravel();
            }
        });



    }


    public void addCreditForTravel(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Please enter your Recharge card.");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();
                System.out.println("Text***************************:"+m_Text);

                addCreditToDatabase(m_Text);

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }


    public void addCreditToDatabase(String relordNumber){
        Retrofit retrofitClient = ApiConnector.getInstance();
        apiService = retrofitClient.create(IApiService.class);


        Call<JsonObject> call = apiService.reloadBalance(userId,relordNumber);
        // Call<JsonObject> call = apiService.loginUser(loginEmail.getText().toString(),loginPassword.getText().toString());


        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {


                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Reload Failed", Toast.LENGTH_SHORT).show();
                    // return;
                }else {



                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));
                        System.out.println("**********"+jsonObject.toString());
                        // Toast.makeText(getApplicationContext(),jsonObject.toString(), Toast.LENGTH_SHORT).show();


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






    public void getFinalBalance(){

        Retrofit retrofitClient = ApiConnector.getInstance();
        apiService = retrofitClient.create(IApiService.class);

        Call<List<com.example.mobilebusticketingsystem.Model.Balance>> call = apiService.getBalenceById(userId);

        call.enqueue(new Callback<List<com.example.mobilebusticketingsystem.Model.Balance>>() {
            @Override
            public void onResponse(Call<List<com.example.mobilebusticketingsystem.Model.Balance>> call, Response<List<com.example.mobilebusticketingsystem.Model.Balance>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Error In Balance", Toast.LENGTH_SHORT).show();
                    // return;
                }else {
                        List<com.example.mobilebusticketingsystem.Model.Balance> balance = response.body();
                    if(balance == null){
                        Toast.makeText(getApplicationContext(),"Result is empty", Toast.LENGTH_SHORT).show();
                    }else{
                        finalBalance.setText(String.valueOf(balance.get(0).getBalance()));
                    }
                }
            }
            @Override
            public void onFailure(Call<List<com.example.mobilebusticketingsystem.Model.Balance>> call, Throwable t) {
                System.out.println("Fail :"+t.getMessage());
                Toast.makeText(getApplicationContext(), "Fail :"+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
