/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Mountain
 */
public class AuthenticationFilter implements Filter {

    /**
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     *
     * @param sr
     * @param sr1
     * @param fc
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response = (HttpServletResponse) sr1;
        if (isAuthenticated(request)) {
            fc.doFilter(request, response);
            return;
        }
        request.getRequestDispatcher("/view/checkSession.jsp").forward(request, response);
    }

    /**
     *
     */
    @Override
    public void destroy() {
    }

    public boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession() != null && request.getSession().getAttribute("account") != null;
    }

}
