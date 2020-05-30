package com.mgco.dao;

import java.sql.SQLException;
import java.util.List;

import com.mgco.beans.ClaimForm;

public interface ClaimFormDAO {
public void insertClaim(ClaimForm rf) throws SQLException;
	
	public List<ClaimForm> getClaimList() throws SQLException;
	
	public List<ClaimForm> getClaimById(int id) throws SQLException;
}
