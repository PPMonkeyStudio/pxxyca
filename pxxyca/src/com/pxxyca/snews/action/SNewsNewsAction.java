/*package com.pxxyca.snews.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pxxyca.snews.domain.NewsAndCategoryAndContentDTO;
import com.pxxyca.snews.domain.page_list_newsVO;
import com.pxxyca.snews.domain.searchNewsListDTO;
import com.pxxyca.snews.domain.snews_content;
import com.pxxyca.snews.domain.snews_news;
import com.pxxyca.snews.service.SNewsCategoryService;
import com.pxxyca.snews.service.SNewsNewsService;

import util.TimeUtil;

@SuppressWarnings("serial")
public class SNewsNewsAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	
	//跳转到创建新闻页
	public String createNews() {
		return "page_create_news";
	}
	
	//跳转到新闻列表页
	public String listNews() {
		return "page_list_news";
	}
	

	private SNewsNewsService sNewsNewsService;
	
	public SNewsNewsService getsNewsNewsService() {
		return sNewsNewsService;
	}

	public void setsNewsNewsService(SNewsNewsService sNewsNewsService) {
		this.sNewsNewsService = sNewsNewsService;
	}

	public SNewsCategoryService getsNewsCategoryService() {
		return sNewsCategoryService;
	}

	public void setsNewsCategoryService(SNewsCategoryService sNewsCategoryService) {
		this.sNewsCategoryService = sNewsCategoryService;
	}


	private SNewsCategoryService sNewsCategoryService;
	
	private page_list_newsVO page_list_news;

	private snews_news news;

	private snews_content content;

	private String page;
	
	private String option;

	private searchNewsListDTO searchNewsList;

	private String remain_oldAnnex;

	private HttpServletResponse http_response;

	private HttpServletRequest http_request;

	
	 * 
	 
	private File[] file;
	private String[] fileFileName;
	private String[] fileContentType;

	
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 

	public String updateNewsPage() {

		NewsAndCategoryAndContentDTO newsAndCategoryAndContentDTO = sNewsNewsService
				.get_NewsAndCategoryAndContent_ByNewsID(news.getSnews_news_id());

		ActionContext.getContext().getValueStack().set("newsAndCategoryAndContentDTO", newsAndCategoryAndContentDTO);

		ActionContext.getContext().getValueStack().set("option", "update");

		ActionContext.getContext().getValueStack().set("page", "page_create_news");

		return "updateNewsPage";
	}

	public void deleteNews() {
		sNewsNewsService.removeNewsByID(news);

		try {

			http_response.setContentType("text/html;charset=utf-8");

			http_response.getWriter().write("success");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void getNewsUUID() {
		String newsUUID = UUID.randomUUID().toString();
		try {
			http_response.setContentType("text/html;charset=utf-8");
			http_response.getWriter().write(newsUUID);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void saveNewsContentImg() {

		
		 * Ȩ���ж�
		 

		if ("premission_management".equals(ActionContext.getContext().getSession().get("role_premission_snews"))) {

		} else {

			try {
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("��Ȩ��");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				return;
			}

		}
		
		 * 
		 

		if (file != null) {
			int i = 0;
			String[] imgUrl = new String[file.length];
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();// ��ʽ��
			gsonBuilder.disableHtmlEscaping();// ����'='ת���'\'
			Gson gson = gsonBuilder.create();

			while (i < file.length) {

				String filePath;
				String fileName = UUID.randomUUID().toString()
						+ fileFileName[i].substring(fileFileName[i].lastIndexOf("."));
				filePath = "C://xxyjsjgcxy/xxyjsjgcxy_img/snews_news/content_temporary/" + news.getSnews_news_id()
						+ "_" + fileName;

				File newFile = new File(filePath);
				try {
					FileUtils.copyFile(file[i], newFile);
				} catch (IOException e) {
					e.printStackTrace();
				}

				imgUrl[i] = "/xxyjsjgcxy/snews/img_getNewsTemporaryContentImg?imgName=" + news.getSnews_news_id()
						+ "_" + fileName;

				i++;
			}
			
			 * ����
			 

			Map<String, Object> map = new HashMap<>();
			map.put("error", 0);
			map.put("url", imgUrl);
			
			 * 
			 
			try {
				http_response.setContentType("text/html;charset=utf-8");
				http_response.getWriter().write(gson.toJson(map));
				System.out.println(gson.toJson(map));
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	}

	public void updateNews() {

		System.out.println("-----------------------------updateNews-------------------------------");
		
		 * Ȩ���ж�
		 

		if ("premission_management".equals(ActionContext.getContext().getSession().get("role_premission_snews"))) {

		} else {

			try {
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("premission_none");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				return;
			}

		}
	}

	public void saveNews() {
		
		 * 保存新闻
		 
		//premission_management管理员权限
		if ("premission_management".equals(ActionContext.getContext().getSession().get("role_premission_snews"))) {

		} else {

			try {
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("premission_none");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				return;
			}

		}
		
		 * 
		 
		
		 * ��ͼ
		 

		
		 * ����
		 
		if (file != null) {

			int i = 0;
			while (i < file.length) {
				String filePath;
				String fileName = fileFileName[i];
				filePath = "C://xxyjsjgcxy/xxyjsjgcxy_annex/snews_news/" + news.getSnews_news_id() + "_" + fileName;
				System.out.println(fileName);


				File newFile = new File(filePath);
				try {
					FileUtils.copyFile(file[i], newFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
				i++;
			}
		} else {
			System.out.println("δ�ϴ�����");
		}

		
		 * ����
		 
		boolean isSave_news = sNewsNewsService.save_News(news);

		
		 * ���ݵ�ͼƬ����
		 
		System.out.println("content:" + content);
		content = sNewsNewsService.removeContentTemporaryImg_saveContentImg(news, content);
		
		 * ���ݱ�
		 

		content.setContent_news(news.getSnews_news_id());
		content.setContent_text(content.getContent_text().replaceAll("border=\"0\"",
				"border=\"0\" class=\"table table-striped table-bordered table-hover\""));
		sNewsNewsService.save_Content(content);

		
		 * end
		 
	}

	public String page_create_news() {

		ActionContext.getContext().getValueStack().set("page", page);

		return "page_create_news";
	}

	public void listNewsAll() {

		List<snews_news> newsList = sNewsNewsService.listNewsAll();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// ��ʽ��json����
		Gson gson = gsonBuilder.create();

		try {

			http_response.setContentType("text/html;charset=utf-8");

			http_response.getWriter().write(gson.toJson(newsList));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public String page_list_news() {
		
		 * Ȩ���ж�
		 

		if ("premission_management".equals(ActionContext.getContext().getSession().get("role_premission_snews"))
				|| "premission_browse".equals(ActionContext.getContext().getSession().get("role_premission_snews"))) {

		} else {

			try {
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("premission_none");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				return "page_list_news";
			}

		}

		
		if (searchNewsList != null) {
			System.out.println(searchNewsList);
			page_list_news.setSearch(searchNewsList);

			System.out.println(searchNewsList.getStart_time());
			System.out.println(searchNewsList.getStop_time());
		}

		page_list_news = sNewsNewsService.list_NewsAndCategoryAndContent_ByPage(page_list_news);

		ActionContext.getContext().getValueStack().set("page_list_news", page_list_news);

		ActionContext.getContext().getValueStack().set("page", "page_list_news");

		return "page_list_news";
	}

	
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 

	@Override
	public void setServletRequest(HttpServletRequest http_request) {

		this.http_request = http_request;

	}

	public HttpServletResponse getHttp_response() {
		return http_response;
	}

	public void setHttp_response(HttpServletResponse http_response) {
		this.http_response = http_response;
	}

	public HttpServletRequest getHttp_request() {
		return http_request;
	}

	public void setHttp_request(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	public SNewsNewsService getSnewsNewsService() {
		return sNewsNewsService;
	}

	public void setSnewsNewsService(SNewsNewsService snewsNewsService) {
		this.sNewsNewsService = snewsNewsService;
	}

	public snews_news getNews() {
		return news;
	}

	public snews_content getContent() {
		return content;
	}

	public void setContent(snews_content content) {
		this.content = content;
	}

	public void setNews(snews_news news) {
		this.news = news;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String[] getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String[] fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public searchNewsListDTO getSearchNewsList() {
		return searchNewsList;
	}

	public void setSearchNewsList(searchNewsListDTO searchNewsList) {
		this.searchNewsList = searchNewsList;
	}


	@Override
	public void setServletResponse(HttpServletResponse http_response) {

		this.http_response = http_response;

	}

}
*/