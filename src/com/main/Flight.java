package com.main;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Flight {
    private String fromCity;
    private String toCity;
    private int price;

    public String getFromCity() {
        return fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return  '\n'+ "{" +
                "fromCity='" + fromCity + '\'' +
                ", toCity='" + toCity + '\'' +
                ", price=" + price +
                '}' + '\n';
    }
}
