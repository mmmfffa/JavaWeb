package com.mf.Listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//统计在线人数
public class OnlineCountListener implements HttpSessionListener{
    //创建session的监听
    //一旦创建一个session就会触发
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        ServletContext context = httpSessionEvent.getSession().getServletContext();
//        System.out.println(httpSessionEvent.getSession().getId());
        Integer onlineCount = (Integer) context.getAttribute("OnlineCount");
        if (onlineCount==null){
            onlineCount= 1;
        }
        else{
            int count= onlineCount;
            onlineCount= count + 1;
        }
//        System.out.println(onlineCount);
        context.setAttribute("onlineCount",onlineCount);
    }
    //销毁session的监听
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        ServletContext context = httpSessionEvent.getSession().getServletContext();
        httpSessionEvent.getSession().invalidate();
        Integer onlineCount = (Integer) context.getAttribute("OnlineCount");
        if (onlineCount==null) onlineCount=0;
        else onlineCount-=1;
        context.setAttribute("onlineCount",onlineCount);
    }

    /*
    session销毁：
    1.手动：过期时间
    2.重启服务器
    * */
}
