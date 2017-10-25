package com.pxxyca.snews.domain;

public class jsj_snews_category {
	// 新闻类别id
	private String jsj_snews_category_id;
	// 新闻类别名
	private String category_name;
	// 新闻创建时间
	private String category_gmt_create;
	// 新闻修改时间
	private String category_gmt_modified;

	public jsj_snews_category() {

	}

	public jsj_snews_category(String jsj_snews_category_id, String category_name, String category_gmt_create,
			String category_gmt_modified) {
		this.jsj_snews_category_id = jsj_snews_category_id;
		this.category_name = category_name;
		this.category_gmt_create = category_gmt_create;
		this.category_gmt_modified = category_gmt_modified;
	}

	@Override
	public String toString() {
		return "jsj_snews_category [jsj_snews_category_id=" + jsj_snews_category_id + ", category_name=" + category_name
				+ ", category_gmt_create=" + category_gmt_create + ", category_gmt_modified=" + category_gmt_modified
				+ "]";
	}

	public String getJsj_snews_category_id() {
		return jsj_snews_category_id;
	}

	public void setJsj_snews_category_id(String jsj_snews_category_id) {
		this.jsj_snews_category_id = jsj_snews_category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getCategory_gmt_create() {
		return category_gmt_create;
	}

	public void setCategory_gmt_create(String category_gmt_create) {
		this.category_gmt_create = category_gmt_create;
	}

	public String getCategory_gmt_modified() {
		return category_gmt_modified;
	}

	public void setCategory_gmt_modified(String category_gmt_modified) {
		this.category_gmt_modified = category_gmt_modified;
	}

}
