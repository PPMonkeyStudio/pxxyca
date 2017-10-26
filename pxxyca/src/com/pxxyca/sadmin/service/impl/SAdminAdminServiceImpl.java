package com.pxxyca.sadmin.service.impl;

import java.util.List;
import java.util.UUID;

import com.pxxyca.sadmin.dao.SAdminAdminDao;
import com.pxxyca.sadmin.domain.jsj_sadmin_admin;
import com.pxxyca.sadmin.service.SAdminAdminService;

import util.TimeUtil;

public class SAdminAdminServiceImpl implements SAdminAdminService {

	@Override
	public Boolean addAdmin(jsj_sadmin_admin admin) {
		admin.setJsj_sadmin_admin_id(UUID.randomUUID().toString());
		admin.setAdmin_gmt_create(TimeUtil.getStringSecond());
		admin.setAdmin_gmt_modified(admin.getAdmin_gmt_create());
		sAdminAdminDao.saveAdmin(admin);
		return true;
	}

	@Override
	public Boolean deleteAdmin(String jsj_sadmin_admin_id) {
		sAdminAdminDao.deleteAdmin(jsj_sadmin_admin_id);
		return true;
	}

	@Override
	public Boolean updateAdmin(jsj_sadmin_admin admin) {
		sAdminAdminDao.updateAdmin(admin);
		return true;
	}

	@Override
	public Boolean resetPersonlPassword(String jsj_sadmin_admin_id, String admin_password) {
		sAdminAdminDao.updatePersonlPassword(jsj_sadmin_admin_id, admin_password);
		return true;
	}

	@Override
	public List<jsj_sadmin_admin> queryAllAdmin() {
		List<jsj_sadmin_admin> admin_list = sAdminAdminDao.queryAllAdmin();
		return admin_list;
	}
	
	@Override
	public jsj_sadmin_admin queryAdminByUsernameAndPassword(String username, String password) {
		jsj_sadmin_admin admin = sAdminAdminDao.queryAdminByUsernameAndPassword(username, password);
		return admin;
	}

	@Override
	public String[] queryPermission(String jsj_sadmin_admin_id) {
		String[] permission = new String[2];
		jsj_sadmin_admin admin = sAdminAdminDao.queryPermission(jsj_sadmin_admin_id);
		String permission_sadmin = admin.getAdmin_premission_sadmin();
		String permission_snews = admin.getAdmin_premission_snews();
		String sadminPermission = "无权限";
		String snewsPermission = "无权限";
		if ("新闻子系统权限".equals(permission_snews)) {  
			snewsPermission = "新闻子系统权限";
		}
		if ("管理员子系统权限".equals(permission_sadmin)) {
			sadminPermission = "管理员子系统权限";
		}
		permission[0] = sadminPermission;
		permission[1] = snewsPermission;
		return permission;
	}
	
	@Override
	public Boolean setPermission(String[] permission) {
		sAdminAdminDao.setPermission(permission);
		return true;
	}

	private SAdminAdminDao sAdminAdminDao;

	public SAdminAdminDao getsAdminAdminDao() {
		return sAdminAdminDao;
	}

	public void setsAdminAdminDao(SAdminAdminDao sAdminAdminDao) {
		this.sAdminAdminDao = sAdminAdminDao;
	}

	
}
