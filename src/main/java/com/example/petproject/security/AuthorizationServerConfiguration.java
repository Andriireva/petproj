package com.example.petproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    private final String clientid;
    private final String clientSecret;
    private final String privateKey;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthorizationServerConfiguration(@Value("${spring.security.oauth2.client.clientId}") String clientid,
                                            @Value("${spring.security.oauth2.client.clientSecret}") String clientSecret,
                                            @Value("${spring.security.oauth2.client.privateKey}") String privateKey,
                                            @Qualifier("authenticationManagerBean") AuthenticationManager authenticationManager) {
        this.clientid = clientid;
        this.clientSecret = clientSecret;
        this.privateKey = privateKey;
        this.authenticationManager = authenticationManager;
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        return converter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
              .tokenStore(tokenStore())
              .accessTokenConverter(tokenEnhancer());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")
              .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
              .withClient(clientid)
              .secret(clientSecret)
              .scopes("read", "write")
              .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
              .refreshTokenValiditySeconds(200000);

    }
}
