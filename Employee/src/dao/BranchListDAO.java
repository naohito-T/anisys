package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.BranchListDTO;
import util.DBConnector;

public class BranchListDAO {

	public List<BranchListDTO> branchFactoryList() {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<BranchListDTO> branchList = new ArrayList<BranchListDTO>();
		String sql = "select * from mst_branch order by branchId asc";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				BranchListDTO dto = new BranchListDTO();
				dto.setBranchId(rs.getInt("branchId"));
				dto.setBranchName(rs.getString("branchName"));
				branchList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return branchList;
	}



}
