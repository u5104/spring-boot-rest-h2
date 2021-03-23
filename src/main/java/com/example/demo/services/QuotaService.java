package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class QuotaService implements IQuotaService {

    @Override
    public Iterable<Double> getAvailableQuotas() {
        return Arrays.asList(new Double[]{1d, 10d, 100d});
    }
}
