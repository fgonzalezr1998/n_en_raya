package com.example.nenraya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentLoginListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTrans;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startLoginFragment();
    }

    @Override
    public void playButClick(String player1, String player2, String tableDims) {
        Log.w("NEnRaya", player1);
        Log.w("NEnRaya", player2);
        Log.w("NEnRaya", tableDims);

        // If login is ok, run the second fragment

        if (!loginOk(player1, player2)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Invalid Login!", Toast.LENGTH_SHORT);
            toast.show();
        }
        // RUN THE SECOND FRAGMENT!
    }

    private boolean loginOk(String p1, String p2) {
        return !p1.equals("") && !p2.equals("") && !p1.equals(p2);
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