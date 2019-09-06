package com.example.conestogaeats.DBHelperClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.example.conestogaeats.Models.Dish;
import com.example.conestogaeats.Models.Ingredient;
import com.example.conestogaeats.Models.Order;
import com.example.conestogaeats.Models.OrderItem;
import com.example.conestogaeats.Models.Restaurant;
import com.example.conestogaeats.Models.User;

import java.util.ArrayList;

public class MainDBHelperClass extends SQLiteOpenHelper {

    Context context;

    public static final String DATABASE_NAME = "ConestogaEats";
    public static final String TABLE_NAME = "Users";
    public static final String COLOUMN_USR_ID = "Id";
    public static final String COLOUMN_USR_FNAME = "FirstName";
    public static final String COLOUMN_USR_LNAME = "LastName";
    public static final String COLOUMN_USR_EMAIL_ID = "EmailId";
    public static final String COLOUMN_USR_MOBILE_NO = "MobileNo";
    public static final String COLOUMN_USR_ADDRESS = "Address";
    public static final String COLOUMN_USR_PASSWORD = "Password";

    // For Restaurant Table

    public static final String TABLE_NAME_RES = "Restaurants";
    public static final String COLOUMN_RES_ID = "Id";
    public static final String COLOUMN_RES_IMAGE = "Image";
    public static final String COLOUMN_RES_NAME = "Name";
    public static final String COLOUMN_RES_ADDRESS = "Address";
    public static final String COLOUMN_RES_LAT = "Latitude";
    public static final String COLOUMN_RES_LONG = "Longitude";
    public static final String COLOUMN_RES_AVGRATINGS = "AvgRatings";

    //For Dishes Table

    public static final String TABLE_NAME_DIS = "Dishes";
    public static final String COLOUMN_DIS_ID = "Id";
    public static final String COLOUMN_DIS_RES_ID = "RestaurantId";
    public static final String COLOUMN_DIS_NAME = "Name";
    public static final String COLOUMN_DIS_PRICE = "Price";
    public static final String COLOUMN_DIS_CALORIES = "Calories";


    //For Ingredients Table

    public static final String TABLE_NAME_ING = "Ingredients";
    public static final String COLOUMN_ING_ID = "Id";
    public static final String COLOUMN_ING_DIS_ID = "DishId";
    public static final String COLOUMN_ING_INGREDIENTS = "Ingredients";

    //For Orders Table

    public static final String TABLE_NAME_ODR = "Orders";
    public static final String COLOUMN_ODR_ID = "Id";
    public static final String COLOUMN_ODR_USR_ID = "UserId";
    public static final String COLOUMN_ODR_ORDERDATE = "OrderDate";
    public static final String COLOUMN_ODR_PAY_MODE = "PaymentMode";

    //For OrdersItems Table

    public static final String TABLE_NAME_ODR_ITM = "OrdersItems";
    public static final String COLOUMN_ODR_ITM_ID = "Id";
    public static final String COLOUMN_ODR_ITM_ODR_ID = "OrderId";
    public static final String COLOUMN_ODR_ITM_DIS_ID = "DishId";
    public static final String COLOUMN_ODR_ITM_DIS_QTY = "Quantity";


    public MainDBHelperClass(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(" + COLOUMN_USR_ID + " Integer primary key autoincrement," + COLOUMN_USR_FNAME + " text," + COLOUMN_USR_LNAME + " text," + COLOUMN_USR_EMAIL_ID + " text," + COLOUMN_USR_MOBILE_NO + " text," + COLOUMN_USR_ADDRESS + " text," + COLOUMN_USR_PASSWORD + " text);");
        db.execSQL("create table " + TABLE_NAME_RES + "(" + COLOUMN_RES_ID + " Integer primary key autoincrement," + COLOUMN_RES_IMAGE + " int," + COLOUMN_RES_NAME + " text," + COLOUMN_RES_ADDRESS + " text," + COLOUMN_RES_LAT + " text," + COLOUMN_RES_LONG + " text," + COLOUMN_RES_AVGRATINGS + " text);");
        db.execSQL("create table " + TABLE_NAME_DIS + "(" + COLOUMN_DIS_ID + " Integer primary key autoincrement," + COLOUMN_DIS_RES_ID + " text," + COLOUMN_DIS_NAME + " text," + COLOUMN_DIS_PRICE + " text," + COLOUMN_DIS_CALORIES + " text);");
        db.execSQL("create table " + TABLE_NAME_ING + "(" + COLOUMN_ING_ID + " Integer primary key autoincrement," + COLOUMN_ING_DIS_ID + " text," + COLOUMN_ING_INGREDIENTS + " text);");
        db.execSQL("create table " + TABLE_NAME_ODR + "(" + COLOUMN_ODR_ID + " Integer primary key autoincrement," + COLOUMN_ODR_USR_ID + " text," + COLOUMN_ODR_ORDERDATE + " text," + COLOUMN_ODR_PAY_MODE + " text);");
        db.execSQL("create table " + TABLE_NAME_ODR_ITM + "(" + COLOUMN_ODR_ITM_ID + " Integer primary key autoincrement," + COLOUMN_ODR_ITM_ODR_ID + " text," + COLOUMN_ODR_ITM_DIS_ID + " text," + COLOUMN_ODR_ITM_DIS_QTY + " text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public boolean IsEmailIdExist(String email) {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLOUMN_USR_EMAIL_ID + "= '" + email + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean AddUser(User user) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLOUMN_USR_FNAME, user.getFname());
        contentValues.put(COLOUMN_USR_LNAME, user.getLname());
        contentValues.put(COLOUMN_USR_EMAIL_ID, user.getEmailId());
        contentValues.put(COLOUMN_USR_MOBILE_NO, user.getMobileNo());
        contentValues.put(COLOUMN_USR_ADDRESS, user.getAddress());
        contentValues.put(COLOUMN_USR_PASSWORD, user.getPassword());

        long id = database.insert(TABLE_NAME, null, contentValues);

        if (id != -1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean logIn(String email, String password) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLOUMN_USR_EMAIL_ID + " = '" + email + "' AND " + COLOUMN_USR_PASSWORD + " = '" + password + "';", null);
        if (cursor != null && cursor.getCount() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addData(Restaurant restaurant) {
        SQLiteDatabase resWritableDB = getWritableDatabase();
        ContentValues contentValuesRes = new ContentValues();
        contentValuesRes.put(COLOUMN_RES_IMAGE, restaurant.getImage());
        contentValuesRes.put(COLOUMN_RES_NAME, restaurant.getName());
        contentValuesRes.put(COLOUMN_RES_ADDRESS, restaurant.getAddress());
        contentValuesRes.put(COLOUMN_RES_AVGRATINGS, restaurant.getAvgRatings());
        contentValuesRes.put(COLOUMN_RES_LAT, restaurant.getLatitude());
        contentValuesRes.put(COLOUMN_RES_LONG, restaurant.getLogitude());

        long idRes = resWritableDB.insert(TABLE_NAME_RES, null, contentValuesRes);
        if (idRes != -1) {
            SQLiteDatabase resReadableDB = getReadableDatabase();
            Cursor cursorRes = resReadableDB.rawQuery("select * from " + TABLE_NAME_RES + " order by " + COLOUMN_RES_ID + " DESC limit 1;", null);

            if (cursorRes != null) {
                cursorRes.moveToPosition(0);
                for (int j = 0; j < restaurant.getDishes().size(); j++) {
                    SQLiteDatabase databaseDishesAdd = getWritableDatabase();

                    ContentValues contentValuesDish = new ContentValues();
                    contentValuesDish.put(COLOUMN_DIS_RES_ID, cursorRes.getInt(0));
                    contentValuesDish.put(COLOUMN_DIS_NAME, restaurant.getDishes().get(j).getName());
                    contentValuesDish.put(COLOUMN_DIS_CALORIES, restaurant.getDishes().get(j).getCalories());
                    contentValuesDish.put(COLOUMN_DIS_PRICE, restaurant.getDishes().get(j).getPrice());
                    long idDish = databaseDishesAdd.insert(TABLE_NAME_DIS, null, contentValuesDish);

                    if ((idDish != -1)) {
                        SQLiteDatabase disReadableDB = getReadableDatabase();
                        Cursor cursorDis = disReadableDB.rawQuery("select * from " + TABLE_NAME_DIS + " order by " + COLOUMN_DIS_ID + " DESC limit 1;", null);

                        if (cursorDis != null) {
                            cursorDis.moveToPosition(0);
                            for (int k = 0; k < restaurant.getDishes().get(j).getIngredients().size(); k++) {
                                SQLiteDatabase databaseIngAdd = getWritableDatabase();

                                ContentValues contentValuesIng = new ContentValues();
                                contentValuesIng.put(COLOUMN_ING_DIS_ID, cursorDis.getInt(0));
                                contentValuesIng.put(COLOUMN_ING_INGREDIENTS, restaurant.getDishes().get(j).getIngredients().get(k).getIngredient());
                                long idIng = databaseIngAdd.insert(TABLE_NAME_ING, null, contentValuesIng);
                            }
                        }

                    }
                }

            }
        }
        return true;
    }

    public Boolean IsProductsExists() {
        //checking if product exists or not
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from " + TABLE_NAME_RES, null);
        cursor.moveToFirst();
        if (cursor.getCount() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Restaurant> getRestaurants() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME_RES + ";", null);
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                Restaurant restaurant = new Restaurant();
                ArrayList<Dish> dishes = new ArrayList<>();

                restaurant.setId(cursor.getString(0));
                restaurant.setImage(cursor.getInt(1));
                restaurant.setName(cursor.getString(2));
                restaurant.setAddress(cursor.getString(3));
                restaurant.setLatitude(cursor.getString(4));
                restaurant.setLogitude(cursor.getString(5));
                restaurant.setAvgRatings(cursor.getString(6));

                SQLiteDatabase databaseDish = getReadableDatabase();
                Cursor cursorDish = databaseDish.rawQuery("SELECT * FROM " + TABLE_NAME_DIS + " WHERE " + COLOUMN_DIS_RES_ID + " ='" + restaurant.getId() + "';", null);
                if (cursorDish != null) {
                    cursorDish.moveToFirst();
                    do {
                        Dish dish = new Dish();
                        ArrayList<Ingredient> ingredients = new ArrayList<>();
                        dish.setId(cursorDish.getString(0));
                        dish.setRestaurantId(cursorDish.getString(1));
                        dish.setName(cursorDish.getString(2));
                        dish.setPrice(cursorDish.getString(3));
                        dish.setCalories(cursorDish.getString(4));

                        SQLiteDatabase databaseIng = getReadableDatabase();
                        Cursor cursorIng = databaseIng.rawQuery("SELECT * FROM " + TABLE_NAME_ING + " WHERE " + COLOUMN_ING_DIS_ID + " ='" + dish.getId() + "';", null);
                        if (cursorIng != null) {
                            Ingredient ingredient = new Ingredient();
                            cursorIng.moveToFirst();
                            do {

                                ingredient.setId(cursorIng.getString(0));
                                ingredient.setDishid(cursorIng.getString(1));
                                ingredient.setIngredient(cursorIng.getString(2));

                                ingredients.add(ingredient);
                                dish.setIngredients(ingredients);
                            } while (cursorIng.moveToNext());
                        }
                        dishes.add(dish);
                    } while (cursorDish.moveToNext());
                }
                restaurant.setDishes(dishes);
                restaurants.add(restaurant);
            } while (cursor.moveToNext());
        }

        return restaurants;
    }

    public ArrayList<Dish> getDishes(String id) {
        ArrayList<Dish> dishes = new ArrayList<>();
        SQLiteDatabase databaseDish = getReadableDatabase();
        Cursor cursorDish = databaseDish.rawQuery("SELECT * FROM " + TABLE_NAME_DIS + " WHERE " + COLOUMN_DIS_RES_ID + " ='" + id + "';", null);
        if (cursorDish != null) {
            cursorDish.moveToFirst();
            do {
                Dish dish = new Dish();
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                dish.setId(cursorDish.getString(0));
                dish.setRestaurantId(cursorDish.getString(1));
                dish.setName(cursorDish.getString(2));
                dish.setPrice(cursorDish.getString(3));
                dish.setCalories(cursorDish.getString(4));

                SQLiteDatabase databaseIng = getReadableDatabase();
                Cursor cursorIng = databaseIng.rawQuery("SELECT * FROM " + TABLE_NAME_ING + " WHERE " + COLOUMN_ING_DIS_ID + " ='" + dish.getId() + "';", null);
                if (cursorIng != null) {
                    cursorIng.moveToFirst();
                    do {
                        Ingredient ingredient = new Ingredient();
                        ingredient.setId(cursorIng.getString(0));
                        ingredient.setDishid(cursorIng.getString(1));
                        ingredient.setIngredient(cursorIng.getString(2));

                        ingredients.add(ingredient);
                        dish.setIngredients(ingredients);
                    } while (cursorIng.moveToNext());
                    dishes.add(dish);
                }
            } while (cursorDish.moveToNext());
        }
        return dishes;
    }

    public Dish getDishById(String id) {
        Dish dish = new Dish();
        SQLiteDatabase databaseDish = getReadableDatabase();
        Cursor cursorDish = databaseDish.rawQuery("SELECT * FROM " + TABLE_NAME_DIS + " WHERE " + COLOUMN_DIS_ID + " ='" + id + "';", null);
        if (cursorDish != null) {
            cursorDish.moveToFirst();
            do {

                ArrayList<Ingredient> ingredients = new ArrayList<>();
                dish.setId(cursorDish.getString(0));
                dish.setRestaurantId(cursorDish.getString(1));
                dish.setName(cursorDish.getString(2));
                dish.setPrice(cursorDish.getString(3));
                dish.setCalories(cursorDish.getString(4));

                SQLiteDatabase databaseIng = getReadableDatabase();
                Cursor cursorIng = databaseIng.rawQuery("SELECT * FROM " + TABLE_NAME_ING + " WHERE " + COLOUMN_ING_DIS_ID + " ='" + dish.getId() + "';", null);
                if (cursorIng != null) {
                    cursorIng.moveToFirst();
                    do {
                        Ingredient ingredient = new Ingredient();
                        ingredient.setId(cursorIng.getString(0));
                        ingredient.setDishid(cursorIng.getString(1));
                        ingredient.setIngredient(cursorIng.getString(2));

                        ingredients.add(ingredient);
                        dish.setIngredients(ingredients);
                    } while (cursorIng.moveToNext());
                }
            } while (cursorDish.moveToNext());
        }
        return dish;
    }

    public boolean bookOrder(Order order) {

        SQLiteDatabase databaseOdr = getWritableDatabase();
        ContentValues contentValuesOdr = new ContentValues();
        contentValuesOdr.put(COLOUMN_ODR_USR_ID, order.getUserId());
        contentValuesOdr.put(COLOUMN_ODR_ORDERDATE, order.getOrderdate());
        contentValuesOdr.put(COLOUMN_ODR_PAY_MODE, "Cash");

        long idOdr = databaseOdr.insert(TABLE_NAME_ODR, null, contentValuesOdr);

        if (idOdr != -1) {
            SQLiteDatabase OdrReadableDB = getReadableDatabase();
            Cursor cursorOdr = OdrReadableDB.rawQuery("select * from " + TABLE_NAME_ODR + " order by " + COLOUMN_ODR_ID + " DESC limit 1;", null);
            if (cursorOdr != null) {
                cursorOdr.moveToFirst();

                for (int i = 0; i < order.getOrderItems().size(); i++) {
                    OrderItem orderItem = new OrderItem();
                    orderItem = order.getOrderItems().get(i);

                    SQLiteDatabase databaseOI = getWritableDatabase();

                    ContentValues contentValuesOI = new ContentValues();

                    contentValuesOI.put(COLOUMN_ODR_ITM_ODR_ID, cursorOdr.getInt(0));
                    contentValuesOI.put(COLOUMN_ODR_ITM_DIS_ID, orderItem.getDishId());
                    contentValuesOI.put(COLOUMN_ODR_ITM_DIS_QTY, orderItem.getQty());

                    databaseOI.insert(TABLE_NAME_ODR_ITM, null, contentValuesOI);
                }
            }
        }
        return true;
    }

    public ArrayList<Order> getOrderdetailsByUserId(String userId) {
        ArrayList<Order> orders = new ArrayList<>();
        SQLiteDatabase databaseOdr = getReadableDatabase();
        Cursor cursorOdr = databaseOdr.rawQuery("SELECT * FROM " + TABLE_NAME_ODR + " WHERE " + COLOUMN_ODR_USR_ID + " ='" + userId + "';", null);

        if (cursorOdr != null && cursorOdr.getCount() != 0) {
            cursorOdr.moveToFirst();
            do {
                Order order = new Order();
                ArrayList<OrderItem> orderItems = new ArrayList<>();
                order.setOrderdate(cursorOdr.getString(2));

                SQLiteDatabase databaseOI = getReadableDatabase();
                Cursor cursorOI = databaseOI.rawQuery("SELECT * FROM " + TABLE_NAME_ODR_ITM + " WHERE " + COLOUMN_ODR_ITM_ODR_ID + " ='" + cursorOdr.getString(0) + "';", null);
                if (cursorOI != null) {
                    cursorOI.moveToFirst();
                    do {


                        OrderItem orderItem = new OrderItem();
                        orderItem.setDishId(cursorOI.getString(2));
                        orderItem.setQty(cursorOI.getString(3));

                        orderItems.add(orderItem);
                    } while (cursorOI.moveToNext());
                    order.setOrderItems(orderItems);
                }

                orders.add(order);
            } while (cursorOdr.moveToNext());
        }
        return orders;
    }

    public User getUserByEmail(String email) {
        User user = new User();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLOUMN_USR_EMAIL_ID + " ='" + email + "';", null);
        if (cursor != null) {
            cursor.moveToFirst();
            user.setFname(cursor.getString(1));
            user.setLname(cursor.getString(2));
            user.setEmailId(cursor.getString(3));
            user.setMobileNo(cursor.getString(4));
            user.setAddress(cursor.getString(5));
        }
        return user;
    }
}