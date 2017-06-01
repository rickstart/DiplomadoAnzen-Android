package com.mobintum.tabsexample.models;

import java.io.Serializable;

/**
 * Created by Ricardo on 01/06/17.
 * www.mobintum.com
 */

public class Card implements Serializable {
    String bank;
    String number;
    String name;

    public Card(String bank, String number, String name) {
        this.bank = bank;
        this.number = number;
        this.name = name;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
