package com.example.student_project;

import javax.servlet.*;
import java.io.IOException;

public class CharsetFilter implements Filter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) {
        encoding = filterConfig.getInitParameter("requestEncoding");
        if (encoding == null) {
            encoding = "UTF-8";
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // (see HTTP specification section 3.4.1)
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding(encoding);
        }
        // Set the default response content type and encoding
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
