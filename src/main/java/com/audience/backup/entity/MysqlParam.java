package com.audience.backup.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author audience7510
 * @Date 2022/6/17
 * @ClassName MysqlParam
 * @Description
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class MysqlParam {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
