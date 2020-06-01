package com.mgco.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mgco.beans.Employee;
import com.mgco.dao.EmployeeDAO;
import com.mgco.util.ConnFactory;

public class EmployeeDAOImpl implements EmployeeDAO {
	public static ConnFactory cf = ConnFactory.getInstance();
	@Override
	public void insertEmployee(Employee e) throws SQLException {
		String sql = "{ call INSERT_EMPLOYEE(?,?,?,?,?,?)";
		Connection conn = cf.getConnection();
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1, e.getfName());
		call.setString(2, e.getlName());
		call.setInt(3, e.getTitle());
		call.setString(4, e.getUsername());
		call.setString(5, e.getPassword());
		call.setInt(6, e.getAmtBalance());
		call.close();
	}

	@Override
	public List<Employee> getEmployeeList() throws SQLException {
		String sql = "SELECT * FROM EMPLOYEE";
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Employee e = null;
		ArrayList<Employee> empList = new ArrayList<>();
		while (rs.next()) {
			e = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			empList.add(e);
		}
		return empList;
	}

	@Override
	public Employee getEmployeeByName(String filter) {
		String sql = "SELECT * FROM EMPLOYEES WHERE USERNAME = '"+filter+"'";
		Connection conn = cf.getConnection();
		Employee e = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				e = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7));
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return e;
	}
	
	public boolean getLogin(String user, String pass) {
		Employee e = getEmployeeByName(user);
		
		return checkPassword(user, pass);
	}
	
	public boolean checkPassword(String user, String pass) {
		Connection conn = cf.getConnection();
		String sql = "SELECT PASSWORD FROM EMPLOYEES WHERE USERNAME = ?";
		PreparedStatement ps;
		String s = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				s = rs.getString(1);
			}
		} catch (SQLException r) {
			r.printStackTrace();
		}
		if(s.equals(pass)) {
				return true;
			} else {
				return false;
			}
	}
	
	public Employee getEmployeeById(String filter) {
		String sql = "SELECT * FROM EMPLOYEE WHERE USER_ID = '"+filter+"'";
		Connection conn = cf.getConnection();
		Employee e = null;
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				e = new Employee(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6), rs.getInt(7));	
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return e;
	}

}
