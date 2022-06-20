package com.audience.backup;

import com.audience.backup.mysql.MysqlDumpScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class BackupApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackupApplication.class, args);
        MysqlDumpScheduler.run();
        log.info("==服务启用成功==");
    }

}
