package com.liner.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Liner on 18/6/22.
 */
public class EnumDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(EnumDemo.class);

    public static void main(String[] args) {
        EnumDemo enumDemo = new EnumDemo();
        double result = enumDemo.foreignExchange(4, CurrencyEnum.SGD);
        System.out.println(result);
    }

    public double foreignExchange(double rmb, CurrencyEnum currencyEnum) {
        LOGGER.info("{}{} = {}RMB", rmb, currencyEnum.getDisc(), currencyEnum.getRate() * rmb);
        return currencyEnum.getRate() * rmb;
    }
}

enum CurrencyEnum {
    RMB("renminbi", 1),
    USD("meiyuan", 6.6),
    SGD("xinjiapoyuan", 4.5),
    NZD("xinxilanbi", 4.55),
    TWD("taiwanxinbi", 0.2),
    GBP("yingbang", 8.1);

    private String disc;
    private double rate;

    CurrencyEnum(String disc, double rate) {
        this.disc = disc;
        this.rate = rate;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
