 package com.pxxyca.snews.domain;

public class jsj_snews_news {
	// 新闻id
	private String jsj_snews_news_id;
	// 新闻标题
	private String news_title;
	// 新闻图
	private String news_img;
	// 新闻类别
	private String news_category;
	// 新闻显示时间
	private String news_gmt_show;
	// 新闻创建时间
	private String news_gmt_create;
	// 新闻修改时间
	private String news_gmt_modified;

	public jsj_snews_news() {

	}

	public jsj_snews_news(String jsj_snews_news_id, String news_title, String news_img, String news_category,
			String news_gmt_show, String news_gmt_create, String news_gmt_modified) {
		this.jsj_snews_news_id = jsj_snews_news_id;
		this.news_title = news_title;
		this.news_img = news_img;
		this.news_category = news_category;
		this.news_gmt_show = news_gmt_show;
		this.news_gmt_create = news_gmt_create;
		this.news_gmt_modified = news_gmt_modified;
	}

	@Override
	public String toString() {
		return "jsj_snews_news [jsj_snews_news_id=" + jsj_snews_news_id + ", news_title=" + news_title + ", news_img="
				+ news_img + ", news_category=" + news_category + ", news_gmt_show=" + news_gmt_show
				+ ", news_gmt_create=" + news_gmt_create + ", news_gmt_modified=" + news_gmt_modified + "]";
	}

	public String getJsj_snews_news_id() {
		return jsj_snews_news_id;
	}

	public void setJsj_snews_news_id(String jsj_snews_news_id) {
		this.jsj_snews_news_id = jsj_snews_news_id;
	}

	public String getNews_title() {
		return news_title;
	}

	public void setNews_title(String news_title) {
		this.news_title = news_title;
	}

	public String getNews_img() {
		return news_img;
	}

	public void setNews_img(String news_img) {
		this.news_img = news_img;
	}

	public String getNews_category() {
		return news_category;
	}

	public void setNews_category(String news_category) {
		this.news_category = news_category;
	}

	public String getNews_gmt_show() {
		return news_gmt_show;
	}

	public void setNews_gmt_show(String news_gmt_show) {
		this.news_gmt_show = news_gmt_show;
	}

	public String getNews_gmt_create() {
		return news_gmt_create;
	}

	public void setNews_gmt_create(String news_gmt_create) {
		this.news_gmt_create = news_gmt_create;
	}

	public String getNews_gmt_modified() {
		return news_gmt_modified;
	}

	public void setNews_gmt_modified(String news_gmt_modified) {
		this.news_gmt_modified = news_gmt_modified;
	}

}
