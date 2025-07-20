package com.connectify.api_gateway.filters;

import com.connectify.api_gateway.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Slf4j
@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    private final JwtService jwtService;

    public AuthFilter(JwtService jwtService) {
        super(Config.class);
        this.jwtService = jwtService;
    }


    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            log.info("Login request: {}", exchange.getRequest().getURI());

            final String tokenHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
            if(tokenHeader==null || !tokenHeader.startsWith("Bearer")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                log.error("Authorization token header not found");
                return exchange.getResponse().setComplete();
            }
            try {
                final String token = tokenHeader.split("Bearer ")[1];

                Claims claims = jwtService.getUserFromToken(token);
                ServerWebExchange webExchange = exchange.mutate()
                        .request(r -> {
                            r.header("X-User-Id", claims.getSubject());
                            claims
                                    .forEach((key, value)-> r.header("X-User-" + key, String.valueOf(value)));
                        })
                        .build();

                return chain.filter(webExchange);
            } catch (JwtException e) {
                log.error("JwtException Occurred: {}", e.getMessage());
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }

    public static class Config {}

}
