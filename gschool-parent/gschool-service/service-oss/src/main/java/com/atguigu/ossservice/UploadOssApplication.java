package com.atguigu.ossservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * UploadOssStarter
 *
 * @Author: wd
 * @CreateTime: 2020-04-11
 * @Description:
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.atguigu"})
public class UploadOssApplication {
    public static void main(String[] args) {
        SpringApplication.run(UploadOssApplication.class,args);
    }
}