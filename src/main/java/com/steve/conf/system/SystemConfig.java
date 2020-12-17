package com.steve.conf.system;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author steve
 * @date 2020/4/15
 */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "system")
public class SystemConfig {

    /**
     * oss 访问前缀
     */
    private String ossUrl = "";
}
