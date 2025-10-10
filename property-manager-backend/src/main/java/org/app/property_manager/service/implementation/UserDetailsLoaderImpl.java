package org.app.property_manager.service.implementation;

import lombok.RequiredArgsConstructor;
import org.app.property_manager.application.config.AppUserDetails;
import org.app.property_manager.model.entity.app.User;
import org.app.property_manager.repository.UserRepository;
import org.app.property_manager.service.UserDetailsLoader;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsLoaderImpl implements UserDetailsLoader {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new AppUserDetails(u);
    }
}
