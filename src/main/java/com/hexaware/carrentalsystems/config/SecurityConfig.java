package com.hexaware.carrentalsystems.config;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hexaware.carrentalsystems.filter.JwtAuthFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

	/*
	 * @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws
	 * Exception { http .csrf(csrf -> csrf.disable()) .cors(cors -> {}) // configure
	 * origin as needed .authorizeHttpRequests(auth -> auth
	 * .requestMatchers("/auth/login","/api/users/**","/api/cars/getall",
	 * "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
	 * .anyRequest().authenticated()
	 * 
	 * ) .addFilterBefore(jwtAuthFilter,
	 * UsernamePasswordAuthenticationFilter.class);
	 * 
	 * return http.build(); }
	 */
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> {}) // configure CORS if needed
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/auth/login",
                    "/api/users/**",
                    "/api/cars/getall",
                    "/v3/api-docs/**",
                    "/swagger-ui/**",
                    "/swagger-ui.html"
                ).permitAll()
                .requestMatchers(
                    "/api/users/**",
                    "/api/reservations/**",
                    "/api/cars/**",
                    "/api/feedback/**",
                    "/api/payments/**"
                ).authenticated()
            )
            // If you want form login UI, remove stateless session management:
            
            //.formLogin(withDefaults()) // enables default Spring login page
            .formLogin(form -> form
            	    .usernameParameter("name")
            	)
            // If you want stateless JWT, comment out above formLogin and enable this:
            // .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
