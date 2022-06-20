package com.audience.backup.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.audience.backup.entity.CkParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.audience.backup.daock", sqlSessionFactoryRef = "ckSqlSessionFactory")
@Slf4j
public class DruidConfigByCk {
    @Resource
    private CkParam ckParam;

    @Bean(name = "ckDataSource")
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(ckParam.getUrl());
        datasource.setDriverClassName(ckParam.getDriverClassName());
        datasource.setInitialSize(ckParam.getInitialSize());
        datasource.setMinIdle(ckParam.getMinIdle());
        datasource.setMaxActive(ckParam.getMaxActive());
        datasource.setMaxWait(ckParam.getMaxWait());
        datasource.setUsername(ckParam.getUsr());
        datasource.setPassword(ckParam.getPassword());
        return datasource;
    }

    @Bean(name = "ckSqlSessionFactory")
    public SqlSessionFactory ckSessionFactory(@Qualifier("ckDataSource") DataSource datasource){
        SqlSessionFactory factory = null;
        try {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(datasource);
            bean.setMapperLocations(
                    // 设置mybatis的xml所在位置
                    new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/ck/*.xml"));
            factory = bean.getObject();
            factory.getConfiguration().setMapUnderscoreToCamelCase(true);
        }catch (Exception e){
            log.error("{}",e);
        }
        return factory;
    }

    @Bean("ckSqlSessionTemplate")
    public SqlSessionTemplate ckSessionTemplate(
            @Qualifier("ckSqlSessionFactory") SqlSessionFactory sessionfactory) {
        try {

            return new SqlSessionTemplate(sessionfactory);
        }catch (Exception e){
            log.error("{}",e);
        }
       return null;
    }
}
