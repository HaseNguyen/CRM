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
import com.myclass.dto.TaskDTO;
import com.myclass.entity.Task;

public class TaskDAO {
	public List<Task> getList() {
		List<Task> taskList = new ArrayList<Task>();
		Task task = null;
		String query = "select * from tasks";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				task = new Task();
				task.setId(result.getInt("id"));
				task.setName(result.getString("name"));
				task.setStartDate(result.getDate("start_date"));
				task.setEndDate(result.getDate("end_date"));
				task.setUserId(result.getInt("user_id"));
				task.setJobId(result.getInt("job_id"));
				task.setStatusId(result.getInt("status_id"));
				taskList.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskList;
	}

	public List<TaskDTO> getDTOList() {
		List<TaskDTO> taskList = new ArrayList<TaskDTO>();
		TaskDTO task = null;
		String query = "select t.id, t.name, t.start_date, t.end_date, u.fullname, j.name as job_name, s.name as status_name from tasks t join users u on t.user_id = u.id join jobs j on t.job_id = j.id join status s on t.status_id = s.id";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				task = new TaskDTO();
				task.setId(result.getInt("id"));
				task.setName(result.getString("name"));
				task.setStartDate(dateFormat(result.getTimestamp("start_date")));
				task.setEndDate(dateFormat(result.getTimestamp("end_date")));
				task.setUserId(result.getString("fullname"));
				task.setJobId(result.getString("job_name"));
				task.setStatusId(result.getString("status_name"));
				taskList.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskList;
	}

	public List<TaskDTO> getDTOListUser(int id) {
		List<TaskDTO> taskList = new ArrayList<TaskDTO>();
		TaskDTO task = null;
		String query = "select t.id, t.name, t.start_date, t.end_date, u.fullname, j.name as job_name, s.name as status_name from tasks t join users u on t.user_id = u.id join jobs j on t.job_id = j.id join status s on t.status_id = s.id where u.id = ?";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				task = new TaskDTO();
				task.setId(result.getInt("id"));
				task.setName(result.getString("name"));
				task.setStartDate(dateFormat(result.getTimestamp("start_date")));
				task.setEndDate(dateFormat(result.getTimestamp("end_date")));
				task.setUserId(result.getString("fullname"));
				task.setJobId(result.getString("job_name"));
				task.setStatusId(result.getString("status_name"));
				taskList.add(task);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return taskList;
	}

	public TaskDTO getTaskDTO(int id) {
		TaskDTO task = null;
		String query = "select t.id, t.name, t.start_date, t.end_date, u.fullname, j.name as job_name, s.name as status_name from tasks t join users u on t.user_id = u.id join jobs j on t.job_id = j.id join status s on t.status_id = s.id where t.id = ?";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				task = new TaskDTO();
				task.setId(result.getInt("id"));
				task.setName(result.getString("name"));
				task.setStartDate(dateFormat(result.getTimestamp("start_date")));
				task.setEndDate(dateFormat(result.getTimestamp("end_date")));
				task.setUserId(result.getString("fullname"));
				task.setJobId(result.getString("job_name"));
				task.setStatusId(result.getString("status_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return task;
	}

	public Task getStatus(int id) {
		Task task = new Task();
		String query = "select id, status_id from tasks where id = ?";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				task.setId(result.getInt("id"));
				task.setStatusId(result.getInt("status_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return task;
	}

	public void addTask(Task task) {
		String query = "insert into tasks (name, start_date, end_date, user_id,job_id,status_id) values(?,?,?,?,?,?)";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, task.getName());
			statement.setTimestamp(2, convertTimeStamp(task.getStartDate()));
			statement.setTimestamp(3, convertTimeStamp(task.getEndDate()));
			statement.setInt(4, task.getUserId());
			statement.setInt(5, task.getJobId());
			statement.setInt(6, 1);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTask(Task task) {
		String query = "update tasks set name = ?, start_date = ?, end_date = ?, user_id = ?,job_id = ? where id = ?";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, task.getName());
			statement.setTimestamp(2, convertTimeStamp(task.getStartDate()));
			statement.setTimestamp(3, convertTimeStamp(task.getEndDate()));
			statement.setInt(4, task.getUserId());
			statement.setInt(5, task.getJobId());
			statement.setInt(6, task.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTaskStatus(Task task) {
		String query = "update tasks set status_id = ? where id = ?";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, task.getStatusId());
			statement.setInt(2, task.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteTask(int id) {
		String query = "delete from tasks where id = ?";
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
}
