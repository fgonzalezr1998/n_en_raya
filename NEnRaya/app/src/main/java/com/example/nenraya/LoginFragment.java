package com.example.nenraya;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

public class LoginFragment extends Fragment implements View.OnClickListener {

    private OnFragmentLoginListener onFragmentLoginListener;

    private EditText player1Name, player2Name;
    private Spinner tableSize;
    private Button playBut;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onFragmentLoginListener = (OnFragmentLoginListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup vg, Bundle state) {
        View v = inf.inflate(R.layout.login_fragment, vg, false);

        initParams(v);

        // Set onClick:

        playBut.setOnClickListener(this);

        return v;
    }

    // Button method:

    @Override
    public void onClick(View v) {
        String player1, player2;
        String tableDims;

        player1 = player1Name.getText().toString();
        player2 = player2Name.getText().toString();

        tableDims = tableSize.getSelectedItem().toString();

        onFragmentLoginListener.playButClick(player1, player2, tableDims);
    }

    private void initParams(View v) {
        player1Name = (EditText) v.findViewById(R.id.loginText1);
        player2Name = (EditText) v.findViewById(R.id.loginText2);
        tableSize = (Spinner) v.findViewById(R.id.spinner);
        playBut = (Button) v.findViewById(R.id.play_button);
    }

    public interface OnFragmentLoginListener {
        void playButClick(String player1, String player2, String tableDims);
    }
}
