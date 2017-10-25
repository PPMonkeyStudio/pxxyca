package com.pxxyca.snews.domain;

public class searchNewsListDTO {
	//
	private String category = "%%";
	//
	private String start_time = "0000-00-00";
	private String stop_time = "9999-99-99";
	//
	private String sqrt = "news_gmt_create";
	private String sqrt_sc = "desc";

	/*
	 * 
	 */

	@Override
	public String toString() {
		return "searchNewsListDTO [category=" + category + ", start_time=" + start_time + ", stop_time=" + stop_time +  ", sqrt="
				+ sqrt + ", sqrt_sc=" + sqrt_sc + "]";
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getStop_time() {
		return stop_time;
	}

	public void setStop_time(String stop_time) {
		this.stop_time = stop_time;
	}

	public String getSqrt() {
		return sqrt;
	}

	public void setSqrt(String sqrt) {
		this.sqrt = sqrt;
	}

	public String getSqrt_sc() {
		return sqrt_sc;
	}

	public void setSqrt_sc(String sqrt_sc) {
		this.sqrt_sc = sqrt_sc;
	}

}
