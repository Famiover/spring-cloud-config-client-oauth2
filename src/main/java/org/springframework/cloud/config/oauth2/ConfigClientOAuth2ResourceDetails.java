package org.springframework.cloud.config.oauth2;

import java.util.List;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Marcos Barbero
 */
@Data
@ConfigurationProperties("spring.cloud.config.client.oauth2")
public class ConfigClientOAuth2ResourceDetails {
    private String clientId;
    private String clientSecret;
    private String accessTokenUri;
    private List<String> scope;
}
