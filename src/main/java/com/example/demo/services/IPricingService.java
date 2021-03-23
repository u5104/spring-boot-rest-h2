package com.example.demo.services;

import java.io.IOException;

public interface IPricingService {
    String getPriceForQuotaAmount(Double quota) throws IOException, InterruptedException;
}
