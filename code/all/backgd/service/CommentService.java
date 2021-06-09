/**
 * 
 */
package com.chen.service;

import com.chen.bean.Comments;
import com.chen.bean.Topics;
import com.chen.bean.Users;


public interface CommentService {
	/**
	 * @param comment
	 * @param user 
	 * @param topic 
	 * 新增一个评论
	 */
	public boolean newComment(Comments comment, Users user, Topics topic);


	/**
	 * @param comment
	 * @param topic 
	 * 删除一个评论
	 */
	public void deleteComment(Comments comment, Topics topic);
}
