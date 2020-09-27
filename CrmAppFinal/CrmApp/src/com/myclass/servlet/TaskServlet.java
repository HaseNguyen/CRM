package com.myclass.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myclass.dao.JobDAO;
import com.myclass.dao.TaskDAO;
import com.myclass.dao.UserDAO;
import com.myclass.dto.UserDTO;
import com.myclass.entity.Task;
import com.myclass.urls.UrlConstructors;

@WebServlet(urlPatterns = { "/task", "/task/add", "/task/edit", "/task/delete", "/task/process" })
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String ServletUrl = "/task";
	private TaskDAO taskDao = null;
	private UserDAO userDao = null;
	private JobDAO jobDao = null;

	@Override
	public void init() throws ServletException {
		taskDao = new TaskDAO();
		userDao = new UserDAO();
		jobDao = new JobDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();
		switch (action) {
		case ServletUrl: {
			UserDTO userDto = (UserDTO) req.getSession().getAttribute("LoginInfo");
			if (!userDto.getRoleName().equals("ROLE_USER")) {
				req.setAttribute("taskList", taskDao.getDTOList());
			} else {
				req.setAttribute("taskList", taskDao.getDTOListUser(userDto.getId()));
			}
			UrlConstructors.getDispatcher(ServletUrl + "/index.jsp", req, resp);
			break;
		}
		case ServletUrl + "/add": {
			req.setAttribute("userList", userDao.getList());
			req.setAttribute("jobList", jobDao.getList());
			UrlConstructors.getDispatcher(ServletUrl + "/add.jsp", req, resp);
			break;
		}
		case ServletUrl + "/edit": {
			int id = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("userList", userDao.getList());
			req.setAttribute("jobList", jobDao.getList());
			req.setAttribute("task", taskDao.getTaskDTO(id));
			UrlConstructors.getDispatcher(ServletUrl + "/edit.jsp", req, resp);
			break;
		}
		case ServletUrl + "/delete": {
			int id = Integer.parseInt(req.getParameter("id"));
			taskDao.deleteTask(id);
			UrlConstructors.getRedirect(ServletUrl, req, resp);
			break;
		}
		case ServletUrl + "/process": {
			int id = Integer.parseInt(req.getParameter("id"));
			Task task = taskDao.getStatus(id);
			if (task.getStatusId() < 3) {
				task.setStatusId(task.getStatusId() + 1);
			} else {
				task.setStatusId(task.getStatusId());
			}
			taskDao.setTaskStatus(task);
			UrlConstructors.getRedirect(ServletUrl, req, resp);
			break;
		}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case ServletUrl + "/add": {
			try {
				addTask(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			UrlConstructors.getRedirect(ServletUrl, req, resp);
			break;
		}
		case ServletUrl + "/edit": {
			try {
				updateTask(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			UrlConstructors.getRedirect(ServletUrl, req, resp);
			break;
		}
		}
	}

	private void addTask(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Task task = new Task();
		String name = req.getParameter("taskName");
		Date startDate = convertDate(req.getParameter("taskStart"));
		Date endDate = convertDate(req.getParameter("taskEnd"));
		int userId = Integer.parseInt(req.getParameter("userGet"));
		int jobId = Integer.parseInt(req.getParameter("jobGet"));
		task.setName(name);
		task.setStartDate(startDate);
		task.setEndDate(endDate);
		task.setUserId(userId);
		task.setJobId(jobId);
		taskDao.addTask(task);
	}

	private void updateTask(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Task task = new Task();
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("taskName");
		Date startdDate = convertDate(req.getParameter("taskStart"));
		Date endDate = convertDate(req.getParameter("taskEnd"));
		int userId = Integer.parseInt(req.getParameter("userGet"));
		int jobId = Integer.parseInt(req.getParameter("jobGet"));
		task.setId(id);
		task.setName(name);
		task.setStartDate(startdDate);
		task.setEndDate(endDate);
		task.setUserId(userId);
		task.setJobId(jobId);
		taskDao.setTask(task);
	}

	private Date convertDate(String string) throws Exception {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(string);
		return date;
	}
}
