package com.example.demo.services;

import java.io.IOException;

public class TestPricingService implements IPricingService {

    @Override
    public String getPriceForAccountType(String accountType) throws IOException, InterruptedException {
        return String.valueOf(getRandomNumber(1, 10));
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
