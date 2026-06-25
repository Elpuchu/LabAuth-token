package com.lab;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.Duration;
import java.util.Set;

@Path("/auth")
public class TokenResource {

    @Inject
    ClientAuthService clientAuthService;

    @POST
    @Path("token")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getToken(String clientId, String secret) {
        if (clientAuthService.isValid(clientId, secret)) {
            String jwt = Jwt.issuer("token-api")
                .subject(clientId)
                .groups(Set.of("service"))
                .expiresIn(Duration.ofHours(1))
                .sign();
        }

        return "Invalid client credentials";
    }
}
