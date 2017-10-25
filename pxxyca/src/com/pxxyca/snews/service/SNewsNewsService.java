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
	
//	NewsAndCategoryAndContentDTO  queryNewsByNewsID(String jsj_snews_news_id);
	
	List<NewsAndCategoryAndContentDTO> search(String searchWords);
	
	
	

	/*
	public List<NewsAndCategoryAndContentDTO> list_NewsAndCategoryAndContent_ByKeywords_Num(String keywords, int num,
			String newsID);

	public List<NewsAndCategoryAndContentDTO> list_NewsAndCategoryAndContent_ByCategory_Num(String categoryName,
			int num);

	public List<NewsAndCategoryAndContentDTO> list_NewsAndCategoryAndContent_ByCategorySon_Num(String categoryName,
			int num);

	public List<NewsAndCategoryAndContentDTO> list_NewsAndCategoryAndContent_ByRecommend_Num(int num);
*/
	/*
	public page_list_newsVO queryNewsAndCategoryAndContent_ByPage(page_list_newsVO page_list_news);

	public ListVO list_NewsAndCategoryAndContent_ByCategoryAndPage(ListVO listVO);

	public ListVO list_NewsAndCategoryAndContent_BySearchAndPage(ListVO listVO);

	public ListVO list_NewsAndCategoryAndContent_ByDateAndPage(ListVO listVO) throws ParseException;
*/

//	public jsj_snews_content removeContentTemporaryImg_saveContentImg(jsj_snews_news news, snews_content content);

//	public void removeOldContentImg(jsj_snews_news news, snews_content content);

/*
	public void removeOldAnnex(jsj_snews_news news, String remain_oldAnnex);

	public void removeContentImgByNewsID(jsj_snews_news news);

	public void removeNewsAnnexByNewsID(jsj_snews_news news);

	public void removeNewsBImgByNewsID(jsj_snews_news news);

	public void removeNewsSImgByNewsID(jsj_snews_news news);
*/

}
