package com.audience.backup.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author audience7510
 * @Date 2022/6/17
 * @ClassName CkParam
 * @Description
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.ck")
public class CkParam {
    private String driverClassName;
    private String url;
    private String usr;
    private String password;
    private Integer initialSize;
    private Integer maxActive;
    private Integer minIdle;
    private Integer maxWait;
}
