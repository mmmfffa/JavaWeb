package com.mf.study01;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author mf
 * @create 2021-05-19-16:19
 */
public class ImageServlet extends HttpServlet {

    //生成随机数
    private String makeRandom(){
        Random random = new Random();
        String num= random.nextInt(99999999)+"";
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i <8- num.length(); i++) {
            buffer.append("0");
        }
        num=num+buffer.toString();
        return num;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //如何让浏览器1s刷新一次
        resp.setHeader("refresh","5");
       //在内存中创建图片
        BufferedImage image = new BufferedImage(300, 80, BufferedImage.TYPE_INT_RGB);
       //得到图片
        Graphics2D graphics =(Graphics2D) image.getGraphics();//一只2D的笔
        graphics.setBackground(Color.white);
        graphics.fillRect(0,0,300,80);
        //给图片写数据
        graphics.setColor(Color.yellow);
        graphics.setFont(new Font(null,Font.BOLD,60));
        graphics.drawString(makeRandom(),10,60);
        //告诉浏览器这个请求用图片方式打开
        resp.setContentType("image/jpeg");
        //网站存在缓存，不让浏览器缓存
        resp.setDateHeader("expires",-1);
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Pragma","no-cache");
        //把图片写给图片
        ImageIO.write(image,"jpeg",resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
