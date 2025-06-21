package com.aniketmore.fittrack.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class HealthCheckController {
    @GetMapping("/healthz")
    public Map<String, String> healthCheck() {
        return Map.of("status", "healthy");
    }

    @GetMapping("/user/profile")
    public Map<String, Object> userProfile(@AuthenticationPrincipal Jwt jwt) {
        System.out.println(jwt.getClaims());
        return Map.of("username", jwt.getClaimAsString("given_name"));
        // return Map.of(
        // "username", jwt.getClaimAsString("preferred_username"),
        // "roles", jwt.getClaimAsStringList("realm_access.roles"));
    }

    @GetMapping("/admin/profile")
    public Map<String, Object> adminProfile(@AuthenticationPrincipal Jwt jwt) {
        return Map.of(
                "username", jwt.getClaimAsString("preferred_username"),
                "roles", jwt.getClaimAsStringList("realm_access.roles"));
    }

}
