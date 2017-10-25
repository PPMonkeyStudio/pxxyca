package com.pxxyca.snews.domain;

public class jsj_scarousel_img {
	// 轮播图id
	private String jsj_scarousel_img_id;
	// 轮播图所属新闻
	private String img_news;
	// 轮播图创建时间
	private String img_gmt_create;
	// 轮播图修改时间
	private String img_gmt_modified;

	public jsj_scarousel_img() {

	}

	public jsj_scarousel_img(String jsj_scarousel_img_id, String img_news, String img_gmt_create,
			String img_gmt_modified) {
		this.jsj_scarousel_img_id = jsj_scarousel_img_id;
		this.img_news = img_news;
		this.img_gmt_create = img_gmt_create;
		this.img_gmt_modified = img_gmt_modified;
	}

	@Override
	public String toString() {
		return "jsj_scarousel_img [jsj_scarousel_img_id=" + jsj_scarousel_img_id + ", img_news=" + img_news
				+ ", img_gmt_create=" + img_gmt_create + ", img_gmt_modified=" + img_gmt_modified + "]";
	}

	public String getJsj_scarousel_img_id() {
		return jsj_scarousel_img_id;
	}

	public void setJsj_scarousel_img_id(String jsj_scarousel_img_id) {
		this.jsj_scarousel_img_id = jsj_scarousel_img_id;
	}

	public String getImg_news() {
		return img_news;
	}

	public void setImg_news(String img_news) {
		this.img_news = img_news;
	}

	public String getImg_gmt_create() {
		return img_gmt_create;
	}

	public void setImg_gmt_create(String img_gmt_create) {
		this.img_gmt_create = img_gmt_create;
	}

	public String getImg_gmt_modified() {
		return img_gmt_modified;
	}

	public void setImg_gmt_modified(String img_gmt_modified) {
		this.img_gmt_modified = img_gmt_modified;
	}

}
