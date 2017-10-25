/*package com.pxxyca.snews.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pxxyca.snews.dao.SNewsCategoryDao;
import com.pxxyca.snews.domain.jsj_jsj_snews_category;
import com.pxxyca.snews.domain.jsj_snews_category;
import com.pxxyca.snews.domain.jsj_snews_news;


@SuppressWarnings("unchecked")
public class SNewsCategoryDaoImpl implements SNewsCategoryDao {
	
	private SNewsCategoryDao sNewsCategoryDao;
	
	@Override
	public Boolean addCategory(jsj_jsj_snews_category category) {
		
		Session session = getSession();

		session.save(category);

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
	*//***************************************************************************************************************//*


	public SNewsCategoryDao getsNewsCategoryDao() {
		return sNewsCategoryDao;
	}

	public void setsNewsCategoryDao(SNewsCategoryDao sNewsCategoryDao) {
		this.sNewsCategoryDao = sNewsCategoryDao;
	}

	@Override
	public jsj_snews_category get_Category_ByNewsCategory(snews_news news) {

		Session session = getSession();

		String hql = "from jsj_snews_category category where category.jsj_snews_category_id='" + news.getNews_category()
				+ "'";

		System.out.println(hql);

		Query query = session.createQuery(hql);

		jsj_snews_category category = (jsj_snews_category) query.uniqueResult();

		return category;
	}

	@Override
	public boolean removeCategoryByFather(jsj_snews_category category) {

		Session session = getSession();

		String hql = "delete from jsj_snews_category category where category.category_father='"
				+ category.getjsj_snews_category_id() + "'";

		Query query = session.createQuery(hql);

		query.executeUpdate();

		return true;
	}

	@Override
	public boolean removeCategoryByID(jsj_snews_category category) {

		Session session = getSession();

		String hql = "delete from jsj_snews_category category where category.jsj_snews_category_id='"
				+ category.getjsj_snews_category_id() + "'";

		Query query = session.createQuery(hql);

		query.executeUpdate();

		return true;
	}

	@Override
	public jsj_snews_category get_Category_ByName(jsj_snews_category category) {

		Session session = getSession();

		String hql = "from jsj_snews_category  where category_name='" + category.getCategory_name() + "'";

		Query query = session.createQuery(hql);

		category = (jsj_snews_category) query.uniqueResult();

		if (category != null) {

			return category;

		} else {
			return null;
		}

	}

	@Override
	public boolean save_Category(jsj_snews_category category) {

		Session session = getSession();

		session.save(category);

		return true;
	}

	@Override
	public List<jsj_snews_category> listCategoryByRank(String category_rank) {

		Session session = getSession();

		String hql = "from jsj_snews_category category where category.category_rank='" + category_rank + "'";

		Query query = session.createQuery(hql);

		List<jsj_snews_category> categoryList = query.list();

		if (categoryList != null) {

			return categoryList;

		} else {
			return null;
		}
	}

	@Override
	public List<jsj_snews_category> listCategoryByName(String category_name) {
		Session session = getSession();

		String hql = "from jsj_snews_category category where category.category_name='" + category_name + "'";

		Query query = session.createQuery(hql);

		List<jsj_snews_category> categoryList = query.list();

		if (categoryList != null) {

			return categoryList;

		} else {
			return null;
		}
	}

	@Override
	public List<jsj_snews_category> listCategoryByRankOne_ForHeader() {

		Session session = getSession();

		String hql = "from jsj_snews_category category where category.category_show='1' and category.category_rank='1' order by category_gmt_create asc";

		Query query = session.createQuery(hql);

		List<jsj_snews_category> categoryList = query.list();

		if (categoryList != null) {

			return categoryList;

		} else {
			return null;
		}
	}

	@Override
	public List<jsj_snews_category> listCategoryAll() {

		Session session = getSession();

		String hql = "from jsj_snews_category order by category_gmt_create desc";

		Query query = session.createQuery(hql);

		List<jsj_snews_category> categoryList = query.list();

		return categoryList;
	}

	@Override
	public List<jsj_snews_category> listCategoryByFatherName(String category_fatherName) {

		Session session = getSession();

		String hql = "from jsj_snews_category category where category.category_name='" + category_fatherName + "'";

		Query query = session.createQuery(hql);

		jsj_snews_category father = (jsj_snews_category) query.uniqueResult();

		hql = "from jsj_snews_category category where category.category_father='" + father.getjsj_snews_category_id()
				+ "'";

		query = session.createQuery(hql);

		List<jsj_snews_category> categoryList = query.list();

		return categoryList;
	}

	@Override
	public List<jsj_snews_category> listCategoryByFather(String category_father) {

		Session session = getSession();

		String hql = "from jsj_snews_category category where category.category_father='" + category_father + "'";

		Query query = session.createQuery(hql);

		List<jsj_snews_category> categoryList = query.list();

		return categoryList;
	}

	@Override
	public jsj_snews_category get_Category_ByID(jsj_snews_category category) {

		Session session = getSession();

		String hql = "from jsj_snews_category category where category.jsj_snews_category_id='"
				+ category.getjsj_snews_category_id() + "'";

		Query query = session.createQuery(hql);

		category = (jsj_snews_category) query.uniqueResult();

		return category;

	}

	@Override
	public boolean update_RemoveCategoryNewsByNewsID(String newsID) {

		Session session = getSession();

		String hql = "update jsj_snews_category category set category.category_news='' where category.category_news='"
				+ newsID + "'";

		Query query = session.createQuery(hql);

		query.executeUpdate();

		return true;
	}

	@Override
	public boolean updateCategoryAllByID(jsj_snews_category category) {

		Session session = getSession();

		String hql = "update jsj_snews_category category set category.category_name='" + category.getCategory_name()
				+ "' where category.jsj_snews_category_id='" + category.getjsj_snews_category_id() + "'";

		Query query = session.createQuery(hql);

		query.executeUpdate();

		return true;
	}

	
	 * 
	 

	

	@Override
	public boolean updateCategoryShowByID(jsj_snews_category category) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public jsj_jsj_snews_category get_Category_ByName(jsj_jsj_snews_category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public jsj_jsj_snews_category get_Category_ByID(jsj_jsj_snews_category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public jsj_jsj_snews_category get_Category_ByNewsCategory(jsj_snews_news news) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean save_Category(jsj_jsj_snews_category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCategoryShowByID(jsj_jsj_snews_category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCategoryAllByID(jsj_jsj_snews_category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCategoryByID(jsj_jsj_snews_category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCategoryByFather(jsj_jsj_snews_category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean saveSnews_caegory(jsj_jsj_snews_category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deletejsj_snews_category(String news_category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updatejsj_snews_categor(jsj_snews_category news_category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public jsj_snews_category query jsj_snews_category_ByNewsCategory(String news_category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public jsj_snews_category querySnews_categoty_ByNewsID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<jsj_snews_category> queryAlljsj_snews_category() {
		// TODO Auto-generated method stub
		return null;
	}

}
*/