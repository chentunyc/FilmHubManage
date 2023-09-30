package com.mic.tech;

import java.util.Scanner;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class ForgetUserPasswordAction extends AbstractAction{
    GlobalState state=null;
    UserService userService=null;
    Scanner scanner=null;
    ForgetUserPasswordAction(GlobalState state, UserService userService, Scanner scanner){
        this.state=state;
        this.userService=userService;
        this.scanner=scanner;
    }
    public String getDescription() {
        return "你可以找回密码 everyone";
    }

    public String getActionName() {
        return "FORGET_USER_PASSWORD";
    }

    public void run() {
            super.print("请输入用户名");
            String userName=scanner.nextLine();
            if(userService.getUserByUserName(userName)!=null) {
                super.print("请输入邮箱");
                String email = scanner.nextLine();
                User user = userService.getUserByUserName(userName);
                user.setPassword("123456789");
        }
    }
    // 发送邮件的方法
    private static void sendEmail(String email,String password) {
        // 邮件服务器配置信息
        String host = "smtp.qq.com"; // QQ邮箱服务器地址
        String port = "465"; // QQ邮箱服务器端口号
        String user = "807455309@qq.com"; // 发件人邮箱账号
        String password1 = "bnitfvxydlulbdgd"; // 发件人邮箱密码

        // 构造邮件内容
        String to = email; // 收件人邮箱账号
        String subject = "Your Random Password"; // 邮件主题
        String body = "Your random password is: " + password; // 邮件正文

        // 创建邮件消息对象
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.port", port);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password1);
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(subject);
            message.setText(body);
            // 发送邮件
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
