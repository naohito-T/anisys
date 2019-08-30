package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.EmployeeSerchDAO;
import dto.EmployeeSerchDTO;

public class EmployeeSerchAction extends ActionSupport implements SessionAware {

	private String name;
	private int branchId;
	private int departmentId;
	private int enable;
	private Map<String,Object> session;
	List<String> errorMessage = new ArrayList<>();


	private List<EmployeeSerchDTO> employeeList;

	public String  execute() throws SQLException {


		EmployeeSerchDAO dao = new EmployeeSerchDAO();
		employeeList = dao.employeeSerch(name, branchId, departmentId, enable);

		if(employeeList.isEmpty()){
			errorMessage.add("該当する人物はいませんでした。");
			employeeList.clear();
		}
		return SUCCESS;
	}

	public List<String> getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(List<String> errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<EmployeeSerchDTO> getEmployeeList() {
		return employeeList;
	}
	public void setEmployeeList(List<EmployeeSerchDTO> employeeList) {
		this.employeeList = employeeList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public Map<String, Object> getSession() {
		return session;
	}




	}

