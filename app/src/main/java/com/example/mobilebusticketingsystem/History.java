package com.example.mobilebusticketingsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mobilebusticketingsystem.Adapters.TravelListAdapter;
import com.example.mobilebusticketingsystem.ApiManager.ApiConnector;
import com.example.mobilebusticketingsystem.ApiManager.IApiService;
import com.example.mobilebusticketingsystem.Model.Travel;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class History extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    IApiService apiService;

    ListView travelHistoryListView;
    private TravelListAdapter adapter;
    private List<Travel> mTravelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_details);



        //*********************************Create list view*******************************************************************

        Retrofit retrofitClient = ApiConnector.getInstance();
        apiService = retrofitClient.create(IApiService.class);

        travelHistoryListView = (ListView) findViewById(R.id.listview);

        mTravelList = new ArrayList<Travel>();
        Call<List<Travel>> call = apiService.getTravelsDetailsById(ApiConnector.ID);


        call.enqueue(new Callback<List<Travel>>() {
            @Override
            public void onResponse(Call<List<Travel>> call, Response<List<Travel>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Travel> travelHistory = response.body();

                if(travelHistory == null){
                    Toast.makeText(getApplicationContext(),"Result is empty", Toast.LENGTH_SHORT).show();
                }else{
                    for(Travel travel :travelHistory){
                        mTravelList.add(new Travel(travel.get_id(),travel.getPassengerId(),travel.getBusId(),travel.getDate(),travel.getStartingPoint(),travel.getEndingPoint(),travel.getFare(),travel.getTimeDuration()));
                    }
                }

                adapter = new TravelListAdapter(getApplicationContext(), mTravelList);
                travelHistoryListView.setAdapter(adapter);

                travelHistoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //Do something
                        //Ex: display msg with product id get from view.getTag
                      //  Toast.makeText(getApplicationContext(), "Clicked product id =" + mTravelList.get(position).get_id()+":"+mTravelList.get(position).getFare(), Toast.LENGTH_SHORT).show();
                        AlertDialog.Builder builder = new AlertDialog.Builder(History.this);

                        LayoutInflater inflater = getLayoutInflater();
                        View dialogView = inflater.inflate(R.layout.travel_history_showing_page, null);
                        builder.setView(dialogView);

                        TextView busId = (TextView) dialogView.findViewById(R.id.busId);
                        TextView pickup = (TextView) dialogView.findViewById(R.id.pickup);
                        TextView destination = (TextView) dialogView.findViewById(R.id.destination);
                        TextView timeTaken = (TextView) dialogView.findViewById(R.id.timeTacken);
                        TextView fee = (TextView) dialogView.findViewById(R.id.fee);
                        TextView passenger = (TextView) dialogView.findViewById(R.id.passenger);
                        final AlertDialog dialog = builder.create();



                        busId.setText(mTravelList.get(position).get_id());
                        pickup.setText(mTravelList.get(position).getStartingPoint());
                        destination.setText(mTravelList.get(position).getEndingPoint());
                        timeTaken.setText(mTravelList.get(position).getTimeDuration());
                        fee.setText(String.valueOf(mTravelList.get(position).getFare()));
                        passenger.setText(ApiConnector.EMAIL);
                        // Set the custom layout as alert dialog view
/*                        Button ok = (Button) findViewById(R.id.ok);
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent message = new Intent(History.this, History.class);
                                startActivity(message);
                            }
                        });*/
                        dialog.show();

                    }
                });
            }

            @Override
            public void onFailure(Call<List<Travel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }



    @Override
    public void onBackPressed() {
        Intent message = new Intent(History.this, Home.class);
        startActivity(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
