package com.example.mobilebusticketingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.mobilebusticketingsystem.Adapters.TravelListAdapter;
import com.example.mobilebusticketingsystem.ApiManager.ApiConnector;
import com.example.mobilebusticketingsystem.ApiManager.IApiService;
import com.example.mobilebusticketingsystem.Model.Travel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    IApiService apiService;
    TextView tdate;
    TextView month_time;
    TextView greeting;
    ImageButton paymentDetails;
    ImageButton busSearch;
    ImageButton historyDetailsGetButton;
    String userId = "5d9064b26511891fbcdc4928";

    ListView travelHistoryListView;
    private TravelListAdapter adapter;
    private List<Travel> mTravelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //*******************Changeable Date and Time**************************************************************************
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                tdate = (TextView) findViewById(R.id.date);
                                month_time = (TextView) findViewById(R.id.monthAndTime);
                                greeting = (TextView) findViewById(R.id.greeting_id);
                                long date = System.currentTimeMillis();
                                //long month = System.currentTimeMillis();
                                SimpleDateFormat currentDate = new SimpleDateFormat("dd");
                                SimpleDateFormat monthWithTime = new SimpleDateFormat("MMM \nyyyy\nhh.mm:ss a");
                                String dateString = currentDate.format(date);
                                String date_timeString = monthWithTime.format(date);
                                tdate.setText(dateString);
                                month_time.setText(date_timeString);
                                greeting.setText(getTimeGreeting());
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();
        //*********************************History Button*****************************************************************
        historyDetailsGetButton = (ImageButton) findViewById(R.id.historyButton);
        historyDetailsGetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyDetailsIntent = new Intent(v.getContext(),History.class);
                startActivityForResult(historyDetailsIntent, 0);
            }
        });


        //*********************************Payment Details****************************************************************
        paymentDetails = (ImageButton) findViewById(R.id.paymentButton);
        paymentDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent paymentDetailsIntent= new Intent(v.getContext(),Balance.class);
                startActivityForResult(paymentDetailsIntent, 0);
            }
        });


        //*********************************bus search*********************************************************************

        busSearch = (ImageButton) findViewById(R.id.bus_search_button);
        busSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent busSerchIntent = new Intent(v.getContext(), BusDetails.class);
                startActivityForResult(busSerchIntent, 0);
            }
        });

        //*********************************Create list view*******************************************************************

        Retrofit retrofitClient = ApiConnector.getInstance();
        apiService = retrofitClient.create(IApiService.class);

        travelHistoryListView = (ListView) findViewById(R.id.listview);

        mTravelList = new ArrayList<Travel>();
        Call<List<Travel>> call = apiService.getTravelsDetailsById(userId);


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
                     //   Toast.makeText(getApplicationContext(), "Clicked product id =" + view.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Travel>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    public String getTimeGreeting(){

        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            return "Good Morning";
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            return "Good Afternoon";
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            return "Good Evening";
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            return "Good Night";
        }
        return "Hello";
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
