package com.audience.backup.entity;

import lombok.Data;

/**
 * @Author audience7510
 * @Date 2022/6/17
 * @ClassName SysUnit
 * @Description
 * @Version 1.0
 */
@Data
public class SysUnit {
    private Integer id;
    private String quantity;
    private String symbol;
    private String unit;
    private String name;
}
