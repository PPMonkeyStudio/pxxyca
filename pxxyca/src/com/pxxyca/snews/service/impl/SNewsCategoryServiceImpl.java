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
		sNewsCategoryDao.saveSnews_caegory(category);
		return true;
	}

	@Override
	public Boolean deleteSnews_category(String news_category) {
		sNewsCategoryDao.deleteSnews_category(news_category);
		return true;
	}

	@Override
	public Boolean updateSnews_category(jsj_snews_category category) {
		sNewsCategoryDao.updateSnews_category(category);
		return true;
	}

	@Override
	public jsj_snews_category querySnews_category_ByNewsCategory(String news_category) {
		jsj_snews_category category = sNewsCategoryDao.querySnews_category_ByNewsCategory(news_category);
		return category;
	}

	@Override
	public jsj_snews_category querySnews_categoty_ByNewsID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<jsj_snews_category> queryAllSnews_category() {
		List<jsj_snews_category> category_list = sNewsCategoryDao.queryAllSnews_category();
		return category_list;
	}

	/*@Override
	public List<jsj_snews_category> listCategoryAll() {

		return sNewsCategoryDao.listCategoryAll();
	}

	@Override
	public boolean removeCategoryByID(jsj_snews_category category) {

		// ɾ������
		sNewsCategoryDao.removeCategoryByID(category);

		// ɾ������
		sNewsCategoryDao.removeCategoryByFather(category);

		// �������ţ�����ÿ�

		// �������ţ�����ÿ�

		return true;
	};

	@Override
	public jsj_snews_category getCategoryByName(jsj_snews_category category) {

		category = sNewsCategoryDao.get_Category_ByName(category);

		if (category == null) {
			return null;
		} else {
			return category;
		}

	}

	@Override
	public boolean saveCategory(jsj_snews_category category) {

		category.setjsj_snews_category_id(UUID.randomUUID().toString());

		category.setCategory_gmt_create(TimeUtil.getStringSecond());

		category.setCategory_gmt_modified(category.getCategory_gmt_create());

		sNewsCategoryDao.save_Category(category);

		return true;
	}

	@Override
	public List<jsj_snews_category> listCategoryByRankOne() {

		return sNewsCategoryDao.listCategoryByRank("1");
	}

	@Override
	public List<jsj_snews_category> listCategoryRankTwo() {

		return sNewsCategoryDao.listCategoryByRank("2");
	}

	@Override
	public List<jsj_snews_category> listCategoryByName(String category_name) {

		return sNewsCategoryDao.listCategoryByName(category_name);
	}

	@Override
	public List<jsj_snews_category> listCategoryByRankOne_ForHeader() {

		return sNewsCategoryDao.listCategoryByRankOne_ForHeader();
	}

	@Override
	public List<CategoryListDTO> listCategoryOneAndSon() {

		List<jsj_snews_category> categoryList = sNewsCategoryDao.listCategoryByRank("1");

		List<CategoryListDTO> categoryListDTOList = new ArrayList<CategoryListDTO>();

		List<jsj_snews_category> sonCategoryList = new ArrayList<jsj_snews_category>();

		for (jsj_snews_category tmpCategory : categoryList) {

			sonCategoryList = sNewsCategoryDao.listCategoryByFather(tmpCategory.getjsj_snews_category_id());

			categoryListDTOList.add(new CategoryListDTO(tmpCategory, sonCategoryList));

		}

		return categoryListDTOList;
	}

	@Override
	public boolean updateCategoryShowByID(jsj_snews_category category) {

		// ͨ��ID���ҵ�ԭ��¼
		jsj_snews_category oldCategory = sNewsCategoryDao.get_Category_ByID(category);

		// �ı�category_show�ֶ�
		sNewsCategoryDao.updateCategoryShowByID(category);

		return true;
	}

	@Override
	public jsj_snews_category getCategoryByID(jsj_snews_category category) {

		return sNewsCategoryDao.get_Category_ByID(category);
	}

	@Override
	public boolean update_RemoveCategoryNewsByNewsID(String newsID) {

		return sNewsCategoryDao.update_RemoveCategoryNewsByNewsID(newsID);
	}

	@Override
	public boolean updateCategoryAllByID(jsj_snews_category category) {
		sNewsCategoryDao.updateCategoryAllByID(category);
		return true;
	}

	
	 * 
	 * 
	 
	private SNewsCategoryDao sNewsCategoryDao;
	
	public SNewsCategoryDao getsNewsCategoryDao() {
		return sNewsCategoryDao;
	}

	public void setsNewsCategoryDao(SNewsCategoryDao sNewsCategoryDao) {
		this.sNewsCategoryDao = sNewsCategoryDao;
	}
*/

}
