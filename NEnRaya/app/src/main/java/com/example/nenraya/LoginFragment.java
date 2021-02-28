package com.example.nenraya;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup vg, Bundle state) {
        View v = inf.inflate(R.layout.login_fragment, vg, false);

        return v;
    }
}
