package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnector;


public class EmployeeEntryDAO {
//従業員登録
	public void entry(String empNo,String fullName,String kanaName,String loginId,String password, String mail,int branchId,int departmentId, int bossId,int userRole) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		//↓DBのテーブルと表示順を合わせなくても挿入可能。

		String sql = "INSERT INTO mst_employee(empId,branchId,departmentId,empNo,fullName,kanaName,loginId,password,mail,bossId,roleNo) values(employeeseq.nextval,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, branchId);
			ps.setInt(2, departmentId);
			ps.setString(3, empNo);
			ps.setString(4, fullName);
			ps.setString(5, kanaName);
			ps.setString(6, loginId);
			ps.setString(7, password);
			ps.setString(8, mail);
			ps.setInt(9, bossId);//番号で判別 =empNo
			ps.setString(10,String.format("%010d",userRole));
			ps.execute();
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			con.close();
		}
	}
//重複確認をする
	public boolean exitsUser(String empNo) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		boolean result = false;
		String sql = "select count(*) as cnt FROM mst_employee WHERE empNo=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, empNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				if (rs.getInt("cnt") > 0) { //0より多ければ重複。

					result = true;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			con.close();
		}

		return result;
	}
//従業員最後のNo出す

	public int empMaxNo() throws SQLException{
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int count = 1;
		String sql = "select MAX(empNo) FROM mst_employee";
		try{
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				count += rs.getInt("MAX(empNo)");
			}

	}catch (SQLException e) {
		e.printStackTrace();
	}finally {
		con.close();
	}
	return count;
	}

//更新
	public int empUpdate(String empNo,String fullName,String kanaName,String loginId,String password, String mail,int branchId,int departmentId,int bossId,int userRole) throws SQLException {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int count = 0;
		String sql = "UPDATE mst_employee SET empNo=? ,fullName=? ,kanaName=?, loginId=?,password=?,mail=?,branchId=?,departmentId=?,bossId=?,roleNo=?,enable=0 WHERE empId =?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, empNo);
			ps.setString(2, fullName);
			ps.setString(3, kanaName);
			ps.setString(4, loginId);
			ps.setString(5, password);
			ps.setString(6, mail);
			ps.setInt(7,branchId);
			ps.setInt(8, departmentId);
			ps.setInt(9, bossId);
			ps.setString(10,String.format("%010d",userRole));
			ps.setString(11, empNo);

			count = ps.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			con.close();
		}
		return count;
	}

//更新2
	public int empUpdate2(String empNo,String fullName,String kanaName,String loginId,String password, String mail,int branchId,int departmentId,int bossId,int userRole, int empId) throws SQLException {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int count = 0;
		String sql = "UPDATE mst_employee SET empNo=? ,fullName=? ,kanaName=?, loginId=?,password=?,mail=?,branchId=?,departmentId=?,bossId=?,roleNo=?,enable=0 WHERE empNo =?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, empNo);
			ps.setString(2, fullName);
			ps.setString(3, kanaName);
			ps.setString(4, loginId);
			ps.setString(5, password);
			ps.setString(6, mail);
			ps.setInt(7,branchId);
			ps.setInt(8, departmentId);
			ps.setInt(9, bossId);
			ps.setString(10,String.format("%010d",userRole));
			ps.setInt(11, empId);

			count = ps.executeUpdate();
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			con.close();
		}
		return count;
	}
//管理者確認 1人版
	public boolean adminCheck() throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		boolean result = false;
		String sql = "select count(*) as cnt from mst_employee where SUBSTR(roleNo,-1) = 1 AND enable = 0";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getInt("cnt") >= 1){ //1人以上管理者がいる場合登録できる。
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
//ユーザの権限確認。
	public boolean employeeCheck() throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		boolean result = false;
		String sql = "select count(*) as cnt from mst_employee where SUBSTR(roleNo,-1) = 1 AND enable = 0";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				if(rs.getInt("cnt") >= 1){ //1人以上管理者がいる場合登録できる。
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

//ユーザがいるか確認
	public boolean sameUser(String loginId,String password) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		boolean result = false;
		String sql = "select count(*) as cnt FROM mst_employee WHERE loginId=? AND password=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()){
				if (rs.getInt("cnt") > 0) { //1より大きい場合true
					result = true;
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			con.close();
		}

		return result;
	}


	//ユーザがいるか確認
		public boolean sameUserNear(String loginId,String password,int empNo) throws SQLException {
			DBConnector db = new DBConnector();
			Connection con = db.getConnection();
			boolean result = false;
			String sql = "select count(*) as cnt FROM mst_employee WHERE loginId=? AND password=? AND empNo=?";
			try {
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, loginId);
				ps.setString(2, password);
				ps.setInt(3, empNo);
				ResultSet rs = ps.executeQuery();
				while (rs.next()){
					if (rs.getInt("cnt") > 0) { //1より大きい場合true
						result = true;
					}
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}finally {
				con.close();
			}

			return result;
		}

}
