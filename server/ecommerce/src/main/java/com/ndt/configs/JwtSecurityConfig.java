package com.ndt.configs;

import com.ndt.filters.CustomAccessDeniedHandler;
import com.ndt.filters.JwtAuthenticationTokenFilter;
import com.ndt.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.ndt.controllers",
    "com.ndt.repositories",
    "com.ndt.services",
    "com.ndt.components"
})

@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests().antMatchers("/api/test/**").permitAll();
        http.authorizeRequests().antMatchers("/api/login/").permitAll();
        http.authorizeRequests().antMatchers("/api/logout/").permitAll();
        http.authorizeRequests().antMatchers("/api/register/").permitAll();
        http.authorizeRequests().antMatchers("/api/account/**").permitAll();
        http.authorizeRequests().antMatchers("/api/check-cookies/").permitAll();
        http.authorizeRequests().antMatchers("/api/refresh/").permitAll();
        http.authorizeRequests().antMatchers("/api/products/").permitAll();
        http.authorizeRequests().antMatchers("/api/products/**").permitAll();
        http.authorizeRequests().antMatchers("/api/categories/").permitAll();
        http.authorizeRequests().antMatchers("/api/reviews/**").permitAll();
        http.authorizeRequests().antMatchers("/api/replies/**").permitAll();
        http.authorizeRequests().antMatchers("/api/checkout/**").permitAll();
        http.authorizeRequests().antMatchers("/api/save-transaction/**").permitAll();
        http.authorizeRequests().antMatchers("/api/save-shipping-process/**").permitAll();
        http.authorizeRequests().antMatchers("/api/purchase-history/**").permitAll();
        // http.authorizeRequests().antMatchers("/api/for-sellers/**/manage-products/**").permitAll();

        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint())
				.and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/api/products/add").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLER')")
                .antMatchers(HttpMethod.POST, "/api/products/update").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLER')")
                .antMatchers(HttpMethod.GET, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLER')")
                .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLER')")
                .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SELLER')")
				.and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}
