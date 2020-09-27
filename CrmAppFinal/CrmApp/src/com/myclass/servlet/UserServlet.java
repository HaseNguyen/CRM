package com.myclass.servlet;

import com.myclass.dao.RoleDAO;
import com.myclass.dao.UserDAO;
import com.myclass.urls.UrlConstructors;
import com.myclass.entity.User;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

@WebServlet(name = "UserServlet", urlPatterns = { "/user", "/user/add", "/user/edit", "/user/delete", "/user/details" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String ServletUrl = "/user";
	private UserDAO userDao = null;
	private RoleDAO roleDao = null;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		userDao = new UserDAO();
		roleDao = new RoleDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case "/user": {
			getUserList(req, resp);
			UrlConstructors.getDispatcher(ServletUrl + "/index.jsp", req, resp);
			break;
		}
		case "/user/add": {
			req.setAttribute("roleList", roleDao.getList());
			UrlConstructors.getDispatcher(ServletUrl + "/add.jsp", req, resp);
			break;
		}
		case "/user/edit": {
			req.setAttribute("roleList", roleDao.getList());
			getUser(req, resp);
			UrlConstructors.getDispatcher(ServletUrl + "/edit.jsp", req, resp);
			break;
		}
		case "/user/details": {
			int id = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("user", userDao.getUserDTO(id));
			UrlConstructors.getDispatcher(ServletUrl + "/details.jsp", req, resp);
			break;
		}
		case "/user/delete": {
			deleteUser(req, resp);
			UrlConstructors.getRedirect(ServletUrl, req, resp);
			break;
		}
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();
		switch (action) {
		case "/user/add":
			addUser(req, resp);
			UrlConstructors.getRedirect(ServletUrl, req, resp);
			break;
		case "/user/edit":
			setUser(req, resp);
			UrlConstructors.getRedirect(ServletUrl, req, resp);
			break;
		default:
			break;
		}
	}

	// get user List
	private void getUserList(HttpServletRequest req, HttpServletResponse resp) {
		req.setAttribute("userList", userDao.getDTOList());
	}

	// get user
	private void getUser(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("user", userDao.getUser(id));
	}

	// set User
	private void setUser(HttpServletRequest req, HttpServletResponse resp) {
		User user = new User();
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("fullname");
		String email = req.getParameter("email");
		int roleId = Integer.parseInt(req.getParameter("roleId"));
		user.setId(id);
		user.setFullname(name);
		user.setEmail(email);
		user.setRoleId(roleId);
		userDao.setUser(user);
	}

	// delete User
	private void deleteUser(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		userDao.deleteUser(id);
	}

	// add User
	private void addUser(HttpServletRequest req, HttpServletResponse resp) {
		User user = new User();
		String name = req.getParameter("fullname");
		String pass = req.getParameter("password");
		String email = req.getParameter("email");
		String hashed = BCrypt.hashpw(pass, BCrypt.gensalt());
		int roleId = Integer.parseInt(req.getParameter("roleId"));
		user.setFullname(name);
		user.setPassword(hashed);
		user.setEmail(email);
		user.setRoleId(roleId);
		userDao.addUser(user);
	}
}
