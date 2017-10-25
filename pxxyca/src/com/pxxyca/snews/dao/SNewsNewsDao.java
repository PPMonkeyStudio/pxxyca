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
	
	//query+表命+By+字段名
	jsj_snews_news querySnews_news_ByNewsID(String jsj_snews_news_id);
	
	jsj_snews_content querySnews_content_ByNewsID(String jsj_snews_news_id);
	
	List<jsj_snews_news> queryAllSnews_news();
	
	List<jsj_snews_news> querySnews_news_ByNewsTitle(String searchWords);
	
}
