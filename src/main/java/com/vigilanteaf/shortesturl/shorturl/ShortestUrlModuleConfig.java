package com.vigilanteaf.shortesturl.shorturl;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ShortestUrlProperties.class)
public class ShortestUrlModuleConfig {
}
