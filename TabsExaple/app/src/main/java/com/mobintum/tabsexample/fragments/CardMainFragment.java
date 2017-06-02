package com.mobintum.tabsexample.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobintum.tabsexample.R;
import com.mobintum.tabsexample.models.Card;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardMainFragment extends Fragment {

    private static final String ARG_PARAM_CARD = "paramCard";
    private Card card;
    private TextView txtCardNumber;

    public static CardMainFragment newInstance(Card card) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_CARD,card);
        CardMainFragment fragment = new CardMainFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public CardMainFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null)
            this.card = (Card) getArguments().getSerializable(ARG_PARAM_CARD);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_main, container, false);
        txtCardNumber = (TextView) view.findViewById(R.id.txtCardNumber);
        if (card!=null)
            txtCardNumber.setText(card.getNumber());
        Log.e("DEBUG CARD", card.getBank());
        return view;
    }

}
