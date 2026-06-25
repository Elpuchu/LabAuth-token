package com.lab;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.Map;

@ApplicationScoped
public class ClientAuthService {

    private final Map<String, String> clients = Map.of(
      "orders-service", "1234",
      "inventory-service",   "5678"
    );

    public boolean isValid(String clientId, String secret) {
        return secret.equals(clients.get(clientId));
    }
}
