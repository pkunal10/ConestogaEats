package com.example.conestogaeats;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.conestogaeats.AdapterClasses.DishAdapter;
import com.example.conestogaeats.DBHelperClasses.MainDBHelperClass;
import com.example.conestogaeats.Models.Dish;
import com.example.conestogaeats.Models.Restaurant;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    Restaurant mSelectedRes;
    RecyclerView mRvDish;
    ArrayList<Dish> dishList;
    LinearLayoutManager linearLayoutManager;
    DishAdapter dishAdapter;
    MainDBHelperClass mainDBHelperClass;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mainDBHelperClass=new MainDBHelperClass(this);
        mSelectedRes=new Restaurant();
        mSelectedRes=getIntent().getExtras().getParcelable("selectedRes");
        getSupportActionBar().setTitle(mSelectedRes.getName());
        mRvDish=findViewById(R.id.rvMenu);
        dishList=new ArrayList<>();
        linearLayoutManager=new LinearLayoutManager(this);
        sp=getSharedPreferences("CE",MODE_PRIVATE);

        dishList=mainDBHelperClass.getDishes(mSelectedRes.getId());

        dishAdapter=new DishAdapter(this,dishList);

        mRvDish.setLayoutManager(new GridLayoutManager(this,2));
        mRvDish.setAdapter(dishAdapter);

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
            startActivity(new Intent(MenuActivity.this,CartActivity.class));
        }
        else if(item.getItemId()==R.id.menu_log_out)
        {
            SharedPreferences.Editor editor=sp.edit();
            editor.remove("UserEmail");
            editor.commit();
            startActivity(new Intent(MenuActivity.this,LoginSignupActivity.class));
        }
        return true;
    }
}
