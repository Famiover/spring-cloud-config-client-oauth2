package org.springframework.cloud.config.oauth2;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * @author Marcos Barbero
 */
@Configuration
@EnableConfigurationProperties(ConfigClientOAuth2ResourceDetails.class)
@ConditionalOnClass({ ConfigServicePropertySourceLocator.class,
		OAuth2RestTemplate.class })
@ConditionalOnProperty(value = "spring.cloud.config.client.oauth2.clientId")
public class ConfigClientOAuth2BootstrapConfiguration {

	@Bean
	@ConditionalOnMissingBean(ConfigClientOAuth2ResourceDetails.class)
	public ConfigClientOAuth2ResourceDetails configClientOAuth2ResourceDetails() {
		return new ConfigClientOAuth2ResourceDetails();
	}

	@Bean
	protected ConfigClientOAuth2Configurer configClientOAuth2Configurator() {
		return new ConfigClientOAuth2Configurer();
	}

	protected static class ConfigClientOAuth2Configurer {

		@Autowired
		private ConfigServicePropertySourceLocator locator;

		@Autowired
		private ConfigClientOAuth2ResourceDetails configClientOAuth2ResourceDetails;

		@PostConstruct
		public void init() {
			BaseOAuth2ProtectedResourceDetails details = new ClientCredentialsResourceDetails();
			BeanUtils.copyProperties(this.configClientOAuth2ResourceDetails.getOauth2(), details);
			locator.setRestTemplate(new OAuth2RestTemplate(details));
		}

	}
}
