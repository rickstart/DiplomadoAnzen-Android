package com.mobintum.tabsexample.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobintum.tabsexample.R;
import com.mobintum.tabsexample.adapters.CardsPagerAdapter;
import com.mobintum.tabsexample.models.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardTabsFragment extends Fragment {

    private CardsPagerAdapter pagerAdapter;
    private ViewPager mViewPager;

    public CardTabsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_tabs, container, false);
        pagerAdapter = new CardsPagerAdapter(getActivity().getSupportFragmentManager(),getCards());

        Log.e("DEBUG", "ENETER");
        mViewPager = (ViewPager) view.findViewById(R.id.container);
        mViewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    private List<Card> getCards(){
        List<Card> cards = new ArrayList<>();
        cards.add(new Card("Bancomer","**** 1620","Jesus"));
        cards.add(new Card("Banamex","**** 1523","Manuel"));
        cards.add(new Card("Banorte","**** 1739","Maria"));
        cards.add(new Card("Banorte","**** 1739","Maria"));
        return cards;
    }

}
