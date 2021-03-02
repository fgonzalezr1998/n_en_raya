package com.example.nenraya;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class GameFragment extends Fragment {

    private String player1Name,player2Name;
    private TextView player1Text, player2Text, player1Result, player2Result;
    private int player1Marker, player2Marker;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup vg, Bundle state) {
        View v = inf.inflate(R.layout.game_fragment, vg, false);

        initParams(v);

        player1Name = getArguments().getString("player1");
        player2Name = getArguments().getString("player2");

        player1Text.setText(player1Name);
        player2Text.setText(player2Name);
        player1Result.setText(Integer.toString(player1Marker));
        player2Result.setText(Integer.toString(player2Marker));

        return v;
    }

    private void initParams(View v) {
        player1Text = (TextView) v.findViewById(R.id.player1Marker);
        player2Text = (TextView) v.findViewById(R.id.player2Marker);
        player1Result = (TextView) v.findViewById(R.id.player1Result);
        player2Result = (TextView) v.findViewById(R.id.player2Result);

        player1Marker = 0;
        player2Marker = 0;
    }
}
