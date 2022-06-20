package com.audience.backup.service.impl;


import com.alibaba.fastjson.JSON;
import com.audience.backup.service.ClickhouseService;
import com.audience.backup.daock.ClickhouseMapper;
import com.audience.backup.entity.ClusterInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.GZIPOutputStream;

@Slf4j
@Service
public class ClickhouseServiceImpl implements ClickhouseService {

    @Resource
    private ClickhouseMapper clickhouseMapper;

    @Override
    public List<ClusterInfo> selectList() {
        return clickhouseMapper.selectList();
    }

    private static final Long value = 500000L;
    @Override
    public void ckBack() throws Exception {
        OutputStream outputStream = new GZIPOutputStream(new FileOutputStream(new File("testBack")));
        Long count = clickhouseMapper.getCount();
        Long v = count/value;
        //余数加1多执行一次
        Long addV = v+1;
        for (int i = 0; i < addV; i++) {
            List<String> s = clickhouseMapper.getString(i*value);
            //todo 性能
            log.info(JSON.toJSONString(s));
        }

    }
}
