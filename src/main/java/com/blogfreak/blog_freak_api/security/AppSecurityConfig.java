package com.blogfreak.blog_freak_api.security;

import static org.springframework.security.config.Customizer.withDefaults;

import com.blogfreak.blog_freak_api.util.Constant;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class AppSecurityConfig {
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        http.csrf(csrf -> csrf.disable());
        http.cors(corsCustomizer -> corsCustomizer.configurationSource(new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost"));
                corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
                corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                corsConfiguration.setAllowCredentials(Boolean.TRUE);
                corsConfiguration.setMaxAge(3600L);
                corsConfiguration.addExposedHeader(Constant.AUTHORIZATION);
                return corsConfiguration;
            }
        }));
        http.formLogin(flc -> flc.disable());
        http.httpBasic(withDefaults());
        http.addFilterBefore(new JWTVerificationFilter(), BasicAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(
            UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder) {
        BlogFreakApplicationAuthenticationProvider bfap =
                new BlogFreakApplicationAuthenticationProvider(userDetailsService, passwordEncoder);
        ProviderManager pm = new ProviderManager(bfap);
        pm.setEraseCredentialsAfterAuthentication(Boolean.FALSE);
        return pm;
    }

    @Bean
    public PasswordEncoder getPasswordEncode() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
