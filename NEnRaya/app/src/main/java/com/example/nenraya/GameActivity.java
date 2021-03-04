package com.example.nenraya;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements AdapterView.OnItemClickListener {

    private TextView player1Name, player2Name, player1Marker, player2Marker;
    private GridView gridView;

    private String player1, player2;
    private int marker1, marker2, tableSize;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_game);

        getParams();
        initParams();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "Clicked position " + position, Toast.LENGTH_SHORT).show();
    }

    private void getParams() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle == null) {
            return;
        }

        tableSize = 0;
        player1 = bundle.getString("player1");
        player2 = bundle.getString("player2");
        tableSize = bundle.getInt("table_size");

        marker1 = 0;
        marker2 = 0;

        if (player1 == null || player2 == null || tableSize == 0) {
            Toast.makeText(this, "[ERROR] Bad params!", Toast.LENGTH_SHORT).show();
        }
    }

    private void initParams() {
        player1Name = (TextView) findViewById(R.id.player1Marker);
        player2Name = (TextView) findViewById(R.id.player2Marker);
        player1Marker = (TextView) findViewById(R.id.player1Result);
        player2Marker = (TextView) findViewById(R.id.player2Result);
        gridView = (GridView) findViewById(R.id.tableGrid);
        gridView.setNumColumns(tableSize);

        player1Name.setText(player1);
        player2Name.setText(player2);
        player1Marker.setText(Integer.toString(marker1));
        player2Marker.setText(Integer.toString(marker2));

        myAdapter = new MyAdapter(getApplicationContext(), tableSize);

        gridView.setAdapter(myAdapter);
        gridView.setOnItemClickListener(this);
    }
}
