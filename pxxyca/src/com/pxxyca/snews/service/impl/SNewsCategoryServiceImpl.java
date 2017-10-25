package com.pxxyca.snews.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.pxxyca.snews.dao.SNewsCategoryDao;
import com.pxxyca.snews.domain.jsj_snews_category;
import com.pxxyca.snews.service.SNewsCategoryService;

import util.TimeUtil;

public class SNewsCategoryServiceImpl implements SNewsCategoryService {
	
	private SNewsCategoryDao sNewsCategoryDao;

	@Override
	public Boolean addSnews_category(jsj_snews_category category) {
		category.setCategory_gmt_create(TimeUtil.getStringSecond());
		category.setCategory_gmt_modified(category.getCategory_gmt_create());
		category.setJsj_snews_category_id(UUID.randomUUID().toString());
		sNewsCategoryDao.saveSnews_category(category);
		return true;
	}
	
	@Override
	public Boolean createSnews_category(String news_catrgory) {
		jsj_snews_category category = new jsj_snews_category();
		category.setCategory_gmt_create(TimeUtil.getStringSecond());
		category.setCategory_gmt_modified(category.getCategory_gmt_create());
		category.setJsj_snews_category_id(UUID.randomUUID().toString());
		sNewsCategoryDao.saveSnews_category(category);
		return true;
	}


	@Override
	public Boolean deleteSnews_category(String news_category) {
		sNewsCategoryDao.deleteSnews_category(news_category);
		return true;
	}

	@Override
	public Boolean updateSnews_category(jsj_snews_category category) {
		category.setJsj_snews_category_id(UUID.randomUUID().toString());
		category.setCategory_gmt_modified(category.getCategory_gmt_create());
		sNewsCategoryDao.updateSnews_category(category);
		return true;
	}

	@Override
	public jsj_snews_category querySnews_category_ByNewsCategory(String news_category) {
		jsj_snews_category category = sNewsCategoryDao.querySnews_category_ByNewsCategory(news_category);
		return category;
	}

	@Override
	public List<jsj_snews_category> queryAllSnews_category() {
		List<jsj_snews_category> category_list = sNewsCategoryDao.queryAllSnews_category();
		return category_list;
	}


}
