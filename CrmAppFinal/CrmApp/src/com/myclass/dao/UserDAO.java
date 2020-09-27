package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnect;
import com.myclass.dto.UserDTO;
import com.myclass.entity.User;;

public class UserDAO {
	// get list
	public List<User> getList() {
		List<User> userList = new ArrayList<User>();
		User user = null;
		String query = "select * from users";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				user = new User();
				user.setId(result.getInt("id"));
				user.setFullname(result.getString("fullname"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				user.setRoleId(result.getInt("role_id"));
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}

	public List<UserDTO> getDTOList() {
		List<UserDTO> userList = new ArrayList<UserDTO>();
		UserDTO user = null;
		String query = "select u.id,u.fullname, u.email, u.password, r.name from users u join roles r on u.role_id = r.id";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				user = new UserDTO();
				user.setId(result.getInt("id"));
				user.setFullname(result.getString("fullname"));
				user.setEmail(result.getString("email"));
				user.setPassword(result.getString("password"));
				user.setRoleName(result.getString("name"));
				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public UserDTO getUserDTO(int id) {
		UserDTO user = null;
		String query = "select u.id,u.fullname, u.email, r.description from users u join roles r on u.role_id = r.id where u.id = ?";
		try (Connection conn = JDBCConnect.getConnection()){
			PreparedStatement statment = conn.prepareStatement(query);
			statment.setInt(1, id);
			ResultSet result = statment.executeQuery();
			while(result.next()) {
				user = new UserDTO();
				user.setId(result.getInt("id"));
				user.setFullname(result.getString("fullname"));
				user.setEmail(result.getString("email"));
				user.setRoleName(result.getString("description"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	// get user
	public User getUser(int id) {
		User user = new User();
		String query = "select * from users where id = ?";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				user.setId(result.getInt("id"));
				user.setFullname(result.getString("fullname"));
				user.setPassword(result.getString("password"));
				user.setEmail(result.getString("email"));
				user.setRoleId(result.getInt("role_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	// set user
	public void setUser(User user) {
		String query = "update users set fullname = ?, email= ?, role_id = ? where id = ?";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, user.getFullname());
			statement.setString(2, user.getEmail());
			statement.setInt(3, user.getRoleId());
			statement.setInt(4, user.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// delete user
	public void deleteUser(int id) {
		String query = "delete from users where id = ?";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// add user
	public void addUser(User user) {
		String query = "insert into users(fullname, password, email, role_id) values(?,?,?,?)";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, user.getFullname());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getEmail());
			statement.setInt(4, user.getRoleId());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ham khoi tao
	public UserDAO() {

	}
}
