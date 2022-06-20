package com.audience.backup.service;


import com.audience.backup.entity.ClusterInfo;

import java.util.List;

public interface ClickhouseService {
    List<ClusterInfo> selectList();

    void ckBack() throws Exception;
}
