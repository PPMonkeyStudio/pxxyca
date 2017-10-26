package com.pxxyca.sadmin.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pxxyca.sadmin.dao.SAdminAdminDao;
import com.pxxyca.sadmin.domain.jsj_sadmin_admin;

public class SAdminAdminDaoImpl implements SAdminAdminDao {

	@Override
	public Boolean saveAdmin(jsj_sadmin_admin admin) {
		Session session = getSession();
		session.save(admin);
		return true;
	}

	@Override
	public Boolean deleteAdmin(String jsj_sadmin_admin_id) {
		Session session = getSession();
		String hql = "delete from jsj_sadmin_admin where jsj_sadmin_admin_id='" + jsj_sadmin_admin_id + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}

	@Override
	public Boolean updateAdmin(jsj_sadmin_admin admin) {
		Session session = getSession();
		String hql = "update jsj_sadmin_admin set admin_account='"
				+ admin.getAdmin_account() +"',	admin_password='" + admin.getAdmin_password() + "',admin_premission_sadmin='"
				+admin.getAdmin_premission_sadmin()+ "',admin_permission_snews='" + admin.getAdmin_premission_snews()+ "',admin_gmt_create='"
				+ admin.getAdmin_gmt_create() + "',admin_gmt_modified='" + admin.getAdmin_gmt_modified() + "' where jsj_sadmin_admin_id='"
				+ admin.getJsj_sadmin_admin_id() + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}

	@Override
	public Boolean updatePersonlPassword(String jsj_sadmin_admin_id, String admin_password) {
		Session session = getSession();
		String hql = "update jsj_sadmin_admin set admin_password='" + admin_password + "' where jsj_sadmin_admin_id='" + admin_password + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}

	@Override
	public List<jsj_sadmin_admin> queryAllAdmin() {
		Session session = getSession();
		String hql = "from jsj_sadmin_admin";
		Query query = session.createQuery(hql);
		List<jsj_sadmin_admin> admin_list = query.list();
		return admin_list;
	}

	@Override
	public jsj_sadmin_admin queryAdminByUsernameAndPassword(String username, String password) {
		Session session = getSession();
		String hql = "from jsj_sadmin_admin where admin_account='" + username + "',admin_password='" + password + "'";
		Query query = session.createQuery(hql);
		jsj_sadmin_admin admin = (jsj_sadmin_admin)query.uniqueResult();
		return admin;
	}

	@Override
	public jsj_sadmin_admin queryPermission(String jsj_sadmin_admin_id) {
		Session session = getSession();
		String hql = "from jsj_sadmin_admin where jsj_sadmin_admin_id='" + jsj_sadmin_admin_id + "'";
		jsj_sadmin_admin admin = (jsj_sadmin_admin) session.createQuery(hql).uniqueResult();
		return admin;
	}

	@Override
	public Boolean setPermission(String[] permission) {
		Session session =getSession();
		String hql = "update jsj_sadmin_admin set admin_premission_sadmin='" + permission[0] + "',admin_premission_snews='" + permission[1] + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}	
	
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	

	
}
