package com.portfolioAPI.portfolioapi.Config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "server")
public record ClientConfig(String address, int port) {
}
