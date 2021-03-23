package com.example.demo.services;

import java.io.IOException;

public interface IPlatformService {
    String updateQuota(Integer customerId, String accountType, Long quota) throws IOException, InterruptedException;
}
