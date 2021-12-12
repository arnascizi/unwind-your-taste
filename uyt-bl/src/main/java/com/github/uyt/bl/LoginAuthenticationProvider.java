package com.github.uyt.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import com.github.uyt.bl.service.UserAccountService;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        String username = authentication.getDetails().toString();
        String password = authentication.getCredentials().toString();
        UserDetails user = userAccountService.loadUserByUsername(username);
        return new PreAuthenticatedAuthenticationToken(user, password);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        // return request != null && request.getRequestURI() != null && authentication.equals(UsernamePasswordAuthenticationToken.class);
        return true;
    }
}
