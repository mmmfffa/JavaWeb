package com.mf;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.net.URLEncoder;
import java.util.Properties;

//发送一封带图片邮件
public class MailTest02 {
    public static void main(String[] args) throws Exception {
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
        ts.connect("smtp.qq.com","3460942872@qq.com","nrsifyrlsqsudbjb");
        //4.创建邮件
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件发送人
        message.setFrom(new InternetAddress("3460942872@qq.com"));
        //指明邮件的收件人,自己给自己发
        message.setRecipient(Message.RecipientType.TO,new InternetAddress("1106486773@qq.com"));
        //邮件的标题
        message.setSubject("带图片的邮件");
        //==================================复杂邮件内容=======================
        //准备图片数据
        MimeBodyPart image = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("D:\\IDEA\\MyProject\\KuangStudy\\JavaWeb\\Email\\target\\classes\\雨.png"));
        image.setDataHandler(dh);
        image.setContentID(URLEncoder.encode("雨.png","UTF-8"));
        //准备正文数据
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("邮件带有<img src='cid:"+URLEncoder.encode("雨.png","UTF-8")+"'>的图片哦！","text/html;charset=UTF-8");
        //描述数据关系
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(image);
        mm.setSubType("related");
        //设置到消息中，保存修改
        message.setContent(mm);
        message.saveChanges();
        //==================================复杂邮件内容=======================
        //5.发送邮件
        ts.sendMessage(message,message.getAllRecipients());
        ts.close();

    }
}
