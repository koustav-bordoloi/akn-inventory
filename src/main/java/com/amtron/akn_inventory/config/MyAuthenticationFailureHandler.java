package com.amtron.akn_inventory.config;

import java.io.IOException;
import java.util.Objects;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
        String errorMessage = "Invalid Username or Password !";
        if (Objects.equals(exception.getMessage(), "Bad credentials")) {
            response.sendRedirect(request.getContextPath() + "/login-error?error=" + errorMessage);
        } else {
            response.sendRedirect(request.getContextPath() + "/login-error?error=" + exception.getMessage() + " !");
        }
    }
}
