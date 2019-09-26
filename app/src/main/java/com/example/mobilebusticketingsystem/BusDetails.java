package com.example.mobilebusticketingsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mobilebusticketingsystem.Adapters.BusListAdapter;
import com.example.mobilebusticketingsystem.Adapters.TravelListAdapter;
import com.example.mobilebusticketingsystem.ApiManager.ApiConnector;
import com.example.mobilebusticketingsystem.ApiManager.IApiService;
import com.example.mobilebusticketingsystem.Model.Bus;
import com.example.mobilebusticketingsystem.Model.BusList;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BusDetails extends AppCompatActivity {
    IApiService apiService;
    Button chooseTime;
    private List<Bus> mBusInfoList;

    private ArrayList<Bus> busList;
    private ArrayList<Bus> busObjects;

    EditText fromValue;
    EditText toValue;
    String timeValue;
    Button searchBuses;
    TimePickerDialog timePickerDialog;
    Calendar calendar;
    int currentHour;
    int currentMinute;
    String amPm;

    BusListAdapter busListAdapter;
    ListView busInfoListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bus_details);

        //****************Time Picker*********************************************
        chooseTime = (Button)findViewById(R.id.etChooseTime);
        chooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });

        //****************Search buses*********************************************
        searchBuses = (Button)findViewById(R.id.searchBusesButton);
        searchBuses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    busInformtionFormValidation();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        busInfoListView = (ListView) findViewById(R.id.bus_list_view);


    }





    public void busInformtionFormValidation() throws JSONException {
        fromValue = (EditText)findViewById(R.id.fromInputField);
        toValue = (EditText)findViewById(R.id.toInputField);
        timeValue = chooseTime.getText().toString();

        if(fromValue.getText().toString().length()==0) {
            fromValue.requestFocus();
            fromValue.setError("FIELD CANNOT BE EMPTY");
        }else if(toValue.getText().toString().length() == 0) {
            toValue.requestFocus();
            toValue.setError("FIELD CANNOT BE EMPTY");
        }else {

            Bus busInfo = new Bus("Moratuwa","Dehiwala",timeValue);

            Retrofit retrofitClient = ApiConnector.getInstance();
            apiService = retrofitClient.create(IApiService.class);


            System.out.println("********************************************"+timeValue);

            Call<BusList> call = apiService.getBusDetails(fromValue.getText().toString().trim().replaceAll("\\s{2,}", " "),toValue.getText().toString().trim().replaceAll("\\s{2,}", " "));
            call.enqueue(new Callback<BusList>() {
                @Override
                public void onResponse(Call<BusList> call, Response<BusList> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    busObjects = new ArrayList<>();
                    busList = response.body().getBuses();

                    if(busList == null){
                        Toast.makeText(getApplicationContext(),"Result is empty", Toast.LENGTH_SHORT).show();
                    }else{
                        for(int i = 0;i < busList.size();i++){
                            busObjects.add(new Bus(busList.get(i).getBusPlateNo(),busList.get(i).getBusNo(),busList.get(i).getPrice(),busList.get(i).getStartTime(),busList.get(i).getEndTime()));
                            System.out.println(busList.get(i).getBusPlateNo()+busList.get(i).getBusNo()+busList.get(i).getEndTime()+busList.get(i).getStartTime());
                        }


                        busListAdapter = new BusListAdapter(getApplicationContext(), busObjects);
                        busInfoListView.setAdapter(busListAdapter);

                        busInfoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //Do something
                                //Ex: display msg with product id get from view.getTag
                                Toast.makeText(getApplicationContext(), "Clicked product id =" + view.getTag(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onFailure(Call<BusList> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    public void showTimePicker(){
        calendar = Calendar.getInstance();
        currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        currentMinute = calendar.get(Calendar.MINUTE);

        timePickerDialog = new TimePickerDialog(BusDetails.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                if (hourOfDay >= 12) {
                    amPm = "PM";
                } else {
                    amPm = "AM";
                }
                chooseTime.setText(String.format("%02d:%02d", hourOfDay, minutes));
            }
        }, currentHour, currentMinute, false);

        timePickerDialog.show();
    }

}
