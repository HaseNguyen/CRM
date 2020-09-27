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
import com.myclass.entity.Job;
import com.myclass.urls.UrlConstructors;

@WebServlet(name = "JobServlet", urlPatterns = { "/job", "/job/add", "/job/edit", "/job/delete" })
public class JobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String ServletUrl = "/job";
	private JobDAO jobDao = null;

	@Override
	public void init() throws ServletException {
		jobDao = new JobDAO();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getServletPath();
		switch (action) {
		case ServletUrl: {
			req.setAttribute("jobList", jobDao.getDTOList());
			UrlConstructors.getDispatcher(ServletUrl + "/index.jsp", req, resp);
			break;
		}
		case ServletUrl + "/add": {
			UrlConstructors.getDispatcher(ServletUrl + "/add.jsp", req, resp);
			break;
		}
		case ServletUrl + "/edit": {
			int id = Integer.parseInt(req.getParameter("id"));
			req.setAttribute("job", jobDao.getJobDTO(id));
			UrlConstructors.getDispatcher(ServletUrl + "/edit.jsp", req, resp);
			break;
		}
		case ServletUrl + "/delete": {
			int id = Integer.parseInt(req.getParameter("id"));
			jobDao.deleteJob(id);
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
				addJob(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			UrlConstructors.getRedirect(ServletUrl, req, resp);
			break;
		}
		case ServletUrl + "/edit": {
			try {
				updateJob(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
			UrlConstructors.getRedirect(ServletUrl, req, resp);
			break;
		}
		}
	}

	private void addJob(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Job job = new Job();
		String name = req.getParameter("jobName");
		Date jobStart = convertDate(req.getParameter("jobStart"));
		Date jobEnd = convertDate(req.getParameter("jobEnd"));
		job.setName(name);
		job.setStartDate(jobStart);
		job.setEndDate(jobEnd);
		jobDao.addJob(job);
	}

	private void updateJob(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Job job = new Job();
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("jobName");
		Date startDate = convertDate(req.getParameter("jobStart"));
		Date endDate = convertDate(req.getParameter("jobEnd"));
		job.setId(id);
		job.setName(name);
		job.setStartDate(startDate);
		job.setEndDate(endDate);
		jobDao.setJob(job);
	}

	private Date convertDate(String string) throws Exception {
		Date date = new SimpleDateFormat("dd/MM/yyyy").parse(string);
		return date;
	}
}
