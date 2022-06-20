package com.audience.backup.controller;


import com.audience.backup.service.MysqlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Slf4j
@RestController
public class TestSourceController {

//    @Resource
//    private ClickhouseService clickhouseService;
    @Resource
    private MysqlService mysqlService;


//    /**
//     * 测试走clickhouse
//     * @return
//     */
//    @GetMapping("/ckList")
//    public Result ckList() {
//        List<ClusterInfo> clusterInfos = clickhouseService.selectList();
//        log.info("数据条数"+clusterInfos.size());
//        return ResultUtil.success(clusterInfos);
//    }
//
//    @GetMapping("/mysqlList")
//    public Result mysqlList() {
//        List<SysUnit> sysUnits = mysqlService.selectList();
//        log.info("数据条数"+sysUnits.size());
//        return ResultUtil.success(sysUnits);
//    }
//    @GetMapping("/ckBack")
//    public void ckBack() {
//        try{
//            clickhouseService.ckBack();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    @GetMapping("/mysqlBackup")
    public void mysqlBackup() {
        mysqlService.mysqlBackup();
    }
}
