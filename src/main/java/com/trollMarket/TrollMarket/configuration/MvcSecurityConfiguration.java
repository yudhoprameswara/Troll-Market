package com.trollMarket.TrollMarket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MvcSecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.securityMatcher("/**").csrf((request) -> request
                        .disable()
        ).authorizeHttpRequests((request) -> request
                .requestMatchers("/resources/**","/account/**").permitAll()
                .requestMatchers("/profile/**").hasAnyAuthority("Seller","Buyer")
                .requestMatchers("/shop/**","/cart/**").hasAuthority("Buyer")
                .requestMatchers("/merchandise/**").hasAuthority("Seller")
                .requestMatchers("/shipper/**","/admin/**","/history/**").hasAuthority("Administrator")
                .anyRequest().authenticated()
        ).formLogin((form) -> form
                .loginPage("/account/loginForm")
                .loginProcessingUrl("/authenticating")
                .failureUrl("/account/failLogin")
        ).logout((logout) -> logout
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID","remember-me-cookie").permitAll()
        ).exceptionHandling((exception) -> exception
                .accessDeniedPage("/account/accessDenied")
        );;
        return http.build();
    }

}

