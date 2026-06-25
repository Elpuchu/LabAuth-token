package com.lab;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.Duration;
import java.util.Set;

@Path("/auth")
public class TokenResource {

    @Inject
    ClientAuthService clientAuthService;

    @POST
    @Path("/token")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateToken(TokenRequest request) {

        if (!clientAuthService.isValid(request.getClientId(), request.getClientSecret())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        String jwt = Jwt.issuer("token-api")
                .subject(request.getClientId())
                .groups(Set.of("service"))
                .expiresIn(Duration.ofHours(1))
                .sign();

        return Response.ok(new TokenResponse(jwt, 3600)).build();
    }

    @POST
    @Path("/test")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response test(TokenRequest request) {
        return Response.ok("Recibido: " + request.getClientId()).build();
    }
}