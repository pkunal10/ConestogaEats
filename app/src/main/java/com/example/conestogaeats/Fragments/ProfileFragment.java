package com.example.conestogaeats.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.conestogaeats.DBHelperClasses.MainDBHelperClass;
import com.example.conestogaeats.HomeActivity;
import com.example.conestogaeats.Models.User;
import com.example.conestogaeats.R;

public class ProfileFragment extends Fragment {
    TextView mNameTv, mEmailTv, mMobileTv, mAddressTv;
    MainDBHelperClass mainDBHelperClass;
    SharedPreferences sp;
    User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomeActivity homeActivity= (HomeActivity) getActivity();
        homeActivity.getSupportActionBar().setTitle("My Profile");
        mNameTv = view.findViewById(R.id.myProfileNameTv);
        mEmailTv = view.findViewById(R.id.myProfileEmailTv);
        mMobileTv = view.findViewById(R.id.myProfileMobileTv);
        mAddressTv = view.findViewById(R.id.myProfileAddressTv);
        sp = getActivity().getSharedPreferences("CE", Context.MODE_PRIVATE);
        mainDBHelperClass = new MainDBHelperClass(getActivity());
        user = new User();

        displayData();
    }

    public void displayData() {
        user = mainDBHelperClass.getUserByEmail(sp.getString("UserEmail", ""));
        mNameTv.setText(user.getFname()+" "+user.getLname());
        mEmailTv.setText(user.getEmailId());
        mMobileTv.setText(user.getMobileNo());
        mAddressTv.setText(user.getAddress());
    }
}
