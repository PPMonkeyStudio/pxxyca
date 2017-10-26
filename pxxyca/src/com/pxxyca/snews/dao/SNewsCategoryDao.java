package com.pxxyca.snews.dao;

import java.util.List;

import com.pxxyca.snews.domain.jsj_snews_category;
import com.pxxyca.snews.domain.jsj_snews_content;
import com.pxxyca.snews.domain.jsj_snews_news;


public interface SNewsCategoryDao {
	Boolean saveSnews_category(jsj_snews_category category);
	
	Boolean deleteSnews_category(String news_category);

	Boolean updateSnews_category(jsj_snews_category news_category);
	
	jsj_snews_category querySnews_category_ByNewsCategory(String news_category);
	
	List<jsj_snews_category> queryAllSnews_category();

}
