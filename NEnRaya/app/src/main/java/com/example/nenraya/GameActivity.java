package com.example.nenraya;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class GameActivity extends Activity implements AdapterView.OnItemClickListener, MyAdapter.NInLine, View.OnClickListener {

    private TextView player1Name, player2Name, player1Marker, player2Marker;
    private GridView gridView;

    private String player1, player2, currentPlayer;
    private int marker1, marker2, tableSize;
    private MyAdapter myAdapter;
    private Map<String, Integer> playersCards;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_game);

        getParams();
        initParams();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Toast.makeText(getApplicationContext(), "Clicked position " + position, Toast.LENGTH_SHORT).show();
        setBox(currentPlayer, position, view);
    }

    @Override
    public void setBox(String player, int position, View v) {
        int row, col;
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);

        row = getTableRow(position);
        col = getTableCol(position);

        if (positionIsEmpty(row, col)) {
            imageView.setImageResource(playersCards.get(player));
            setOccupied(currentPlayer, row, col);

            if (currentPlayer.equals(player1))
                currentPlayer = player2;
            else
                currentPlayer = player1;
        } else {
            Toast.makeText(getApplicationContext(), "Occupied position!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void setOccupied(String player, int row, int col) {
        myAdapter.table[row][col] = player;
    }

    @Override
    public boolean positionIsEmpty(int row, int col) {
        return myAdapter.table[row][col] == "";
    }

    @Override
    public int getTableRow(int pos) {
        int res;
        res = (int) ((float) pos / (float) tableSize);

        return res;
    }

    @Override
    public int getTableCol(int position) {
        int row, p, col;

        row = getTableRow(position) + 1;
        p = row * tableSize - 1;

        col = p - position;
        col = tableSize - col - 1;

        return col;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.newGameButton:
                startNewGame();
                break;
            case R.id.optionsButton:
                break;
        }
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
        linearLayout = (LinearLayout) findViewById(R.id.gameButtons);
        setButtonsListener(linearLayout);

        playersCards = new HashMap<String, Integer>();
        playersCards.put(player1, R.drawable.x1);
        playersCards.put(player2, R.drawable.o1);

        currentPlayer = player1;

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

    private void startNewGame() {
        marker1 = 0;
        marker2 = 0;
        initParams();
    }

    private void setButtonsListener(ViewGroup parent_layout) {
        View element;

        for (int i = 0; i < parent_layout.getChildCount(); i++) {
            element = parent_layout.getChildAt(i);
            if (element != null && element.isClickable()) {
                element.setOnClickListener(this);
            }
            if (parent_layout.getChildAt(i) instanceof ViewGroup)
                this.setButtonsListener((ViewGroup) parent_layout.getChildAt(i));
        }
    }
}
