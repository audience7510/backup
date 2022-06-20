package com.audience.backup.service;

import com.audience.backup.entity.SysUnit;

import java.util.List;

public interface MysqlService {
    List<SysUnit> selectList();

    void mysqlBackup();
}
