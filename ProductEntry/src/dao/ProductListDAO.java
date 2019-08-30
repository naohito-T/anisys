package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProductListDTO;
import util.DBConnector;

//大分類一覧
public class ProductListDAO {

	public List<ProductListDTO> productList() throws SQLException {

		List<ProductListDTO> productList = new ArrayList<ProductListDTO>();
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "SELECT * from mst_shouhin01 where parentId=0 order by rowNo asc";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				ProductListDTO dto = new ProductListDTO();
				dto.setProductId(rs.getInt("shouhin01Id"));
				dto.setParentId(rs.getInt("parentId"));
				dto.setProductCD(rs.getString("shouhin01CD"));
				dto.setProductName(rs.getString("shouhin01Name"));
				dto.setRowNo(rs.getInt("rowNo"));
				productList.add(dto);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			con.close();
		}
		return productList;

	}
//中分類一覧 大分類のshouhin01IDを元に中分類のparentIdと紐づけて一覧を取得。
	public List<ProductListDTO> productListMedium(int productId) throws SQLException { //引数は大分類shouhin01Id

		List<ProductListDTO> productListMedium = new ArrayList<ProductListDTO>();
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "select * from mst_shouhin01 where parentId=? order by rowNo asc";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, productId);//shouhin01IdをparentIdに

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				ProductListDTO dto = new ProductListDTO();
				dto.setProductId(rs.getInt("shouhin01Id"));
				dto.setProductName(rs.getString("shouhin01Name"));
				dto.setRowNo(rs.getInt("rowNo"));
				dto.setParentId(rs.getInt("parentId"));
				productListMedium.add(dto);
			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			con.close();
		}
		return productListMedium;

	}
//小分類一覧
	public List<ProductListDTO> productListSmall(int productId) throws SQLException {

		List<ProductListDTO> productListSmall = new ArrayList<ProductListDTO>();
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String sql = "SELECT * from mst_shouhin01 where parentId=? order by rowNo asc";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, productId);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				ProductListDTO dto = new ProductListDTO();
				dto.setProductId(rs.getInt("shouhin01Id"));
				dto.setProductName(rs.getString("shouhin01Name"));
				dto.setRowNo(rs.getInt("rowNo"));
				dto.setParentId(rs.getInt("parentId"));
				productListSmall.add(dto);

			}
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			con.close();
		}
		return productListSmall;

	}

}
