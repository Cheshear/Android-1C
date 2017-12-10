package com.example.mariia.withoutdoctor;

/**
 * Created by mariia on 03.12.2017.
 */

public class Wallet {
    private String walletNumber;
    private Integer emountOfMoney;
    public Wallet(String walletNumber, Integer emountOfMoney){
        this.walletNumber = walletNumber;
        this.emountOfMoney = emountOfMoney;
    }

    public Wallet() {}

    public void setEmountOfMoney(Integer emountOfMoney) {
        this.emountOfMoney = emountOfMoney;
    }

    public String getWalletNumber() {
        return walletNumber;
    }
    
    public Integer getEmountOfMoney(){
        return emountOfMoney;
    }
}
