package com.project.ExpenseTracker.config;


import com.project.ExpenseTracker.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Autowired
    UserDetailsService CustomUserDetailsService;

    //private final HandlerLogout handlerLogout;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request.requestMatchers("register", "login").permitAll()
                        .anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults());
        http.securityContext((context) -> context.securityContextRepository(securityContextRepository));
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED));


        return http.build();

    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(CustomUserDetailsService);

        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        return daoAuthenticationProvider;

    }
}
