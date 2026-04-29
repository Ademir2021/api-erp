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
                        // Obs.: Pemite requisições do tipo items/search_name?name=mouse
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/users").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/brand").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/brands").hasAnyRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/brand").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/subgroup").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/subgroup").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/sub_groups").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/groups").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/group").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/group").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/states").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/state").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/state").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/citys").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/city").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/city").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/typeitems").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/typeitem").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/typeitem").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/items").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/item").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/item").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/itemclasses").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/itemclasse").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/itemclasse").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/operationsales").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/operationsale").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/operationsale").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/search_item/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/sale").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/sales/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/person").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/person").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/persons/**").hasRole("USER")
                        .requestMatchers(HttpMethod.POST, "/account_receivable").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/account_receivable/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/accounts_receivable/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET, "/search_person/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/nota/**").permitAll()
                         .requestMatchers(HttpMethod.GET, "/cupom/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/address").permitAll()
                        .requestMatchers(HttpMethod.GET, "/countrys").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/country").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/country").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/zipcodes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/zipcodes").permitAll()
                        .requestMatchers(HttpMethod.GET, "/states").permitAll()
                        .requestMatchers(HttpMethod.GET, "/states").permitAll()
                        .requestMatchers(HttpMethod.GET, "/operations_sale").permitAll()
                        .requestMatchers(HttpMethod.POST, "/teste").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cash/list").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/cash").hasRole("ADMIN")
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
