package com.example.petproject.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class OAuth2Configuration extends ResourceServerConfigurerAdapter {
  private final TokenStore tokenStore;

  public OAuth2Configuration(TokenStore tokenStore) {
    this.tokenStore = tokenStore;
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http
          .requestMatchers()
          .and()
          .authorizeRequests()
          .antMatchers(HttpMethod.GET, "/cats/*").access("hasAnyAuthority('DEFAULT', 'ADMIN')")
          .antMatchers(HttpMethod.POST, "/cats/*").access("hasAnyAuthority('ADMIN')")
          .anyRequest().authenticated()
          .and()
          .exceptionHandling()
          .accessDeniedHandler(new OAuth2AccessDeniedHandler());
  }

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.tokenStore(tokenStore);
  }
}
