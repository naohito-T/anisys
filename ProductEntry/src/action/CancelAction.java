package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.ProductListDAO;
import dto.ProductListDTO;

public class CancelAction extends ActionSupport implements SessionAware {

	private int productId;
	private String rowNo;
	private int parentId;
	private int parentId2;
	private String parentName;
	private String parentMiddleName;
	private String productName;
	private Map<String, Object> session;
	public boolean transitionFlag;
	public boolean mediumFlag;
	public boolean smallFlag;

	public String execute() throws SQLException {

		System.out.println("CancelAction");

		if (session.containsKey("transitionFlag")) { // そのため最初にsessionに値が入っているか確認しないといけない。
			transitionFlag = (boolean) session.get("transitionFlag"); // 万が一sessionに値が入っていない場合nullポになる。
			if (transitionFlag == true) {
				List<ProductListDTO> productList = new ArrayList<ProductListDTO>();

				ProductListDAO dao = new ProductListDAO();
				try {
					productList = dao.productList();
					System.out.println(productList);
				} catch (NullPointerException e) {
					productList = null;
				}
				session.remove("productListMedium");
				session.remove("productListSmall");
				session.remove("mediumFlag");
				session.remove("smallFlag");
				session.put("productList", productList);
				return SUCCESS;
			}
		}

		if (session.containsKey("mediumFlag")) {
			mediumFlag = (boolean) session.get("mediumFlag");
			if (mediumFlag == true) {
				List<ProductListDTO> productListMedium = new ArrayList<ProductListDTO>();

				ProductListDAO dao = new ProductListDAO();
				try {
					productListMedium = dao.productListMedium(parentId);
					System.out.println(productListMedium);
				} catch (NullPointerException e) {
					productListMedium = null;
				}
				session.remove("productList");
				session.remove("productListSmall");
				session.remove("transitionFlag");
				session.remove("smallFlag");
				session.put("productListMedium", productListMedium);
				return SUCCESS;
			}
		}

		if (session.containsKey("smallFlag")) {
			smallFlag = (boolean) session.get("smallFlag");
			if (smallFlag == true) {
				List<ProductListDTO> productListSmall = new ArrayList<ProductListDTO>();

				ProductListDAO dao = new ProductListDAO();
				try {

					productListSmall = dao.productListMedium(parentId);

				} catch (NullPointerException e) {
					productListSmall = null;
				}
				session.remove("productList");
				session.remove("productListMedium");
				session.remove("transitionFlag");
				session.remove("mediumFlag");
				session.put("productListSmall", productListSmall);
				return SUCCESS;
			}

		}
		return SUCCESS;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public boolean isTransitionFlag() {
		return transitionFlag;
	}

	public void setTransitionFlag(boolean transitionFlag) {
		this.transitionFlag = transitionFlag;
	}

	public boolean isMediumFlag() {
		return mediumFlag;
	}

	public void setMediumFlag(boolean mediumFlag) {
		this.mediumFlag = mediumFlag;
	}

	public boolean isSmallFlag() {
		return smallFlag;
	}

	public void setSmallFlag(boolean smallFlag) {
		this.smallFlag = smallFlag;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentMiddleName() {
		return parentMiddleName;
	}

	public void setParentMiddleName(String parentMiddleName) {
		this.parentMiddleName = parentMiddleName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getParentId2() {
		return parentId2;
	}

	public void setParentId2(int parentId2) {
		this.parentId2 = parentId2;
	}

}
