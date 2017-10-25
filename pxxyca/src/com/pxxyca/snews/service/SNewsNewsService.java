package com.pxxyca.snews.service;

import java.util.List;

import com.pxxyca.snews.domain.NewsAndCategoryAndContentDTO;
import com.pxxyca.snews.domain.jsj_snews_category;
import com.pxxyca.snews.domain.jsj_snews_content;
import com.pxxyca.snews.domain.jsj_snews_news;
import com.pxxyca.snews.domain.page_list_newsVO;

public interface SNewsNewsService {
	
	
	Boolean addNews(NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO);
	
	Boolean deleteNews(String jsj_snews_news_id);
	
	Boolean updateNews(NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO );
	
	List<NewsAndCategoryAndContentDTO> queryAllNews();
	
	List<NewsAndCategoryAndContentDTO> search(String searchWords);
	
}
