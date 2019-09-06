package com.example.conestogaeats.Fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.conestogaeats.AdapterClasses.RestaurantAdapter;
import com.example.conestogaeats.DBHelperClasses.MainDBHelperClass;
import com.example.conestogaeats.HomeActivity;
import com.example.conestogaeats.Models.Restaurant;
import com.example.conestogaeats.R;

import java.util.ArrayList;

public class RestaurantFragment extends Fragment {
    RecyclerView mRvRestaurant;
    ArrayList<Restaurant> mRestaurants;
    LinearLayoutManager linearLayoutManager;
    RestaurantAdapter restaurantAdapter;
    MainDBHelperClass mainDBHelperClass;
    double Usrlongitude, UsrLatitude;
    private LocationManager locationManager;
    private String provider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.restaurant_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        HomeActivity homeActivity = (HomeActivity) getActivity();
        homeActivity.getSupportActionBar().setTitle("Restaurants");

        if (!IsInternetAvailable(getActivity())) {
            Toast.makeText(getActivity(), "No Internet Connection", Toast.LENGTH_LONG).show();
            startActivity(new Intent(Settings.ACTION_SETTINGS));
        }

//        locationManager = (LocationManager)
//                homeActivity.getSystemService(Context.LOCATION_SERVICE);
//
//        Criteria criteria = new Criteria();
//        provider = locationManager.getBestProvider(criteria, false);
//
//        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//// Initialize the location
//        if (location != null) {
//            UsrLatitude = location.getLatitude();
//            Usrlongitude = location.getLongitude();
//            // Toast.makeText(this, String.valueOf(lat), Toast.LENGTH_SHORT).show();
//            onLocationChanged(location);
//        }

        mRvRestaurant = view.findViewById(R.id.rvRestaurants);
        mRestaurants = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        mainDBHelperClass = new MainDBHelperClass(getActivity());

        new GetResdata().execute();
//        mRestaurants = mainDBHelperClass.getRestaurants();
//        restaurantAdapter = new RestaurantAdapter(getActivity(), mRestaurants, UsrLatitude, Usrlongitude);
//        mRvRestaurant.setAdapter(restaurantAdapter);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        mRvRestaurant.setLayoutManager(linearLayoutManager);

    }

    public void onLocationChanged(Location location) {
        UsrLatitude = location.getLatitude();
        Usrlongitude = location.getLongitude();
        //Toast.makeText(this, "hiiiiii", Toast.LENGTH_SHORT).show();
    }

    public void onStatusChanged(String provider,
                                int status, Bundle extras) {

    }

    public void onProviderEnabled(String provider) {

    }

    public void onProviderDisabled(String provider) {

    }

    public class GetResdata extends AsyncTask<String, Void, String> {


        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setTitle("Fetching Restaurants....");
            progressDialog.setMessage("Please Wait....");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            locationManager = (LocationManager)
                    getActivity().getSystemService(Context.LOCATION_SERVICE);

            Criteria criteria = new Criteria();
            provider = locationManager.getBestProvider(criteria, false);

            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            // Initialize the location
            if (location != null) {
                UsrLatitude = location.getLatitude();
                Usrlongitude = location.getLongitude();
                // Toast.makeText(this, String.valueOf(lat), Toast.LENGTH_SHORT).show();
                onLocationChanged(location);
            }
            mRestaurants = mainDBHelperClass.getRestaurants();

            if (mRestaurants != null) {
                return "success";
            } else {
                return "fail";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            if (s.equalsIgnoreCase("success")) {
                restaurantAdapter = new RestaurantAdapter(getActivity(), mRestaurants, UsrLatitude, Usrlongitude);
                mRvRestaurant.setAdapter(restaurantAdapter);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                mRvRestaurant.setLayoutManager(linearLayoutManager);
            } else {
                Toast.makeText(getActivity(), "No Restaurants.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean IsInternetAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkinfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkinfo != null && activeNetworkinfo.isConnected();
    }
}



