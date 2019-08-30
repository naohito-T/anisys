package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import dao.ProductEntryDAO;
import dao.ProductListDAO;
import dto.ProductListDTO;
import util.InputChecker;

public class ProductEntryAction extends ActionSupport implements SessionAware {

	private int productId; // 親からなら0
	private String productName;
	private String parentName;
	private String parentMiddleName;
	private String rowNo;
	private int parentId; // 中分類・小分類でのproductIdがくる。
	private Map<String, Object> session;
	private List<String> productNameError;
	private List<String> rowNoError;
	private String rowNoErrorMessage;
	private String entrySuccessMessage;
	private boolean transitionFlag;
	private boolean mediumFlag;
	private boolean smallFlag;

	public String execute() throws SQLException {

		System.out.println("ProductEntryAction");
		InputChecker ic = new InputChecker();
		String result = ERROR;
		ProductEntryDAO dao = new ProductEntryDAO();
		ProductListDAO listDAO = new ProductListDAO();
		List<ProductListDTO> productList = new ArrayList<ProductListDTO>();
		int minNo = 0;

		session.put("entryProductName", productName);
		session.put("entryProductRow", rowNo);

		productName = productName.trim();
		productNameError = ic.doCheck("名称", productName, 1, 50, true, true, true, true, false, true, true);
		rowNoError = ic.doCheck("行番号", rowNo, 1, 6, false, false, false, true, false, false, false);
		if (productNameError.size() > 0 || rowNoError.size() > 0) {
			return result;
		}

		if (rowNo.contains("-")) {
			rowNoErrorMessage = "0以上英数半角で入力して下さい。";
			return result;
		}

		int xRow = Integer.valueOf(rowNo);
		if (!(xRow > 0)) {
			rowNoErrorMessage = "0以上英数半角で入力して下さい。";
			return result;
		}
		int no = dao.MaxNo(parentId);//列の最終番号を取得
		if(xRow <= no){ //ユーザが希望するNoが列の最後尾より小さいときtrue
			if (dao.exitsUser(rowNo, parentId)) { // 重複確認
				 minNo = dao.minNo(rowNo, parentId); //その列の最終番号の順番取得
				 dao.productRowEntry(minNo, parentId, Integer.valueOf(rowNo)); // 商品の行スペース追加


				//登録をするためminNoを登録メソッドへ提出

			}
		}

		if(xRow >= no){ //ユーザが希望するNoが列の最後尾より大きいとき
			xRow = dao.MaxNo(parentId); //その列のMaxNoを取得しユーザに与える。
			//登録をする
		}

		if (session.containsKey("transitionFlag")) { // そのため最初にsessionに値が入っているか確認しないといけない。
			transitionFlag = (boolean) session.get("transitionFlag");
			if (transitionFlag == true) {
				if (dao.productEntry(xRow, parentId, productName)) {
					entrySuccessMessage = "商品情報[" + productName + "]登録成功しました。";

					productList = listDAO.productList();
					session.put("productList", productList);
					result = SUCCESS;
					return result;
				}
			}
		}

		if (session.containsKey("mediumFlag")) {
			mediumFlag = (boolean) session.get("mediumFlag");
			if (mediumFlag == true) {
				if (dao.productEntry(xRow, parentId, productName)) {
					productList = listDAO.productListMedium(parentId);
					session.put("productListMedium", productList);
					entrySuccessMessage = "商品情報[" + productName + "]登録成功しました。";
					result = SUCCESS;
					return result;
				}

			}
		}

		if (session.containsKey("smallFlag")) {
			smallFlag = (boolean) session.get("smallFlag");
			if (smallFlag == true) {
				if (dao.productEntry(xRow, productId, productName)) {
					productList = listDAO.productListMedium(productId);
					session.put("productListSmall", productList);
					entrySuccessMessage = "商品情報[" + productName + "]登録成功しました。";
					result = SUCCESS;
					return result;
				}
			}
		}

		return result;
	}


	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
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

	public boolean isTransitionFlag() {
		return transitionFlag;
	}

	public void setTransitionFlag(boolean transitionFlag) {
		this.transitionFlag = transitionFlag;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getEntrySuccessMessage() {
		return entrySuccessMessage;
	}

	public void setEntrySuccessMessage(String entrySuccessMessage) {
		this.entrySuccessMessage = entrySuccessMessage;
	}

	public String getRowNoErrorMessage() {
		return rowNoErrorMessage;
	}

	public void setRowNoErrorMessage(String rowNoErrorMessage) {
		this.rowNoErrorMessage = rowNoErrorMessage;
	}

	public String getParentMiddleName() {
		return parentMiddleName;
	}

	public void setParentMiddleName(String parentMiddleName) {
		this.parentMiddleName = parentMiddleName;
	}

}
