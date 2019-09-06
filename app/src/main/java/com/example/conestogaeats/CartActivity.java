package com.example.conestogaeats;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.conestogaeats.AdapterClasses.CartAdapter;
import com.example.conestogaeats.AdapterClasses.CartViewHolder;
import com.example.conestogaeats.DBHelperClasses.MainDBHelperClass;
import com.example.conestogaeats.Models.Dish;
import com.example.conestogaeats.Models.Order;
import com.example.conestogaeats.Models.OrderItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class CartActivity extends AppCompatActivity {

    SharedPreferences sp;
    Set<String> productSet = new HashSet<>();
    List<String> productList;
    ArrayList<Dish> mDishes;
    MainDBHelperClass mainDBHelperClass;
    RecyclerView mCartRv;
    LinearLayoutManager linearLayoutManager;
    CartAdapter cartAdapter;
    TextView mTvCartTotal;
    LinearLayout pnlChkBtn;
    Button btnChkOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        sp = getSharedPreferences("CE", MODE_PRIVATE);
        mainDBHelperClass = new MainDBHelperClass(this);
        mCartRv = findViewById(R.id.rvCart);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mTvCartTotal = findViewById(R.id.tvCartTotal);
        pnlChkBtn = findViewById(R.id.chkBtnPnl);
        btnChkOut = findViewById(R.id.btnCartCheckout);


        CheckEmptyCart();

        btnChkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkOut();
            }
        });
    }

    public void CheckEmptyCart() {

        mDishes = new ArrayList<>();
        productList = new ArrayList<>();
        productSet = sp.getStringSet("ProductSet", null);
        if (productSet != null)
            productList = new ArrayList<>(productSet);
        if (!sp.contains("ProductSet")) {
            Toast.makeText(this, "Cart Is Empty", Toast.LENGTH_LONG).show();
            pnlChkBtn.setVisibility(View.GONE);

        } else if (productList.size() == 0) {
            Toast.makeText(this, "Cart Is Empty", Toast.LENGTH_LONG).show();
            pnlChkBtn.setVisibility(View.GONE);

        } else {
            for (int i = 0; i < productList.size(); i++) {
                mDishes.add(mainDBHelperClass.getDishById(productList.get(i)));
            }
            cartAdapter = new CartAdapter(this, mDishes);
            mCartRv.setAdapter(cartAdapter);
            mCartRv.setLayoutManager(linearLayoutManager);
        }
        calculateGrandTotal();
    }

    public void calculateGrandTotal() {
        Float grandTotal = 0.0f;

        for (int i = 0; i < mCartRv.getChildCount(); i++) {
            //CartViewHolder cartViewHolder= (CartViewHolder) mCartRv.findViewHolderForAdapterPosition(i);
            View view = mCartRv.getLayoutManager().findViewByPosition(i);
            TextView textView = view.findViewById(R.id.tvCartTotalPriceProduct);
            Float price = Float.parseFloat(textView.getText().toString().substring(8));
            grandTotal += price;
        }
        mTvCartTotal.setText("Grand Total: $" + grandTotal);
    }

    public void checkOut() {
        Order order = new Order();
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        order.setUserId(sp.getString("UserEmail", ""));
        order.setOrderdate(new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));

        for (int i = 0; i < mCartRv.getChildCount(); i++) {
            OrderItem orderItem = new OrderItem();
            View view = mCartRv.getLayoutManager().findViewByPosition(i);
            TextView idTv=view.findViewById(R.id.dishId);
            orderItem.setDishId(idTv.getText().toString());
            EditText etQty=view.findViewById(R.id.etCartQty);
            orderItem.setQty(etQty.getText().toString());
            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);
        mainDBHelperClass.bookOrder(order);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("ProductSet");
        editor.commit();
//        Toast.makeText(this, "Order has been booked.", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Order Confirmation");
        builder.setMessage("Your order has been placed and will be delivered on your registered address.(Payment mode:- Cash).").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(CartActivity.this,HomeActivity.class));
            }
        }).create().show();
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
            startActivity(new Intent(CartActivity.this,CartActivity.class));
        }
        else if(item.getItemId()==R.id.menu_log_out)
        {
            SharedPreferences.Editor editor=sp.edit();
            editor.remove("UserEmail");
            editor.commit();
            startActivity(new Intent(CartActivity.this,LoginSignupActivity.class));
        }
        return true;
    }
}
