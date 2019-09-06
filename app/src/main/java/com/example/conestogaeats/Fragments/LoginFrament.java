package com.example.conestogaeats.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.conestogaeats.HomeActivity;
import com.example.conestogaeats.LoginSignupActivity;
import com.example.conestogaeats.R;

public class LoginFrament extends Fragment {

    Button mBtnLogin;
    EditText mEtEmail, mEtPassword;
    MainDBHelperClass mainDBHelperClass;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LoginSignupActivity loginSignupActivity=(LoginSignupActivity) getActivity();
        loginSignupActivity.getSupportActionBar().setTitle("Conestoga Eats");

        mBtnLogin = view.findViewById(R.id.btnLogin);
        mEtEmail = view.findViewById(R.id.etEmailId);
        mEtPassword = view.findViewById(R.id.etPassword);
        mainDBHelperClass = new MainDBHelperClass(getActivity());
        sharedPreferences = getActivity().getSharedPreferences("CE", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {
                    if (mainDBHelperClass.logIn(mEtEmail.getText().toString(), mEtPassword.getText().toString())) {
                        editor.putString("UserEmail", mEtEmail.getText().toString());
                        editor.commit();
                        startActivity(new Intent(getActivity(),HomeActivity.class));
                        getActivity().finish();
                    }
                    else
                    {
                        Toast.makeText(getActivity(), "Invalid Email or Password.", Toast.LENGTH_SHORT).show();
                        mEtEmail.setText("");
                        mEtPassword.setText("");
                        mEtEmail.requestFocus();
                    }
                }
            }
        });
    }

    private boolean isValid() {
        if (mEtEmail.getText().toString().equalsIgnoreCase("")) {
            mEtEmail.setError("Enter EmailId");
            mEtEmail.requestFocus();
            return false;
        } else if (mEtPassword.getText().toString().equalsIgnoreCase("")) {
            mEtPassword.setError("Enter Passsword");
            mEtPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }
}
