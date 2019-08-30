package action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class EmployeeChoiceAction extends ActionSupport implements SessionAware {

	private int empId;
	private String kanaName;
	private String loginId;
	private String password;
	private String mail;
	private int branchId;
	private int departmentId;
	private int bossId;
	private String key;
	private String userRole;
	private Collection<String> checkList;
	private List<String> checked = new ArrayList<>();
	private int enable;
	//検索上
	private int empNo;
	private String fullName;
	private String branchName;
	private String departmentName;
	private String bossName;
	Map<String,Object> session;

	public String execute() {

		if(session.containsKey("bossFlag")){ //上司検索から遷移した場合

			if(enable == 0){ //
				session.get("empMaxNo");
				session.put("bossName", fullName);
				session.put("bossId",empNo);
			}


		}else{ //従業員検索から遷移した場合
			for(int i = 0; i < userRole.length(); i++){
				int test = Character.getNumericValue(userRole.charAt(i));
				System.out.println(userRole);
				System.out.println(test);
				if (test == 1){
					switch (i){
					case 0:
						checked.add("10");
						break;
					case 1:
						checked.add("9");
						break;
					case 2:
						checked.add("8");
						break;
					case 3:
						checked.add("7");
						break;
					case 4:
						checked.add("6");
						break;
					case 5:
						checked.add("5");
						break;
					case 6:
						checked.add("4");
						break;
					case 7:
						checked.add("3");
						break;
					case 8:
						checked.add("2");
						break;
					case 9:
						checked.add("1");
						break;
					}
				}
			}
			checkList = checked;
			session.put("roleNo",userRole);
			session.put("empMaxNo", empNo); //ユーザID
			session.put("empId",empNo); //hiddenのempId
			session.put("fullName",fullName);
			session.put("kanaName", kanaName);
			session.put("loginId",loginId);
			session.put("login", password);
			session.put("mail", mail);
			session.put("branchId", branchId);
			session.put("departmentId", departmentId);
			session.put("bossName",bossName);
			session.put("bossId", bossId);
			session.put("checkList",checkList);
			session.put("bossName", bossName);
		}
		return SUCCESS;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getBossName() {
		return bossName;
	}
	public void setBossName(String bossName) {
		this.bossName = bossName;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
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
	public int getBossId() {
		return bossId;
	}
	public void setBossId(int bossId) {
		this.bossId = bossId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public Collection<String> getCheckList() {
		return checkList;
	}
	public void setCheckList(Collection<String> checkList) {
		this.checkList = checkList;
	}
	public List<String> getChecked() {
		return checked;
	}
	public void setChecked(List<String> checked) {
		this.checked = checked;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}

}
