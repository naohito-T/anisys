package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnector;

public class DeleteDAO {

	//削除
		public boolean deleteUser(int empNo) throws SQLException {
			DBConnector db = new DBConnector();
			Connection con = db.getConnection();
			boolean result = false;
			String sql = "update mst_employee set enable = 1 where empNo = ?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, empNo);

					 ps.execute();
					result = true;
			}catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				con.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
			return result;
		}
//管理者2人以上いるか
		public boolean adminCheck() throws SQLException {
			DBConnector db = new DBConnector();
			Connection con = db.getConnection();
			boolean result = false;
			String sql = "select count(*) as cnt from mst_employee where SUBSTR(roleNo,-1) = 1 AND enable = 0";

			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					if(rs.getInt("cnt") > 1){ //2人以上管理者がいる場合true
						result = true;
					}
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}try {
				con.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
			return result;
		}

}

