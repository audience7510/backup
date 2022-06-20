package com.audience.backup.daock;

import com.audience.backup.entity.ClusterInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ClickhouseMapper {

    List<ClusterInfo> selectList();

    List<String> getString(Long val);

    Long getCount();
}