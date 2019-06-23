package com.prosmv.form;


public class PermissionForm {
	private String companyName;
	private String roleName;
	private String subModuleName;
	private String loginUserName;
	private boolean canAccess;
	private boolean canView;
	private boolean canSave;
	private boolean canUpdate;
	private boolean canDelete;
	private boolean canSendForApproval;
	private boolean canApprove;
	private boolean canPrint;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getSubModuleName() {
		return subModuleName;
	}
	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}
	public boolean isCanAccess() {
		return canAccess;
	}
	public void setCanAccess(boolean canAccess) {
		this.canAccess = canAccess;
	}
	public boolean isCanView() {
		return canView;
	}
	public void setCanView(boolean canView) {
		this.canView = canView;
	}
	public boolean isCanSave() {
		return canSave;
	}
	public void setCanSave(boolean canSave) {
		this.canSave = canSave;
	}
	public boolean isCanUpdate() {
		return canUpdate;
	}
	public void setCanUpdate(boolean canUpdate) {
		this.canUpdate = canUpdate;
	}
	public boolean isCanDelete() {
		return canDelete;
	}
	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}
	public boolean isCanSendForApproval() {
		return canSendForApproval;
	}
	public void setCanSendForApproval(boolean canSendForApproval) {
		this.canSendForApproval = canSendForApproval;
	}
	public boolean isCanApprove() {
		return canApprove;
	}
	public void setCanApprove(boolean canApprove) {
		this.canApprove = canApprove;
	}
	public boolean isCanPrint() {
		return canPrint;
	}
	public void setCanPrint(boolean canPrint) {
		this.canPrint = canPrint;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getLoginUserName() {
		return loginUserName;
	}
	public void setLoginUserName(String loginUserName) {
		this.loginUserName = loginUserName;
	}
	
}
