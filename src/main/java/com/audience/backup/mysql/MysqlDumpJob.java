package com.audience.backup.mysql;

import com.audience.backup.entity.MysqlParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class MysqlDumpJob implements Job {

    @Resource
    private MysqlParam mysqlParam;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
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
