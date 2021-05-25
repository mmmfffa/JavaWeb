package com.mf.filter;

import com.mf.utils.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resq=(HttpServletResponse) servletResponse;
        if(req.getSession().getAttribute(Constant.USER_SESSION)==null)
            resq.sendRedirect(req.getContextPath()+"/error.jsp");

        filterChain.doFilter(req,resq);
    }

    @Override
    public void destroy() {

    }
}
