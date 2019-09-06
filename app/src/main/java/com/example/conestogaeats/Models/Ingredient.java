package com.example.conestogaeats.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Ingredient implements Parcelable {
    String id,Dishid,ingredient;

    public Ingredient(){}

    protected Ingredient(Parcel in) {
        id = in.readString();
        Dishid = in.readString();
        ingredient = in.readString();
    }

    public static final Creator<Ingredient> CREATOR = new Creator<Ingredient>() {
        @Override
        public Ingredient createFromParcel(Parcel in) {
            return new Ingredient(in);
        }

        @Override
        public Ingredient[] newArray(int size) {
            return new Ingredient[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDishid() {
        return Dishid;
    }

    public void setDishid(String dishid) {
        Dishid = dishid;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(Dishid);
        dest.writeString(ingredient);
    }
}
