package com.pxxyca.snews.dao;

import java.util.List;

import com.pxxyca.snews.domain.NewsAndCategoryAndContentDTO;
import com.pxxyca.snews.domain.jsj_snews_category;
import com.pxxyca.snews.domain.jsj_snews_content;
import com.pxxyca.snews.domain.jsj_snews_news;
import com.pxxyca.snews.domain.page_list_newsVO;


public interface SNewsNewsDao {
	
	Boolean saveSnews_news(jsj_snews_news news);
	
	Boolean saveSnews_content(jsj_snews_content content);
	
	Boolean deleteSnews_news(String jsj_snews_news_id);
	
	Boolean deleteSnews_content(String content_news);
	
	Boolean updateSnews_news(jsj_snews_news jsj_snews_news_id);
	
	Boolean updateSnews_content(jsj_snews_content content_news);
	
	jsj_snews_news querySnews_news();
	//query+表命+By+字段名
	jsj_snews_news querySnews_news_ByNewsID(String jsj_snews_news_id);
	
	jsj_snews_content querySnews_content_ByNewsID(String jsj_snews_news_id);
	
	List<jsj_snews_news> queryAllSnews_news();
	
	List<jsj_snews_news> querySnews_news_ByNewsTitle(String searchWords);
	
	
	/*//添加新闻
		public boolean addNews(jsj_snews_news news);
		//查询新闻列表
		public List<NewsAndCategoryAndContentDTO> queryAllNews();
		//根据新闻ID，获取新闻正文，被查询新闻列表方法调用
		public jsj_snews_content get_Content_ByNewsID(jsj_snews_news news);
		//添加新闻正文
		public boolean addContents(jsj_snews_content content);
		//搜索新闻
		public List<NewsAndCategoryAndContentDTO> search(String jsj_snews_news_title);
		//删除新闻
		public void deleteNewsByID(jsj_snews_news news);
		//修改新闻
		public void updateNews(jsj_snews_news news);
		
		public void updateContent(jsj_snews_content content);
		
		public List<NewsAndCategoryAndContentDTO> queryAllNewsAndCategoryAndContent();
		
		public NewsAndCategoryAndContentDTO queryNewsAndCategoryAndContentByNewsID(String jsj_snews_news_id);
		
		public List<NewsAndCategoryAndContentDTO> queryNewsAndCategoryAndContentByNewsTitle(String jsj_snews_news_title);

		public void updateNewsBrowse(String newsID);

		public page_list_newsVO queryAllNewsAndCategoryAndContentByPage(page_list_newsVO page_list_news);
		
		//查询新闻表
		public List<jsj_snews_news> queryNewsList();
		//查询新闻内容表
		public List<jsj_snews_content> queryContentListByNewsID();
*//*	public boolean saveNews(jsj_snews_news news);

	public boolean saveContent(jsj_snews_content content);
	
	public List<NewsAndCategoryAndContentDTO> list_NewsAndCategoryAndContent_All();

	
	 * 
	 
	public List<jsj_snews_news> list_News_All();

	public List<jsj_snews_news> list_News_ByKeywords(String keywords);

	public List<jsj_snews_news> list_News_ByKeywords(String keywords, String newsID);

	public List<jsj_snews_news> list_News_ByCategory_Num(String categoryName, int num);

	public List<jsj_snews_news> list_News_ByRecommend_Num(int num);

//	public List<jsj_snews_news> list_News_ByPage(page_list_newsVO page_list_news);

//	public List<jsj_snews_news> list_News_ByCategoryAndPage(ListVO listVO);

//	public List<jsj_snews_news> list_News_BySearchAndPage(ListVO listVO);

//	public List<jsj_snews_news> list_News_ByDateAndPage(ListVO listVO) throws ParseException;

	
	 * 
	 

	public jsj_snews_content get_Content_ByNewsID(jsj_snews_news news);

	public int get_News_TotalRecords();

	public int get_News_TotalRecords_ByCategory(String categoryID);

	public int get_News_TotalRecords_BySearch(String userSearch);

	public int get_News_TotalRecords_ByDate(String date);

	
	 * 
	 
	public jsj_snews_news get_News_ByID(String newsID);

	
	 * 
	 
	public void removeNewsByID(jsj_snews_news news);

	public void removeContentByNewsID(jsj_snews_news news);

	
	 * 
	 
	public void updateNews(jsj_snews_news news);

	public void updateContent(jsj_snews_content content);
*/
		List<jsj_snews_content> queryContentList();
}
