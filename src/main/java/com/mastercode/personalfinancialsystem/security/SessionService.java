package com.mastercode.personalfinancialsystem.security;

import com.mastercode.personalfinancialsystem.domain.User;
import com.mastercode.personalfinancialsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private UserService userService;

    public User getUserFromSession() {

        try {
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            Long userId = Long.parseLong(authentication.getPrincipal().toString());
            User user = userService.findById(userId);
            return user;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new AuthenticationCredentialsNotFoundException("User is not authenticated");
        }
    }
}
