package com.junfu.ep.service.serviceImpl;

import com.junfu.ep.po.Weather;
import com.junfu.ep.service.MailService;
import com.junfu.ep.utils.MyHutool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailServiceImpl implements MailService {
    @Autowired
    protected JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    protected String from;

    /**
     * 发送纯文本格式的邮件
     * @param sub 主题
     * @param content 内容
     * @param to_list 发送人
     * @param cc_list 抄送人
     */
    public void sendSimpleMail(String sub, String content, String to_list, String cc_list) {
        //创建邮件内容
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from); //这里指的是发送者的账号
        message.setTo(to_list.split(","));
        message.setCc(cc_list.split(","));
        message.setSubject(sub);
        message.setText(content);
        //发送邮件
        javaMailSender.send(message);
    }

    /**
     * 发送html格式的邮件
     * @param sub 主题
     * @param content 内容
     * @param to_list 发送人
     * @param cc_list 抄送人
     */
    @Override
    public void sendWeatherHtmlMail(String sub, String content, String to_list, String cc_list,String cityNumber) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // true 表示创建一个multipart message
            helper.setFrom(from);
            helper.setSubject(sub);
            StringBuffer city = new StringBuffer();
            StringBuffer tip = new StringBuffer();
            StringBuffer publishTime = new StringBuffer();
            StringBuffer temp_day_c = new StringBuffer();
            StringBuffer temp_day_n = new StringBuffer();
            Weather weather = MyHutool.getWeather(cityNumber);
            StringBuffer htmlContent = new StringBuffer();
            htmlContent.append("<H1 style=\"color: rgb(213, 114, 216)\">这是今天的天气提醒哟！</H1>");
            htmlContent.append("</hr>");
            //设置最高温度
            temp_day_c.append("<h3 style=\"color: rgb(0, 255, 255)\">白天最高温度: ");
            temp_day_c.append("<i style=\"color: rgb(255, 108, 110)\">");
            temp_day_c.append(weather.getTemp_day_c());
            temp_day_c.append("℃</i>");
            temp_day_c.append("</h3>");
            //设置最低温度
            temp_day_n.append("<h3 style=\"color: rgb(0, 255, 255)\">夜间最低温度: ");
            temp_day_n.append("<i style=\"color: rgb(255, 108, 110)\">");
            temp_day_n.append(weather.getTemp_night_c());
            temp_day_n.append("℃</i>");
            temp_day_n.append("</h3>");
            //设置小提示
            tip.append("<h3 style=\"color: rgb(132, 198, 198)\">小提示: ");
            tip.append("<i style=\"color: rgb(255, 85, 127)\">");
            tip.append("❤");
            tip.append(weather.getTip());
            tip.append("❤");
            tip.append("</i>");
            tip.append("</h3>");
            //设置城市
            city.append("<h3 style=\"color: rgb(85, 170, 255)\"> 城市: ");
            city.append(weather.getCity());
            city.append("</h3>");
            //设置发布时间
            publishTime.append("<h3 style=\"color: rgb(255, 102, 163)\"> 发布时间: ");
            publishTime.append(weather.getPublishTime());
            publishTime.append("</h3>");

            htmlContent.append(temp_day_c);
            htmlContent.append(temp_day_n);
            htmlContent.append(tip);
            htmlContent.append(city);
            htmlContent.append(publishTime);
            htmlContent.append("<img src=\"https://pic1.zhimg.com/v2-9ba2e866837ec70034e2fa1d57c41378_b.webp\" />");
            helper.setText(htmlContent.toString(),true);
            helper.setTo(to_list.split(","));
//            helper.setCc(cc_list.split(","));
            //发送
            javaMailSender.send(message);
            System.out.println("发送成功");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
