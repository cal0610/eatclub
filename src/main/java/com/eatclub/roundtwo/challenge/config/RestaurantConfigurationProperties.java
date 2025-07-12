package com.eatclub.roundtwo.challenge.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "restaurant")
@Getter
@Setter
public class RestaurantConfigurationProperties {
	private String url;
}
