package com.myclass.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dto.UserDTO;
import com.myclass.urls.UrlConstructors;

@WebFilter(urlPatterns = { "/task/add", "/task/edit", "/task/delete" })
public class TaskFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (req.getSession().getAttribute("LoginInfo") != null) {
			UserDTO dto = (UserDTO) req.getSession().getAttribute("LoginInfo");
			if (!dto.getRoleName().equals("ROLE_USER")) {
				chain.doFilter(request, response);
			} else {
				UrlConstructors.getRedirect("/forbidden", req, resp);
			}
		} else {
			UrlConstructors.getRedirect("/login", req, resp);
		}
	}

}
