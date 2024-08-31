package com.lon.admin.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 应用配置
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "lonadmin.config")
public class ApplicationConfig {

}
