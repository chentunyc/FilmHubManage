package com.mic.tech;

import java.util.Scanner;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
public class ForgetUserPasswordAction extends AbstractAction{
    private GlobalState state=null;
    private UserService userService=null;
    private Scanner scanner=null;
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
                //sendEmail(email);
                user.setPassword("123456789");
                userService.updateUser(user);
        }
    }
    private static void sendEmail(String email) {
        // 邮件服务器配置信息

        final String username = "807455309@qq.com"; // 发送人的QQ邮箱
        final String password = "nuwwgzrkisbwbfdb"; // 发送人的邮箱密码
        String toEmail = email; // 收件人的邮箱地址

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Subject Here");
            message.setText("Email Content Here");

            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
