package com.michalkaluzinski.fantasyleague.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.michalkaluzinski.fantasyleague.services.JwtTokenProviderService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private JwtTokenProviderService jwtTokenProvider;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/admin/**")
        .hasRole("ADMIN")
        .antMatchers(HttpMethod.POST, "/users", "/users/login")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/users/confirmRegistration")
        .permitAll()
        .and()
        .apply(new JwtConfigurer(jwtTokenProvider));
  }
}
