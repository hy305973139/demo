package com.example.demo.dao;

import com.example.demo.entity.Price;

public interface PriceDao {
    int addPrice(Price price);

    int updatePrice(Price price);

    Price findPrice(String ip);

    Price findPriceByNumber(String number);
}
