package com.myclass.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.urls.UrlConstructors;

@WebServlet(name = "forbidden", urlPatterns = "/forbidden")
public class ForbiddenSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String ServletUrl = "/forbidden";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UrlConstructors.getDispatcher(ServletUrl + "/index.jsp", req, resp);
	}
}
