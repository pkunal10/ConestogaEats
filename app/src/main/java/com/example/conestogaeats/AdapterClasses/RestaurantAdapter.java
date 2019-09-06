package com.example.conestogaeats.AdapterClasses;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.conestogaeats.MenuActivity;
import com.example.conestogaeats.Models.Restaurant;
import com.example.conestogaeats.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {

    Context context;
    ArrayList<Restaurant> restaurants;
    double UsrLat,UsrLong;

    public RestaurantAdapter(Context context, ArrayList<Restaurant> restaurants,double UsrLat,double UsrLong) {
        this.context = context;
        this.restaurants = restaurants;
        this.UsrLat=UsrLat;
        this.UsrLong=UsrLong;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.restaurant_item_layout, viewGroup, false);
        RestaurantViewHolder restaurantViewHolder = new RestaurantViewHolder(view);
        return restaurantViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder restaurantViewHolder, int i) {
        final Restaurant restaurant = restaurants.get(i);

        restaurantViewHolder.mResImage.setImageResource(restaurant.getImage());
        restaurantViewHolder.mTvName.setText(restaurant.getName());
        restaurantViewHolder.mTvRatings.setText(restaurant.getAvgRatings());


//        Toast.makeText(context, ""+restaurant.getDishes().size(), Toast.LENGTH_LONG).show();

        // restaurantViewHolder.mTvDistance.setText(getDistance(Double.parseDouble(restaurant.getLatitude()),Double.parseDouble(restaurant.getLogitude()),UsrLatitude,Usrlongitude)+"KM.");
//        restaurantViewHolder.mTvDistance.setText(UsrLatitude+"  hi   "+Usrlongitude);
//        restaurantViewHolder.mTvDistance.setText(getDistance(Double.parseDouble(restaurant.getLatitude()),Double.parseDouble(restaurant.getLogitude()),UsrLat,UsrLong)+"KM.");


        Double toLat=getLocationFromAddress(context,restaurant.getAddress()).getLatitude();
        Double toLon=getLocationFromAddress(context,restaurant.getAddress()).getLongitude();

        restaurantViewHolder.mTvDistance.setText(getDistance(toLat,toLon,UsrLat,UsrLong)+" KM");

        restaurantViewHolder.mResImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putParcelable("selectedRes",restaurant);
                context.startActivity(new Intent(context, MenuActivity.class).putExtras(bundle));
            }
        });

    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    private String getDistance(double latTo, double lonTo, double latFrom, double lonFrom) {
        Location loc1 = new Location("User");
        loc1.setLatitude(latFrom);
        loc1.setLongitude(lonFrom);
        Location loc2 = new Location("Res");
        loc2.setLatitude(latTo);
        loc2.setLongitude(lonTo);
        float distanceInMeters = loc1.distanceTo(loc2);
        float KM = distanceInMeters / 1000;
        String strKM = String.format("%.2f", KM);
        return strKM;
    }

    public Address getLocationFromAddress(Context context,String strAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        Address location=null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(strAddress, 5);
            if (address == null) {
                return null;
            }

            location = address.get(0);


        } catch (IOException ex) {

            ex.printStackTrace();
        }

        return location;
    }
}
