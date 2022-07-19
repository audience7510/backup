package com.audience.backup.clickhouse;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class ClickhouseBackupJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String ip21 = "127.0.0.1";
        String ip25 = "127.0.0.5";
        log.info("21服务器ck本地表备份开始");
        ClickhouseBackup.ckBackup(ip21,"root","root");
        log.info("21服务器ck本地表备份结束");
        log.info("\n");
        log.info("25服务器ck本地表备份开始");
        ClickhouseBackup.ckBackup(ip25,"root","root");
        log.info("25服务器ck本地表备份结束");
    }
}
