package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.BranchListDAO;
import dao.DepartmentListDAO;
import dao.RoleListDAO;
import dto.BranchListDTO;
import dto.DepartmentListDTO;
import dto.RoleListDTO;


public class ClearAction extends ActionSupport implements SessionAware {

	private Map<String,Object> session;

	public String execute() {

		System.out.println("クリアーーーーーーーーーーーーーマン");
		session.clear();


		if(!session.containsKey("roleList")) {
			List<RoleListDTO> roleList = new ArrayList<RoleListDTO>();

			RoleListDAO roledao = new RoleListDAO();
			try {
				roleList = roledao.roleFactoryList();
			} catch (NullPointerException e) {
				roleList = null;
			}
		session.put("roleList",roleList);
		System.out.println(roleList);
		}
		if(!session.containsKey("branchList")) {
				List<BranchListDTO> branchList = new ArrayList<BranchListDTO>();

				BranchListDAO branchdao = new BranchListDAO();
				try {
					branchList = branchdao.branchFactoryList();
				} catch (NullPointerException e) {
					branchList = null;
				}
			session.put("branchList",branchList);
			}
		if(!session.containsKey("departmentList")) {
				List<DepartmentListDTO> departmentList = new ArrayList<DepartmentListDTO>();

				DepartmentListDAO departmentdao = new DepartmentListDAO();
				try {
					departmentList = departmentdao.departmentList();
				} catch (NullPointerException e) {
					departmentList = null;
				}
			session.put("departmentList",departmentList);
		  }
		return SUCCESS;
		}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
