package com.mf.study01;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author mf
 * @create 2021-05-19-12:55
 */
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取文件下载的路径
        String realPath = "D:\\IDEA\\MyProject\\KuangStudy\\JavaWeb\\Response\\target\\classes\\成徐.jpeg";
        System.out.println("下载文件的路径" + realPath);
        //2.下载的文件名是什么?
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //3.设置办法让浏览器能够支持(Content-Disposition)我们需要的东西
        //下载文件名处理中文乱码问题：URLEncoder.encode
        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
        //4.获取下载文件的输入流
        FileInputStream in = new FileInputStream(realPath);
        //5.创建缓冲区
        byte[] buffer = new byte[1024];
        int len=0;
        //6.获取OutputStream对象
        ServletOutputStream out = resp.getOutputStream();
        //7.将FileOutputStream流写入到buffer缓冲区,使用OutputStream将缓冲区中的数据输出到客户端
        while ((len=in.read(buffer))!=-1){
            out.write(buffer,0,len);
        }
        in.close();
        out.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
