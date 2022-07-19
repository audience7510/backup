package com.audience.backup.service.impl;

import com.audience.backup.clickhouse.ClickhouseBackup;
import com.audience.backup.service.ClickhouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClickhouseServiceImpl implements ClickhouseService {

    @Override
    public void ckBackup(String number) {
        if ("21".equals(number)){
            String ip = "127.0.0.1";
            String userName = "root";
            String passWord = "root";
            ClickhouseBackup.ckBackup(ip,userName,passWord);
        }else if ("25".equals(number)){
            String ip = "127.0.0.5";
            String userName = "root";
            String passWord = "root";
            ClickhouseBackup.ckBackup(ip,userName,passWord);
        }else {
            return;
        }
    }
}
