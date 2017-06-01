package com.mobintum.tabsexample.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mobintum.tabsexample.activities.MainActivity;
import com.mobintum.tabsexample.fragments.CardMainFragment;
import com.mobintum.tabsexample.models.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo on 01/06/17.
 * www.mobintum.com
 */

public class CardsPagerAdapter extends FragmentPagerAdapter {

    private List<Card> cards;
    public CardsPagerAdapter(FragmentManager fm, List<Card> cards) {
        super(fm);
        this.cards = cards;

    }

    @Override
    public Fragment getItem(int position) {
        return CardMainFragment.newInstance(cards.get(position));
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return cards.get(position).getBank();
    }
}