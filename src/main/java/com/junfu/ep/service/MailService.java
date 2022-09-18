package com.junfu.ep.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface MailService {
    void sendSimpleMail(String sub, String content, String to_list, String cc_list);
    void sendWeatherHtmlMail(String sub, String content, String to_list, String cc_list,String cityNumber);
}
