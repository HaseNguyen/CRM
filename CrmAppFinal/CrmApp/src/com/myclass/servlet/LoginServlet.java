package com.myclass.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;
import com.myclass.connection.JDBCConnect;
import com.myclass.dto.UserDTO;
import com.myclass.urls.UrlConstructors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = { "/login", "/logout" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String ServletUrl = "/login";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();
		switch (action) {
		case ServletUrl: {
			UrlConstructors.getDispatcher(ServletUrl + "/index.jsp", req, resp);
			break;
		}
		case "/logout": {
			if (req.getSession().getAttribute("LoginInfo") != null) {
				HttpSession session = req.getSession();
				session.invalidate();
				UrlConstructors.getRedirect(ServletUrl, req, resp);
			}
		}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		login(req, resp);
	}

	private void login(HttpServletRequest req, HttpServletResponse resp) {
		UserDTO userDto = new UserDTO();
		String email = req.getParameter("email");
		String pass = req.getParameter("password");
		String query = "select u.id, u.email, u.password, u.fullname, r.name from users u join roles r on u.role_id = r.id where u.email = ?";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				userDto.setId(rs.getInt("id"));
				userDto.setFullname(rs.getString("fullname"));
				userDto.setEmail(rs.getString("email"));
				userDto.setPassword(rs.getString("password"));
				userDto.setRoleName(rs.getString("name"));
			}
			if (userDto.getEmail() != null) {
				if (BCrypt.checkpw(pass, userDto.getPassword())) {
					HttpSession session = req.getSession();
					session.setAttribute("LoginInfo", userDto);
					session.setMaxInactiveInterval(60 * 60);
					UrlConstructors.getRedirect("/home", req, resp);
				} else {
					req.setAttribute("passAlert", "Sai mật khẩu hoặc Email!");
					UrlConstructors.getDispatcher(ServletUrl + "/index.jsp", req, resp);
				}
			} else {
				req.setAttribute("emailAlert", "Tài khoản không tồn tại!");
				UrlConstructors.getDispatcher(ServletUrl + "/index.jsp", req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
