/**
 * 
 */
package com.chen.bean;

import java.util.Date;

/**
 * @author wzh
 * 与servlet配合在UserServiceImpl.java用作展示输出的实体类
 */
public class News {
	private int id;
	private Date newTime;  //消息到达时间
	private int status; // 0表示未读，1表示已读
	// 多对一
	// private Users topusers;
	private Users newsCommentUser;
	private Topics newsTopic;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getNewTime() {
		return newTime;
	}

	public void setNewTime(Date newTime) {
		this.newTime = newTime;
	}

	public Users getNewsCommentUser() {
		return newsCommentUser;
	}

	public void setNewsCommentUser(Users newsCommentUser) {
		this.newsCommentUser = newsCommentUser;
	}

	public Topics getNewsTopic() {
		return newsTopic;
	}

	public void setNewsTopic(Topics newsTopic) {
		this.newsTopic = newsTopic;
	}

}
