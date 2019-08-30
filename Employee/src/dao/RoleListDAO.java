package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.RoleListDTO;
import util.DBConnector;

public class RoleListDAO {

	public List<RoleListDTO> roleFactoryList() {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<RoleListDTO> roleList = new ArrayList<RoleListDTO>();
		String sql = "select * from mst_role order by roleNo asc";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				RoleListDTO dto = new RoleListDTO();
				dto.setRoleId(rs.getInt("RoleNo"));
				dto.setRoleName(rs.getString("RoleName"));
				roleList.add(dto);
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
		return roleList;
	}

}
