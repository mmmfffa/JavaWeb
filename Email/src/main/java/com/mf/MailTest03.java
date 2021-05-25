package com.mf;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.net.URLEncoder;
import java.util.Properties;

//发送一封带附件邮件
public class MailTest03 {
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
        MimeMessage message = imageMail(session);
        //==================================复杂邮件内容=======================
        //5.发送邮件
        ts.sendMessage(message,message.getAllRecipients());
        ts.close();
    }
    public static MimeMessage imageMail(Session session) throws Exception {
        //消息的固定信息
        MimeMessage message = new MimeMessage(session);
        //指明邮件发送人
        message.setFrom(new InternetAddress("3460942872@qq.com"));
        //指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO,new InternetAddress("1106486773@qq.com"));
        //邮件的标题
        message.setSubject("带附件的邮件");
        //==================================复杂邮件内容=======================
        //邮件内容：1.图片  2.附件  3.文本
        //准备图片数据
        MimeBodyPart image = new MimeBodyPart();
        DataHandler dh = new DataHandler(new FileDataSource("D:\\IDEA\\MyProject\\KuangStudy\\JavaWeb\\Email\\target\\classes\\雨.png"));
        image.setDataHandler(dh);
        image.setContentID(URLEncoder.encode("雨.png","UTF-8"));
        //准备正文数据
        MimeBodyPart text = new MimeBodyPart();
        text.setContent("邮件带有<img src='cid:"+URLEncoder.encode("雨.png","UTF-8")+"'>的图片哦！","text/html;charset=UTF-8");
        //准备附件,
        MimeBodyPart attachment = new MimeBodyPart();
        FileDataSource fileDataSource = new FileDataSource("D:\\IDEA\\MyProject\\KuangStudy\\JavaWeb\\Email\\src\\main\\resources\\ccf.pdf");
        attachment.setDataHandler(new DataHandler(fileDataSource));
        attachment.setFileName(MimeUtility.encodeText(fileDataSource.getName()));

        //描述数据关系
        MimeMultipart mm = new MimeMultipart();
        mm.addBodyPart(text);
        mm.addBodyPart(image);
        mm.setSubType("related");

        MimeBodyPart contentText = new MimeBodyPart();
        contentText.setContent(mm);

        MimeMultipart mm2 = new MimeMultipart();
        mm2.addBodyPart(attachment);
        mm2.addBodyPart(contentText);
        mm2.setSubType("mixed");

        //设置到消息中，保存修改
        message.setContent(mm2);
        message.saveChanges();
        return message;
    }
}
