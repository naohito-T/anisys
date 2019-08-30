package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBConnector;

public class ProductEntryDAO {

	// 商品の行スペース追加      希望        parentId       exitsNo
	public int productRowEntry(int rowNo, int parentId, int minNo) throws SQLException {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		boolean result = false;
		int count = 0;
		   //mst_shouhin01テーブルのRowNoを+1しろ。条件として既存のNoと希望のNoの間。尚且つparentIdが等しいもの。
		String sql = "update mst_shouhin01 set rowNo = rowNo + 1 where rowNo >= ? AND rowNo <= ? AND parentId=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, minNo); //exitsNo
			ps.setInt(2, rowNo);
			ps.setInt(3, parentId);
			count = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		System.out.println("ProductEntryDAOのproductRowEntry" + result);
		return count;
	}

	// 商品追加
	public boolean productEntry(int rowNo, int productId, String productName) throws SQLException {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		boolean result = false;
		int count = 0;

		String sql = "INSERT INTO mst_shouhin01(shouhin01ID,rowNo,shouhin01Name,parentId) values(productseq.nextval,?,?,?) ";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, rowNo);
			ps.setString(2, productName);
			ps.setInt(3, productId); // 大分類parentId=0 中分類・小分類は遷移してきたproductIdがparentIdに
			count = ps.executeUpdate();
			if (count > 0) {
				result = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		System.out.println("ProductEntryDAOのproductEntry" + result);
		return result;
	}

// 商品変更 productId商品を特定し変更処理をする。その後ユーザで指定された番号でソートする。
	public boolean productChange(int rowNo, String productName, int productId) throws SQLException {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		boolean result = false;
		int count = 0;

		String sql = "UPDATE mst_shouhin01 SET rowNo=?,shouhin01Name=? WHERE shouhin01Id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, rowNo);
			ps.setString(2, productName);
			ps.setInt(3, productId);
			count = ps.executeUpdate();
			if (count > 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		System.out.println("ProductEntryDAOのproductChange" + result);
		return result;
	}

// 重複確認
	public boolean exitsUser(String rowNo, int parentId) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		boolean result = false;
		String sql = "select count(*) as cnt FROM mst_shouhin01 WHERE rowNo=? AND parentId=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, rowNo);
			ps.setInt(2, parentId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getInt("cnt") > 0) { // 0より多ければ重複。

					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return result;
	}

//順番取得
	public int minNo(String rowNo, int parentId) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int count = 0;
		String sql = "SELECT MIN (rowNo + 1) as min FROM mst_shouhin01 where (rowNo + 1) NOT IN (select rowNo from mst_shouhin01 where parentId=?) AND rowNo >= ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(2, rowNo);
			ps.setInt(1, parentId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("min");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return count;
	}

//列の最後尾を取得

	public int MaxNo(int parentId) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int count = 1;
		String sql = "select count(rowNo) as cnt FROM mst_shouhin01 WHERE parentId=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, parentId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				count += rs.getInt("cnt");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

		return count;
	}

//読み出しメソッドの引数			hopeNo    	parentId  exitsNo
	public int productMainasNo(int rowNo, int parentId, int exitsNo) throws SQLException {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		boolean result = false;
		int count = 0;
		//既存のNoに1プラス  where rowNo >= ? AND rowNo <=
         //mst_shouhin01テーブルのRowNoを-1しろ。条件として既存のNoと希望のNoの間。尚且つparentIdが等しいもの。
		String sql = "update mst_shouhin01 set rowNo = rowNo - 1 where rowNo >= ? AND rowNo <= ?  AND parentId=?";
		try {																//以上			以下
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, exitsNo); //既存のNo
			ps.setInt(2, rowNo); //希望のNo
			ps.setInt(3, parentId); //その列No

			count = ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		System.out.println("ProductEntryDAOのproductRowEntry" + result);
		return count;
	}

}
