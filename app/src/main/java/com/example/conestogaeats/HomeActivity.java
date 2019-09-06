package com.example.conestogaeats;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conestogaeats.DBHelperClasses.MainDBHelperClass;
import com.example.conestogaeats.Fragments.HomeFragment;
import com.example.conestogaeats.Fragments.OHFragment;
import com.example.conestogaeats.Fragments.ProfileFragment;
import com.example.conestogaeats.Fragments.RestaurantFragment;
import com.example.conestogaeats.Models.AddData;
import com.example.conestogaeats.Models.Restaurant;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private LocationManager locationManager;
    private String provider;
    private TextView mTextMessage;
    MainDBHelperClass mainDBHelperClass;
    AddData addData;
    ArrayList<Restaurant> restaurants;
    SharedPreferences sp;
    double lat, lon;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();
                    return true;
                case R.id.navigation_restaurant:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new RestaurantFragment()).commit();
                    return true;
                case R.id.navigation_OH:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new OHFragment()).commit();
                    return true;
                case R.id.navigation_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ProfileFragment()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();
        getSupportActionBar().setTitle("Conestoga Eats");
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        addData = new AddData();
        restaurants = new ArrayList<>();
        sp=getSharedPreferences("CE",MODE_PRIVATE);

        mainDBHelperClass = new MainDBHelperClass(HomeActivity.this);
        if (!mainDBHelperClass.IsProductsExists()) {
            restaurants = addData.addProducts();

            for (int i = 0; i < restaurants.size(); i++) {
                mainDBHelperClass.addData(restaurants.get(i));
            }
           // Toast.makeText(this, "Data Added.", Toast.LENGTH_SHORT).show();
        }


        locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
//        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1000, this);
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
// Initialize the location
        if (location != null) {
            lat = location.getLatitude();
            lon = location.getLongitude();
           // Toast.makeText(this, String.valueOf(lat), Toast.LENGTH_SHORT).show();
            onLocationChanged(location);
        }



    }

//    private void marshmallowGPSPremissionCheck() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
//                && checkSelfPermission(
//                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                &&checkSelfPermission(
//                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            requestPermissions(
//                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
//                            Manifest.permission.ACCESS_FINE_LOCATION},
//                    MY_PERMISSION_LOCATION);
//        } else {
//            //   gps functions.
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           String[] permissions, int[] grantResults) {
//        if (requestCode == MY_PERMISSION_LOCATION
//                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            //  gps functionality
//        }
//    }

    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lon = location.getLongitude();
        //Toast.makeText(this, "hiiiiii", Toast.LENGTH_SHORT).show();
    }

    public void onStatusChanged(String provider,
                                int status, Bundle extras) {

    }

    public void onProviderEnabled(String provider) {

    }

    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cart_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.menu_cart)
        {
            startActivity(new Intent(HomeActivity.this,CartActivity.class));
        }else if(item.getItemId()==R.id.menu_log_out)
        {
            SharedPreferences.Editor editor=sp.edit();
            editor.remove("UserEmail");
            editor.commit();
            startActivity(new Intent(HomeActivity.this,LoginSignupActivity.class));
        }
        return true;
    }
}
