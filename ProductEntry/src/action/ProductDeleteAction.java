package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DeleteDAO;
import dao.ProductListDAO;
import dto.ProductListDTO;

public class ProductDeleteAction extends ActionSupport implements SessionAware {

	List<String> errorDeleteMessage = new ArrayList<>();

	private int productId;
	private int parentId;
	private String rowNo;
	private String productName;
	private String parentMiddleName;
	private String rowNoErrorMessage;
	private String deleteSuccessMessage;
	public boolean transitionFlag;
	public boolean mediumFlag;
	public boolean smallFlag;

	private Map<String, Object> session;

	public String execute() throws SQLException {

		System.out.println("ProductDeleteAction");

		DeleteDAO dao = new DeleteDAO();
		ProductListDAO listDAO = new ProductListDAO();

		List<ProductListDTO> productList = new ArrayList<ProductListDTO>();
		String result = ERROR;
		int count = 0;

		if (session.containsKey("transitionFlag")) { // そのため最初にsessionに値が入っているか確認しないといけない。
			transitionFlag = (boolean) session.get("transitionFlag");
			if (rowNo.contains("-")) {
				rowNoErrorMessage = "0以上英数半角で入力して下さい。";
				return result;
			}else if (transitionFlag == true) {
						int exitsNo = dao.exitsRow(productId);
						dao.rowUp(exitsNo, parentId);
						dao.deleteProduct(productId);
						productList = listDAO.productList();
						session.put("productList", productList);
						deleteSuccessMessage = "商品情報[" + productName + "]削除成功しました。";
						result = SUCCESS;
						return result;
				}
			}

		if (session.containsKey("mediumFlag")) {
			mediumFlag = (boolean) session.get("mediumFlag");
			if (rowNo.contains("-")) {
				rowNoErrorMessage = "0以上英数半角で入力して下さい。";
				return result;
			}else if(mediumFlag == true) {

				count = dao.exitsRow(productId);
				dao.rowUp(count, parentId);
				dao.deleteProduct(productId);
				productList = listDAO.productListMedium(parentId);
				session.put("productListMedium", productList);
				deleteSuccessMessage = "商品情報[" + productName + "]削除成功しました。";
				result = SUCCESS;
				return result;
			}
		}

		if (session.containsKey("smallFlag")) {
			smallFlag = (boolean) session.get("smallFlag");
			if (rowNo.contains("-")) {
				rowNoErrorMessage = "0以上英数半角で入力して下さい。";
				return result;
			}else if (smallFlag == true) {
				count = dao.exitsRow(productId);
				dao.rowUp(count, parentId);
				dao.deleteProduct(productId);
				productList = listDAO.productListMedium(parentId);
				session.put("productListSmall", productList);
				deleteSuccessMessage = "商品情報[" + productName + "]削除成功しました。";
				result = SUCCESS;
				return result;
			}
		}

		return result;

	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public List<String> getErrorDeleteMessage() {
		return errorDeleteMessage;
	}

	public void setErrorDeleteMessage(List<String> errorDeleteMessage) {
		this.errorDeleteMessage = errorDeleteMessage;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getRowNoErrorMessage() {
		return rowNoErrorMessage;
	}

	public void setRowNoErrorMessage(String rowNoErrorMessage) {
		this.rowNoErrorMessage = rowNoErrorMessage;
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

	public String getDeleteSuccessMessage() {
		return deleteSuccessMessage;
	}

	public void setDeleteSuccessMessage(String deleteSuccessMessage) {
		this.deleteSuccessMessage = deleteSuccessMessage;
	}

	public String getParentMiddleName() {
		return parentMiddleName;
	}

	public void setParentMiddleName(String parentMiddleName) {
		this.parentMiddleName = parentMiddleName;
	}

}
