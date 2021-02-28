package com.example.nenraya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTrans;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startLoginFragment();
    }

    private void startLoginFragment() {
        LoginFragment loginFrag = new LoginFragment();
        loginFrag.setArguments(getIntent().getExtras());

        fragmentManager = getSupportFragmentManager();
        fragmentTrans = fragmentManager.beginTransaction();
        fragmentTrans.add(R.id.main_frame, loginFrag);
        fragmentTrans.addToBackStack(null);
        fragmentTrans.commit();
    }
}