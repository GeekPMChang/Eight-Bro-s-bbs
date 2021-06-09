package com.chen.bean;

import java.util.Date;

//与servlet配合在HelpServiceImpl.java用作展示输出类
public class Helps {

	private int id;
	private String title;    //帮助标题
	private String content;   //帮助内容
	private Date newtime;   //建立时间

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getNewtime() {
		return newtime;
	}

	public void setNewtime(Date newtime) {
		this.newtime = newtime;
	}


}