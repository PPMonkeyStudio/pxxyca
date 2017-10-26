package com.pxxyca.snews.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;	

import com.pxxyca.snews.dao.SNewsNewsDao;
import com.pxxyca.snews.domain.NewsAndCategoryAndContentDTO;
import com.pxxyca.snews.domain.jsj_snews_category;
import com.pxxyca.snews.domain.jsj_snews_content;
import com.pxxyca.snews.domain.jsj_snews_news;

public class SNewsNewsDaoImpl implements SNewsNewsDao {

	@Override
	public Boolean saveSnews_news(jsj_snews_news news) {
		Session session = getSession();
		//session找到类对应的表，若表id不为空，则数据插入
		session.save(news);
		return true;
	}

	@Override
	public Boolean saveSnews_content(jsj_snews_content content) {
		Session session = getSession();
		session.save(content);
		return true;
	}

	@Override
	public Boolean deleteSnews_news(String jsj_snews_news_id) {
		Session session = getSession();
////////////////////?????????????????hql是否正确
		String hql = "delete from jsj_snews_news news where news.jsj_snews_news_id='" + jsj_snews_news_id + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}

	@Override
	public Boolean deleteSnews_content(String content_news) {
		Session session = getSession();
////////////////////?????????????????hql是否正确
		String hql = "delete from jsj_snews_content content where content.content_news='" + content_news + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}

	@Override
	public Boolean updateSnews_news(jsj_snews_news jsj_snews_news_id) {
		Session session = getSession();
		String hql = "update jsj_snews_news news set news.news_title='" + jsj_snews_news_id.getNews_title() + "',news.news_img='"
				+ jsj_snews_news_id.getNews_img() +  "',news.news_category='" + jsj_snews_news_id.getNews_category()
				+ "',news.news_gmt_show='" + jsj_snews_news_id.getNews_gmt_show() + "',news.news_gmt_modified='"
				+ jsj_snews_news_id.getNews_gmt_modified() + "' where news.jdj_snews_news_id='" + jsj_snews_news_id.getJsj_snews_news_id() + "'";
		System.out.println(hql);
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}

	@Override
	public Boolean updateSnews_content(jsj_snews_content content_news) {
		Session session = getSession();
		String hql = "update jsj_snews_content content set content.content_text='" + content_news.getContent_text()
				+ "',content.content_gmt_modified='" + content_news.getContent_gmt_modified()
				+ "' where content.jsj_snews_content_id='" + content_news.getJsj_snews_content_id() + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		return true;
	}

	@Override
	public jsj_snews_news querySnews_news_ByNewsID(String jsj_snews_news_id) {
		Session session = getSession();
		String hql = "from jsj_snews_news  where jsj_snews_news_id='" + jsj_snews_news_id + "'";
		jsj_snews_news news = (jsj_snews_news) session.createQuery(hql).uniqueResult();
		return news;
	}

	@Override
	public jsj_snews_content querySnews_content_ByNewsID(String jsj_snews_news_id) {
		Session session = getSession();
		String hql = "from jsj_snews_content  where content_news='" + jsj_snews_news_id + "'";
		jsj_snews_content content = (jsj_snews_content) session.createQuery(hql).uniqueResult();
		return content;
	}

	@Override
	public List<jsj_snews_news> queryAllSnews_news() {
		Session session = getSession();
		String hql = "from jsj_snews_news";
		Query query = session.createQuery(hql);
		List<jsj_snews_news> newsList = query.list();
		return newsList;
	}

	@Override
	public List<jsj_snews_news> querySnews_news_ByNewsTitle(String searchWords) {
		Session session = getSession();
		String hql = "from jsj_snews_news news where (news.news_title like '%" + searchWords
				+ "%') order by news_gmt_show desc";
		Query query = session.createQuery(hql);
		List<jsj_snews_news> news_list = query.list();
		return news_list;
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
