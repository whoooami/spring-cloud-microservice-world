package com.whoami.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;

@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Value("${resource.id:spring-boot-application}")
    private String resourceId;
    @Value("${access_token.validity_period:3600}")
    int accessTokenValiditySeconds;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "passthis".toCharArray())
                .getKeyPair("foobar");
        converter.setKeyPair(keyPair);
        return converter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(new InMemoryTokenStore())
                .accessTokenConverter(jwtAccessTokenConverter());
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
            .tokenKeyAccess("permitAll()")
            .checkTokenAccess("isAuthenticated()");//公开/oauth/token的接口
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("whoami")
                .scopes("app")
                .secret("123456")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token")
                .resourceIds(resourceId)
                .accessTokenValiditySeconds(accessTokenValiditySeconds)

                .and()

                .withClient("webapp")
                .scopes("web")
                .authorizedGrantTypes("implicit")
                .resourceIds(resourceId)
                .accessTokenValiditySeconds(accessTokenValiditySeconds);
                //.jdbc(dataSource);//从数据库中获取clientDetails信息

    }

   /* @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }*/
}