package com.example.nenraya;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText player1Text, player2Text;
    private Spinner spinner;
    private Button playButton;

    private String player1, player2;
    private int marker1, marker2, tableSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initParams();

        player1 = "";
        player2 = "";
        marker1 = 0;
        marker2 = 0;
    }

    @Override
    public void onClick(View v) {
        player1 = player1Text.getText().toString();
        player2 = player2Text.getText().toString();
        if (!loginOk(player1, player2)) {
            Toast.makeText(getApplicationContext(), "Invalid Login!", Toast.LENGTH_SHORT).show();
            return;
        }
        getTableSize();

        startGameActivity();
    }

    private void initParams() {
        player1Text = (EditText) findViewById(R.id.loginText1);
        player2Text = (EditText) findViewById(R.id.loginText2);
        spinner = (Spinner) findViewById(R.id.spinner);
        playButton = (Button) findViewById(R.id.play_button);

        playButton.setOnClickListener(this);
    }

    private void getTableSize() {
        String spinnerElem;
        spinnerElem = spinner.getSelectedItem().toString();
        switch (spinnerElem) {
            case "3 in line (3x3)":
                tableSize = 3;
                break;
            case "4 in line (4x4)":
                tableSize = 4;
                break;
            default:
                tableSize = 5;
                break;
        }
    }

    private void startGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        Bundle bundle = new Bundle();

        // Set the parameters:

        bundle.putString("player1", player1);
        bundle.putString("player2", player2);
        bundle.putInt("table_size", tableSize);
        intent.putExtras(bundle);

        startActivity(intent);
    }

    private boolean loginOk(String p1, String p2) {
        return !p1.equals("") && !p2.equals("") && !p1.equals(p2);
    }


}