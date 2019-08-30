package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnector;

public class DeleteDAO {

	public boolean deleteProduct(int productId) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		boolean result = false;
		int count = 0;
		String sql = "DELETE FROM mst_shouhin01 WHERE shouhin01Id=? OR parentId=? OR parentId in (SELECT shouhin01Id from mst_shouhin01 where parentId = ? )";
// 親ID指定の元全件削除する。
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, productId); // 親元のshouhin01Id
			ps.setInt(2, productId);// 親(shouhin01Id)に関連するparentId
			ps.setInt(3, productId);

			count = ps.executeUpdate();
			if (count > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DeleteDAOのdeleteProduct" + result);
		return result;
	}

// rowNo繰り上げ
	public boolean rowUp(int rowNo, int parentId) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		boolean result = false;
		int count = 0;
		String sql = "update mst_shouhin01 set rowNo = rowNo - 1 where rowNo >= ? AND parentId=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, rowNo);
			ps.setInt(2, parentId);
			count = ps.executeUpdate();
			if (count > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DeleteDAOのrowUp" + result);
		return result;
	}

// その商品のrowNo取得する。
	public int exitsRow(int productId) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		boolean result = false;
		int count = 0;
		String sql = "select rowNo from mst_shouhin01 where shouhin01Id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, productId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("rowNo");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("DeleteDAOのrowUp" + result);
		return count;
	}

}
