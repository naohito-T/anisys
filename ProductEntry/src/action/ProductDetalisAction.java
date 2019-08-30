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

public class ProductDetalisAction extends ActionSupport implements SessionAware {

	private int productId;
	/* private int parentId; */
	private String parentName;
	private Map<String, Object> session;
	private boolean mediumFlag = true;

	private Collection<ProductListDTO> form2;
	List<ProductListDTO> productListMedium = new ArrayList<ProductListDTO>();

	public String execute() throws SQLException {

		System.out.println("ProductDetalisAction");

		ProductListDAO dao = new ProductListDAO();
		productListMedium = dao.productListMedium(productId);
		session.put("productListMedium", productListMedium);
		session.put("mediumFlag", mediumFlag);
		session.put("parentName", parentName);
		// 親キーへ変換
		session.put("parentId", productId);
		session.remove("transitionFlag");
		session.remove("productList");
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public boolean isMediumFlag() {
		return mediumFlag;
	}

	public void setMediumFlag(boolean mediumFlag) {
		this.mediumFlag = mediumFlag;
	}

	public Collection<ProductListDTO> getForm2() {
		return form2;
	}

	public void setForm2(Collection<ProductListDTO> form2) {
		this.form2 = form2;
	}

}
