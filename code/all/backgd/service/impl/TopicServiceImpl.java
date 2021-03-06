package com.chen.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chen.bean.Comments;
import com.chen.bean.News;
import com.chen.bean.Pages;
import com.chen.bean.Topics;
import com.chen.bean.Types;
import com.chen.bean.Users;
import com.chen.dao.CommentDao;
import com.chen.dao.NewDao;
import com.chen.dao.PageDao;
import com.chen.dao.TopicDao;
import com.chen.dao.TypeDao;
import com.chen.dao.UserDao;
import com.chen.service.TopicService;

// 帖子服务实现
public class TopicServiceImpl implements TopicService {
	
	// 用到的DAO, 使用get方法初始化
	private TopicDao topicDao;
	private TypeDao typeDao;
	private PageDao pageDao;
	private CommentDao commentDao;
	private NewDao newDao;
	private UserDao userDao;
	// basic getter and setter
	public TopicDao getTopicDao() {
		return topicDao;
	}

	public void setTopicDao(TopicDao topicDao) {
		this.topicDao = topicDao;
	}

	public TypeDao getTypeDao() {
		return typeDao;
	}

	public void setTypeDao(TypeDao typeDao) {
		this.typeDao = typeDao;
	}

	public PageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	public CommentDao getCommentDao() {
		return commentDao;
	}

	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}

	public NewDao getNewDao() {
		return newDao;
	}

	public void setNewDao(NewDao newDao) {
		this.newDao = newDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// 增加一个帖子
	@Override
	public boolean add(Topics topic, Users user, HttpServletResponse response,
			HttpServletRequest request, Types ttype) {
		Date ntime = new Date();
		user.setIntegral(user.getIntegral() + 2 - topic.getIntegral()); // 用户发布一条帖子加2积分
		user.setTopCount(user.getTopCount()+1);
		topic.setTopicsUser(user);

		Types ty = this.typeDao.find(ttype.getId());
		ty.setCountTopics(ty.getCountTopics() + 1); // 帖子相应的类型里的帖子数加1
		ty.getTypesCategory().setCountTopics(
				ty.getTypesCategory().getCountTopics() + 1); // 帖子相应的大类型里的帖子数加1
		topic.setTopicsType(ty);

		topic.setTopicTime(ntime);
		topic.setCountComment(0);
		topic.setStatus(0);
		this.topicDao.add(topic);
		return true;
	}

	@Override
	public Topics find(int id) {
		return this.topicDao.find(id);
	}

	@Override
	public List<Topics> getIndexHotTopic(int index) {
		return this.topicDao.getIndexHot(index);
	}

	@Override
	public List<Topics> getIndexNiceTopic(int index) {
		return this.topicDao.getIndexNice(index);
	}

	@Override
	public List<Topics> getIndexFreshTopic(int index) {
		return this.topicDao.getIndexFresh(index);
	}
	// 读取所有帖子
	@Override
	public Pages getAllForPages(int pageSize, int nowPage) {
		final String sql = "from Topics topic  order by topic.id desc";
		int allRecords = this.pageDao.getAllRowCount(sql);
		int totalPage = Pages.calculateTotalPage(pageSize, allRecords);// 总页数
		final int currentoffset = Pages.currentPage_startRecord(pageSize,
				nowPage);// 当前页的开始记录
		final int length = pageSize;
		final int currentPage = Pages.judgeCurrentPage(nowPage);
		List<Topics> listTopics = this.pageDao.query_Objects_ForPages(sql,
				currentoffset, length);
		Pages pagebean = new Pages();
		pagebean.setPageSize(pageSize);
		pagebean.setAllRecords(allRecords);
		pagebean.setCurrentPage(currentPage);
		pagebean.setTotalPages(totalPage);
		pagebean.setListTopics(listTopics);
		pagebean.init();
		return pagebean;
	}
	// 读取热帖
	@Override
	public Pages getHotForPages(int pageSize, int nowPage) {
		final String sql = "from Topics topic where topic.countComment >=3 order by topic.countComment desc";
		int allRecords = this.pageDao.getAllRowCount(sql);
		int totalPage = Pages.calculateTotalPage(pageSize, allRecords);// 总页数
		final int currentoffset = Pages.currentPage_startRecord(pageSize,
				nowPage);// 当前页的开始记录
		final int length = pageSize;
		final int currentPage = Pages.judgeCurrentPage(nowPage);
		List<Topics> listTopics = this.pageDao.query_Objects_ForPages(sql,
				currentoffset, length);
		Pages pagebean = new Pages();
		pagebean.setPageSize(pageSize);
		pagebean.setAllRecords(allRecords);
		pagebean.setCurrentPage(currentPage);
		pagebean.setTotalPages(totalPage);
		pagebean.setListTopics(listTopics);
		pagebean.init();
		return pagebean;
	}
	// 读取精华帖
	@Override
	public Pages getNiceForPages(int pageSize, int nowPage) {
		final String sql = "from Topics topic where topic.niceTopic =1 order by topic.id desc";
		int allRecords = this.pageDao.getAllRowCount(sql);
		int totalPage = Pages.calculateTotalPage(pageSize, allRecords);// 总页数
		final int currentoffset = Pages.currentPage_startRecord(pageSize,
				nowPage);// 当前页的开始记录
		final int length = pageSize;
		final int currentPage = Pages.judgeCurrentPage(nowPage);
		List<Topics> listTopics = this.pageDao.query_Objects_ForPages(sql,
				currentoffset, length);
		Pages pagebean = new Pages();
		pagebean.setPageSize(pageSize);
		pagebean.setAllRecords(allRecords);
		pagebean.setCurrentPage(currentPage);
		pagebean.setTotalPages(totalPage);
		pagebean.setListTopics(listTopics);
		pagebean.init();
		return pagebean;
	}
	// 归档帖子
	@Override
	public void endTopic(int[] listFloor, List<Comments> listComment) {
		for (int i = 0; i < listComment.size(); i++) {
			listComment.get(i).setIntegral(listFloor[i]);
			listComment
					.get(i)
					.getCommentsUser()
					.setIntegral(
							listComment.get(i).getCommentsUser().getIntegral()
									+ listFloor[i]);
			this.commentDao.update(listComment.get(i));
		}
	}

	@Override
	public void update(Topics topic) {
		this.topicDao.update(topic);
	}
	
	// 帖子中搜索指定内容
	@Override
	public Pages search(String content, int pageSize, int nowPage) {
		final String sql = "from Topics topic where topic.title like '%"
				+ content + "%' or topic.content like '%" + content
				+ "%' order by topic.id desc";
		int allRecords = this.pageDao.getAllRowCount(sql);
		int totalPage = Pages.calculateTotalPage(pageSize, allRecords);// 总页数
		final int currentoffset = Pages.currentPage_startRecord(pageSize,
				nowPage);// 当前页的开始记录
		final int length = pageSize;
		final int currentPage = Pages.judgeCurrentPage(nowPage);
		List<Topics> listTopics = this.pageDao.query_Objects_ForPages(sql,
				currentoffset, length);
		Pages pagebean = new Pages();
		pagebean.setPageSize(pageSize);
		pagebean.setAllRecords(allRecords);
		pagebean.setCurrentPage(currentPage);
		pagebean.setTotalPages(totalPage);
		pagebean.setListTopics(listTopics);
		pagebean.init();
		return pagebean;
	}

	// 更新最新消息和评论
	@Override
	public void updateNews(News tnew, Users user) {
		News tnews = this.newDao.find(tnew.getId());
		if (user.getClock() > 0) {
			user.setClock(user.getClock() - 1);
			//this.userDao.update(user);
		}
		tnews.setStatus(1);
		tnews.getNewsTopic().setTopicsUser(user);
		this.newDao.update(tnews);
	}

}
