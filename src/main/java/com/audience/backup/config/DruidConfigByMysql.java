package com.audience.backup.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.audience.backup.entity.MysqlParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


@Slf4j
@Configuration
@MapperScan(basePackages = "com.audience.backup.dao", sqlSessionFactoryRef = "mysqlSqlSessionFactory")
public class DruidConfigByMysql {

    @javax.annotation.Resource
    private MysqlParam mysqlParam;

    @Bean(name = "mysqlDataSource")
    @Primary
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(mysqlParam.getUrl());
        datasource.setDriverClassName(mysqlParam.getDriverClassName());
        datasource.setUsername(mysqlParam.getUsername());
        datasource.setPassword(mysqlParam.getPassword());
        return datasource;
    }

    @Bean(name = "mysqlSqlSessionFactory")
    @Primary
    public SqlSessionFactory mysqlSessionFactory(@Qualifier("mysqlDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        Resource[] mappers = ArrayUtils.addAll(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml")
        );
        bean.setMapperLocations(mappers);
        SqlSessionFactory factory = bean.getObject();
        assert factory != null;
        factory.getConfiguration().setMapUnderscoreToCamelCase(true);
        return factory;
    }

    @Bean("mysqlSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate mysqlSessionTemplate(
            @Qualifier("mysqlSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}