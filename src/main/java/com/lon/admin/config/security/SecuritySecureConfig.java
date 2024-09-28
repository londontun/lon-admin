package com.lon.admin.config.security;

import com.lon.admin.config.security.handle.AuthenticationEntryPointImpl;
import com.lon.admin.config.security.handle.AuthenticationSuccessHandlerImpl;
import com.lon.admin.config.security.handle.LogoutSuccessHandlerImpl;
import com.lon.admin.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecuritySecureConfig {

    private final UserDetailsServiceImpl userDetailsService;

    private final LogoutSuccessHandlerImpl logoutSuccessHandler;

    private final AuthenticationEntryPointImpl authenticationEntryPoint;

    private final AuthenticationSuccessHandlerImpl authenticationSuccessHandler;

    public SecuritySecureConfig(UserDetailsServiceImpl userDetailsService,
                                LogoutSuccessHandlerImpl logoutSuccessHandler,
                                AuthenticationEntryPointImpl authenticationEntryPoint,
                                AuthenticationSuccessHandlerImpl authenticationSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(bCryptPasswordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.httpBasic(AbstractHttpConfigurer::disable);

        http.formLogin(f -> f.loginProcessingUrl("/auth/login").successHandler(authenticationSuccessHandler).permitAll());
        http.logout(l -> l.logoutUrl("/auth/logout").logoutSuccessHandler(logoutSuccessHandler).permitAll());
        http.exceptionHandling(ex -> ex.authenticationEntryPoint(authenticationEntryPoint));

        http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));

        http.authorizeHttpRequests(r ->
                // swagger接口文档
                r.requestMatchers("doc.html", "/webjars/**", "/v3/**").permitAll()
                        //对于其它任何接口的访问都要经过身份认证方可
                        .anyRequest().authenticated());

        return http.build();
    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}