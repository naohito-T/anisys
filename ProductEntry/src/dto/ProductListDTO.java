package dto;

public class ProductListDTO {

	private int productId;
	private int parentId;
	private String productCD;
	private String productName;
	private int RowNo;
	private String updDt;
	private int updUser;

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getProductCD() {
		return productCD;
	}
	public void setProductCD(String productCD) {
		this.productCD = productCD;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getRowNo() {
		return RowNo;
	}
	public void setRowNo(int rowNo) {
		RowNo = rowNo;
	}
	public String getUpdDt() {
		return updDt;
	}
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	public int getUpdUser() {
		return updUser;
	}
	public void setUpdUser(int updUser) {
		this.updUser = updUser;
	}



}
