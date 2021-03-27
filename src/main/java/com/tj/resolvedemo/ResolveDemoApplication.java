package com.tj.resolvedemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tj.resolvedemo.mapper")  //扫描mapper文件
public class ResolveDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResolveDemoApplication.class, args);
    }

}
