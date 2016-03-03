package org.springframework.cloud.config.oauth2;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * @author Marcos Barbero
 */
@Data
@ConfigurationProperties(ConfigClientOAuth2ResourceDetails.PREFIX)
public class ConfigClientOAuth2ResourceDetails {
    public static final String PREFIX = "spring.cloud.config.client";

	private BaseOAuth2ProtectedResourceDetails oauth2 = new ClientCredentialsResourceDetails();
}
