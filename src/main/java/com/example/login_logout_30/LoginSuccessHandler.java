package com.example.login_logout_30;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Collection;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private static String role="";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        System.out.println("******************* Success handler **********");
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String redirectURL = request.getContextPath();

        System.out.println(userDetails.getUsername());


        String currentUsername = authentication.getName();

        HttpSession session = request.getSession();
        session.setAttribute("USER_NAME", currentUsername);

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        authorities.forEach(auth->
                role=auth.getAuthority()
                );

        System.out.println(role);

        if (role.equalsIgnoreCase("ROLE_USER")) {
            redirectURL = "user";
        } else if (role.equalsIgnoreCase("ROLE_ADMIN")) {
            redirectURL = "admin";
        }

        System.out.println("Redirecting To URL: "+redirectURL);

        response.sendRedirect(redirectURL);
//        super.onAuthenticationSuccess(request,response,authentication);

    }
}
