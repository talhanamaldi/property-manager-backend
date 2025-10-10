package org.app.property_manager.service;

import org.app.property_manager.common.exception.GeneralException;
import org.app.property_manager.common.exception.GeneralWarning;
import org.app.property_manager.model.entity.app.User;
import org.app.property_manager.service.dto.AuthResponse;

public interface AuthService {
    AuthResponse signup(User userPayload) throws GeneralException, GeneralWarning;

    AuthResponse signin(User userPayload) throws GeneralException, GeneralWarning;
}
