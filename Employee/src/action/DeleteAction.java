package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DeleteDAO;

public class DeleteAction extends ActionSupport implements SessionAware {

	List<String> exitsError = new ArrayList<>();
	private int empId;
	private int empMaxNo;
	private Collection<String> checkList;
	private Map<String,Object> session;
	int count = 0;
	private int roleNo;

	public String execute() throws SQLException {

		String result = ERROR;
		DeleteDAO dao = new DeleteDAO();
		if(empId != 0){
			if(dao.adminCheck() || roleNo % 2 == 0){
				dao.deleteUser(empId);
				result = SUCCESS;

			}else {
				exitsError.add("システム管理者が他にいないため削除できません。");


			}
		}else {
			exitsError.add("削除に失敗しました。");
		}

		session.put("checkList", checkList);
		return result;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public List<String> getExitsError() {
		return exitsError;
	}
	public void setExitsError(List<String> exitsError) {
		this.exitsError = exitsError;
	}
	public int getEmpMaxNo() {
		return empMaxNo;
	}
	public void setEmpMaxNo(int empMaxNo) {
		this.empMaxNo = empMaxNo;
	}
	public Collection<String> getCheckList() {
		return checkList;
	}
	public void setCheckList(Collection<String> checkList) {
		this.checkList = checkList;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public int getRoleNo() {
		return roleNo;
	}
	public void setRoleNo(int roleNo) {
		this.roleNo = roleNo;
	}


}
