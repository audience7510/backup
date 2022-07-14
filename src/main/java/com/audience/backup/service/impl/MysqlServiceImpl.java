package com.audience.backup.service.impl;

import com.audience.backup.service.MysqlService;
import com.audience.backup.entity.MysqlParam;
import com.audience.backup.mysql.MySqlBackup;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class MysqlServiceImpl implements MysqlService {

    @Resource
    private MysqlParam mysqlParam;


    @Override
    public void mysqlBackup() {
        String url = mysqlParam.getUrl();
        String s = StringUtils.substringBetween(url, "//", "?");
        String[] split = s.split(":");
        String ip = split[0];
        log.info("ip："+ip);
        String s1 = split[1];
        String[] split1 = s1.split("/");
        String port = split1[0];
        log.info("port："+port);
        String db = split1[1];
        log.info("db："+db);
        String username = mysqlParam.getUsername();
        log.info("username："+username);
        String password = mysqlParam.getPassword();
        log.info("password："+password);
        MySqlBackup.dbbackup(ip,port,username,password,db);
    }
}
