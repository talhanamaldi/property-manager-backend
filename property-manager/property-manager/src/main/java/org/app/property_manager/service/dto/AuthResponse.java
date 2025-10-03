package org.app.property_manager.service.dto;

import java.time.Instant;

public record AuthResponse(
        String token,
        Instant expiresAt
) {}
