/**
 * 
 */
package com.chen.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.bean.Comments;
import com.chen.bean.News;
import com.chen.bean.Pages;
import com.chen.bean.Topics;
import com.chen.bean.Types;
import com.chen.bean.Users;


public interface TopicService {
	public boolean add(Topics topic, Users user, HttpServletResponse response,
			HttpServletRequest request, Types ttype);

	/**
	 * @param id
	 * @return
	 *查找帖子
	 */
	public Topics find(int id);

	/**
	 * @param index
	 * @return
	 * 按照发出时间得到下标为index的帖子
	 */
	public List<Topics> getIndexFreshTopic(int index);

	/**
	 * @param index
	 * @return
	 * 按照精华帖的id得到帖子
	 */
	public List<Topics> getIndexNiceTopic(int index);

	/**
	 * @return
	 * 按page划分所有帖子
	 */
	public Pages getAllForPages(int pageSize, int nowPage);

	/**
	 * @param index
	 * @return
	 * 按照热度排序并得到下标为index的帖子
	 */
	public List<Topics> getIndexHotTopic(int index);

	/**
	 * @param listFloor
	 * @param listComment
	 * 关闭一个帖子 限制评论
	 */
	public void endTopic(int[] listFloor, List<Comments> listComment);

	/**
	 * @param topic
	 * 更新帖子
	 */
	public void update(Topics topic);

	/**
	 * @param i
	 * @param nowPage
	 * @return
	 * 得到一页按热度排序的帖子
	 */
	public Pages getHotForPages(int i, int nowPage);

	/**
	 * @param content
	 * 搜索含指定内容的帖子
	 */
	public Pages search(String content, int pageSize, int nowPage);

	/**
	 * @param tnew
	 * @param user
	 * 更新最新消息和评论
	 */
	public void updateNews(News tnew, Users user);

	/**
	 * @param pageSize
	 * @param nowPage
	 * @return
	 * 得到一页精华帖
	 */
	public Pages getNiceForPages(int pageSize, int nowPage);

}
