/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.marcosbarbero.cloud.config.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import javax.annotation.PostConstruct;

/**
 * @author Marcos Barbero
 */
@Configuration
@EnableConfigurationProperties(ConfigClientOAuth2ResourceDetails.class)
@ConditionalOnClass({ConfigServicePropertySourceLocator.class,
        OAuth2RestTemplate.class})
@ConditionalOnProperty(value = ConfigClientOAuth2ResourceDetails.PREFIX + ".oauth2.clientId")
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
        private ConfigClientOAuth2ResourceDetails resourceDetails;

        @PostConstruct
        public void init() {
            final OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(this.resourceDetails.getOauth2());
            this.locator.setRestTemplate(restTemplate);
        }

    }
}
