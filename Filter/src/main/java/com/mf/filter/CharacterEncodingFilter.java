package com.mf.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    //初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CharacterEncodingFilter正在初始化....");
    }
   /*
    Chain:链
    1.过滤器中的所有代码，在过滤特定请求的时候都会执行
    2，必须要让过滤器继续通行filterChain.doFilter
   * */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=UTF-8");
        System.out.println("过滤器执行前....");
        filterChain.doFilter(servletRequest,servletResponse);//多个过滤器，让请求继续走，不写则停止请求程序
        System.out.println("过滤器执行后...");
    }
    //销毁
    @Override
    public void destroy() {
        System.out.println("CharacterEncodingFilter正在销毁.....");
    }
}
