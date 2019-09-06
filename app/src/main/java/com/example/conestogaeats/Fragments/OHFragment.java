package com.example.conestogaeats.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.conestogaeats.AdapterClasses.OrderAdapter;
import com.example.conestogaeats.DBHelperClasses.MainDBHelperClass;
import com.example.conestogaeats.HomeActivity;
import com.example.conestogaeats.Models.Order;
import com.example.conestogaeats.R;

import java.util.ArrayList;

public class OHFragment extends Fragment {
    RecyclerView mRvOH;
    ArrayList<Order> orders;
    OrderAdapter orderAdapter;
    LinearLayoutManager linearLayoutManager;
    MainDBHelperClass mainDBHelperClass;
    SharedPreferences sp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_history_fragment_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomeActivity homeActivity= (HomeActivity) getActivity();
        homeActivity.getSupportActionBar().setTitle("Order History");
        mRvOH=view.findViewById(R.id.rvOH);
        linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mainDBHelperClass=new MainDBHelperClass(getActivity());
        sp=getActivity().getSharedPreferences("CE", Context.MODE_PRIVATE);

        orders=mainDBHelperClass.getOrderdetailsByUserId(sp.getString("UserEmail",""));

        if(orders!=null&&orders.size()!=0)
        {
            orderAdapter=new OrderAdapter(getActivity(),orders);
            mRvOH.setAdapter(orderAdapter);
            mRvOH.setLayoutManager(linearLayoutManager);
        }
        else
        {
            Toast.makeText(getActivity(), "You have no order history.", Toast.LENGTH_SHORT).show();
        }
    }
}
