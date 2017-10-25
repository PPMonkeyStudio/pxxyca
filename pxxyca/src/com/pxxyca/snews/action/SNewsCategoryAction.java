/*package com.pxxyca.snews.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
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
import com.pxxyca.snews.domain.CategoryListDTO;
import com.pxxyca.snews.domain.jsj_snews_category;
import com.pxxyca.snews.domain.jsj_snews_news;
import com.pxxyca.snews.service.SNewsCategoryService;

@SuppressWarnings("serial")
public class SNewsCategoryAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {
	
	//跳转到创建新闻类别页
		public String createCategory() {
			return "page_create_category";
		}
	//跳转到新闻类别页
	public String listCategory() {
		return "page_list_category";
	}

	private SNewsCategoryService sNewsCategoryService;

	private jsj_snews_news news;

	private jsj_snews_category category;

	//
	private String page;
	//
	private String option;

	private File file;

	private String fileFileName;

	private HttpServletResponse http_response;

	private HttpServletRequest http_request;

	
	 * ����Ҫ�У����Զ�Ѱ��������
	 
	private File category_img;
	private String category_imgFileName;
	private String category_imgContentType;
	
	 * 
	 
	private File news_bimg;
	private String news_bimgFileName;
	private String news_bimgContentType;
	private File news_simg;
	private String news_simgFileName;
	private String news_simgContentType;

	
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 

	public String page_create_category() {

		if (option.equals("update")) {
			category = sNewsCategoryService.getCategoryByID(category);
		} else if (option.equals("create")) {

		}

		// Ϊ�����ø����
		ActionContext.getContext().getValueStack().set("category", category);

		ActionContext.getContext().getValueStack().set("option", option);

		ActionContext.getContext().getValueStack().set("page", page);

		return "page_create_category";
	}

	public String page_list_category() {
		
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
				return "page_list_category";
			}

		}
		
		 * 
		 
		
		 * 
		 
		List<CategoryListDTO> categoryListDTO = sNewsCategoryService.listCategoryOneAndSon();

		
		 * 
		 
		ActionContext.getContext().getValueStack().set("categoryListDTO", categoryListDTO);

		ActionContext.getContext().getValueStack().set("page", page);

		return "page_list_category";
	}

	
	 * �޸����
	 
	public void update_category() {
		
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
		
		 * 
		 

		System.out.println("page:" + page);

		ActionContext.getContext().getValueStack().set("page", page);

		if (category_img != null) {
			if (category_img.length() <= 5242800) {

				String filePath;

				String fileName = UUID.randomUUID().toString()
						+ category_imgFileName.substring(category_imgFileName.lastIndexOf("."));

				filePath = "C://xxyjsjgcxy/xxyjsjgcxy_img/jsj_snews_category/" + fileName;

				System.out.println("������fileName:" + fileName);


				// �洢�ļ�����storageFile
				File newFile = new File(filePath);

				try {

					FileUtils.copyFile(category_img, newFile);

				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		} else {
		}

		sNewsCategoryService.updateCategoryAllByID(category);

		try {

			http_response.setContentType("text/html;charset=utf-8");

			http_response.getWriter().write("success");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	
	 * �������
	 
	public void save_category() {
		
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
		
		 * 
		 
		
		 * ��֤������ƣ�ͼƬ����
		 

		if (sNewsCategoryService.getCategoryByName(category) != null) {

			
			 * �ҵ�������ͬ��category��
			 

			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setPrettyPrinting();// ��ʽ��
			Gson gson = gsonBuilder.create();

			try {

				http_response.setContentType("text/html;charset=utf-8");

				http_response.getWriter().write(gson.toJson("�����ظ�������ʹ�ô�����"));

			} catch (IOException e) {

				e.printStackTrace();
			}

			return;
		}

		
		 * �洢ͼƬ
		 
		if (category_img != null) {
			if (category_img.length() <= 5242800) {

				String filePath;

				String fileName = UUID.randomUUID().toString()
						+ category_imgFileName.substring(category_imgFileName.lastIndexOf("."));

				filePath = "C://xxyjsjgcxy/xxyjsjgcxy_img/jsj_snews_category/" + fileName;

				System.out.println("������fileName:" + fileName);


				// �洢�ļ�����storageFile
				File newFile = new File(filePath);

				try {

					FileUtils.copyFile(category_img, newFile);

				} catch (IOException e) {

					e.printStackTrace();
				}

			}
		} else {
		}

		
		 * �洢����
		 
		sNewsCategoryService.saveCategory(category);

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// ��ʽ��json����
		Gson gson = gsonBuilder.create();
		try {

			http_response.setContentType("text/html;charset=utf-8");

			http_response.getWriter().write(gson.toJson("success"));

			ActionContext.getContext().getValueStack().set("page", page);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	
	 * �������һ�����
	 
	public void list_category_rankOne() {

		List<jsj_snews_category> categoryList = sNewsCategoryService.listCategoryByRankOne();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// ��ʽ��json����
		Gson gson = gsonBuilder.create();

		try {

			http_response.setContentType("text/html;charset=utf-8");

			http_response.getWriter().write(gson.toJson(categoryList));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void listCategoryAll() {

		List<jsj_snews_category> categoryList = sNewsCategoryService.listCategoryAll();

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// ��ʽ��json����
		Gson gson = gsonBuilder.create();

		try {

			http_response.setContentType("text/html;charset=utf-8");

			http_response.getWriter().write(gson.toJson(categoryList));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void listCategoryRankTwo() {

		List<jsj_snews_category> categoryList = sNewsCategoryService.listCategoryRankTwo();

		categoryList.addAll(sNewsCategoryService.listCategoryByName("֪ͨ����"));

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// ��ʽ��json����
		Gson gson = gsonBuilder.create();

		try {

			http_response.setContentType("text/html;charset=utf-8");

			http_response.getWriter().write(gson.toJson(categoryList));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	
	 * �޸�category_show
	 
	public void update_category_show() {

		sNewsCategoryService.updateCategoryShowByID(category);

		try {

			http_response.setContentType("text/html;charset=utf-8");

			http_response.getWriter().write("success");

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void delete_category() {

		sNewsCategoryService.removeCategoryByID(category);

		try {

			http_response.setContentType("text/html;charset=utf-8");

			http_response.getWriter().write("success");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 
	public File getFile() {
		return file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void setFile(File file) {
		this.file = file;
	}

	
	 * 
	 

	public File getNews_bimg() {
		return news_bimg;
	}


	public SNewsCategoryService getsNewsCategoryService() {
		return sNewsCategoryService;
	}

	public void setsNewsCategoryService(SNewsCategoryService sNewsCategoryService) {
		this.sNewsCategoryService = sNewsCategoryService;
	}

	public void setNews_bimg(File news_bimg) {
		this.news_bimg = news_bimg;
	}

	public String getNews_bimgFileName() {
		return news_bimgFileName;
	}

	public void setNews_bimgFileName(String news_bimgFileName) {
		this.news_bimgFileName = news_bimgFileName;
	}

	public String getNews_bimgContentType() {
		return news_bimgContentType;
	}

	public void setNews_bimgContentType(String news_bimgContentType) {
		this.news_bimgContentType = news_bimgContentType;
	}

	public File getNews_simg() {
		return news_simg;
	}

	public void setNews_simg(File news_simg) {
		this.news_simg = news_simg;
	}

	public String getNews_simgFileName() {
		return news_simgFileName;
	}

	public void setNews_simgFileName(String news_simgFileName) {
		this.news_simgFileName = news_simgFileName;
	}

	public String getNews_simgContentType() {
		return news_simgContentType;
	}

	public void setNews_simgContentType(String news_simgContentType) {
		this.news_simgContentType = news_simgContentType;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public File getCategory_img() {
		return category_img;
	}

	public void setCategory_img(File category_img) {
		this.category_img = category_img;
	}

	public String getCategory_imgFileName() {
		return category_imgFileName;
	}

	public void setCategory_imgFileName(String category_imgFileName) {
		this.category_imgFileName = category_imgFileName;
	}

	public String getCategory_imgContentType() {
		return category_imgContentType;
	}

	public void setCategory_imgContentType(String category_imgContentType) {
		this.category_imgContentType = category_imgContentType;
	}

	public jsj_snews_category getCategory() {
		return category;
	}

	public void setCategory(jsj_snews_category category) {
		this.category = category;
	}

	public jsj_snews_news getNews() {
		return news;
	}

	public void setNews(jsj_snews_news news) {
		this.news = news;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	@Override
	public void setServletRequest(HttpServletRequest http_request) {

		this.http_request = http_request;

	}

	@Override
	public void setServletResponse(HttpServletResponse http_response) {

		this.http_response = http_response;

	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		
	}

}
*/