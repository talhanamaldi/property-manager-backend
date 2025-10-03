package org.app.property_manager.application.web.contoller;

import lombok.RequiredArgsConstructor;
import org.app.property_manager.common.exception.GeneralException;
import org.app.property_manager.common.exception.GeneralWarning;
import org.app.property_manager.model.entity.app.User;
import org.app.property_manager.service.AuthService;
import org.app.property_manager.service.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public ResponseEntity<AuthResponse> signup(@RequestBody User payload) throws GeneralException, GeneralWarning {
        return ResponseEntity.ok(authService.signup(payload));
    }

    @RequestMapping(path = "/signin", method = RequestMethod.POST)
    public ResponseEntity<AuthResponse> signin(@RequestBody User payload) throws GeneralException, GeneralWarning {
        return ResponseEntity.ok(authService.signin(payload));
    }
}
