package com.emp.framework.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@EnableTransactionManagement(proxyTargetClass = true)
@Configuration
@MapperScan("com.emp.**.mapper*")
public class MybatisPlusConfig {

    /*
     * 分页插件，自动识别数据库类型
     */
     @Bean
     public PaginationInterceptor paginationInterceptor() {
         return new PaginationInterceptor();
     }

     @Bean
     public OptimisticLockerInterceptor optimisticLockerInterceptor() {
         return new OptimisticLockerInterceptor();
     }

//     @Bean
//     public MetaObjectHandler metaObjectHandler() {
//         return new CustomMetaObjectHandler();
//     }

    /**
     * 自动填充功能
     * @return
     */
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MetaHandler());
        return globalConfig;
    }

}
