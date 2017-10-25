package com.pxxyca.snews.service;

import java.util.List;

import com.pxxyca.snews.domain.jsj_snews_category;


public interface SNewsCategoryService {
	
	Boolean addSnews_category(jsj_snews_category category);
	
	Boolean deleteSnews_category(String news_category);

	Boolean updateSnews_category(jsj_snews_category category);
	
	jsj_snews_category querySnews_category_ByNewsCategory(String news_category);
	
	jsj_snews_category querySnews_categoty_ByNewsID();
	
	List<jsj_snews_category> queryAllSnews_category();


	/*public snews_category getCategoryByName(snews_category category);

	
	 * 
	 
	public boolean saveCategory(snews_category category);

	
	 * 
	 
	public List<snews_category> listCategoryByRankOne();

	public List<snews_category> listCategoryByName(String category_name);

	public List<snews_category> listCategoryByRankOne_ForHeader();

	public List<CategoryListDTO> listCategoryOneAndSon();

	public List<snews_category> listCategoryAll();

	public List<snews_category> listCategoryRankTwo();

	
	 * 
	 
	public boolean updateCategoryShowByID(snews_category category);

	public boolean updateCategoryAllByID(snews_category category);

	public boolean update_RemoveCategoryNewsByNewsID(String newsID);

	
	 * 
	 
	public snews_category getCategoryByID(snews_category category);

	public boolean removeCategoryByID(snews_category category);*/

}
