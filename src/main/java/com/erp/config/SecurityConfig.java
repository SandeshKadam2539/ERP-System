package com.erp.config;

import com.erp.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers(
                    "/api/auth/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-ui.html"
                ).permitAll()

                // Customer module
                .requestMatchers(HttpMethod.POST, "/api/customers/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/customers/**").hasAnyRole("ADMIN", "CUSTOMER")

                // Purchase Order module
                .requestMatchers(HttpMethod.POST, "/api/purchase-orders").hasAnyRole("ADMIN", "PURCHASE_MANAGER")
                .requestMatchers(HttpMethod.GET, "/api/purchase-orders").hasAnyRole("ADMIN", "PURCHASE_MANAGER")
                .requestMatchers(HttpMethod.PUT, "/api/purchase-orders/**").hasAnyRole("ADMIN", "PURCHASE_MANAGER")

                // GRN module
                .requestMatchers(HttpMethod.POST, "/api/grns").hasAnyRole("ADMIN", "INVENTORY_MANAGER")
                .requestMatchers(HttpMethod.GET, "/api/grns").hasAnyRole("ADMIN", "INVENTORY_MANAGER")

                // Invoice module
                .requestMatchers(HttpMethod.POST, "/api/invoices").hasAnyRole("ADMIN", "SALES_EXECUTIVE")
                .requestMatchers(HttpMethod.GET, "/api/invoices/**").hasAnyRole("ADMIN", "SALES_EXECUTIVE")
                
                .requestMatchers("/api/dashboard/summary").hasAnyRole("ADMIN", "SALES_EXECUTIVE")
                .requestMatchers("/api/dashboard/**").hasRole("ADMIN") // बाकी dashboard endpoints फक्त ADMIN साठी

                // Any other request must be authenticated
                .anyRequest().authenticated()
            )
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Add JWT filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
