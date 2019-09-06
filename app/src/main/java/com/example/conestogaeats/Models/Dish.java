package com.example.conestogaeats.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Dish implements Parcelable {
    String id,RestaurantId,name,price,calories;
    ArrayList<Ingredient> ingredients;


    public Dish(){}
    protected Dish(Parcel in) {
        id = in.readString();
        RestaurantId = in.readString();
        name = in.readString();
        price = in.readString();
        calories = in.readString();
        ingredients = in.createTypedArrayList(Ingredient.CREATOR);
    }

    public static final Creator<Dish> CREATOR = new Creator<Dish>() {
        @Override
        public Dish createFromParcel(Parcel in) {
            return new Dish(in);
        }

        @Override
        public Dish[] newArray(int size) {
            return new Dish[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return RestaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        RestaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(RestaurantId);
        dest.writeString(name);
        dest.writeString(price);
        dest.writeString(calories);
        dest.writeTypedList(ingredients);
    }
}
