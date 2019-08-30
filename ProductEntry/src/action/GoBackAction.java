package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.ProductListDAO;
import dto.ProductListDTO;

public class GoBackAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private boolean transitionFlag = true;
	private Collection<ProductListDTO> form;

	public String execute() throws SQLException { // なぜ宣言する必要？

		System.out.println("GoBackAction");
		session.clear();
		if (!session.containsKey("productList")) {
			List<ProductListDTO> productList = new ArrayList<ProductListDTO>();

			ProductListDAO dao = new ProductListDAO();
			try {
				productList = dao.productList();

				System.out.println(productList);
			} catch (NullPointerException e) {
				productList = null;
			}
			session.put("transitionFlag", transitionFlag);
			session.put("productList", productList);
			session.remove("mediumFlag");
			session.remove("smallFlag");
		}
		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public boolean isTransitionFlag() {
		return transitionFlag;
	}

	public void setTransitionFlag(boolean transitionFlag) {
		this.transitionFlag = transitionFlag;
	}

	public Collection<ProductListDTO> getForm() {
		return form;
	}

	public void setForm(Collection<ProductListDTO> form) {
		this.form = form;
	}

}