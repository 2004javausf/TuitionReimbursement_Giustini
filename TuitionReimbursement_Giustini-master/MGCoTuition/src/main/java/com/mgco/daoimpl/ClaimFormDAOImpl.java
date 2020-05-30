package com.mgco.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mgco.beans.ClaimForm;
import com.mgco.dao.ClaimFormDAO;
import com.mgco.util.ConnFactory;

public class ClaimFormDAOImpl implements ClaimFormDAO {
	public static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public void insertClaim(ClaimForm rf) throws SQLException {
		String sql = "{ call INSERT_CLAIM(?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = cf.getConnection();
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, rf.getId());
		call.setString(2, rf.getfName());
		call.setString(3, rf.getlName());
		call.setInt(4, rf.getEventType());
		call.setString(5, rf.getEventDescription());
		call.setDate(6, rf.getEventDate());
		call.setInt(7, rf.getEventTime());
		call.setString(8, rf.getEventLocation());
		call.setInt(9, rf.getEventCost());
		call.setInt(10, rf.getGradeType());
		call.setString(11, rf.getJustification());
		call.setBlob(12, rf.getOptFile());
		call.setInt(13, rf.getAmtApproved());
		call.setInt(14, rf.getStatus());
		call.execute();
		call.close();
	}	

	@Override
	public List<ClaimForm> getClaimList() throws SQLException {
		String sql = "SELECT * FROM CLAIM";
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ClaimForm rf = null;
		ArrayList<ClaimForm> claimList = new ArrayList<>();
		while(rs.next()) {
			rf = new ClaimForm(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDate(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getBlob(12), rs.getInt(13), rs.getInt(14));
		claimList.add(rf);
		}
		return claimList;
	}

	@Override
	public List<ClaimForm> getClaimById(int id) throws SQLException {
		String sql = "SELECT * FROM CLAIM WHERE CLAIM_ID ="+id;
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		ClaimForm rf = null;
		ArrayList<ClaimForm> claimList = new ArrayList<>();
		while(rs.next()) {
			rf = new ClaimForm(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5), rs.getDate(6), rs.getInt(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getString(11), rs.getBlob(12), rs.getInt(13), rs.getInt(14));
		claimList.add(rf);
		}
		return claimList;
	}

}
