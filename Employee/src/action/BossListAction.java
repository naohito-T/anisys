package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.EmployeeSerchDAO;
import dto.EmployeeSerchDTO;

public class BossListAction extends ActionSupport implements SessionAware {

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
	private Collection<String> checkList;
	private String userRole;

	private boolean bossFlag = true;

	private Map<String,Object> session;
	EmployeeSerchDAO dao = new EmployeeSerchDAO();
	EmployeeSerchDTO dto = new EmployeeSerchDTO();

	List<EmployeeSerchDTO> employeeList = new ArrayList<EmployeeSerchDTO>();

	public String execute() throws SQLException {

		session.put("empNo", empNo);
		session.put("empMaxNo", empNo);
		session.put("fullName",fullName);
		session.put("kanaName", kanaName);
		session.put("loginId",loginId);
		session.put("password", password);
		session.put("mail", mail);
		session.put("branchId", branchId);
		session.put("departmentId", departmentId);
		session.put("bossName", bossName);
		session.put("bossId", bossId);
		session.put("checkList",checkList);
		session.put("userRole", userRole);
		session.put("bossFlag", bossFlag);


		employeeList = dao.list();
		session.put("employeeList", employeeList);
		return SUCCESS;
	}


public Collection<String> getCheckList() {
		return checkList;
	}


	public void setCheckList(Collection<String> checkList) {
		this.checkList = checkList;
	}


	public String getUserRole() {
		return userRole;
	}


	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}


public List<EmployeeSerchDTO> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<EmployeeSerchDTO> employeeList) {
		this.employeeList = employeeList;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
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



	public int getBossId() {
		return bossId;
	}

	public void setBossId(int bossId) {
		this.bossId = bossId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}


	public boolean isBossflag() {
		return bossFlag;
	}


	public void setBossflag(boolean bossflag) {
		this.bossFlag = bossflag;
	}


}
