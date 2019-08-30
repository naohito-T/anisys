package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.EmployeeSerchDTO;
import util.DBConnector;

public class EmployeeSerchDAO {

	// 従業員一覧
	public List<EmployeeSerchDTO> list() throws SQLException {

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<EmployeeSerchDTO> employeeSerchDTO = new ArrayList<>();

		String sql = "select mst_employee.empNo,mst_employee.fullName,mst_employee.fullName AS fullName, "
				+ "mst_employee.kanaName,mst_employee.loginId,mst_employee.password,mst_employee.roleNo, "
				+ "mst_employee.mail,mst_employee.branchId,mst_employee.departmentId,mst_employee.bossId,mst_branch.branchName, "
				+ "mst_department.departmentName,boss.fullName bossName ,boss.enable bossEnable FROM mst_employee "
				+ "INNER JOIN mst_branch ON mst_employee.branchId = mst_branch.branchId "
				+ "INNER JOIN mst_department ON mst_employee.departmentId = mst_department.departmentId "
				+ "LEFT JOIN mst_employee boss ON mst_employee.bossId = boss.empId where mst_employee.enable = 0 order by mst_employee.empNo asc";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				EmployeeSerchDTO dto = new EmployeeSerchDTO();
				dto.setEmpNo(rs.getInt("empNo"));
				dto.setFullName(rs.getString("fullName"));
				dto.setKanaName(rs.getString("kanaName"));
				dto.setLoginId(rs.getString("loginId"));
				dto.setPassword(rs.getString("password"));
				dto.setMail(rs.getString("mail"));
				dto.setBranchName(rs.getString("branchName"));
				dto.setDepartmentName(rs.getString("departmentName"));
				if(rs.getInt("bossEnable") == 1){
					dto.setBossName("");
				}else {
					dto.setBossName(rs.getString("bossName"));
				}

			/*	dto.setBossName(rs.getString("bossName"));*/
				dto.setBranchId(rs.getInt("branchId"));
				dto.setDepartmentId(rs.getInt("departmentId"));
				dto.setBossId(rs.getInt("bossId"));
				dto.setRoleNo(rs.getString("roleNo"));
				employeeSerchDTO.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return employeeSerchDTO;
	}

	// 検索
	public List<EmployeeSerchDTO> employeeSerch(String fullName, int branchId, int departmentId, int enable)
			throws SQLException {


		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<EmployeeSerchDTO> employeeSerchDTO = new ArrayList<EmployeeSerchDTO>();


			String sql = "select mst.empNo,mst.fullName empName, mst.kanaName,mst.loginId,mst.password,mst.mail,mst.roleNo, "
					+ "mst.branchId,mst.departmentId,mst.bossId,mst.enable mEnable ,boss.enable bEnable ,BR.branchName,DP.departmentName,boss.fullName bossName "
					+ "FROM mst_employee mst LEFT JOIN mst_branch BR ON mst.branchID = BR.branchId "
					+ "LEFT JOIN mst_department DP ON mst.departmentId = DP.departmentId "
					+ "LEFT JOIN mst_employee boss ON mst.bossId = boss.empId WHERE "
					+ "(mst.fullName Like ? or mst.kanaName Like ?) and mst.enable=?";

			if(!(branchId == 0)){
				sql += " AND mst.branchId= " + branchId;
			}
			if(!(departmentId == 0)){
				sql += " AND mst.departmentId= " + departmentId;
			}

			sql += " order by mst.empNo asc";


		try {
			PreparedStatement ps = con.prepareStatement(sql);

			ps.setString(1, "%" + fullName + "%");
			ps.setString(2, "%" + fullName + "%");

			ps.setInt(3, enable);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				EmployeeSerchDTO dto = new EmployeeSerchDTO();//繰り返しインスタンスを生成することで

					dto.setEmpNo(rs.getInt("empNo"));
							dto.setFullName(rs.getString("empName"));
					dto.setKanaName(rs.getString("kanaName"));
					dto.setLoginId(rs.getString("loginId"));
					dto.setPassword(rs.getString("password"));
					dto.setMail(rs.getString("mail"));
					dto.setBranchName(rs.getString("branchName"));
					dto.setDepartmentName(rs.getString("departmentName"));

				if(enable == 0 && rs.getInt("bEnable") == 0 ){
					dto.setBossName(rs.getString("bossName"));
				}
				if(enable == 1){
					dto.setBossName("");
				}
				dto.setBranchId(rs.getInt("branchId"));
				dto.setDepartmentId(rs.getInt("departmentId"));
				dto.setBossId(rs.getInt("bossId"));
				dto.setRoleNo(rs.getString("roleNo"));
				dto.setEnable(rs.getInt("mEnable"));

				employeeSerchDTO.add(dto);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeSerchDTO;
	}

	//ユーザ確認


}
