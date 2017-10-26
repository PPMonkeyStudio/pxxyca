package com.pxxyca.sadmin.service;

import java.util.List;

import com.pxxyca.sadmin.domain.jsj_sadmin_admin;
/**
 *管理员模块接口
 *@author no one 
 */
public interface SAdminAdminService {

	/**
	 *添加管理员
	 *@author no one
	 *@param admin 管理员添加页面数据封装到此对象
	 *@return true添加成功，false添加失败
	 */
	Boolean addAdmin(jsj_sadmin_admin admin);
	/**
	 *根据管理员ID删除管理员
	 *@author no one
	 *@param jsj_sadmin_admin_id  管理员ID 
	 *@return true删除成功，false删除失败
	 */
	Boolean deleteAdmin(String jsj_sadmin_admin_id);
	/**
	 *根据管理员ID修改管理员
	 *@author no one
	 *@param admin 管理员修改后数据封装到此对象
	 *@return true更新成功，false更新失败 
	 */
	Boolean updateAdmin(jsj_sadmin_admin admin);
	/**
	 *修改个人密码
	 *@author no one
	 *@param jsj_sadmin_admin_id 管理员ID
	 *@param admin_password 管理员新密码
	 *@return true修改成功，false修改失败 
	 */
	Boolean resetPersonlPassword(String jsj_sadmin_admin_id, String admin_password);
	/**
	 *查询管理员列表
	 *@author no one
	 *@return 被封装的查询到的管理员列表集合 
	 */
	jsj_sadmin_admin queryAdminByUsernameAndPassword(String username, String password);

	List<jsj_sadmin_admin> queryAllAdmin();
	//查询权限
	String[] queryPermission(String jsj_sadmin_admin_id);
	//设置权限
	Boolean setPermission(String[] permission);
	
	
}
