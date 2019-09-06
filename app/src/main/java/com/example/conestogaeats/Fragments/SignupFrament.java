package com.example.conestogaeats.Fragments;

import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.conestogaeats.DBHelperClasses.MainDBHelperClass;
import com.example.conestogaeats.LoginSignupActivity;
import com.example.conestogaeats.Models.User;
import com.example.conestogaeats.R;

public class SignupFrament extends Fragment {

    EditText mEtFname, mEtLname, mEtEmail, mEtMobile, mEtAddress, mEtPassword;
    Button mBtnSignup;
    MainDBHelperClass mainDBHelperClass;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.signup_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LoginSignupActivity loginSignupActivity=(LoginSignupActivity) getActivity();
        loginSignupActivity.getSupportActionBar().setTitle("Conestoga Eats");
        mEtFname = view.findViewById(R.id.etFName);
        mEtLname = view.findViewById(R.id.etLName);
        mEtEmail = view.findViewById(R.id.etEmailId);
        mEtMobile = view.findViewById(R.id.etMobile);
        mEtAddress = view.findViewById(R.id.etAddress);
        mEtPassword = view.findViewById(R.id.etPassword);
        mBtnSignup = view.findViewById(R.id.btnSignup);
        mainDBHelperClass = new MainDBHelperClass(getActivity());

        mBtnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (IsValid()) {
                    if (mainDBHelperClass.IsEmailIdExist(mEtEmail.getText().toString())) {
                        Toast.makeText(getActivity(), "This email id already has an account.", Toast.LENGTH_SHORT).show();
                        mEtFname.setText("");
                        mEtLname.setText("");
                        mEtEmail.setText("");
                        mEtMobile.setText("");
                        mEtAddress.setText("");
                        mEtPassword.setText("");
                        LoginSignupActivity loginSignupActivity= (LoginSignupActivity) getActivity();
                        loginSignupActivity.mViewPager.setCurrentItem(0);
                    }
                    else
                    {
                        if(addUser())
                        {
                            Toast.makeText(getActivity(), "You are registered.", Toast.LENGTH_SHORT).show();
                            mEtFname.setText("");
                            mEtLname.setText("");
                            mEtEmail.setText("");
                            mEtMobile.setText("");
                            mEtAddress.setText("");
                            mEtPassword.setText("");
                            LoginSignupActivity loginSignupActivity= (LoginSignupActivity) getActivity();
                            loginSignupActivity.mViewPager.setCurrentItem(0);
                        }
                        else
                        {
                            Toast.makeText(getActivity(), "You are not registered.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }

    private boolean addUser() {
        User user=new User();

        user.setFname(mEtFname.getText().toString());
        user.setLname(mEtLname.getText().toString());
        user.setEmailId(mEtEmail.getText().toString());
        user.setMobileNo(mEtMobile.getText().toString());
        user.setAddress(mEtAddress.getText().toString());
        user.setPassword(mEtPassword.getText().toString());

        if(mainDBHelperClass.AddUser(user))
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    private boolean IsValid() {
        if (mEtFname.getText().toString().equalsIgnoreCase("")) {
            mEtFname.setError("Enter First Name");
            mEtFname.requestFocus();
            return false;
        } else if (mEtLname.getText().toString().equalsIgnoreCase("")) {
            mEtLname.setError("Enter Last Name");
            mEtLname.requestFocus();
            return false;
        } else if (mEtEmail.getText().toString().equalsIgnoreCase("")) {
            mEtEmail.setError("Enter Email");
            mEtEmail.requestFocus();
            return false;
        } else if (mEtMobile.getText().toString().equalsIgnoreCase("")) {
            mEtMobile.setError("Enter Mobile No");
            mEtMobile.requestFocus();
            return false;
        } else if (mEtAddress.getText().toString().equalsIgnoreCase("")) {
            mEtAddress.setError("Enter Address");
            mEtAddress.requestFocus();
            return false;
        } else if (mEtPassword.getText().toString().equalsIgnoreCase("")) {
            mEtPassword.setError("Enter Password");
            mEtPassword.requestFocus();
            return false;
        } else {
            return true;
        }

    }
}
