package com.audience.backup.entity;


import lombok.Data;

@Data
public class ClusterInfo {
    private String monitorTimestamp;
    private String deviceName;
    private Long deviceCode;
    private Double deviceValue;
}