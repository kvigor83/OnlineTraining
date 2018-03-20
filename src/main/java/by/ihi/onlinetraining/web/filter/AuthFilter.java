package by.kastsiuchenka.onlinetraining.web.filter;


import by.kastsiuchenka.onlinetraining.web.command.CommandType;
import by.kastsiuchenka.onlinetraining.web.handler.RequestHandler;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static by.kastsiuchenka.onlinetraining.web.command.CommandType.LOGIN;


//@WebFilter(urlPatterns = {"/frontController"})
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        CommandType type = RequestHandler.getCommand(req);
        if (LOGIN.equals(type)) {
            String contextPath = req.getContextPath();
            HttpSession session = req.getSession();
            if((session.getAttribute("user") != null)) {
                res.sendRedirect(contextPath + "/frontController?command=home");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}