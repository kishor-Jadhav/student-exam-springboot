package com.practice.p1.Configurations.Security;

import com.practice.p1.ExceptionHandling.JWTTokenException;
import com.practice.p1.Services.AuthService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.Arrays;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtHelper jwtHelper;
    private final AuthService authService;

    public JwtAuthenticationFilter(AuthService authService, JwtHelper jwtHelper) {
        this.authService = authService;
        this.jwtHelper = jwtHelper;
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Use getServletPath() instead of getRequestURI() to safely exclude app context paths
        String path = request.getServletPath();

        return path.startsWith("/v3/api-docs")
                || path.startsWith("/swagger-ui")
                || path.equals("/swagger-ui.html")
                || path.startsWith("/swagger-resources")
                || path.startsWith("/webjars")
                || path.startsWith("/auth/")       // Bypasses filter for auth endpoints
                || path.equals("/registration");   // Bypasses filter for registration
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) { // Added missing space after Bearer
            token = requestHeader.substring(7).trim();
            try {
                username = this.jwtHelper.getUsernameFromToken(token);
            } catch (IllegalArgumentException e) {
                logger.warn("Illegal Argument while fetching the username !!");
            } catch (ExpiredJwtException e) {
                logger.warn("Given jwt token is expired !!");
                // Handled gracefully: do not throw raw exceptions into the filter chain
                sendErrorResponse(response, "Given jwt token is expired !!");
                return;
            } catch (MalformedJwtException e) {
                logger.warn("Some change has been done in token !! Invalid Token");
                sendErrorResponse(response, "Invalid Token structure !!");
                return;
            } catch (Exception e) {
                logger.error("Unexpected error while processing JWT token", e);
                sendErrorResponse(response, "Unexpected JWT error");
                return;
            }
        } else {
            logger.debug("No valid Bearer token header found, passing down the chain.");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = authService.loadUserByUsername(username);

            if (this.jwtHelper.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                logger.warn("Validation fails !!");
                sendErrorResponse(response, "Invalid or Expired JWT Token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    // Helper method to catch and write explicit exceptions inside the filter layer safely
    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"" + message + "\"}");
    }
}