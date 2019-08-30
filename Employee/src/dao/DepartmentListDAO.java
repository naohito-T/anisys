package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.DepartmentListDTO;
import util.DBConnector;

public class DepartmentListDAO {

	public List<DepartmentListDTO> departmentList() {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<DepartmentListDTO> departmentList = new ArrayList<DepartmentListDTO>();
		String sql = "select * from mst_department order by departmentId asc";

		try {

			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				DepartmentListDTO dto = new DepartmentListDTO();
				dto.setDepartmentId(rs.getString("departmentId"));
				dto.setDepartmentName(rs.getString("departmentName"));
				departmentList.add(dto);
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
		return departmentList;
	}

}
