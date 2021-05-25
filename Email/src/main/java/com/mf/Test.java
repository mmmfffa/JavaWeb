package com.mf;

import javax.activation.FileDataSource;
import java.io.UnsupportedEncodingException;

public class Test {
    //简单邮件没有附件和图片，纯文本文件
    //要发送邮件需要获得协议和支持，开启服务POP3和SMTP！
    public static void main(String[] args) throws UnsupportedEncodingException {
        FileDataSource source = new FileDataSource("D:\\IDEA\\MyProject\\KuangStudy\\JavaWeb\\Email\\src\\main\\resources\\ccf.pdf");
        System.out.println(source.getName());
        System.out.println();
    }
}
