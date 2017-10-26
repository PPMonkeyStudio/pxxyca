package com.pxxyca.snews.service;

import java.util.List;

import com.pxxyca.snews.domain.jsj_snews_category;


public interface SNewsCategoryService {
	//添加类别
	Boolean addSnews_category(jsj_snews_category category);
	//创建类别
	Boolean createSnews_category(String news_catrgory);
	
	Boolean deleteSnews_category(String news_category);

	Boolean updateSnews_category(jsj_snews_category category);
	
	jsj_snews_category querySnews_category_ByNewsCategory(String news_category);
	
	List<jsj_snews_category> queryAllSnews_category();


}
