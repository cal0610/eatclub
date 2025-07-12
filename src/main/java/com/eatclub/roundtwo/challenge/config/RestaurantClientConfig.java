package com.eatclub.roundtwo.challenge.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
@EnableConfigurationProperties(RestaurantConfigurationProperties.class)
public class RestaurantClientConfig {

	@Bean
	public RestClient restClient(RestaurantConfigurationProperties properties) {
		return RestClient.builder()
			.baseUrl(properties.getUrl())
			.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
			.build();
	}
}
