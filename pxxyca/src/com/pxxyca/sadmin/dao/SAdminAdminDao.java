package com.pxxyca.sadmin.dao;

import java.util.List;

import com.pxxyca.sadmin.domain.jsj_sadmin_admin;

public interface SAdminAdminDao {

	Boolean saveAdmin(jsj_sadmin_admin admin);
	
	Boolean deleteAdmin(String jsj_sadmin_admin_id);
	//修改管理员
	Boolean updateAdmin(jsj_sadmin_admin admin);
	//修改个人密码
	Boolean updatePersonlPassword(String jsj_sadmin_admin_id, String admin_password);
	
	jsj_sadmin_admin queryAdminByUsernameAndPassword(String username, String password);
	
	List<jsj_sadmin_admin> queryAllAdmin();
	//查询权限
	jsj_sadmin_admin queryPermission(String jsj_sadmin_admin_id);
	//设置权限
	Boolean setPermission(String[] permission);
	
	
}
