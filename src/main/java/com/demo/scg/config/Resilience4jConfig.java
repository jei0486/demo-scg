package com.demo.scg.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Slf4j
@Configuration
public class Resilience4jConfig {

    //===== default
    @Value("${resilience4j.circuitbreaker.configs.default.slidingWindowType:COUNT_BASED}")
    private String slidingWindowType;

    @Value("${resilience4j.circuitbreaker.configs.default.slidingWindowSize:10}")
    private int slidingWindowSize;

    @Value("${resilience4j.circuitbreaker.configs.default.minimumNumberOfCalls:5}")
    private int minimumNumberOfCalls;

    @Value("${resilience4j.circuitbreaker.configs.default.failureRateThreshold:50}")
    private float failureRateThreshold;

    @Value("${resilience4j.circuitbreaker.configs.default.waitDurationInOpenState:5000}")
    private long waitDurationInOpenState;

    @Value("${resilience4j.circuitbreaker.configs.default.permittedNumberOfCallsInHalfOpenState:2}")
    private int permittedNumberOfCallsInHalfOpenState;

    @Value("${resilience4j.circuitbreaker.configs.default.slowCallDurationThreshold:3000}")
    private long slowCallDurationThreshold;

    @Value("${resilience4j.circuitbreaker.configs.default.slowCallRateThreshold:100}")
    private float slowCallRateThreshold;

    @Value("${resilience4j.timeout.default:3000}")
    private long timeout;

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {

        log.info(">>>>>>>>>>>> START defaultCustomizer()");


        CircuitBreakerConfig.SlidingWindowType winType = ("COUNT_BASED".equals(this.slidingWindowType)? CircuitBreakerConfig.SlidingWindowType.COUNT_BASED: CircuitBreakerConfig.SlidingWindowType.TIME_BASED);

        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .slidingWindowType(winType)
                .slidingWindowSize(this.slidingWindowSize)
                .minimumNumberOfCalls(this.minimumNumberOfCalls)
                .failureRateThreshold(this.failureRateThreshold)
                .waitDurationInOpenState(Duration.ofMillis(this.waitDurationInOpenState))
                .slowCallDurationThreshold(Duration.ofMillis(this.slowCallDurationThreshold))
                .slowCallRateThreshold(this.slowCallRateThreshold)
                .permittedNumberOfCallsInHalfOpenState(this.permittedNumberOfCallsInHalfOpenState)
                .recordExceptions(java.io.IOException.class,
                        java.util.concurrent.TimeoutException.class,
                        org.springframework.web.server.ResponseStatusException.class)
                .build();

        TimeLimiterConfig timeoutConfig = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(timeout))
                .build();

        return factory ->
                factory.configure(builder ->
                        builder.circuitBreakerConfig(config)
                                .timeLimiterConfig(timeoutConfig)
                                .build(), "mycb");
    }
}
