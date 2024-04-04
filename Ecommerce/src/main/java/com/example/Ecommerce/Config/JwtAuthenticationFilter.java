package com.example.Ecommerce.Config;

import com.example.Ecommerce.Entity.User.CustomUserDetails;
import com.example.Ecommerce.Service.UserService;
import com.example.Ecommerce.Service.Jwt.jwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private jwtService jwtService;
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwt = authHeader.substring(7);
        username = jwtTokenProvider.ExtractUserName(jwt); // TODO Chích xuất người dùng từ JWT TOKEN
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            CustomUserDetails userCustomDetails = (CustomUserDetails) this.userService.loadUserByUsername(username);
            if (jwtTokenProvider.isTokenValid(jwt, userCustomDetails)) //Kiểm tra 2 điều kiện (1 là jtw còn hạn không , 2 là tkhoan có toofn tại hay không)
            {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userCustomDetails, null, userCustomDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
