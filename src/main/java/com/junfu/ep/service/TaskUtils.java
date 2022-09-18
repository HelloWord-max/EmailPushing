package com.junfu.ep.service;

import com.junfu.ep.po.Weather;
import com.junfu.ep.service.MailService;
import com.junfu.ep.utils.MyHutool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;


@Slf4j
@Component
@EnableAsync
@EnableScheduling
public class TaskUtils {

    private MailService mailService;

    @Autowired
    public void getMailService(MailService mailService){
        this.mailService = mailService;
    }

    /**
     * @description 定时每天早上7点发送天气信息
     */
//    @Scheduled(cron = "0 15 7 * * ?")
    @Scheduled(fixedRate = 5000)
    public void task(){
//        mailService.sendWeatherHtmlMail("天气小提示","天气小提示","2582025269@qq.com","","101040100");
//        mailService.sendWeatherHtmlMail("天气小提示","天气小提示","1101460133@qq.com","2582025269@qq.com","101230601");
//        mailService.sendWeatherHtmlMail("天气小提示","天气小提示","2585815471@qq.com","","101040100");
//        mailService.sendWeatherHtmlMail("天气小提示","天气小提示","3038886588@qq.com","","101040100");
//        mailService.sendWeatherHtmlMail("天气小提示","天气小提示","894587550@qq.com","","101040100");
//        mailService.sendWeatherHtmlMail("天气小提示","天气小提示","1035528660@qq.com","","101040100");
//        mailService.sendWeatherHtmlMail("天气小提示","天气小提示","1769341067@qq.com","","101040100");
//        mailService.sendWeatherHtmlMail("天气小提示","天气小提示","2534775143@qq.com","","101040100");
//        mailService.sendWeatherHtmlMail("天气小提示","天气小提示","1578588205@qq.com","","101030100");
        mailService.sendWeatherHtmlMail("天气小提示","天气小提示","1829617604@qq.com","","101040100");
    }
}
