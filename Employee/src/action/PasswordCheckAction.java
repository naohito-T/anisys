package action;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/*import dao.PasswordCheckDAO;*/
import util.InputChecker;

public class PasswordCheckAction extends ActionSupport implements SessionAware {

	private String loginPass;
	private String loginCheck;
	private Map<String,Object> session;
	private List<String> passError;
	private List<String> passDuplicateError;
	private boolean passArea;
/*	private List<String> passCheckError;*/

	/*セッション用*/
	private String empNo;
	private int empId;
	private String fullName;
	private String kanaName;
	private String loginId;
	private String password;
	private String mail;
	private int branchId;
	private int departmentId;
	private String bossName;
	private int bossId;
	private int roleId;
	private Collection<String> checkList;

	public String execute() throws SQLException {

	/*	PasswordCheckDAO dao = new PasswordCheckDAO();*/ //DBの重複パスワード確認用
		InputChecker ic = new InputChecker();
		String result = ERROR;
		passArea = true;

		if(session.containsKey("bossFlag")){

			session.put("empNo", empNo);
			session.put("empMaxNo", empNo);
			session.put("fullName",fullName);
			session.put("kanaName", kanaName);
			session.put("loginId",loginId);
			session.put("login", password);
			session.put("mail", mail);
			session.put("branchId", branchId);
			session.put("departmentId", departmentId);
			session.put("bossName", bossName);
			session.put("bossId", bossId);
			session.put("roleId", roleId);
			session.put("checkList", checkList);
		}else{
			session.put("empNo", empNo);
			session.put("empMaxNo", empNo);
			session.put("empId", empId);
			session.put("fullName",fullName);
			session.put("kanaName", kanaName);
			session.put("loginId",loginId);
			session.put("login", password);
			session.put("mail", mail);
			session.put("branchId", branchId);
			session.put("departmentId", departmentId);
			session.put("bossName", bossName);
			session.put("bossId", bossId);
			session.put("roleId", roleId);
			session.put("checkList", checkList);

		}

		passError = ic.doCheck("パスワード", loginPass, 1, 10, true, false, false, true, false, false, false);
		passDuplicateError = ic.doPasswordCheck(loginPass, loginCheck);

		if(passError.size() > 0 || passDuplicateError.size() > 0){
			return result;
		}else {
			session.put("login",loginPass); //Map<key,value> Jspで取り出すときはkey指定 employeeEntry.jsp
			passArea = false;
			result = SUCCESS;
		}

/*		if(dao.passCheck(loginPass)){ //重複メソッド
			result = "SUCCESS";
		}else {
			passCheckError.add("以前のパスワードと同じです。変更して下さい");
		}*/
		return result;

	}
	public boolean isPassArea() {
		return passArea;
	}
	public void setPassArea(boolean passArea) {
		this.passArea = passArea;
	}
	public String getLoginPass() {
		return loginPass;
	}
	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}
	public String getLoginCheck() {
		return loginCheck;
	}
	public void setLoginCheck(String loginCheck) {
		this.loginCheck = loginCheck;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public List<String> getPassError() {
		return passError;
	}
	public void setPassError(List<String> passError) {
		this.passError = passError;
	}
	public List<String> getPassDuplicateError() {
		return passDuplicateError;
	}
	public void setPassDuplicateError(List<String> passDuplicateError) {
		this.passDuplicateError = passDuplicateError;
	}
/*	public List<String> getPassCheckError() {
		return passCheckError;
	}
	public void setPassCheckError(List<String> passCheckError) {
		this.passCheckError = passCheckError;
	}*/

	public String getEmpNo() {
		return empNo;
	}
	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getKanaName() {
		return kanaName;
	}
	public void setKanaName(String kanaName) {
		this.kanaName = kanaName;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public String getBossName() {
		return bossName;
	}
	public void setBossName(String bossName) {
		this.bossName = bossName;
	}
	public int getBossId() {
		return bossId;
	}
	public void setBossId(int bossId) {
		this.bossId = bossId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public Collection<String> getCheckList() {
		return checkList;
	}
	public void setCheckList(Collection<String> checkList) {
		this.checkList = checkList;
	}



}
