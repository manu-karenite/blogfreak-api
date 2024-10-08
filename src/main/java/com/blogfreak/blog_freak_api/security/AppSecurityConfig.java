package com.blogfreak.blog_freak_api.security;

import com.blogfreak.blog_freak_api.util.Constant;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
        http.authorizeHttpRequests((requests) -> requests.requestMatchers(
                        "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**")
                .permitAll()
                // -------------------------------- Authentication Related Endpoint -------------------------------- //
                .requestMatchers(HttpMethod.POST, "/login")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/register")
                .permitAll()
                // -------------------------------- Blogger Related Endpoint -------------------------------- //
                .requestMatchers(HttpMethod.PATCH, "/blogger")
                .hasAuthority(Constant.AUTHORITY_MANAGE)
                .requestMatchers(HttpMethod.PATCH, "/blogger/password")
                .hasAuthority(Constant.AUTHORITY_MANAGE)
                .requestMatchers(HttpMethod.DELETE, "/blogger")
                .hasAuthority(Constant.AUTHORITY_DELETE)
                .requestMatchers(HttpMethod.GET, "/bloggers/**")
                .hasAuthority(Constant.AUTHORITY_READ)
                .requestMatchers(HttpMethod.GET, "/bloggers")
                .hasAuthority(Constant.AUTHORITY_READ)
                .requestMatchers(HttpMethod.GET, "/blogger/profile")
                .hasAuthority(Constant.AUTHORITY_READ)
                // -------------------------------- Category Related Endpoint -------------------------------- //
                .requestMatchers(HttpMethod.POST, "/categories")
                .hasAuthority(Constant.AUTHORITY_ADMIN)
                .requestMatchers(HttpMethod.DELETE, "/categories/**")
                .hasAuthority(Constant.AUTHORITY_ADMIN)
                .requestMatchers(HttpMethod.PATCH, "/categories/**")
                .hasAuthority(Constant.AUTHORITY_ADMIN)
                .requestMatchers(HttpMethod.GET, "/categories/**")
                .hasAuthority(Constant.AUTHORITY_READ)
                .requestMatchers(HttpMethod.GET, "/categories")
                .hasAuthority(Constant.AUTHORITY_READ)
                // -------------------------------- Blog Related Endpoint -------------------------------- //
                .requestMatchers(HttpMethod.DELETE, "/blogs/**")
                .hasAuthority(Constant.AUTHORITY_DELETE)
                .requestMatchers(HttpMethod.PATCH, "/blogs/**")
                .hasAuthority(Constant.AUTHORITY_MANAGE)
                .requestMatchers(HttpMethod.POST, "/blogs")
                .hasAuthority(Constant.AUTHORITY_WRITE)
                .requestMatchers(HttpMethod.POST, "/blogs/**")
                .hasAuthority(Constant.AUTHORITY_WRITE)
                .requestMatchers(HttpMethod.GET, "/blogs/**")
                .hasAuthority(Constant.AUTHORITY_READ)
                .requestMatchers(HttpMethod.GET, "/blogs")
                .hasAuthority(Constant.AUTHORITY_READ)
                // -------------------------------- Health Check Related Endpoint -------------------------------- //
                .requestMatchers(HttpMethod.GET, "/healthcheck")
                .permitAll()
                .anyRequest()
                .authenticated());
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
        http.addFilterBefore(new JWTVerificationFilter(), BasicAuthenticationFilter.class);
        http.exceptionHandling(ex -> ex.accessDeniedHandler(new BlogFreakAccessDeniedHandler()));
        http.exceptionHandling(ex -> ex.authenticationEntryPoint(new BlogFreakAuthEntryPoint()));
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
