/**
 * 
 */
package com.chen.bean;

import java.util.Date;

/**
 * @author wzh
 * 与servlet配合在CommentServiceImpl.java中用作展示输出类
 */
public class Comments {
	private int id;
	private String content;    //评论内容
	private int floor;         //用户处于几层（楼）
	private Date commentTime; //评论时间
	private int integral;
	private int status;  //评论的状态，是否被删除，0表示没有，1表示被删除
	// 多对一
	private Topics commentsTopic;
	private Users commentsUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	public Topics getCommentsTopic() {
		return commentsTopic;
	}

	public void setCommentsTopic(Topics commentsTopic) {
		this.commentsTopic = commentsTopic;
	}

	public Users getCommentsUser() {
		return commentsUser;
	}

	public void setCommentsUser(Users commentsUser) {
		this.commentsUser = commentsUser;
	}

}
