package com.mgco.daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mgco.beans.ClaimForm;
import com.mgco.beans.ClaimStatus;
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
	
	private void awardClaimID(int id) throws SQLException {
		Connection conn = cf.getConnection();
		try {
			String award;
			award = "UPDATE EMPLOYEES"
					+ "SET "
					+ "AVAILABLE_AMT = (AVAILABLE_AMT - (SELECT APPROVED_AMT FROM CLAIM WHERE CLAIM_ID = ?))"
					+ "WHERE "
					+ "EMPLOYEES.USER_LNAME = (SELECT LNAME FROM CLAIM WHERE CLAIM_ID = ?)";
			PreparedStatement ps = conn.prepareStatement(award);
			ps.setInt(1, id);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	
	private void sendMessage(ClaimStatus sc, String msg) throws SQLException {
		Connection conn = cf.getConnection();
		try {
			String message;
			message = "INSERT INTO MESSAGE VALUES("
						+ "MESSAGE_SEQ.NEXTVAL,"
						+ "CURRENT_TIMESTAMP,"
						+ "?,"
						+ "?,"
						+ "?,"
						+ "?)";
			PreparedStatement ps = conn.prepareStatement(message);
			ps.setInt(1, sc.getApprID());
			ps.setInt(2, sc.getEmpID());
			ps.setInt(3, sc.getClaimID());
			ps.setString(4, msg);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}
	
	public void updateStatus(ClaimStatus sc) throws SQLException {
		String sql = null;
		switch (sc.getTitle()) {
		case "Supervisor":
			switch (sc.getClaimStatus()) {
			case "ApproveForm":
				sql = "UPDATE CLAIM SET CLAIM_STATUS = 2 WHERE CLAIM_ID ="+sc.getClaimID();
				sendMessage(sc, "Supervisor "+sc.getApprID()+" approved claim #"+sc.getClaimID());
				break;
			case "DenyForm":
				sql = "UPDATE CLAIM SET CLAIM_STATUS = 3 WHERE CLAIM_ID ="+sc.getClaimID();
				sendMessage(sc, "Supervisor "+sc.getApprID()+" denied claim #"+sc.getClaimID()+"\n"+sc.getReason());
				break;
			}
			break;
		case "Department Head":
			switch (sc.getClaimStatus()) {
			case "ApproveForm":
				sql = "UPDATE CLAIM SET CLAIM_STATUS = 2 WHERE CLAIM_ID ="+sc.getClaimID();
				sendMessage(sc, "Supervisor "+sc.getApprID()+" approved claim #"+sc.getClaimID());
				break;
			case "DenyForm":
				sql = "UPDATE CLAIM SET CLAIM_STATUS = 3 WHERE CLAIM_ID ="+sc.getClaimID();
				sendMessage(sc, "Supervisor "+sc.getApprID()+" denied claim #"+sc.getClaimID()+"\n"+sc.getReason());
				break;
		}
			break;
		case "Employee":
			switch (sc.getClaimStatus()) {
			case "ApproveForm": 
				sql = "UPDATE CLAIM SET APPROVE_COORDINATOR = 'Approved',"
				+ "COORDINATOR_SUBMIT_DATE = CURRENT_TIMESTAMP,"
				+ "FORM_STATUS = 'Pending' WHERE ID ="+sc.getClaimID();
				sendMessage(sc, "Benefits Coordinator "+sc.getApprID()+" approved form #"+sc.getClaimID());
				break;
			case "DenyForm":
				sql = "UPDATE CLAIM SET APPROVE_COORDINATOR = 'Declined',"
				+ "COORDINATOR_SUBMIT_DATE = CURRENT_TIMESTAMP,"
				+ "REJECTION_JUSTIFY = '"+sc.getReason()+"', "
				+ "FORM_STATUS = 'Denied' WHERE ID ="+sc.getClaimID();
				sendMessage(sc, "Benefits Coordinator "+sc.getApprID()+" declined form #"+sc.getClaimID()+"\n"+sc.getReason());
				break;
			case "AcceptBenCoOffer": 
				sql = "UPDATE CLAIM SET ALTERED_FORM = 'Approved',"
				+ "FORM_STATUS = 'In-review' WHERE ID ="+sc.getClaimID();
				sendMessage(sc, "Associate "+sc.getApprID()+" accepted alternate offer on form #"+sc.getClaimID());
				break;
			case "DeclineBenCoOffer":
				sql = "UPDATE CLAIM SET ALTERED_FORM = 'Declined',"
				+ "FORM_STATUS = 'Canceled' WHERE ID ="+sc.getClaimID();
				sendMessage(sc, "Associate "+sc.getApprID()+" delined alternate offer on form #"+sc.getClaimID());
				break;
			case "SubmitFinalGrade": 
				sql = "UPDATE CLAIM SET ON_FINISH_GRADE = "+sc.getReason()
				+ " WHERE ID ="+sc.getClaimID();
				sendMessage(sc, "Associate "+sc.getApprID()+" submitted final grade "+sc.getReason()+" for form #"+sc.getClaimID());
				break;
			case "SubmitFinalPres":
				sql = "UPDATE CLAIM SET ON_FINISH_PRESENTATION = '"+sc.getReason()
				+ "' WHERE ID ="+sc.getClaimID();
				sendMessage(sc, "Associate "+sc.getApprID()+" submitted final presentation for form #"+sc.getClaimID());
				break;
			case "ConfirmPres": 
				sql = "UPDATE CLAIM SET FORM_STATUS = 'Approved' "
				+ "WHERE ID ="+sc.getClaimID();
				awardClaimID(sc.getEmpID());
				sendMessage(sc, "Reienbursement from form #"+sc.getClaimID()+" has been awarded");
				break;
			case "RejectPres":
				sql = "UPDATE CLAIM SET FORM_STATUS = 'Rejected',"
				+ "APPROVE_PRESENTATION = 'Rejected',"
				+ "REJECTION_JUSTIFY = 'Not a passing Presentation' "
				+ "WHERE CLAIM_ID ="+sc.getClaimID();
				sendMessage(sc, "Reienbursement from form #"+sc.getClaimID()+" has been denied"+"\n"+sc.getReason());
				break;
			case "AlterForm": 
				sql = "UPDATE CLAIM SET ALTERED_FORM = 'True', "
				+ "PENDING_REIMBURSEMENT = "+sc.getReason()
				+ "WHERE CLAIM_ID ="+sc.getClaimID();
				sendMessage(sc, "Benefits Coordinator#"+sc.getApprID()+" has altered form "+sc.getClaimID()+"\nPlease review the new offer");
				break;	
			default:
				break;
			} 
			break;
		default:
			break;
	}
	Connection conn = cf.getConnection();
	try {
		Statement stmt = conn.createStatement();
		stmt.executeQuery(sql);
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		conn.close();
	}
	}
	
	public int getNextClaimID() throws SQLException {
		String sql = "SELECT CLAIM_SEQ.NEXTVAL FROM DUAL";
		int result = 0;
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		result = rs.getInt(1);
		conn.close();
		return result;
	}
}
