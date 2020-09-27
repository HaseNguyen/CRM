package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.myclass.connection.JDBCConnect;
import com.myclass.dto.JobDTO;
import com.myclass.entity.Job;

public class JobDAO {
	public List<Job> getList() {
		List<Job> jobList = new ArrayList<Job>();
		Job job = null;
		String query = "select * from jobs";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				job = new Job();
				job.setId(result.getInt("id"));
				job.setName(result.getString("name"));
				job.setStartDate(result.getDate("start_date"));
				job.setEndDate(result.getDate("end_date"));
				jobList.add(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobList;
	}

	public List<JobDTO> getDTOList() {
		List<JobDTO> jobList = new ArrayList<JobDTO>();
		JobDTO job = null;
		String query = "select * from jobs";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				job = new JobDTO();
				job.setId(result.getInt("id"));
				job.setName(result.getString("name"));
				job.setStartDate(dateFormat(result.getTimestamp("start_date")));
				job.setEndDate(dateFormat(result.getTimestamp("end_date")));
				jobList.add(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jobList;
	}

	public Job getJob(int id) {
		Job job = null;
		String query = "select * from jobs where id = ?";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				job = new Job();
				job.setId(result.getInt("id"));
				job.setName(result.getString("name"));
				job.setStartDate(convertDate(result.getTimestamp("start_date")));
				job.setEndDate(convertDate(result.getTimestamp("end_date")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return job;
	}

	public JobDTO getJobDTO(int id) {
		JobDTO job = null;
		String query = "select * from jobs where id = ?";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				job = new JobDTO();
				job.setId(result.getInt("id"));
				job.setName(result.getString("name"));
				job.setStartDate(dateFormat(result.getTimestamp("start_date")));
				job.setEndDate(dateFormat(result.getTimestamp("end_date")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return job;
	}

	public void addJob(Job job) {
		String query = "insert into jobs(name, start_date, end_date) values(?,?,?)";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, job.getName());
			statement.setTimestamp(2, convertTimeStamp(job.getStartDate()));
			statement.setTimestamp(3, convertTimeStamp(job.getEndDate()));
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setJob(Job job) {
		String query = "update jobs set name = ?, start_date = ?, end_date = ? where id = ?";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, job.getName());
			statement.setTimestamp(2, convertTimeStamp(job.getStartDate()));
			statement.setTimestamp(3, convertTimeStamp(job.getEndDate()));
			statement.setInt(4, job.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteJob(int id) {
		String query = "delete from jobs where id = ?";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Timestamp convertTimeStamp(Date date) {
		Timestamp timeStamp = new Timestamp(date.getTime());
		return timeStamp;
	}

	private String dateFormat(Timestamp timeStamp) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		String date = dateFormatter.format(timeStamp.getTime());
		return date;
	}

	private Date convertDate(Timestamp timeStamp) {
		Date date = new Date(timeStamp.getTime());
		return date;
	}
}
