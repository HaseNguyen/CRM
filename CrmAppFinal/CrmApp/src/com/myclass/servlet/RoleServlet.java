package com.myclass.servlet;

import com.myclass.dao.RoleDAO;
import com.myclass.urls.UrlConstructors;
import com.myclass.entity.Role;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RoleServlet", urlPatterns = { "/role", "/role/add", "/role/edit", "/role/delete" })
public class RoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String ServletUrl = "/role";
	private RoleDAO dao = null;
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		dao = new RoleDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/role":
			getListRole(req);
			UrlConstructors.getDispatcher(ServletUrl + "/index.jsp", req, resp);
			break;
		case "/role/add":
			UrlConstructors.getDispatcher(ServletUrl + "/add.jsp", req, resp);
			break;
		case "/role/edit":
			getRole(req, resp);
			UrlConstructors.getDispatcher(ServletUrl + "/edit.jsp", req, resp);
			break;
		case "/role/delete":
			deleteRole(req, resp);
			UrlConstructors.getRedirect(ServletUrl, req, resp);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();
		switch (action) {
		case "/role/add":
			addRole(req);
			UrlConstructors.getRedirect(ServletUrl, req, resp);
			break;
		case "/role/edit":
			setRole(req, resp);
			UrlConstructors.getRedirect(ServletUrl, req, resp);
			break;
		default:
			break;
		}
	}

	// get list from Database
	private void getListRole(HttpServletRequest req) {
		req.setAttribute("roleList", dao.getList());
	}

	// get Role
	private void getRole(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("roleid"));
		req.setAttribute("role", dao.getRole(id));
	}

	// set Role
	private void setRole(HttpServletRequest req, HttpServletResponse resp) {
		Role role = new Role();
		int id = Integer.parseInt(req.getParameter("roleId"));
		String name = req.getParameter("roleName");
		String text = req.getParameter("roleText");
		role.setRoleId(id);
		role.setRoleName(name);
		role.setRoleText(text);
		dao.setRole(role);
	}

	// delete role
	private void deleteRole(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("roleid"));
		dao.deleteRole(id);
	}

	// Add role
	private void addRole(HttpServletRequest req) {
		Role role = new Role();
		String name = req.getParameter("roleName");
		String text = req.getParameter("roleText");
		role.setRoleName(name);
		role.setRoleText(text);
		dao.addRole(role);
	}

}
