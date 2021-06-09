package com.chen.bean;

import java.util.Date;


/**
 * @author wzh
 * 与servlet配合在AnnounceServiceImpl.java用作展示输出的实体类
 */
public class Announces {

	private Integer id;  //主键
	private String announcement; //公告内容
	private String title; //标题
	private Date thetime; //公告时间

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAnnouncement() {
		return this.announcement;
	}

	public void setAnnouncement(String announcement) {
		this.announcement = announcement;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getThetime() {
		return thetime;
	}

	public void setThetime(Date thetime) {
		this.thetime = thetime;
	}

}