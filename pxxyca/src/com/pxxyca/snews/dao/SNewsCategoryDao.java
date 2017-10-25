package com.pxxyca.snews.dao;

import java.util.List;

import com.pxxyca.snews.domain.jsj_snews_category;
import com.pxxyca.snews.domain.jsj_snews_content;
import com.pxxyca.snews.domain.jsj_snews_news;


public interface SNewsCategoryDao {
	
	Boolean saveSnews_caegory(jsj_snews_category category);
	
	Boolean deleteSnews_category(String news_category);

	Boolean updateSnews_category(jsj_snews_category news_category);
	
	jsj_snews_category querySnews_category_ByNewsCategory(String news_category);
	
	jsj_snews_category querySnews_categoty_ByNewsID();
	
	List<jsj_snews_category> queryAllSnews_category();

	Boolean addCategory(jsj_snews_category category);

	
	/*public Boolean addCategory(jsj_snews_category category);

	
	*//*******************************************************************************************************************//*
	
	 * 
	 
	public jsj_snews_category get_Category_ByName(jsj_snews_category category);

	public jsj_snews_category get_Category_ByID(jsj_snews_category category);

	public jsj_snews_category get_Category_ByNewsCategory(jsj_snews_news news);

	
	 * 
	 
	public boolean save_Category(jsj_snews_category category);

	
	 * 
	 
	public List<jsj_snews_category> listCategoryByRank(String category_rank);

	public List<jsj_snews_category> listCategoryByName(String category_name);

	public List<jsj_snews_category> listCategoryByRankOne_ForHeader();

	public List<jsj_snews_category> listCategoryAll();

	public List<jsj_snews_category> listCategoryByFather(String category_father);

	public List<jsj_snews_category> listCategoryByFatherName(String category_fatherName);

	
	 * 
	 
	public boolean updateCategoryShowByID(jsj_snews_category category);

	public boolean updateCategoryAllByID(jsj_snews_category category);

	public boolean update_RemoveCategoryNewsByNewsID(String newsID);

	
	 * 
	 
	public boolean removeCategoryByID(jsj_snews_category category);

	public boolean removeCategoryByFather(jsj_snews_category category);
	
	 * 
	 */


}
