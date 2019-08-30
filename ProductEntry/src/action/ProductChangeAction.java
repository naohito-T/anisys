package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.DeleteDAO;
import dao.ProductEntryDAO;
import dao.ProductListDAO;
import dto.ProductListDTO;
import util.InputChecker;

public class ProductChangeAction extends ActionSupport implements SessionAware {

	private String rowNo;

	private String productName;
	private String parentMiddleName;
	private int productId;
	private int parentId;
	private Map<String, Object> session;
	private Collection<ProductListDTO> form2;
	private List<String> productNameError;
	private List<String> rowNoError;
	private String rowNoErrorMessage;
	private String changeSuccessMessage;
	public boolean transitionFlag;
	public boolean mediumFlag;
	public boolean smallFlag;
	private List<ProductListDTO> productList = new ArrayList<ProductListDTO>();
	private List<ProductListDTO> productListMedium = new ArrayList<ProductListDTO>();
	private List<ProductListDTO> productListSmall = new ArrayList<ProductListDTO>();

	public String execute() throws SQLException {
		InputChecker ic = new InputChecker();
		String result = ERROR;
		ProductEntryDAO dao = new ProductEntryDAO();
		DeleteDAO deleteDAO = new DeleteDAO();
		ProductListDAO listDAO = new ProductListDAO();

		int no = dao.MaxNo(parentId); //列の最終番号を取得 今後はparentIdが必要
		int minNo =0;
		int exitsNo = deleteDAO.exitsRow(productId); //その商品の(現在の)rowNoを取得する。
		int hopeRowNo = Integer.valueOf(rowNo);		 //ユーザの希望するrowNoを取得。

		productName = productName.trim();
		rowNoError = ic.doCheck("行番号", rowNo, 1, 6, false, false, false, true, false, false, false);
		productNameError = ic.doCheck("名称", productName, 1, 50, true, true, true, true, false, true, true);

		if (productNameError.size() > 0 || rowNoError.size() > 0) {
			return result;
		}
		if (rowNo.contains("-")){
			rowNoErrorMessage = "0以上英数半角で入力して下さい。";
			return result;
		}



		if(hopeRowNo >= no){
			hopeRowNo = dao.MaxNo(parentId);
			hopeRowNo -= 1;
			//そのまま変更💛💛💛💛💛💛💛💛
		}

		if(hopeRowNo <= no){//ユーザが希望するNoが列の最後尾より小さいときtrue。そう重複があるということだ 重複しない場合はそもそも希望の値が列の値より大きい。
			if(hopeRowNo > exitsNo){ //希望のNoがもともとのNoより大きい場合💛💛💛💛
				dao.productMainasNo(hopeRowNo, parentId, exitsNo); //既存Noと希望のNoの間を-1
			}else {
				if (dao.exitsUser(rowNo, parentId)) {
					/*minNo = da o.minNo("0", parentId);*/
					 dao.productRowEntry(exitsNo,parentId,hopeRowNo);
					 hopeRowNo += minNo;
				}
				//既存Noと希望のNoの間を+1
			}
		}

		if(session.containsKey("transitionFlag")){					 //最初にsessionに値が入っているか確認しないといけない。
			transitionFlag = (boolean) session.get("transitionFlag"); //万が一sessionに値が入っていない場合nullポになる。
			if (transitionFlag == true) {
				dao.productChange(hopeRowNo, productName,productId);
				productList = listDAO.productList();
				session.put("productList", productList);
				changeSuccessMessage = "商品情報" + productName + "変更完了しました。";
				result = SUCCESS;
				return result;
			}
		}

		if(session.containsKey("mediumFlag")){
			mediumFlag = (boolean) session.get("mediumFlag");
			if (mediumFlag == true) {
				if(dao.productChange(hopeRowNo, productName,productId)){
					productListMedium = listDAO.productListMedium(parentId);
					session.put("productListMedium", productListMedium);
					changeSuccessMessage = "商品情報" + productName + "変更完了しました。";
					result = SUCCESS;
					return result;
				}

			}
		}

		if(session.containsKey("smallFlag")){
			smallFlag = (boolean) session.get("smallFlag");
			if (smallFlag == true) {
				dao.productChange(hopeRowNo, productName,productId);
				productListSmall = listDAO.productListMedium(parentId);
				session.put("productListSmall", productListSmall);
				changeSuccessMessage = "商品情報" + productName + "変更完了しました。";
				result = SUCCESS;
				return result;
			}
		}
		return result;
}

	public String getRowNoErrorMessage() {
		return rowNoErrorMessage;
	}

	public void setRowNoErrorMessage(String rowNoErrorMessage) {
		this.rowNoErrorMessage = rowNoErrorMessage;
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

	public List<String> getProductNameError() {
		return productNameError;
	}

	public void setProductNameError(List<String> productNameError) {
		this.productNameError = productNameError;
	}

	public List<String> getRowNoError() {
		return rowNoError;
	}

	public void setRowNoError(List<String> rowNoError) {
		this.rowNoError = rowNoError;
	}

	public Collection<ProductListDTO> getForm2() {
		return form2;
	}

	public void setForm2(Collection<ProductListDTO> form2) {
		this.form2 = form2;
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

	public String getChangeSuccessMessage() {
		return changeSuccessMessage;
	}

	public void setChangeSuccessMessage(String changeSuccessMessage) {
		this.changeSuccessMessage = changeSuccessMessage;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<ProductListDTO> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductListDTO> productList) {
		this.productList = productList;
	}

	public List<ProductListDTO> getProductListMedium() {
		return productListMedium;
	}

	public void setProductListMedium(List<ProductListDTO> productListMedium) {
		this.productListMedium = productListMedium;
	}

	public List<ProductListDTO> getProductListSmall() {
		return productListSmall;
	}

	public void setProductListSmall(List<ProductListDTO> productListSmall) {
		this.productListSmall = productListSmall;
	}

	public String getParentMiddleName() {
		return parentMiddleName;
	}

	public void setParentMiddleName(String parentMiddleName) {
		this.parentMiddleName = parentMiddleName;
	}

}
