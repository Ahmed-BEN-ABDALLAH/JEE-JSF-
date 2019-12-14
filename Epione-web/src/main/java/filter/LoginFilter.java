package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ManagerBean.UserManagement;

public class LoginFilter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		System.out.println(" url = " + ((HttpServletRequest) servletRequest).getRequestURL());

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		UserManagement identity = (UserManagement) request.getSession().getAttribute("identity");
		if (identity != null && identity.getLoggedInAsAgent() == true) {
			filterChain.doFilter(request, response);
		} else if (request.getRequestURL().toString().contains("Login.jsf")) {
			filterChain.doFilter(request, response);

		} else {
			response.sendRedirect(request.getContextPath() + "/Login.jsf");
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
