package com.audience.backup.controller;

import com.audience.backup.service.ClickhouseService;
import com.audience.backup.service.MysqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
public class TestSourceController {

    @Resource
    private MysqlService mysqlService;
    @Resource
    private ClickhouseService clickhouseService;

    @GetMapping("/mysqlBackup")
    public void mysqlBackup() {
        mysqlService.mysqlBackup();
    }

    @GetMapping("/ckBackup/{number}")
    public void ckBackup(@PathVariable String number) {
        clickhouseService.ckBackup(number);
    }
}
