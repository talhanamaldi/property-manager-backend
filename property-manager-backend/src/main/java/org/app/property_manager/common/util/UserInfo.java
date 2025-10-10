package org.app.property_manager.common.util;

import lombok.Getter;
import lombok.Setter;
import org.app.property_manager.application.config.AppUserDetails;
import org.app.property_manager.model.entity.app.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public final class UserInfo {

    private UserInfo() {}

    public static UserView getUserInfo() {
        UserView view = new UserView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return view;
        }

        Collection<? extends GrantedAuthority> authorities =
                auth.getAuthorities() != null ? auth.getAuthorities() : Collections.emptyList();
        view.setAuthorities(authorities);

        Object principal = auth.getPrincipal();
        if (principal instanceof AppUserDetails aud) {
            User u = aud.getDomainUser();
            if (u != null) {
                view.setUserId(u.getId());
                view.setEmail(u.getEmail());
                view.setUsername(u.getUsername());
                view.setAdmin(Boolean.TRUE.equals(u.getIsAdmin()));
            }
        } else if (principal instanceof UserDetails ud) {

            view.setEmail(ud.getUsername());
        }

        return view;
    }

    public static boolean isAuthenticated() {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        return a != null && a.isAuthenticated();
    }

    public static Long userIdOrNull() { return getUserInfo().getUserId(); }
    public static String emailOrNull() { return getUserInfo().getEmail(); }
    public static String usernameOrNull() { return getUserInfo().getUsername(); }
    public static boolean isAdmin() {
        Boolean admin = getUserInfo().getAdmin();
        return Boolean.TRUE.equals(admin);
    }
    public static boolean hasAuthority(String name) {
        return getUserInfo().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equalsIgnoreCase(name));
    }
    public static boolean hasNotAuthority(String name) { return !hasAuthority(name); }

    @Getter
    @Setter
    public static class UserView {
        private Long userId;
        private String email;
        private String username;
        private Boolean admin;
        private Collection<? extends GrantedAuthority> authorities = Collections.emptyList();
    }
}