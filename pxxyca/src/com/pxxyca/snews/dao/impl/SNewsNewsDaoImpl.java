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
		String hql = "update sjsj_snews_news news set news.news_title='" + jsj_snews_news_id.getNews_title() + "',news.news_img='"
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

	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	*//***********************************************************************************************************************************************************************//*	
/*	@Override
	public void updateContent(snews_content content) {

		Session session = getSession();

		String hql = "update snews_content content set content.content_text='" + content.getContent_text()
				+ "',content.content_gmt_modified='" + content.getContent_gmt_modified()
				+ "' where content.snews_content_id='" + content.getSnews_content_id() + "'";

		Query query = session.createQuery(hql);

		query.executeUpdate();

	}

	@Override
	public void updateNews(snews_news news) {

		Session session = getSession();
		String hql = "update snews_news news set news.news_title='" + news.getNews_title() + "',news.news_img='"
				+ news.getNews_img() +  "',news.news_category='" + news.getNews_category()
				+ "',news.news_gmt_show='" + news.getNews_gmt_show() + "',news.news_gmt_modified='"
				+ news.getNews_gmt_modified() + "' where news.snews_news_id='" + news.getSnews_news_id() + "'";

		System.out.println(hql);

		Query query = session.createQuery(hql);

		query.executeUpdate();

	}

/*	
	@Override
	public void removeNewsByID(snews_news news) {

		Session session = getSession();

		String hql = "delete from snews_news news where  news.snews_news_id='" + news.getSnews_news_id()
				+ "'";
		
		Query query = session.createQuery(hql);

		query.executeUpdate();
	}

	@Override
	public int get_News_TotalRecords_ByDate(String mydate) {
		Session session = getSession();

		String start_time = "0000-00-00";
		String stop_time = "9999-99-99";

		if (mydate.equals("1")) {
			start_time = TimeUtil.getStringDay();
		} else if (mydate.equals("2")) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// Сд��mm��ʾ���Ƿ���

			String dstr = TimeUtil.getStringDay();

			java.util.Date date = null;
			try {
				date = sdf.parse(dstr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Calendar calendar = new GregorianCalendar();

			calendar.setTime(date);

			String day = TimeUtil.getDay_Of_Week(TimeUtil.getDateDay());

			switch (day) {
			case "星期日":
				day = "1";
				break;
			case "星期一":
				day = "2";
				break;
			case "星期二":
				day = "3";
				break;
			case "星期三":
				day = "4";
				break;
			case "星期四":
				day = "5";
				break;
			case "星期五":
				day = "6";
				break;
			case "星期六":
				day = "7";
				break;
			}

			System.out.println("��ݣ�" + day);

			calendar.add(calendar.DATE, -1 * (Integer.parseInt(day) - 1));// ��������������һ��.����������,������ǰ�ƶ�
			date = calendar.getTime(); // ���ʱ���������������һ��Ľ��

			start_time = sdf.format(date);
			System.out.println("start_time:" + start_time);
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// Сд��mm��ʾ���Ƿ���

			String dstr = TimeUtil.getStringDay();

			java.util.Date date = null;
			try {
				date = sdf.parse(dstr);
			} catch (ParseException e) {

				e.printStackTrace();
			}

			Calendar calendar = new GregorianCalendar();

			calendar.setTime(date);

			// ��������������һ��.����������,������ǰ�ƶ�
			calendar.add(calendar.DATE, -1 * (Integer.parseInt(TimeUtil.getStringDay().substring(8)) - 1));

			date = calendar.getTime(); // ���ʱ���������������һ��Ľ��

			start_time = sdf.format(date);

			System.out.println("start_time:" + start_time);
		}

		String hql = "select count(*) from snews_news news   where news.news_publish ='1' and   news.news_gmt_show >= '"
				+ start_time + "'   order by news.news_gmt_show desc ";

		System.out.println(hql);

		Query query = session.createQuery(hql);

		int count = ((Number) query.uniqueResult()).intValue();
		System.out.println("count:" + count);
		//
		return count;
	}

	@Override
	public int get_News_TotalRecords_ByCategory(String categoryID) {

		Session session = getSession();

		String category = "%%";

		String recommend = "%%";

		if (categoryID != null) {
			if (categoryID.equals("ѧԺҪ��")) {
				recommend = "1";
			} else {
				category = "%" + categoryID + "%";
			}

		}

		String hql = "select count(*) from snews_news news,snews_category category where category.snews_category_id=news.news_category and category_name like '"
				+ category + "' and news_recommend like '" + recommend + "'";

		System.out.println(hql);

		Query query = session.createQuery(hql);

		int count = ((Number) query.uniqueResult()).intValue();
		System.out.println("count:" + count);
		//
		return count;
	}

	@Override
	public int get_News_TotalRecords() {

		Session session = getSession();

		String hql = "select count(*) from snews_news";

		Query query = session.createQuery(hql);

		int count = ((Number) query.uniqueResult()).intValue();

		//
		return count;

	}

	@Override
	public int get_News_TotalRecords_BySearch(String search) {
		Session session = getSession();

		search = "%" + search + "%";

		String hql = "select count(*) from snews_news news where ( news.news_title like '" + search
				+ "' or news.news_keywords like '" + search + "' ) and news.news_publish='1'";

		System.out.println(hql);

		Query query = session.createQuery(hql);

		int count = ((Number) query.uniqueResult()).intValue();

		System.out.println("count:" + count);

		return count;
	}
*/
	/*public List<snews_news> list_News_BySearchAndPage(ListVO listVO) {

		Session session = getSession();

		String search = "%" + listVO.getSearch() + "%";

		String hql = "from snews_news news where (news.news_title like '" + search
				+ "' or news.news_keywords like '" + search
				+ "')  and news.news_publish='1' order by news_gmt_show desc";
		System.out.println("hql:" + hql);
		Query query = session.createQuery(hql);
		query.setFirstResult((listVO.getPageIndex() - 1) * listVO.getPageSize());
		query.setMaxResults(listVO.getPageSize());
		List<snews_news> newsList = query.list();

		session.clear();

		for (snews_news news : newsList) {

			System.out.println(listVO.getSearch());

			news.setNews_title(news.getNews_title().replaceAll(listVO.getSearch(),
					"<span style='color: #ff5063;font-size: 20px;'>" + listVO.getSearch() + "</span>"));

		}

		return newsList;
	}
*/
	/*@Override
	public List<snews_news> list_News_ByDateAndPage(ListVO listVO) throws ParseException {

		Session session = getSession();

		String start_time = "0000-00-00";
		String stop_time = "9999-99-99";

		if (listVO.getDate().equals("1")) {
			start_time = TimeUtil.getStringDay();
		} else if (listVO.getDate().equals("2")) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// Сд��mm��ʾ���Ƿ���

			String dstr = TimeUtil.getStringDay();

			java.util.Date date = sdf.parse(dstr);

			Calendar calendar = new GregorianCalendar();

			calendar.setTime(date);

			String day = TimeUtil.getDay_Of_Week(TimeUtil.getDateDay());

			switch (day) {
			case "������":
				day = "1";
				break;
			case "����һ":
				day = "2";
				break;
			case "���ڶ�":
				day = "3";
				break;
			case "������":
				day = "4";
				break;
			case "������":
				day = "5";
				break;
			case "������":
				day = "6";
				break;
			case "������":
				day = "7";
				break;
			}

			System.out.println("��ݣ�" + day);

			calendar.add(calendar.DATE, -1 * (Integer.parseInt(day) - 1));// ��������������һ��.����������,������ǰ�ƶ�
			date = calendar.getTime(); // ���ʱ���������������һ��Ľ��

			start_time = sdf.format(date);
			System.out.println("start_time:" + start_time);
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// Сд��mm��ʾ���Ƿ���

			String dstr = TimeUtil.getStringDay();

			java.util.Date date = sdf.parse(dstr);

			Calendar calendar = new GregorianCalendar();

			calendar.setTime(date);

			// ��������������һ��.����������,������ǰ�ƶ�
			calendar.add(calendar.DATE, -1 * (Integer.parseInt(TimeUtil.getStringDay().substring(8)) - 1));

			date = calendar.getTime(); // ���ʱ���������������һ��Ľ��

			start_time = sdf.format(date);

			System.out.println("start_time:" + start_time);
		}

		String hql = "from snews_news news where news.news_publish ='1' and   news.news_gmt_show >= '" + start_time
				+ "'   order by news.news_gmt_show desc";

		System.out.println("hql:" + hql);

		Query query = session.createQuery(hql);

		query.setFirstResult((listVO.getPageIndex() - 1) * listVO.getPageSize());

		query.setMaxResults(listVO.getPageSize());

		List<snews_news> newsList = query.list();

		session.clear();

		return newsList;
	}
*/
	/*@Override
	public List<snews_news> list_News_ByCategoryAndPage(ListVO listVO) {
		Session session = getSession();
		String category = "%%";
		String recommend = "%%";
		String father = "%%";
		if (listVO.getCategory() != null) {
			if (listVO.getCategory().equals("ѧԺҪ��")) {
				recommend = "1";
			} else {
				String hql = "from snews_category  where  category_name='" + listVO.getCategory() + "' ";
				System.out.println("hql:" + hql);
				Query query = session.createQuery(hql);
				snews_category thisCategory = (snews_category) query.uniqueResult();
				if (thisCategory.getCategory_rank() == 1) {
					// һ�����
					if (listVO.getCategory().equals("֪ͨ����")) {
						category = "%" + listVO.getCategory() + "%";
					} else {
						// ��ָ��
						father = "%" + thisCategory.getsnews_category_id() + "%";
					}
				} else {
					// �������
					category = "%" + listVO.getCategory() + "%";
				}
			}
		}
		String hql = "select news from snews_news news,snews_category category where news.news_category=category.snews_category_id and category.category_name like '"
				+ category + "' and news.news_recommend like '" + recommend + "' and category.category_father like '"
				+ father + "'  and news.news_publish='1' order by news_gmt_show desc";
		System.out.println("hql:" + hql);
		Query query = session.createQuery(hql);
		query.setFirstResult((listVO.getPageIndex() - 1) * listVO.getPageSize());
		query.setMaxResults(listVO.getPageSize());
		List<snews_news> newsList = query.list();
		session.clear();
		return newsList;
	}
*/
	/*@Override
	public List<snews_news> list_News_ByPage(page_list_newsVO page_list_news) {

		Session session = getSession();

		String news_publish = "0,1";
		String news_recommend = "0,1";
		String category = "%%";

		String sqrt = "news_gmt_create";
		String sqrt_sc = "desc";

		String news_title = "%%";

		String news_keywords = "%%";

		String start_time = "0000-00-00";
		String stop_time = "9999-99-99";

		if (page_list_news.getSearch() != null) {
			if (page_list_news.getSearch().getPublish() != -1) {
				news_publish = "" + page_list_news.getSearch().getPublish();
			}

			if (page_list_news.getSearch().getRecommend() != -1) {
				news_recommend = "" + page_list_news.getSearch().getRecommend();
			}
			if (!page_list_news.getSearch().getCategory().equals("-1")) {
				category = page_list_news.getSearch().getCategory();
			}

			sqrt = page_list_news.getSearch().getSqrt();
			sqrt_sc = page_list_news.getSearch().getSqrt_sc();

			news_title = "%" + page_list_news.getSearch().getKeywords() + "%";

			news_keywords = "%" + page_list_news.getSearch().getKeywords() + "%";

			start_time = page_list_news.getSearch().getStart_time();

			if (!page_list_news.getSearch().getStop_time().equals("9999-99-99")) {

				try {

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// Сд��mm��ʾ���Ƿ���
					String dstr = page_list_news.getSearch().getStop_time();
					java.util.Date date = sdf.parse(dstr);

					Calendar calendar = new GregorianCalendar();
					calendar.setTime(date);
					calendar.add(calendar.DATE, 1);// ��������������һ��.����������,������ǰ�ƶ�
					date = calendar.getTime(); // ���ʱ���������������һ��Ľ��

					String str = sdf.format(date);

					stop_time = str;

				} catch (ParseException e) {

					e.printStackTrace();
				}
			}

		}

		String hql = "from snews_news news where (news.news_title like '" + news_title
				+ "' or news.news_keywords like '" + news_keywords + "') and news.news_category like '" + category
				+ "' and news.news_publish in (" + news_publish + ") and  news.news_recommend in (" + news_recommend
				+ ") and news.news_gmt_show >= '" + start_time + "' and news.news_gmt_show <= '" + stop_time
				+ "'   order by news." + sqrt + " " + sqrt_sc;

		System.out.println("hql:" + hql);

		Query query = session.createQuery(hql);

		query.setFirstResult((page_list_news.getPageIndex() - 1) * page_list_news.getPageSize());

		query.setMaxResults(page_list_news.getPageSize());

		List<snews_news> newsList = query.list();

		session.clear();

		return newsList;
	}*/

/*	@Override
	public snews_content get_Content_ByNewsID(snews_news news) {

		Session session = getSession();

		String hql = "from snews_content content where content.content_news='" + news.getSnews_news_id() + "'";

		Query query = session.createQuery(hql);

		snews_content content = (snews_content) query.uniqueResult();

		return content;

	}

	@Override
	public List<snews_news> list_News_ByRecommend_Num(int num) {

		Session session = getSession();

		String hql = "from snews_news news  where  news.news_publish='1' and news.news_recommend='1' order by news.news_gmt_show desc";
		System.out.println(hql);
		Query query = session.createQuery(hql);

		query.setFirstResult(0);

		query.setMaxResults(num);

		List<snews_news> newsList = query.list();
		System.out.println(newsList.size());
		return newsList;
	}

	@Override
	public List<snews_news> list_News_ByCategory_Num(String categoryName, int num) {

		Session session = getSession();

		String hql = "select news from snews_news news,snews_category category where news.news_publish='1' and news.news_category=category.snews_category_id and category.category_name='"
				+ categoryName + "' order by news_gmt_show desc";
		System.out.println(hql);
		Query query = session.createQuery(hql);

		query.setFirstResult(0);

		query.setMaxResults(num);

		List<snews_news> newsList = query.list();

		return newsList;

	}

	@Override
	public List<snews_news> list_News_ByKeywords(String keywords) {

		Session session = getSession();

		String[] splitKeywords = keywords.split(";");

		int KeywordsNum = splitKeywords.length;

		int i = 0;

		String hql = "from snews_news where news_keywords like '%" + splitKeywords[i] + "%' ";

		i++;

		while (i < KeywordsNum) {

			hql = hql + "or news_keywords like '%" + splitKeywords[i] + "%' ";

			i++;
		}

		hql = hql + "order by news_browse desc ";

		System.out.println(hql);

		Query query = session.createQuery(hql);

		List<snews_news> newsList = query.list();

		return newsList;
	}

	@Override
	public List<snews_news> list_News_ByKeywords(String keywords, String newsID) {

		Session session = getSession();

		String[] splitKeywords = keywords.split(";");

		int KeywordsNum = splitKeywords.length;

		int i = 0;

		String hql = "from snews_news where (news_keywords like '%" + splitKeywords[i] + "%' ";

		i++;

		while (i < KeywordsNum) {

			hql = hql + "or news_keywords like '%" + splitKeywords[i] + "%' ";

			i++;
		}

		hql = hql + " )and news_recommend='1' and snews_news_id <> '" + newsID + "' order by news_browse desc ";

		System.out.println(hql);

		Query query = session.createQuery(hql);

		query.setFirstResult(0);

		query.setMaxResults(4);

		List<snews_news> newsList = query.list();

		if (newsList.size() == 4) {
			return newsList;
		}

		// ���δ���ĸ��ʹ������������

		int num = 4 - newsList.size();

		hql = "from snews_news where news_recommend='1' and  snews_news_id <> '" + newsID
				+ "' order by news_browse desc";

		System.out.println(hql);

		query = session.createQuery(hql);

		query.setFirstResult(0);

		query.setMaxResults(num);

		newsList.addAll(query.list());

		return newsList;
	}

	@Override
	public List<snews_news> list_News_All() {

		Session session = getSession();

		String hql = "from snews_news";

		Query query = session.createQuery(hql);

		List<snews_news> newsList = query.list();

		return newsList;
	}

	@Override
	public boolean saveContent(snews_content content) {

		Session session = getSession();

		session.save(content);

		return true;
	}

	@Override
	public boolean saveNews(snews_news news) {

		Session session = getSession();

		session.save(news);

		return true;
	}
	
	public List<NewsAndCategoryAndContentDTO> list_NewsAndCategoryAndContent_All() {
		
		Session session = getSession();

		String hql = "from snews_news, snews_category, snews_content";

		Query query = session.createQuery(hql);

		List<NewsAndCategoryAndContentDTO> newsList = query.list();

		return newsList;
	}

	
	 * 
	 * 
	 * 
	 */
}
