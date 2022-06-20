package com.audience.backup.dao;

import com.audience.backup.entity.SysUnit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MysqlMapper {

    List<SysUnit> selectList();
}