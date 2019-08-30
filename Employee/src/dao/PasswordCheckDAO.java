package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnector;

public class PasswordCheckDAO {

	public boolean passCheck(String loginPass)  throws SQLException {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		boolean result = false;
		String sql = "SELECT COUNT(*) AS cnt FROM mst_employee WHERE password=?";

		try {
			System.out.println("重複確認中");
			PreparedStatement ps = con.prepareStatement(sql);
			/*ps.setInt(1, empNo);*/ //本来紐づけすればpasswordだけでいけるんじゃないんだろうか
			ps.setString(2, loginPass);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				if(rs.getInt("cnt") == 0){ //0がいない 1がいる。
					result = true;
				}
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		try {
			con.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		return result;
	}

}
