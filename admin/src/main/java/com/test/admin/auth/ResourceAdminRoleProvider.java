package com.test.admin.auth;

import java.util.HashMap;
import java.util.Map;

public class ResourceAdminRoleProvider {

	private String resource;
	private String path;
	private Map<AdminPermission, String[]> roles;
	
	public ResourceAdminRoleProvider(String resource) {
		this.resource = resource;
		this.path = "/" + resource;
		this.roles = new HashMap<AdminPermission, String[]>();
		initRoles();
	}
	
	private void initRoles() {
		
		if(!resource.equals("review")) {
			putRoleByPermission(AdminPermission.Create);
			putRoleByPermission(AdminPermission.Update);
		}
		putRoleByPermission(AdminPermission.Read);
		putRoleByPermission(AdminPermission.Delete);
	}
	
	private void putRoleByPermission(AdminPermission permission) {
		roles.put(permission, getRoleByPermission(permission));
	}
	
	private String[] getRoleByPermission(AdminPermission permission) {
		String[] roles = { getSuperRole(), getRole("ALL"), getRole(permission.toString()) };
		return roles;
	}
	
	private String getSuperRole() {
		return "ROLE_SUPER";
	}
	
	private String getRole(String role) {
		return String.format("ROLE_%s_%s", this.resource, role);
	}
	
	public String[] getRole(AdminPermission permission) {
		return roles.get(permission);
	}
	
	public String getPath() {
		return this.path;
	}
	
	public String getSubPaths() {
		return String.format("%s/*", this.path);
	}
	
	public String getWritePath() {
		return String.format("%s/write", this.path);
	}
	
	public String getEditPath() {
		return String.format("%s/*/edit", this.path);
	}
	
	public String getAdminAuthPath() {
		return String.format("%s/*/auths", this.path);
	}
}
