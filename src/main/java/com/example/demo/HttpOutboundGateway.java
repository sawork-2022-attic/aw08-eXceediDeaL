package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.dsl.Http;
import org.springframework.stereotype.Component;

@Component
public class HttpOutboundGateway {
    @Bean
    public IntegrationFlow outGate() {
        return IntegrationFlows.from("sampleChannel")
                .handle(Http.outboundGateway("https://api.chucknorris.io/jokes/random")
                        .httpMethod(HttpMethod.GET)
                        .expectedResponseType(Joke.class))
                .<Joke, String>transform((j) -> j.getValue())
                .handle(System.out::println)
                .get();
    }
}