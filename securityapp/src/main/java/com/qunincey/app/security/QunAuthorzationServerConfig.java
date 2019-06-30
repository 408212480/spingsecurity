package com.qunincey.app.security;

import com.qunincey.security.core.properties.OAuth2ClientProperties;
import com.qunincey.security.core.properties.SecurityProperties;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @program: spingsecurity
 * @description:
 * @author: qiuxu
 * @create: 2019-06-29 18:48
 **/
@Configuration
@EnableAuthorizationServer
public class QunAuthorzationServerConfig extends AuthorizationServerConfigurerAdapter  {

    @Autowired
    private UserDetailsService userDetailsService;


    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    public QunRedisStore tokenStore;


    public QunAuthorzationServerConfig(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder client = clients.inMemory();
        if (ArrayUtils.isNotEmpty(securityProperties.getOauth().getClients())){
            for (OAuth2ClientProperties oAuth2ClientProperties:
            securityProperties.getOauth().getClients()) {
                client.withClient(oAuth2ClientProperties.getClientId())
                        .secret(oAuth2ClientProperties.getClientSecret())
                        .accessTokenValiditySeconds(oAuth2ClientProperties.getAccessTokenValidateSeconds())
                        .scopes("all");
            }
        }

    }
}
