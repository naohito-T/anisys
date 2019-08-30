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

public class ProductDetalisSmallAction extends ActionSupport implements SessionAware {

	private int productId;
	private String rowNo;
	private String productName;
	private String parentName;
	private String parentMiddleName;
	private int parentId;
	private boolean smallFlag = true;
	private Map<String, Object> session;
	private Collection<ProductListDTO> form2;
	List<ProductListDTO> productListSmall = new ArrayList<ProductListDTO>();

	public String execute() throws SQLException {

		ProductListDAO dao = new ProductListDAO();
		productListSmall = dao.productListSmall(productId);
		session.put("smallFlag", smallFlag);
		session.put("productListSmall", productListSmall);
		session.put("parentId", parentId);// 大のID
		session.put("productId", productId);// 中のID
		session.put("parentMiddleName", parentMiddleName);
		session.put("productId", productId);
		session.put("parentId2", productId);

		session.remove("transitionFlag");
		session.remove("mediumFlag");
		session.remove("productList");
		session.remove("productListMedium");

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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public boolean isSmallFlag() {
		return smallFlag;
	}

	public void setSmallFlag(boolean smallFlag) {
		this.smallFlag = smallFlag;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentMiddleName() {
		return parentMiddleName;
	}

	public void setParentMiddleName(String parentMiddleName) {
		this.parentMiddleName = parentMiddleName;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public Collection<ProductListDTO> getForm2() {
		return form2;
	}

	public void setForm2(Collection<ProductListDTO> form2) {
		this.form2 = form2;
	}

}
