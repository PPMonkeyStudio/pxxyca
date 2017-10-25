package com.pxxyca.snews.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pxxyca.snews.dao.SNewsCategoryDao;
import com.pxxyca.snews.domain.jsj_snews_category;
import com.pxxyca.snews.domain.jsj_snews_category;
import com.pxxyca.snews.domain.jsj_snews_news;


@SuppressWarnings("unchecked")
public class SNewsCategoryDaoImpl implements SNewsCategoryDao {
	
	private SNewsCategoryDao sNewsCategoryDao;
	
	@Override
	public Boolean saveSnews_category(jsj_snews_category category) {
		Session session = getSession();
		session.save(category);
		return true;
	}
	
	@Override
	public Boolean deleteSnews_category(String news_category) {
		Session session = getSession();
		String hql = "delete from jsj_snews_category where jsj_snews_category_id='" + news_category + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}
	
	public Boolean updateSnews_category(jsj_snews_category news_category) {
		Session session = getSession();
		String hql = "update jsj_snews_category set jsj_snews_news_id='" +news_category.getJsj_snews_category_id() + "',category_name="
				+ "'" + news_category.getCategory_name() + "',category_gmt_create='" + news_category.getCategory_gmt_create() + "',category_gmt_modified='"
				+ news_category.getCategory_gmt_modified() + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}

	@Override
	public jsj_snews_category querySnews_category_ByNewsCategory(String news_category) {
		Session session = getSession();
		String hql = "from jsj_snews_category where jsj_snews_category_id='" + news_category + "'";
		jsj_snews_category category = (jsj_snews_category) session.createQuery(hql).uniqueResult();
		return category;
	}

	@Override
	public List<jsj_snews_category> queryAllSnews_category() {
		Session session = getSession();
		String hql = "from jsj_snews_category";
		Query query= session.createQuery(hql);
		List<jsj_snews_category> list = query.list();
		return list;
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
