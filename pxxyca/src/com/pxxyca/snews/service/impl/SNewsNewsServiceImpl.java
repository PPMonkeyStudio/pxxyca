package com.pxxyca.snews.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.pxxyca.snews.dao.SNewsCategoryDao;
import com.pxxyca.snews.dao.SNewsNewsDao;
import com.pxxyca.snews.domain.NewsAndCategoryAndContentDTO;
import com.pxxyca.snews.domain.jsj_snews_category;
import com.pxxyca.snews.domain.jsj_snews_content;
import com.pxxyca.snews.domain.jsj_snews_news;
import com.pxxyca.snews.service.SNewsNewsService;

import util.TimeUtil;


public class SNewsNewsServiceImpl implements SNewsNewsService {

	private SNewsNewsDao sNewsNewsDao;
	
	private SNewsCategoryDao sNewsCategoryDao;

	@Override
	public Boolean addNews(NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO) {
		jsj_snews_news news = newsAndCategoryAndContentDTO.getNews();
		news.setJsj_snews_news_id(UUID.randomUUID().toString());
		news.setNews_gmt_create(TimeUtil.getStringSecond());
		news.setNews_gmt_modified(news.getNews_gmt_create());
		jsj_snews_content content= newsAndCategoryAndContentDTO.getContent();
		content.setJsj_snews_content_id(UUID.randomUUID().toString());
		content.setContent_gmt_create(TimeUtil.getStringSecond());
		content.setContent_gmt_modified(content.getContent_gmt_create());
		jsj_snews_category category = newsAndCategoryAndContentDTO.getCategory();
		category.setJsj_snews_category_id(UUID.randomUUID().toString());
		category.setCategory_gmt_create(TimeUtil.getStringSecond());
		category.setCategory_gmt_modified(category.getCategory_gmt_create());
		sNewsNewsDao.saveSnews_news(news);
		sNewsNewsDao.saveSnews_content(content);
		sNewsCategoryDao.saveSnews_caegory(category);
		return true;
	}

	@Override
	public Boolean deleteNews(String jsj_snews_news_id) {
		jsj_snews_news news = sNewsNewsDao.querySnews_news_ByNewsID(jsj_snews_news_id);
		sNewsNewsDao.deleteSnews_news(jsj_snews_news_id);
		sNewsCategoryDao.deleteSnews_category(news.getNews_category());
		sNewsNewsDao.deleteSnews_content(jsj_snews_news_id);
//		jsj_snews_content content = sNewsNewsDao.querySnews_content_ByNewsID(jsj_snews_news_id);
//		sNewsNewsDao.deleteSnews_content(content.getContent_news());
		return true;
	}

	@Override
	public Boolean updateNews(NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO) {
		sNewsNewsDao.updateSnews_news(newsAndCategoryAndContentDTO.getNews());
		sNewsNewsDao.updateSnews_content(newsAndCategoryAndContentDTO.getContent());
		sNewsCategoryDao.updateSnews_category(newsAndCategoryAndContentDTO.getCategory());
		return true;
	}

	@Override
	public List<NewsAndCategoryAndContentDTO> queryAllNews() {
		List<jsj_snews_news> news_list = sNewsNewsDao.queryAllSnews_news();
		List<NewsAndCategoryAndContentDTO> newsAndCategoryAndContent_list = new ArrayList<NewsAndCategoryAndContentDTO>();
		NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO;
		jsj_snews_content content;
		jsj_snews_category category;
		for (jsj_snews_news newsList : news_list) {

			content = sNewsNewsDao.querySnews_content_ByNewsID(newsList.getJsj_snews_news_id());

			category = sNewsCategoryDao.querySnews_category_ByNewsCategory(newsList.getNews_category());

			newsAndCategoryAndContentDTO = new NewsAndCategoryAndContentDTO(newsList, category, content);

			newsAndCategoryAndContent_list.add(newsAndCategoryAndContentDTO);
		}
		return newsAndCategoryAndContent_list;
	}

	@Override
	public List<NewsAndCategoryAndContentDTO> search(String searchWords) {
		List<jsj_snews_news> news_list = sNewsNewsDao.querySnews_news_ByNewsTitle(searchWords);
		List<NewsAndCategoryAndContentDTO> newsAndCategoryAndContent_list = new ArrayList<NewsAndCategoryAndContentDTO>();
		NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO;
		jsj_snews_content content;
		jsj_snews_category category;
		for (jsj_snews_news newsList : news_list) {

			content = sNewsNewsDao.querySnews_content_ByNewsID(newsList.getJsj_snews_news_id());

			category = sNewsCategoryDao.querySnews_category_ByNewsCategory(newsList.getNews_category());

			newsAndCategoryAndContentDTO = new NewsAndCategoryAndContentDTO(newsList, category, content);

			newsAndCategoryAndContent_list.add(newsAndCategoryAndContentDTO);
		}
		return newsAndCategoryAndContent_list;
	}


	/*@Override
	public boolean addContents(jsj_snews_content content) {
		
		content.setJsj_snews_content_id(UUID.randomUUID().toString());
		
		content.setContent_gmt_create(TimeUtil.getStringSecond());
		
		content.setContent_gmt_modified(content.getContent_gmt_create());
		
		System.out.println(content.toString());
		
		sNewsNewsDao.addContents(content);
		
		return true;
	}*/
	
	/*@Override
	public boolean addCategory(jsj_snews_category caegory) {
		// TODO Auto-generated method stub
		return false;
	}*/
	



//	@Override
//	public void updateContent(snews_content content) {
//		
//
//		content.setContent_gmt_modified(TimeUtil.getStringSecond());
//
//		sNewsNewsDao.updateContent(content);
//	}


/*	public NewsAndCategoryAndContentDTO get_NewsAndCategoryAndContent_ByNewsID(String newsID) {

		snews_news news = sNewsNewsDao.get_News_ByID(newsID);

		snews_category category = snewsCategoryDao.get_Category_ByNewsCategory(news);

		snews_content content = sNewsNewsDao.get_Content_ByNewsID(news);

		NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO = new NewsAndCategoryAndContentDTO(news, category,
				content);

		return newsAndCategoryAndContentDTO;
	}*/

	/*@Override
	public void removeNewsByID(snews_news news) {

		news = sNewsNewsDao.get_News_ByID(news.getSnews_news_id());

		// ����
		sNewsNewsDao.removeNewsByID(news);

		// ����

		sNewsNewsDao.removeContentByNewsID(news);

		// ����ͼƬ

		removeContentImgByNewsID(news);

		// ����

		removeNewsAnnexByNewsID(news);

		// ��Сͼ

		removeNewsBImgByNewsID(news);

		removeNewsSImgByNewsID(news);
		
		 * 
		 * 
		 * 
		 
		System.out.println("12");
		// ɾ���ֲ�
		;
		;
		System.out.println("32");
		// ɾ�����ָ������
	}


	@Override
	public void removeContentImgByNewsID(snews_news news) {
		File root = new File("C://xxyjsjgcxy/xxyjsjgcxy_img/snews_news/content");
		File[] allFiles = root.listFiles();

		for (File file : allFiles) {
			String[] splitFileName = file.getName().split("_");
			if (splitFileName[0].equals(news.getSnews_news_id())) {
				file.delete();
			}
		}

	}

	public ListVO list_NewsAndCategoryAndContent_ByDateAndPage(ListVO listVO) throws ParseException {
		List<NewsAndCategoryAndContentDTO> newsAndCategoryAndContentDTOList = new ArrayList<NewsAndCategoryAndContentDTO>();

		NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO;

		snews_category category;

		snews_content content;

		List<snews_news> newsList = sNewsNewsDao.list_News_ByDateAndPage(listVO);

		// ƥ���ɫ

		// ��װ�ܼ�¼��
		listVO.setTotalRecords(sNewsNewsDao.get_News_TotalRecords_ByDate(listVO.getDate()));

		System.out.println("�ܼ�¼��:" + listVO.getTotalRecords());

		// ��װ��ҳ��
		listVO.setTotalPages(((listVO.getTotalRecords() - 1) / listVO.getPageSize()) + 1);

		if (listVO.getPageIndex() <= 1) {
			listVO.setHavePrePage(false);
		} else {
			listVO.setHavePrePage(true);
		}
		if (listVO.getPageIndex() >= listVO.getTotalPages()) {
			listVO.setHaveNextPage(false);
		} else {
			listVO.setHaveNextPage(true);
		}

		for (snews_news news : newsList) {

			content = sNewsNewsDao.get_Content_ByNewsID(news);

			category = snewsCategoryDao.get_Category_ByNewsCategory(news);

			newsAndCategoryAndContentDTO = new NewsAndCategoryAndContentDTO(news, category, content);

			newsAndCategoryAndContentDTOList.add(newsAndCategoryAndContentDTO);
		}

		listVO.setListNews(newsAndCategoryAndContentDTOList);

		return listVO;
	}

	@Override
	public ListVO list_NewsAndCategoryAndContent_BySearchAndPage(ListVO listVO) {

		List<NewsAndCategoryAndContentDTO> newsAndCategoryAndContentDTOList = new ArrayList<NewsAndCategoryAndContentDTO>();

		NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO;

		snews_category category;

		snews_content content;

		List<snews_news> newsList = sNewsNewsDao.list_News_BySearchAndPage(listVO);

		// ƥ���ɫ

		// ��װ�ܼ�¼��
		listVO.setTotalRecords(sNewsNewsDao.get_News_TotalRecords_BySearch(listVO.getSearch()));

		System.out.println("�ܼ�¼��:" + listVO.getTotalRecords());

		// ��װ��ҳ��
		listVO.setTotalPages(((listVO.getTotalRecords() - 1) / listVO.getPageSize()) + 1);

		if (listVO.getPageIndex() <= 1) {
			listVO.setHavePrePage(false);
		} else {
			listVO.setHavePrePage(true);
		}
		if (listVO.getPageIndex() >= listVO.getTotalPages()) {
			listVO.setHaveNextPage(false);
		} else {
			listVO.setHaveNextPage(true);
		}

		for (snews_news news : newsList) {

			content = sNewsNewsDao.get_Content_ByNewsID(news);

			category = snewsCategoryDao.get_Category_ByNewsCategory(news);

			newsAndCategoryAndContentDTO = new NewsAndCategoryAndContentDTO(news, category, content);

			newsAndCategoryAndContentDTOList.add(newsAndCategoryAndContentDTO);
		}

		listVO.setListNews(newsAndCategoryAndContentDTOList);

		return listVO;
	}

	@Override
	public ListVO list_NewsAndCategoryAndContent_ByCategoryAndPage(ListVO listVO) {

		List<NewsAndCategoryAndContentDTO> newsAndCategoryAndContentDTOList = new ArrayList<NewsAndCategoryAndContentDTO>();

		NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO;

		snews_category category;

		snews_content content;

		List<snews_news> newsList = sNewsNewsDao.list_News_ByCategoryAndPage(listVO);

		// ƥ���ɫ

		// ��װ�ܼ�¼��
		listVO.setTotalRecords(sNewsNewsDao.get_News_TotalRecords_ByCategory(listVO.getCategory()));

		System.out.println("�ܼ�¼��:" + listVO.getTotalRecords());

		// ��װ��ҳ��
		listVO.setTotalPages(((listVO.getTotalRecords() - 1) / listVO.getPageSize()) + 1);

		if (listVO.getPageIndex() <= 1) {
			listVO.setHavePrePage(false);
		} else {
			listVO.setHavePrePage(true);
		}
		if (listVO.getPageIndex() >= listVO.getTotalPages()) {
			listVO.setHaveNextPage(false);
		} else {
			listVO.setHaveNextPage(true);
		}

		for (snews_news news : newsList) {

			content = sNewsNewsDao.get_Content_ByNewsID(news);

			category = snewsCategoryDao.get_Category_ByNewsCategory(news);

			newsAndCategoryAndContentDTO = new NewsAndCategoryAndContentDTO(news, category, content);

			newsAndCategoryAndContentDTOList.add(newsAndCategoryAndContentDTO);
		}

		listVO.setListNews(newsAndCategoryAndContentDTOList);

		return listVO;
	};

	@Override
	public page_list_newsVO list_NewsAndCategoryAndContent_ByPage(page_list_newsVO page_list_news) {

		List<NewsAndCategoryAndContentDTO> newsAndCategoryAndContentDTOList = new ArrayList<NewsAndCategoryAndContentDTO>();

		NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO;

		snews_category category;

		snews_content content;

		List<snews_news> newsList = sNewsNewsDao.list_News_ByPage(page_list_news);

		// ƥ���ɫ
		if (page_list_news.getSearch() != null && !page_list_news.getSearch().getKeywords().equals("")) {
			System.out.println("������ʾ��" + page_list_news.getSearch().getKeywords());
			int i = 0;
			while (i < newsList.size()) {

				newsList.get(i).setNews_title(newsList.get(i).getNews_title().replaceAll(
						page_list_news.getSearch().getKeywords(),
						"<span style='color: #ff5063;'>" + page_list_news.getSearch().getKeywords() + "</span>"));

				newsList.get(i).setNews_keywords(newsList.get(i).getNews_keywords().replaceAll(
						page_list_news.getSearch().getKeywords(),
						"<span style='color: #ff5063;'>" + page_list_news.getSearch().getKeywords() + "</span>"));

				i++;
			}
		}

		// ��װ�ܼ�¼��
		page_list_news.setTotalRecords(sNewsNewsDao.get_News_TotalRecords());
		// ��װ��ҳ��
		page_list_news.setTotalPages(((page_list_news.getTotalRecords() - 1) / page_list_news.getPageSize()) + 1);

		if (page_list_news.getPageIndex() <= 1) {
			page_list_news.setHavePrePage(false);
		} else {
			page_list_news.setHavePrePage(true);
		}
		if (page_list_news.getPageIndex() >= page_list_news.getTotalPages()) {
			page_list_news.setHaveNextPage(false);
		} else {
			page_list_news.setHaveNextPage(true);
		}

		for (snews_news news : newsList) {

			content = sNewsNewsDao.get_Content_ByNewsID(news);

			category = snewsCategoryDao.get_Category_ByNewsCategory(news);

			newsAndCategoryAndContentDTO = new NewsAndCategoryAndContentDTO(news, category, content);

			newsAndCategoryAndContentDTOList.add(newsAndCategoryAndContentDTO);
		}

		page_list_news.setNewsAndCategoryAndContentDTOList(newsAndCategoryAndContentDTOList);

		return page_list_news;
	}

	public void removeOldContentImg(snews_news news, snews_content content) {

		
		 * ��������ͼƬ���б�
		 
		List<String> imgNameList = new ArrayList<String>();
		String[] splitContent1 = content.getContent_text().split(news.getSnews_news_id() + "_");
		if (splitContent1.length > 1) {
			System.out.println("-----------------------------��ͼƬ------------------------");
			System.out.println("splitContent1.length:" + splitContent1.length);
			for (int i = 1; i < splitContent1.length; i++) {
				String[] splitContent2 = splitContent1[i].split("\"");
				imgNameList.add(splitContent2[0]);
				System.out.println("splitContent2[0]:" + splitContent2[0]);
			}
		} else {
			System.out.println("-----------------------------��ͼƬ------------------------");
		}
		
		 * ɾ���Ѿ�ɾ����ͼƬ
		 

		File root = new File("C://xxyjsjgcxy/xxyjsjgcxy_img/snews_news/content");

		File[] allFiles = root.listFiles();

		
		 * ���ļ���������ͼƬ���������ŵ�
		 
		for (File file : allFiles) {
			
			 * ͼƬ�ļ�
			 
			String[] splitFileName = file.getName().split("_");
			if (splitFileName[0].equals(news.getSnews_news_id())) {
				int j = 0;
				for (String imgName : imgNameList) {
					if (splitFileName[1].equals(imgName)) {
						j = 1;
						break;
					}
				}
				if (j == 0) {
					file.delete();
				}
			}
		}

	}

	@Override
	public snews_content removeContentTemporaryImg_saveContentImg(snews_news news, snews_content content) {
		System.out.println(
				"deleteContentTemporaryImg_saveContentImg:-----newsID��----------" + news.getSnews_news_id());
		
		 * ��������ͼƬ���б�
		 
		List<String> imgNameList = new ArrayList<String>();
		String[] splitContent1 = content.getContent_text().split(news.getSnews_news_id() + "_");
		if (splitContent1.length > 1) {
			System.out.println("-----------------------------��ͼƬ------------------------");
			System.out.println("splitContent1.length:" + splitContent1.length);
			for (int i = 1; i < splitContent1.length; i++) {
				String[] splitContent2 = splitContent1[i].split("\"");
				imgNameList.add(splitContent2[0]);
				System.out.println("splitContent2[0]:" + splitContent2[0]);
			}
		} else {
			System.out.println("-----------------------------��ͼƬ------------------------");
		}
		
		 * ת����ʱͼƬ
		 

		File root = new File("C://xxyjsjgcxy/xxyjsjgcxy_img/snews_news/content_temporary");

		File[] allFiles = root.listFiles();

		
		 * ���ļ���������ͼƬ���������ŵ�
		 
		for (File file : allFiles) {
			String[] splitFileName = file.getName().split("_");

			if (splitFileName[0].equals(news.getSnews_news_id())) {
				
				 * �����ŵ�ͼƬ,���ͼƬ����������ƥ�䣬��ת�ƣ������ɾ��
				 
				System.out.println("splitFileName[1]:" + splitFileName[1]);
				System.out.println(imgNameList.toString());
				for (String imgName : imgNameList) {

					if (splitFileName[1].equals(imgName)) {
						
						 * ת��
						 
						File newFile = new File("C://xxyjsjgcxy/xxyjsjgcxy_img/snews_news/content/" + file.getName());
						try {
							FileUtils.copyFile(file, newFile);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					}
				}
				
				 * ��������ƥ�䵽��Ҫɾ����ʱ�ļ������ڵ��ļ��Ѿ�ת����
				 
				file.delete();
			}

		}
		
		 * �滻���ݵ�ͼƬ��
		 
		content.setContent_text(content.getContent_text().replaceAll("getNewsTemporaryContentImg\\?imgName=",
				"getNewsContentImg?imgName="));
		return content;
	}

	@Override
	public List<NewsAndCategoryAndContentDTO> list_NewsAndCategoryAndContent_ByCategorySon_Num(String categoryName,
			int num) {

		List<NewsAndCategoryAndContentDTO> newsAndCategoryAndContentDTOList = new ArrayList<NewsAndCategoryAndContentDTO>();

		NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO;

		snews_category category;

		snews_content content;

		List<snews_category> categorySonList = snewsCategoryDao.listCategoryByFatherName(categoryName);

		List<snews_news> newsList = new ArrayList<snews_news>();

		for (snews_category categorySon : categorySonList) {

			newsList.addAll(sNewsNewsDao.list_News_ByCategory_Num(categorySon.getCategory_name(), num));

			System.out.println("�����" + categorySon.getCategory_name());
		}

		for (snews_news news : newsList) {

			content = sNewsNewsDao.get_Content_ByNewsID(news);

			category = snewsCategoryDao.get_Category_ByNewsCategory(news);

			newsAndCategoryAndContentDTO = new NewsAndCategoryAndContentDTO(news, category, content);

			newsAndCategoryAndContentDTOList.add(newsAndCategoryAndContentDTO);
		}

		return newsAndCategoryAndContentDTOList;
	}

	@Override
	public List<NewsAndCategoryAndContentDTO> list_NewsAndCategoryAndContent_ByCategory_Num(String categoryName,
			int num) {

		List<NewsAndCategoryAndContentDTO> newsAndCategoryAndContentDTOList = new ArrayList<NewsAndCategoryAndContentDTO>();

		NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO;

		snews_category category;

		snews_content content;

		List<snews_news> newsList = sNewsNewsDao.list_News_ByCategory_Num(categoryName, num);

		for (snews_news news : newsList) {

			content = sNewsNewsDao.get_Content_ByNewsID(news);

			category = snewsCategoryDao.get_Category_ByNewsCategory(news);

			newsAndCategoryAndContentDTO = new NewsAndCategoryAndContentDTO(news, category, content);

			newsAndCategoryAndContentDTOList.add(newsAndCategoryAndContentDTO);
		}

		return newsAndCategoryAndContentDTOList;
	}

	@Override
	public List<NewsAndCategoryAndContentDTO> list_NewsAndCategoryAndContent_ByRecommend_Num(int num) {

		List<NewsAndCategoryAndContentDTO> newsAndCategoryAndContentDTOList = new ArrayList<NewsAndCategoryAndContentDTO>();

		NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO;

		snews_category category;

		snews_content content;

		List<snews_news> newsList = sNewsNewsDao.list_News_ByRecommend_Num(num);

		for (snews_news news : newsList) {

			content = sNewsNewsDao.get_Content_ByNewsID(news);

			category = snewsCategoryDao.get_Category_ByNewsCategory(news);

			newsAndCategoryAndContentDTO = new NewsAndCategoryAndContentDTO(news, category, content);

			newsAndCategoryAndContentDTOList.add(newsAndCategoryAndContentDTO);
		}

		return newsAndCategoryAndContentDTOList;
	}

	@Override
	public List<NewsAndCategoryAndContentDTO> list_NewsAndCategoryAndContent_ByKeywords_Num(String keywords, int num,
			String newsID) {

		List<NewsAndCategoryAndContentDTO> newsAndCategoryAndContentDTOList = new ArrayList<NewsAndCategoryAndContentDTO>();

		NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO;

		snews_category category;

		snews_content content;

		List<snews_news> newsList = sNewsNewsDao.list_News_ByKeywords(keywords, newsID);

		for (snews_news news : newsList) {

			content = sNewsNewsDao.get_Content_ByNewsID(news);

			category = snewsCategoryDao.get_Category_ByNewsCategory(news);

			newsAndCategoryAndContentDTO = new NewsAndCategoryAndContentDTO(news, category, content);

			newsAndCategoryAndContentDTOList.add(newsAndCategoryAndContentDTO);
		}

		return newsAndCategoryAndContentDTOList;
	}

	@Override
	public List<snews_news> listNewsAll() {

		return sNewsNewsDao.list_News_All();
	}

	@Override
	public List<NewsAndCategoryAndContentDTO> list_NewsAndCategoryAndContent_All() {

		List<NewsAndCategoryAndContentDTO> newsAndCategoryAndContentDTOList = new ArrayList<NewsAndCategoryAndContentDTO>();

		NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO;

		snews_category category;

		snews_content content;

		List<snews_news> newsList = sNewsNewsDao.list_News_All();

		for (snews_news news : newsList) {

			content = sNewsNewsDao.get_Content_ByNewsID(news);

			category = snewsCategoryDao.get_Category_ByNewsCategory(news);

			newsAndCategoryAndContentDTO = new NewsAndCategoryAndContentDTO(news, category, content);

			newsAndCategoryAndContentDTOList.add(newsAndCategoryAndContentDTO);
		}

		return newsAndCategoryAndContentDTOList;
	}

	@Override
	public boolean save_Content(snews_content content) {

		content.setSnews_content_id(UUID.randomUUID().toString());

		content.setContent_gmt_create(TimeUtil.getStringSecond());

		content.setContent_gmt_modified(content.getContent_gmt_create());

		System.out.println(content.toString());

		sNewsNewsDao.saveContent(content);

		return true;
	}

	@Override
	public boolean save_News(snews_news news) {
		
		news.setNews_gmt_create(TimeUtil.getStringSecond());
		
		news.setNews_gmt_modified(news.getNews_gmt_create());
		
		sNewsNewsDao.saveNews(news);
		
		return true;
	}
	
	@Override
	public List<NewsAndCategoryAndContentDTO> list_NewsAndCategoryAndContent_All() {
		
		return sNewsNewsDao.list_NewsAndCategoryAndContent_All();
	}

	
	 * 
	 


	@Override
	public snews_content removeContentTemporaryImg_saveContentImg(snews_news news, snews_content content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeOldContentImg(snews_news news, snews_content content) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeNewsByID(snews_news news) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeContentImgByNewsID(snews_news news) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNews(snews_news news) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateNewsBrowse(String newsID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeOldAnnex(snews_news news, String remain_oldAnnex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeNewsAnnexByNewsID(snews_news news) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeNewsBImgByNewsID(snews_news news) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeNewsSImgByNewsID(snews_news news) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public NewsAndCategoryAndContentDTO get_NewsAndCategoryAndContent_ByNewsID(String newsID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public page_list_newsVO list_NewsAndCategoryAndContent_ByPage(page_list_newsVO page_list_news) {
		// TODO Auto-generated method stub
		return null;
	}*/




}
