package com.audience.backup.mysql;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class MysqlDumpScheduler {
    //创建调度器
    public static Scheduler getScheduler() throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory.getScheduler();
    }

    // 执行任务
    public static void run(){
        try{
            //创建任务
            JobDetail jobDetail = JobBuilder.newJob(MysqlDumpJob.class)
                    .withIdentity("testJob01", "group11").build();
            //创建触发器 每天凌晨1点执行一次  cron =0 0 1 * * ?
            //每周五下午18点执行一次  cron =0 0 18 ? * 6
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group21")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 1 * * ?"))
                    .build();
            Scheduler scheduler = getScheduler();
            //将任务及其触发器放入调度器
            scheduler.scheduleJob(jobDetail, trigger);
            //调度器开始调度任务
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
