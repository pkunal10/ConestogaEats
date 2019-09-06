package com.example.conestogaeats.Models;

import com.example.conestogaeats.DBHelperClasses.MainDBHelperClass;
import com.example.conestogaeats.R;

import java.util.ArrayList;

public class AddData {
    ArrayList<Restaurant> restaurants=new ArrayList<>();

    public ArrayList<Restaurant> addProducts(){

        Restaurant restaurantSaf=new Restaurant();
        ArrayList<Dish> dishesSaf=new ArrayList<>();
        restaurantSaf.setImage(R.drawable.safron);
        restaurantSaf.setName("Saffron");
        restaurantSaf.setAddress("605 Hespeler Rd, Cambridge, ON N1R 6J3");
        restaurantSaf.setAvgRatings("4.2");
        restaurantSaf.setLatitude("43.404508");
        restaurantSaf.setLogitude("-80.326854");

        Dish dishSaf1=new Dish();
        dishSaf1.setName("Vegetable Samosa (2 Pc.)");
        dishSaf1.setPrice("3.00");
        dishSaf1.setCalories("262");
        ArrayList<Ingredient> ingredientsSafDish1=new ArrayList<>();
        Ingredient ingredient1SafD1=new Ingredient();
        ingredient1SafD1.setIngredient("Maida");
        Ingredient ingredient2SafD1=new Ingredient();
        ingredient2SafD1.setIngredient("Potato");
        Ingredient ingredient3SafD1=new Ingredient();
        ingredient3SafD1.setIngredient("Peas");
        ingredientsSafDish1.add(ingredient1SafD1);
        ingredientsSafDish1.add(ingredient2SafD1);
        ingredientsSafDish1.add(ingredient3SafD1);
        dishSaf1.setIngredients(ingredientsSafDish1);

        Dish dishSaf2=new Dish();
        dishSaf2.setName("Butter Chicken-White Meat");
        dishSaf2.setPrice("15.99");
        dishSaf2.setCalories("438");
        ArrayList<Ingredient> ingredientsSafDish2=new ArrayList<>();
        Ingredient ingredient1SafD2=new Ingredient();
        ingredient1SafD2.setIngredient("Chickenn");
        Ingredient ingredient2SafD2=new Ingredient();
        ingredient2SafD2.setIngredient("Butter");
        Ingredient ingredient3SafD2=new Ingredient();
        ingredient3SafD2.setIngredient("Onion");
        ingredientsSafDish2.add(ingredient1SafD2);
        ingredientsSafDish2.add(ingredient2SafD2);
        ingredientsSafDish2.add(ingredient3SafD2);
        dishSaf2.setIngredients(ingredientsSafDish2);

        dishesSaf.add(dishSaf1);
        dishesSaf.add(dishSaf2);
        restaurantSaf.setDishes(dishesSaf);


        Restaurant restaurantRoy=new Restaurant();
        ArrayList<Dish> dishesRoy=new ArrayList<>();
        restaurantRoy.setImage(R.drawable.royalindian);
        restaurantRoy.setName("Royal Indian Buffet");
        restaurantRoy.setAddress("525 Hespeler Rd #4, Cambridge, ON N1R 6J2");
        restaurantRoy.setAvgRatings("3.0");
        restaurantRoy.setLatitude("43.401200");
        restaurantRoy.setLogitude("-80.325190");

        Dish dishRoy1=new Dish();
        dishRoy1.setName("Dal Makhani");
        dishRoy1.setPrice("10.00");
        dishRoy1.setCalories("427");
        ArrayList<Ingredient> ingredientsRoyDish1=new ArrayList<>();
        Ingredient ingredient1RoyD1=new Ingredient();
        ingredient1RoyD1.setIngredient("Rajma");
        Ingredient ingredient2RoyD1=new Ingredient();
        ingredient2RoyD1.setIngredient("Urad dal");
        Ingredient ingredient3RoyD1=new Ingredient();
        ingredient3RoyD1.setIngredient("Onion");
        ingredientsRoyDish1.add(ingredient1RoyD1);
        ingredientsRoyDish1.add(ingredient2RoyD1);
        ingredientsRoyDish1.add(ingredient3RoyD1);
        dishRoy1.setIngredients(ingredientsRoyDish1);

        Dish dishRoy2=new Dish();
        dishRoy2.setName("Fried Onion");
        dishRoy2.setPrice("5.99");
        dishRoy2.setCalories("258");
        ArrayList<Ingredient> ingredientsRoyDish2=new ArrayList<>();
        Ingredient ingredient1RoyD2=new Ingredient();
        ingredient1RoyD2.setIngredient("Onion");
        Ingredient ingredient2RoyD2=new Ingredient();
        ingredient2RoyD2.setIngredient("Spicies");
        Ingredient ingredient3RoyD2=new Ingredient();
        ingredient3RoyD2.setIngredient("Flour");
        ingredientsRoyDish2.add(ingredient1RoyD2);
        ingredientsRoyDish2.add(ingredient2RoyD2);
        ingredientsRoyDish2.add(ingredient3RoyD2);
        dishRoy2.setIngredients(ingredientsRoyDish2);

        dishesRoy.add(dishRoy1);
        dishesRoy.add(dishRoy2);
        restaurantRoy.setDishes(dishesRoy);

        restaurants.add(restaurantSaf);
        restaurants.add(restaurantRoy);

        return restaurants;

    }
}
