package com.practice.customconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author yanzhihao
 */
@Component
@PropertySource(value = "classpath:custom.yml")
@ConfigurationProperties(prefix = "item")
@Data
public class CustomConfig {

    private Integer demo;

}
