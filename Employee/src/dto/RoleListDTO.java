package dto;

public class RoleListDTO {

	private int roleId;
	private String roleName;
	private boolean roleFlag = true;



	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public boolean isRoleFlag() {
		return roleFlag;
	}
	public void setRoleFlag(boolean roleFlag) {
		this.roleFlag = roleFlag;
	}

}
