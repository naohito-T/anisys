package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DeleteDAO;
import dao.EmployeeEntryDAO;
import util.InputChecker;

public class EmployeeEntryAction extends ActionSupport implements SessionAware {

	private String empNo;
	private int empNo2;
	private int empId;
	private String fullName;
	private String kanaName;
	private String loginId;
	private String password;
	private String mail;
	private int branchId;
	private int departmentId;
	private String bossName;
	private int roleId;
	private int bossId;
	private List<String> loginIdError;
	private List<String> passwordError;
	private List<String> empNoError;
	private List<String> nameError;
	private List<String> kanaError;
	private List<String> bossError = new ArrayList<>();
	private List<String> bossSerchError = new ArrayList<>();
	private List<String> emailError;
	private List<String> exitsError = new ArrayList<>();
	private List<String> departmentError = new ArrayList<>();
	private List<String> branchError = new ArrayList<>();
	private Map<String, Object> session;
	private int userRole;
	private int roleNo;
	private Collection<String> checkList; // collection型にすることにより配列で受け取る値が,(カンマ)区切りではなくなる

	public String execute() throws SQLException {

		InputChecker ic = new InputChecker();
		EmployeeEntryDAO dao = new EmployeeEntryDAO();
		String result = ERROR;
		DeleteDAO deleteDAO = new DeleteDAO();

		if (!checkList.isEmpty()) {
			for (String check : checkList) {
				int id = Integer.parseInt(check);
				switch (id) {
				case 1:
					userRole += 1;
					break;
				case 2:
					userRole += 10;
					break;
				case 3:
					userRole += 100;
					break;
				case 4:
					userRole += 1000;
					break;
				case 5:
					userRole += 10000;
					break;
				case 6:
					userRole += 100000;
					break;
				case 7:
					userRole += 1000000;
					break;
				case 8:
					userRole += 10000000;
					break;
				case 9:
					userRole += 100000000;
					break;
				case 10:
					userRole += 1000000000;
					break;
				}
			}
		}
		if (departmentId == 0 || branchId == 0) {
			departmentError.add("所属・部署を選んでください");

		}
		session.put("empNo", empNo);
		session.put("fullName", fullName);
		session.put("kanaName", kanaName);
		session.put("loginId", loginId);
		session.put("password", password);
		session.put("mail", mail);
		session.put("branchId", branchId);
		session.put("departmentId", departmentId);
		session.put("bossName", bossName);
		session.put("roleId", roleId);
		session.put("bossId", bossId);

		empNoError = ic.doCheck("No", empNo, 1, 10, false, false, false, true, false, false, false);

		if (empNoError.size() > 0) {
			empNo = "0";
		} // エラーがでたらtrue
		empNo2 = Integer.valueOf(empNo);
		if (empNo2 <= 0) {
			if (empId != 0) {
				exitsError.add("従業員Noは半角数字で入力して下さい。");
			} else {
				empNo2 = dao.empMaxNo();
			}
		}
		nameError = ic.doCheck("名前", fullName, 1, 10, true, true, true, true, false, true, false);
		kanaError = ic.doCheck("名前(カナ)", kanaName, 1, 10, false, false, false, false, false, true, false);
		loginIdError = ic.doCheck("ログインID", loginId, 1, 10, true, false, false, true, false, false, false);
		passwordError = ic.doCheck("パスワード", password, 1, 16, true, false, false, true, false, false, false);
		emailError = ic.checkEmail("メールアドレス", mail, 1, 32);

		if (loginIdError.size() > 0 || passwordError.size() > 0 || emailError.size() > 0 || nameError.size() > 0
				|| kanaError.size() > 0 || departmentError.size() > 0 || exitsError.size() > 0) {
			return result;
		}
		if (dao.exitsUser(empNo)) { // 重複確認
			if ((!(empId == 0))) { // empId含まれていた場合はtrue
									// empIdは従業員選択から遷移した場合しか入らないが0が入ってしまう可能性もあるためこの記述。
				if (deleteDAO.adminCheck() || userRole % 2 == 1 || roleNo % 2 == 0) { // システム管理2人以上いれば更新。またはシステム管理押下か管理者が1人以上
					if (empNo.equals(String.valueOf(empId))) { // 既にユーザがいて尚且つempNoとempIdが等しい場合true
						if (empNo.equals(String.valueOf(bossId))) {
							bossError.add("自分自身は上司にできません。");
							return result;
						} else {
							if (dao.sameUser(loginId, password)) { // loginId
								if (!(dao.sameUserNear(loginId, password, empId))) {
									exitsError.add("既にこのログインIDとパスワードは使われています。");
									return result;
								}
							}
							dao.empUpdate2(empNo, fullName, kanaName, loginId, password, mail, branchId, departmentId,
									bossId, userRole, empId);
							result = "update";
							return result;
						}
					} else {
						exitsError.add("既にその従業員Noは使用されております。");
						empNo2 = dao.empMaxNo();
						return result;
					}
				} else {
					exitsError.add("システム管理者を0人にすることはできません。");
					return result;
				}
			}
			// empNoが重複していたら
			exitsError.add("従業員Noが重複しています。");
			empNo2 = dao.empMaxNo();
			/* session.put("empMaxNo",empNo2); */
			return result;
		} // empNoが重複していなかった場合の更新パターン
		if (!(empId == 0)) {
			if (empNo.equals(String.valueOf(bossId))) {
				bossError.add("上司を選んでください");
			}
			if (dao.sameUser(loginId, password)) {
				if (dao.sameUserNear(loginId, password, empId)) {
					dao.empUpdate2(empNo, fullName, kanaName, loginId, password, mail, branchId, departmentId, bossId,
							userRole, empId);
					result = "update";
					return result;
				}
				exitsError.add("既にこのログインIDとパスワードは使われています。");
				return result;
			}
			dao.empUpdate2(empNo, fullName, kanaName, loginId, password, mail, branchId, departmentId, bossId, userRole,
					empId);
			result = "update";
			return result;
		} else {
			// 新規登録
			if (dao.sameUser(loginId, password)) {
				exitsError.add("既にこのログインIDとパスワードは使われています。");
				return result;
			}
			empNo = String.valueOf(empNo2);
			dao.entry(empNo, fullName, kanaName, loginId, password, mail, branchId, departmentId, bossId, userRole);
			result = SUCCESS;
			return result;
		}
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

	public List<String> getLoginIdError() {
		return loginIdError;
	}

	public void setLoginIdError(List<String> loginIdError) {
		this.loginIdError = loginIdError;
	}

	public List<String> getPasswordError() {
		return passwordError;
	}

	public void setPasswordError(List<String> passwordError) {
		this.passwordError = passwordError;
	}

	public List<String> getEmailError() {
		return emailError;
	}

	public void setEmailError(List<String> emailError) {
		this.emailError = emailError;
	}

	public List<String> getExitsError() {
		return exitsError;
	}

	public void setExitsError(List<String> exitsError) {
		this.exitsError = exitsError;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getUserRole() {
		return userRole;
	}

	public void setUserRole(int userRole) {
		this.userRole = userRole;
	}

	public Collection<String> getCheckList() {
		return checkList;
	}

	public void setCheckList(Collection<String> checkList) {
		this.checkList = checkList;
	}

	public List<String> getDepartmentError() {
		return departmentError;
	}

	public void setDepartmentError(List<String> departmentError) {
		this.departmentError = departmentError;
	}

	public List<String> getBranchError() {
		return branchError;
	}

	public void setBranchError(List<String> branchError) {
		this.branchError = branchError;
	}

	public int getRoleNo() {
		return roleNo;
	}

	public void setRoleNo(int roleNo) {
		this.roleNo = roleNo;
	}

	public List<String> getBossError() {
		return bossError;
	}

	public void setBossError(List<String> bossError) {
		this.bossError = bossError;
	}

	public List<String> getEmpNoError() {
		return empNoError;
	}

	public void setEmpNoError(List<String> empNoError) {
		this.empNoError = empNoError;
	}

	public List<String> getBossSerchError() {
		return bossSerchError;
	}

	public void setBossSerchError(List<String> bossSerchError) {
		this.bossSerchError = bossSerchError;
	}

	public List<String> getNameError() {
		return nameError;
	}

	public void setNameError(List<String> nameError) {
		this.nameError = nameError;
	}

	public List<String> getKanaError() {
		return kanaError;
	}

	public void setKanaError(List<String> kanaError) {
		this.kanaError = kanaError;
	}
}
