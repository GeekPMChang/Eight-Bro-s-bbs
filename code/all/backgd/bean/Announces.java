package com.chen.bean;

import java.util.Date;


/**
 * @author wzh
 * ��servlet�����AnnounceServiceImpl.java����չʾ�����ʵ����
 */
public class Announces {

	private Integer id;  //����
	private String announcement; //��������
	private String title; //����
	private Date thetime; //����ʱ��

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