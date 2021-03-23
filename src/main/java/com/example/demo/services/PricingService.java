package com.example.demo.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

@Service
public class PricingService implements IPricingService {

    private static final HttpClient client = HttpClient.newBuilder().version(Version.HTTP_2).build();
    private static final String serviceURL = "http://httpbin.org/get";

    @Override
    public String getPriceForQuotaAmount(Double quota) throws IOException, InterruptedException {
        final String uriString = String.format("%s?type=%s", serviceURL, quota);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriString))
                .build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        return response.body();
    }
}
