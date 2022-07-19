package com.audience.backup.config;

import com.audience.backup.clickhouse.ClickhouseBackupJob;
import com.audience.backup.mysql.MysqlDumpJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzSchedulerConfig {

    @Bean
    public JobDetail testQuartz1() {
        return JobBuilder.newJob(MysqlDumpJob.class).withIdentity("testTask1").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger1() {
        return TriggerBuilder.newTrigger().forJob(testQuartz1())
                .withIdentity("testTask1")
                //创建触发器 每天凌晨1点执行一次  cron =0 0 1 * * ?
                //每周五下午18点执行一次  cron =0 0 18 ? * 6
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 18 ? * 6"))
                .build();
    }

    @Bean
    public JobDetail testQuartz2() {
        return JobBuilder.newJob(ClickhouseBackupJob.class).withIdentity("testTask2").storeDurably().build();
    }

    @Bean
    public Trigger testQuartzTrigger2() {
        //每隔5秒执行一次 cron = */5 * * * * ?
        //每天早上五点执行一次 cron = 0 0 5 * * ?
        return TriggerBuilder.newTrigger().forJob(testQuartz2())
                .withIdentity("testTask2")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 5 * * ?"))
                .build();
    }
}
