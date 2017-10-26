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
//		category.setJsj_snews_category_id(UUID.randomUUID().toString());
//		category.setCategory_gmt_create(TimeUtil.getStringSecond());
//		category.setCategory_gmt_modified(category.getCategory_gmt_create());
		sNewsNewsDao.saveSnews_news(news);
		sNewsNewsDao.saveSnews_content(content);
		sNewsCategoryDao.saveSnews_category(category);
//		NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO2 = new NewsAndCategoryAndContentDTO(news, category, content);
		return true;
	}

	@Override
	public Boolean deleteNews(String jsj_snews_news_id) {
		jsj_snews_news news = sNewsNewsDao.querySnews_news_ByNewsID(jsj_snews_news_id);
		sNewsNewsDao.deleteSnews_news(jsj_snews_news_id);
		sNewsCategoryDao.deleteSnews_category(news.getNews_category());
		sNewsNewsDao.deleteSnews_content(jsj_snews_news_id);
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


	/*
	 * 
	 * 



	public void removeOldContentImg(snews_news news, snews_content content) {
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
		File root = new File("C://xxyjsjgcxy/xxyjsjgcxy_img/snews_news/content_temporary");
		File[] allFiles = root.listFiles();
		for (File file : allFiles) {
			String[] splitFileName = file.getName().split("_");
			if (splitFileName[0].equals(news.getSnews_news_id())) {
				System.out.println("splitFileName[1]:" + splitFileName[1]);
				System.out.println(imgNameList.toString());
				for (String imgName : imgNameList) {
					if (splitFileName[1].equals(imgName)) {
						File newFile = new File("C://xxyjsjgcxy/xxyjsjgcxy_img/snews_news/content/" + file.getName());
						try {
							FileUtils.copyFile(file, newFile);
						} catch (IOException e) {
							e.printStackTrace();
						}
						break;
					}
				}
				file.delete();
			}

		}
		content.setContent_text(content.getContent_text().replaceAll("getNewsTemporaryContentImg\\?imgName=",
				"getNewsContentImg?imgName="));
		return content;
	}
	*/



}
