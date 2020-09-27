package com.myclass.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.myclass.connection.JDBCConnect;
import com.myclass.entity.Role;

public class RoleDAO {
	// get list
	public List<Role> getList() {
		List<Role> roleList = new ArrayList<Role>();
		Role role = null;
		try (Connection conn = JDBCConnect.getConnection()) {
			String query = "select * from roles";
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				role = new Role();
				role.setRoleId(result.getInt("id"));
				role.setRoleName(result.getString("name"));
				role.setRoleText(result.getString("description"));
				roleList.add(role);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return roleList;
	}

	// get role
	public Role getRole(int id) {
		Role role = new Role();
		try (Connection conn = JDBCConnect.getConnection()) {
			String query = "select * from roles where id = ?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				role.setRoleId(result.getInt("id"));
				role.setRoleName(result.getString("name"));
				role.setRoleText(result.getString("description"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	// set role
	public void setRole(Role role) {
		String query = "update roles set name = ?, description = ? where id =?";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, role.getRoleName());
			statement.setString(2, role.getRoleText());
			statement.setInt(3, role.getRoleId());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// delete role
	public void deleteRole(int id) {
		String query = "delete from roles where id = ?";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// add role
	public void addRole(Role role) {
		String query = "insert into roles(name,description) values(?,?)";
		try (Connection conn = JDBCConnect.getConnection()) {
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, role.getRoleName());
			statement.setString(2, role.getRoleText());
			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ham khoi tao
	public RoleDAO() {

	}
}
