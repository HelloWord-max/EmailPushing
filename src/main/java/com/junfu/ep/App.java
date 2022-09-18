package com.junfu.ep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
public class App {
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(App.class);
        ConfigurableEnvironment environment = app.run(args).getEnvironment();
        System.out.println("启动成功！！");
        String property = environment.getProperty("server.port");
        System.out.printf("地址: \thttp://localhost:%s",property);

    }
}
