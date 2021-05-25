package com.mf.utils;

import com.mf.pojo.User;
import com.sun.mail.util.MailSSLSocketFactory;
import lombok.SneakyThrows;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

//网站3秒原则：多线程实现用户体验，异步
public class SendMail extends Thread{
    private String from="3460942872@qq.com";
    private String username="大公司";
    private String password="nrsifyrlsqsudbjb";
    private String host="smtp.qq.com";
    private User user;

    public SendMail(User user) {
        this.user = user;
    }

    @SneakyThrows
    @Override
    public void run() {
        Properties prop = new Properties();
        //设置邮件服务器
        prop.setProperty("mail.host","smtp.qq.com");
        //邮件发送协议
        prop.setProperty("mail.transport.protocol","smtp");
        //验证用户密码
        prop.setProperty("mail.smtp.auth","true");
        //若是qq邮箱还需下面几步设置ssl加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable","true");
        prop.put("mail.smtp.ssl.socketFactory",sf);

        //使用JavaMail发送邮件5步骤
        //1.创建定义整个应用程序所需的环境信息的session对象
        //这段代码qq才有
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                //发件人邮件用户名、授权码
                return new PasswordAuthentication("3460942872@qq.com", "nrsifyrlsqsudbjb");
            }
        });
        //开启session的debug模式，可以查看程序发送Email的运行状态
        session.setDebug(true);
        //2.通过session得到transport对象
        Transport ts = session.getTransport();
        //3.使用邮箱的用户名和授权码连上邮件服务器
        ts.connect(host,username,password);
        //4.创建邮件
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件发送人
        message.setFrom(new InternetAddress(from));
        //指明邮件的收件人,自己给自己发
        message.setRecipient(Message.RecipientType.TO,new InternetAddress(user.getEmail()));
        //邮件的标题
        message.setSubject("欢迎注册！");
        String info="注册成功，您的用户名为："+user.getUsername()+"\n密码为为："+user.getPassword()+"\n祝您使用愉快";
        message.setContent(info,"text/html;charset=UTF-8");
        message.saveChanges();
        //5.发送邮件
        ts.sendMessage(message,message.getAllRecipients());
        ts.close();
    }
}
