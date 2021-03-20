package com.example.demo.services;

import java.io.IOException;

public interface IPricingService {
    String getPriceForAccountType(String accountType) throws IOException, InterruptedException;
}
