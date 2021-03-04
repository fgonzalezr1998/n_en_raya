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

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;

public class GameActivity extends FragmentActivity implements AdapterView.OnItemClickListener,
        MyAdapter.NInLine, View.OnClickListener, OptionsFragment.FragmentListener {

    private TextView player1Name, player2Name, player1Marker, player2Marker;
    private GridView gridView;

    private String player1, player2, currentPlayer;
    private int marker1, marker2, tableSize;
    private boolean gameStarted;
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
            gameStarted = true;
            updateMarker();
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
        // tableSize = 3;   // Uncomment for running the unit tests!
        res = (int) ((float) pos / (float) tableSize);

        return res;
    }

    @Override
    public int getTableCol(int position) {
        int row, p, col;
        // tableSize = 3;   // Uncomment for running the unit tests!
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
                startNewGame(0, 0);
                break;
            case R.id.optionsButton:
                startOptionsFragment();
                break;
        }
    }

    @Override
    public void changePlayersCards() {
        Integer card1, card2;

        if (!gameStarted) {

            card1 = playersCards.get(player1);
            card2 = playersCards.get(player2);

            playersCards.put(player1, card2);
            playersCards.put(player2, card1);
        } else {
            Toast.makeText(this, "Game has started! You must start a new game",
                    Toast.LENGTH_SHORT).show();
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

    private void updateMarker() {
        int m1, m2;
        m1 = marker1;
        m2 = marker2;
        if (winned(player1)) {
            startNewGame(++marker1, marker2);
        } else if (winned(player2)) {
            startNewGame(marker1, ++marker2);
        } else {
            if (isTie()) {
                Toast.makeText(this, "Tie!", Toast.LENGTH_SHORT).show();
                startNewGame(0, 0);
            }
        }
    }

    private boolean isTie() {
        boolean occupied = false;
        for (int i = 0; i < tableSize; i++) {
            for (int j = 0; j < tableSize; j++) {
                occupied = myAdapter.table[i][j].equals(player1) || myAdapter.table[i][j].equals(player2);
                if (!occupied)
                    break;
            }
            if (!occupied)
                break;
        }

        return occupied;
    }

    private boolean winned(String player) {
        return isLineHorizontal(player) || isLineVertical(player) || isLineDiagonal(player);
    }

    private boolean isLineHorizontal(String player) {
        boolean found = false;

        for (int i = 0; i < tableSize; i++) {
            found = false;
            for (int j = 0; j < tableSize; j++) {
                found = myAdapter.table[i][j] == player;
                if (!found)
                    break;
            }
            if (found)
                break;
        }
        return found;
    }

    private boolean isLineVertical(String player) {
        boolean found = false;

        for (int j = 0; j < tableSize; j++) {
            found = false;
            for (int i = 0; i < tableSize; i++) {
                found = myAdapter.table[i][j] == player;
                if (!found)
                    break;
            }
            if (found)
                break;
        }
        return found;
    }

    private boolean isLineDiagonal(String player) {
        boolean found = false;

        // Check first diagonal:

        for (int i = 0; i < tableSize; i++) {
            found = myAdapter.table[i][i] == player;
            if (!found)
                break;
        }

        if (!found) {
            // Check second diagonal:

            for (int i = tableSize - 1; i >= 0; i--) {
                found = myAdapter.table[i][tableSize - i - 1] == player;
                if (!found)
                    break;
            }
        }

        return found;
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
        gameStarted = false;
    }

    private void startNewGame(int m1, int m2) {
        marker1 = m1;
        marker2 = m2;
        initParams();
    }

    private void startOptionsFragment() {
        OptionsFragment optionsFragment = new OptionsFragment();
        optionsFragment.setArguments(getIntent().getExtras());

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();

        trans.replace(R.id.gameButtons, optionsFragment);
        trans.addToBackStack(null);
        trans.commit();
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
