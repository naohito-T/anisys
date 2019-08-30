package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.ProductListDAO;
import dto.ProductListDTO;

public class BackAction extends ActionSupport implements SessionAware {

	private int productId;
	private int parentId;
	private String parentName;
	private boolean mediumFlag = true; // フィールドを定義すればvalueStackに値が入る。

	List<ProductListDTO> productListMedium = new ArrayList<ProductListDTO>();

	private Map<String, Object> session;

	public String execute() throws SQLException {

		// 小分類から中分類へ遷移

		System.out.println("BackAction");

		ProductListDAO dao = new ProductListDAO();
		productListMedium = dao.productListMedium(parentId);
		session.put("productListMedium", productListMedium);
		session.put("mediumFlag", mediumFlag);

		session.remove("transitionFlag");
		session.remove("smallFlag");
		session.remove("productListSmall");

		return SUCCESS;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<ProductListDTO> getProductListMedium() {
		return productListMedium;
	}

	public void setProductListMedium(List<ProductListDTO> productListMedium) {
		this.productListMedium = productListMedium;
	}

	public boolean isMediumFlag() {
		return mediumFlag;
	}

	public void setMediumFlag(boolean mediumFlag) {
		this.mediumFlag = mediumFlag;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

}
