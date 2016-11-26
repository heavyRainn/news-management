package com.epam.newsmanagement.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Sets request/response character encoding to the one specified in web.xml
 */

@WebFilter(urlPatterns = {"/*"}, initParams = {
                @WebInitParam(name = "encoding", value = "UTF-8")})
public class EncodingFilter implements Filter {

    public static final String ENCODING = "encoding";

    private String code;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String codeRequest = servletRequest.getCharacterEncoding();

        if (!code.equalsIgnoreCase(codeRequest)) {
            servletRequest.setCharacterEncoding(code);
            servletResponse.setCharacterEncoding(code);
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter(ENCODING);

    }

    public void destroy() {
        code = null;

    }
}
