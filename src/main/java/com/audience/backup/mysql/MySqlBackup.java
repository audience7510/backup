package com.audience.backup.mysql;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.text.SimpleDateFormat;

/**
 * @Author audience7510
 * @Date 2022/6/20
 * @ClassName MySqlBack
 * @Description
 * @Version 1.0
 */
@Slf4j
public class MySqlBackup {

//    private static String dated = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());

    public static void dbbackup(String ip,String port,String username,String password,String db) {
        String dated = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
//        String winPath = "D:\\sqlBackup";
        String linuxPath = "/usr/local/sqlbackup";
        //保存路径，日期+mysqldump，例如：2020-12-12
//        String winSavePath=winPath + "\\" + dated;
        String linuxSavePath=linuxPath + "/" + dated;
        //文件名
        String fileName =  db + ".sql.gz";
        exportDatabaseTool(ip,port, username, password, linuxSavePath,fileName, db);
    }

    public static void exportDatabaseTool(String hostIP,String port, String userName, String password, String savePath,String fileName, String databaseName) {
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        StringBuilder stringBuilder = new StringBuilder();
        //导出数据的mysql命令
        //mysqldump -udcim -pDcim@2021 chindata_dcim t_inspection_term t_inspection_template | gzip > /usr/local/sqlbackup/bbb.sql.gz
        stringBuilder.append("mysqldump -u").append(userName).append(" -p").append(password);
        stringBuilder.append(" --host=").append(hostIP).append(" --port=").append(port);
        stringBuilder.append(" --skip-extended-insert --skip-lock-tables --skip-add-locks --set-gtid-purged=off ");
        stringBuilder.append(databaseName);
        stringBuilder.append(" | gzip > ");
        stringBuilder.append(savePath).append("/").append(fileName);
        log.info("mysqldump命令："+ stringBuilder);
        try {
            String[] cmds = {"/bin/sh","-c",stringBuilder.toString()};
            //runtime进行执行（本地环境如果执行的话，需要有mysql环境）
            Process process = Runtime.getRuntime().exec(cmds);
            if (process.waitFor() == 0) {// 0 表示线程正常终止。
                log.info("数据库备份成功");
            }
        } catch (Exception e) {
            log.info("数据库备份异常");
            e.printStackTrace();
        }
    }
}
