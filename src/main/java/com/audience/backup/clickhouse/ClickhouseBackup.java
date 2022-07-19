package com.audience.backup.clickhouse;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author audience7510
 * @Date 2022/6/24
 * @ClassName ClickhouseBackup
 * @Description
 * @Version 1.0
 */
@Slf4j
public class ClickhouseBackup {

    public static String getCommand(){
        String table1 = "db_ck1.table1";
        //逗号分隔
        String table2 = ",db_ck1.table2";
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        date = calendar.getTime();
        String dated = new SimpleDateFormat("yyyyMMdd").format(date);
        log.info("本次备份前一天日期为："+dated);
        StringBuilder builder = new StringBuilder();
        builder.append("clickhouse-backup create -t ");
        //园区表拼接前一天日期
        builder.append(table1).append(dated);
        builder.append(table2).append(dated);

        //前一天日期加拼接 _backup作为备份名，如20220715_backup
        builder.append(" ").append(dated).append("_backup");
        String command = builder.toString();
        log.info("备份命令："+command);
        return command;
    }
    public static void ckBackup(String ip,String userName,String passWord) {
        // String command = "clickhouse-backup create -t db_ck1.table1 java_test_backup";
        String command = getCommand();
        // 设置主机IP地址
        Connection c = new Connection(ip);
        try {
            c.connect();
            // 设置登录名称和登录密码
            boolean flag = c.authenticateWithPassword(userName, passWord);
            log.info("登录状态是否成功："+flag);
            Session session = c.openSession();
            session.execCommand(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(session.getStdout(), "UTF-8"));
            String line = null;
            log.info("备份命令执行日志输出======start======");
            while ((line = br.readLine()) != null) {
                log.info("line:" + line);
            }
            log.info("备份命令执行日志输出======end======");
            log.info("执行结束");
            session.close();
            c.close();
        } catch (Exception e) {
            log.info("命令执行异常");
            e.printStackTrace();
        }
    }
}
