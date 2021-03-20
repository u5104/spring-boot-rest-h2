package com.example.demo.services;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class PricingService implements IPricingService {

    private static final HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).build();
    private static final String serviceURL = "http://localhost:9999/api/price/";

    @Override
    public String getPriceForAccountType(String accountType) throws IOException, InterruptedException {
        final String uriString = String.format("%s?type=%s", serviceURL, accountType);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriString))
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }
}
