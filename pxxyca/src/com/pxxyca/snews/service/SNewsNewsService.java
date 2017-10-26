package com.pxxyca.snews.service;

import java.util.List;

import com.pxxyca.snews.domain.NewsAndCategoryAndContentDTO;
import com.pxxyca.snews.domain.jsj_snews_category;
import com.pxxyca.snews.domain.jsj_snews_content;
import com.pxxyca.snews.domain.jsj_snews_news;
import com.pxxyca.snews.domain.page_list_newsVO;
/**
 * 新闻功能模块接口
 * @author no one
 */
public interface SNewsNewsService {
	
	/**
	 * 添加新闻
	 *@author no one
	 *@param newsAndCategoryAndContentDTO 新闻创建页面的数据封装到此对象中
	 *@return true添加成功，false添加失败
	 */
	Boolean addNews(NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO);
	/**
	 * 根据新闻ID删除新闻
	 *@author no one
	 *@param jsj_snews_news_id 新闻ID
	 *@return true删除成功，false删除失败
	 */
	Boolean deleteNews(String jsj_snews_news_id);
	/**
	 * 更新新闻
	 * @author no one
	 * @param newsAndCategoryAndContentDTO 新闻修改后的数据封装到此数据
	 * @return true更新成功，false更新失败
	 */
	Boolean updateNews(NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO );
	/**
	 *查询新闻列表
	 *@author no one
	 *@return 被封装的查询到的新闻集合
	 */
	List<NewsAndCategoryAndContentDTO> queryAllNews();
	/**
	 *搜索新闻
	 *@author no one
	 *@param searchWords 搜索框关键字
	 *@return 被封装的查询到的搜索结果
	 */
	List<NewsAndCategoryAndContentDTO> search(String searchWords);
	
}
