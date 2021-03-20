package com.example.demo.conf;

import com.example.demo.services.IPricingService;
import com.example.demo.services.PricingService;
import com.example.demo.services.TestPricingService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AppConfiguration {
    @Bean
    @ConditionalOnProperty(
            value = "mock.rest.calls",
            havingValue = "false",
            matchIfMissing = false)
    public IPricingService pricingService() {
        return new PricingService();
    }

    @Bean
    @ConditionalOnProperty(
            value = "mock.rest.calls",
            havingValue = "true",
            matchIfMissing = true)
    public IPricingService testPricingService() {
        return new TestPricingService();
    }
}
