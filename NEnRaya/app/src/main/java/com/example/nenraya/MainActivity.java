package com.example.nenraya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnFragmentLoginListener {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startLoginFragment();
    }

    @Override
    public void playButClick(String player1, String player2, String tableDims) {
        // If login is ok, run the second fragment

        if (!loginOk(player1, player2)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Invalid Login!", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // RUN THE SECOND FRAGMENT!

        startGameFragment(player1, player2, tableDims);
    }

    private boolean loginOk(String p1, String p2) {
        return !p1.equals("") && !p2.equals("") && !p1.equals(p2);
    }

    private void startGameFragment(String player1, String player2, String tableDims) {
        Bundle bundle = new Bundle();
        GameFragment gameFragment = new GameFragment();

        bundle.putString("player1", player1);
        bundle.putString("player2", player2);
        bundle.putString("table_dims", tableDims);

        gameFragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        fragmentTrans = fragmentManager.beginTransaction();
        fragmentTrans.replace(R.id.main_frame, gameFragment);
        fragmentTrans.addToBackStack("GameFragment");
        fragmentTrans.commit();
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