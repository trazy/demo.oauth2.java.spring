package me.isakaone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;


	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
		endpoints.userDetailsService(userDetailsService);
		// endpoints.reuseRefreshTokens(false); // default true
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("from-common-client")
				.authorizedGrantTypes(new String[] {
						OAuth2GrantType.password.value(),
						OAuth2GrantType.implicit.value(),
						OAuth2GrantType.refresh_token.value(),
				})
				.authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
				.scopes(new String[] {
					OAuth2Scope.read.value(),
					OAuth2Scope.write.value(),
				})
				.accessTokenValiditySeconds(60)
			.and()
				.withClient("from-web-application")
				.authorizedGrantTypes(new String[] {
						OAuth2GrantType.authorization_code.value(),
						OAuth2GrantType.implicit.value(),
						OAuth2GrantType.refresh_token.value(),
				})
				.authorities("ROLE_CLIENT")
				.scopes(OAuth2Scope.read.value())
				.redirectUris("http://localhost:8080")
				.autoApprove(".*")
				.accessTokenValiditySeconds(60)
			.and()
				.withClient("from-backend-application")
				.authorizedGrantTypes(new String[] {
					OAuth2GrantType.client_credentials.value(),
				})
				.authorities("ROLE_CLIENT")
				.scopes(new String[] {
					OAuth2Scope.read.value(),
					OAuth2Scope.write.value(),
					OAuth2Scope.trusted.value(),
				})
				.secret("blahblah")
				.accessTokenValiditySeconds(0);
	}
	
}
