package action;

import java.util.Collection;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class PasswordFormAction extends ActionSupport implements SessionAware {

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



	Map<String,Object> session;
	private boolean passArea;

	public String execute() {

		if(session.containsKey("bossFlag")){


			session.put("empNo",empNo);
			session.put("empMaxNo", empNo);
			session.put("fullName",fullName);
			session.put("kanaName", kanaName);
			session.put("loginId",loginId);
			session.put("login", password);
			session.put("mail", mail);
			session.put("branchId", branchId);
			session.put("departmentId", departmentId);
			session.put("bossName",bossName);
			session.put("bossId", bossId);
			session.put("roleId", roleId);
		}else{
			session.put("empNo",empNo);
			session.put("empMaxNo", empNo);
			session.put("empId",empId);
			session.put("fullName",fullName);
			session.put("kanaName", kanaName);
			session.put("loginId",loginId);
			session.put("login", password);
			session.put("mail", mail);
			session.put("branchId", branchId);
			session.put("departmentId", departmentId);
			session.put("bossName",bossName);
			session.put("bossId", bossId);
			session.put("roleId", roleId);



			session.put("checkList", checkList);
		}




	passArea = true;
	return SUCCESS;

  }

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

	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getBossId() {
		return bossId;
	}
	public void setBossId(int bossId) {
		this.bossId = bossId;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public boolean isPassArea() {
		return passArea;
	}
	public void setPassArea(boolean passArea) {
		this.passArea = passArea;
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
