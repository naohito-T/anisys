package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.ProductListDAO;
import dto.ProductListDTO;

public class ClearAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	public boolean transitionFlag = true;

	public String execute() throws SQLException {

		System.out.println("クリアーーーーーーーーーーーーーマン");
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
			session.put("productList", productList);
		}
		return SUCCESS;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public boolean isTransitionFlag() {
		return transitionFlag;
	}

	public void setTransitionFlag(boolean transitionFlag) {
		this.transitionFlag = transitionFlag;
	}

}
