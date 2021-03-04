package com.example.nenraya;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class OptionsFragment extends Fragment implements View.OnClickListener {

    Button button;

    FragmentListener fragmentListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentListener = (FragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup view, Bundle state) {
        View v = inf.inflate(R.layout.options_fragment, view, false);


        button = (Button) v.findViewById(R.id.changeCardsButton);
        button.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        fragmentListener.changePlayersCards();
    }

    public interface FragmentListener {
        void changePlayersCards();
    }
}
