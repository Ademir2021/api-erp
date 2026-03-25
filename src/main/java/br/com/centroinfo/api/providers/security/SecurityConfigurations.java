package br.com.centroinfo.api.providers.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize

                        // Pemite requisições do tipo tems/search_name?name=mouse
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/users").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/brand").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/brands").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/brand").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/sub_group").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/sub_groups").permitAll()
                        .requestMatchers(HttpMethod.POST, "/item").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/item").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/items").permitAll()
                        .requestMatchers(HttpMethod.GET, "/search_item/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/sale").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/sales").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/person").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/person").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/persons").permitAll()
                        .requestMatchers(HttpMethod.GET, "/search_person/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/nota/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/address").permitAll()
                        .requestMatchers(HttpMethod.GET, "/zipcodes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/states").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
