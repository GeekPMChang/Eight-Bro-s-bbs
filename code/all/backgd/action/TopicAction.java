/**
 * @author chang
 * @email geekzhangchang@gmail.com
 * 帖子功能
 */
package com.chen.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.chen.bean.Comments;
import com.chen.bean.News;
import com.chen.bean.Pages;
import com.chen.bean.Topics;
import com.chen.bean.Types;
import com.chen.bean.Users;
import com.chen.service.TopicService;

public class TopicAction {
	private TopicService topicService;
	private Topics topic;
	private Types ttype;
	private News tnew;
	private Pages pageBean;
	private int nowPage = 1;
	private int listFloor[];
	private int comFlag = 0;
	private String content;
	private List<Topics> listTopic;
	private List<Comments> listComment;
	HttpSession session = ServletActionContext.getRequest().getSession();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();

	public String add() throws Exception {
		Users user = (Users) session.getAttribute("tu");
		boolean flag = this.topicService.add(topic, user, response, request,
				ttype);
		if (flag != true) {
			return "topic_add_no";
		}
		return "topic_add_ok";
	}

	//去往指定的帖子
	public String goTopic() throws Exception {
		Topics top = this.topicService.find(topic.getId());
		if(top == null){
			return "error";
		}
		List<Comments> list = new ArrayList<Comments>();
		List<Comments> listTemp = new ArrayList<Comments>();
		listTemp.addAll(top.getTopicComments());
		if (this.comFlag == 0) {
			for (int i = 0; i < listTemp.size(); i++) {
				if (listTemp.get(i).getStatus() == 0) {
					list.add(listTemp.get(i));
				}
			}
		} else if (this.comFlag == 2) {
			for (int i = 0; i < listTemp.size(); i++) {
				if (listTemp.get(i).getCommentsUser().getId() == top
						.getTopicsUser().getId()) {
					list.add(listTemp.get(i));
				}
			}
		} else if (this.comFlag == 3) {
			for (int i = 0; i < listTemp.size(); i++) {
				if (listTemp.get(i).getIntegral() > 0) {
					list.add(listTemp.get(i));
				}
			}
		} else {
			list = listTemp;
		}
		/* 先将list按层数排序 */
		if (list.size() > 1) {
			this.QuickSort(list, 0, list.size() - 1);
		}
		this.nowPage = (this.nowPage == 0) ? 1 : this.nowPage;

		/* 再将list按要求分页 */
		this.pageBean = this.QueryCommentsForPage(10, nowPage, list);
		this.listComment = this.pageBean.getListComments();
		this.topic = top;
		return "topic_go_ok";

	}

	//对获取的帖子进行快排（按照低-高顺序进行排序）
	public void QuickSort(List<Comments> list, int first, int end) {
		int i = first;
		int j = end;
		Comments temp = list.get(i); 
		while (i < j) { 
			while (i < j && temp.getFloor() <= list.get(j).getFloor())
				j--;
			list.set(i, list.get(j)); 
			while (i < j && list.get(i).getFloor() <= temp.getFloor())
				i++;
			list.set(j, list.get(i)); 
		}
		list.set(i, temp); 
		if (first < i - 1)
			QuickSort(list, first, i - 1); 
		if (i + 1 < end)
			QuickSort(list, i + 1, end); 
	}
	//帖子的分页处理
	public Pages QueryCommentsForPage(int pageSize, int nowPage,
			List<Comments> listComment) {
		int allRecords = listComment.size();
		int totalPage = Pages.calculateTotalPage(pageSize, allRecords);
		final int currentoffset = Pages.currentPage_startRecord(pageSize,
				nowPage);
		final int length = pageSize;
		final int currentPage = Pages.judgeCurrentPage(nowPage);
		int toIndex = 0;
		if (allRecords >= length + currentoffset) {
			toIndex = currentoffset + length;
		} else {
			toIndex = allRecords;
		}
		List<Comments> subListComment = listComment.subList(currentoffset,
				toIndex);
		Pages pagebean = new Pages();
		pagebean.setPageSize(pageSize);
		pagebean.setAllRecords(allRecords);
		pagebean.setCurrentPage(currentPage);
		pagebean.setTotalPages(totalPage);
		pagebean.setListComments(subListComment);
		pagebean.init();
		return pagebean;
	}

	//去往帖子的末尾
	public String GoEnd() throws Exception {
		this.topic = this.topicService.find(topic.getId());
		List<Comments> list = new ArrayList<Comments>();
		list.addAll(this.topic.getTopicComments());
		/* 先将list按楼层排序 */
		if (list.size() > 1) {
			this.QuickSort(list, 0, list.size() - 1);
		}
		this.listComment = list;
		return "topic_goEnd_ok";
	}

	//完成帖子
	public String EndTopic() throws Exception {
		//Users user = (Users) session.getAttribute("tu");
		String listfloor = request.getParameter("listFloor");
		String[] str = listfloor.split(",");
		int[] listFloor = new int[str.length];
		for (int i = 0; i < listFloor.length; i++) {
			listFloor[i] = Integer.parseInt(str[i]);
		}
		Topics top = this.topicService.find(topic.getId());
		Users user = top.getTopicsUser();
		List<Comments> list = new ArrayList<Comments>();
		List<Comments> listTemp = new ArrayList<Comments>();
		listTemp.addAll(top.getTopicComments());
		for (int i = 0; i < listTemp.size(); i++) {
			int m = listTemp.get(i).getCommentsUser().getId();
			int n = top.getTopicsUser().getId();
			if (m != n) {
				list.add(listTemp.get(i));
			}
		}
		/* 先将list按楼层排序 */
		if (list.size() > 1) {
			this.QuickSort(list, 0, list.size() - 1);
		}
		this.listComment = list;
		this.topicService.endTopic(listFloor, listComment);
		user.setIntegral(user.getIntegral()+top.getIntegral()*20/100);
		top.setTopicsUser(user);
		top.setStatus(1);
		this.topicService.update(top);
		return "topic_end_ok";
	}

	//通过新闻的方式跳转到帖子
	public String goTopicByNews() throws Exception {
		Users user = (Users) session.getAttribute("tu");
		this.topicService.updateNews(this.tnew, user);
		Topics top = this.topicService.find(topic.getId());
		List<Comments> list = new ArrayList<Comments>();
		List<Comments> listTemp = new ArrayList<Comments>();
		listTemp.addAll(top.getTopicComments());
		if (this.comFlag == 0) {
			for (int i = 0; i < listTemp.size(); i++) {
				if (listTemp.get(i).getStatus() == 0) {
					list.add(listTemp.get(i));
				}
			}
		} else if (this.comFlag == 2) {
			for (int i = 0; i < listTemp.size(); i++) {
				if (listTemp.get(i).getCommentsUser().getId() == top
						.getTopicsUser().getId()) {
					list.add(listTemp.get(i));
				}
			}
		} else {
			list = listTemp;
		}
		/* 先将list按楼层排序 */
		if (list.size() > 1) {
			this.QuickSort(list, 0, list.size() - 1);
		}
		this.nowPage = (this.nowPage == 0) ? 1 : this.nowPage;
		/* 再将list按要求分页 */
		this.pageBean = this.QueryCommentsForPage(10, nowPage, list);
		this.listComment = this.pageBean.getListComments();
		this.topic = top;
		return "topic_goTopicByNews_ok";
	}

	public String ManageAll() throws Exception {
		this.pageBean = this.topicService.getAllForPages(12, nowPage);
		this.listTopic = pageBean.getListTopics();
		return "topic_mangeAll_ok";
	}

	public String ManageNice() throws Exception {
		this.topic = this.topicService.find(this.topic.getId());
		this.topic.setNiceTopic(1);
		this.topic.getTopicsUser().setGradeIntegral(
				this.topic.getTopicsUser().getGradeIntegral() + 20); // 帖子被推荐精品帖子作者的等级升级分增加20分
		this.topic.getTopicsUser().setIntegral(
				this.topic.getTopicsUser().getIntegral() + 20); // 帖子被推荐精品帖子作者加20分
		this.topicService.update(this.topic);
		return "topic_manageNice_ok";
	}

	/* 正在写，目前还有一个积分这个大问题没有解决，是否在用户登录时判断是否升级 */
	public String ManageUnNice() throws Exception {
		this.topic = this.topicService.find(this.topic.getId());
		this.topic.getTopicsUser().setGradeIntegral(
				this.topic.getTopicsUser().getGradeIntegral() - 20); // 帖子被取消推荐精品帖子作者的等级升级分减20分
		this.topic.setNiceTopic(0);
		this.topic.getTopicsUser().setIntegral(
				this.topic.getTopicsUser().getIntegral() - 20); // 帖子被取消推荐精品帖子作者减20分
		this.topicService.update(this.topic);
		return "topic_manageUnNice_ok";
	}

//------------------- 帖子的基本操作 ----------------------	

	public String getIndexHotTopic() throws Exception {
		this.listTopic = this.topicService.getIndexHotTopic(10);
		return "topic_getIndexHot_ok";
	}

	public String getIndexNiceTopic() throws Exception {
		this.listTopic = this.topicService.getIndexNiceTopic(10);
		return "topic_getIndexNice_ok";
	}

	public String getIndexFreshTopic() throws Exception {
		this.listTopic = this.topicService.getIndexFreshTopic(10);
		return "topic_getIndexFresh_ok";
	}

	public String getAllTopic() throws Exception {
		this.pageBean = this.topicService.getAllForPages(10, nowPage);
		this.listTopic = pageBean.getListTopics();
		return "topic_getALL_ok";
	}

	public String GetHotTopic() throws Exception {
		this.pageBean = this.topicService.getHotForPages(10, nowPage);
		this.listTopic = pageBean.getListTopics();
		return "topic_getHot_ok";
	}

	public String GetNiceTopic() throws Exception {
		this.pageBean = this.topicService.getNiceForPages(10, nowPage);
		this.listTopic = pageBean.getListTopics();
		return "topic_getNice_ok";
	}

	public String Search() throws Exception {
		this.pageBean = this.topicService.search(content, 5, nowPage);
		this.listTopic = pageBean.getListTopics();
		System.out.println(listTopic);
		return "topic_search_ok";
	}

//------------------- 获得类中的对象 ----------------------	

	public TopicService getTopicService() {
		return topicService;
	}

	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	public Topics getTopic() {
		return topic;
	}

	public void setTopic(Topics topic) {
		this.topic = topic;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public Pages getPageBean() {
		return pageBean;
	}

	public void setPageBean(Pages pageBean) {
		this.pageBean = pageBean;
	}

	public Types getTtype() {
		return ttype;
	}

	public void setTtype(Types ttype) {
		this.ttype = ttype;
	}

	public List<Topics> getListTopic() {
		return listTopic;
	}

	public void setListTopic(List<Topics> listTopic) {
		this.listTopic = listTopic;
	}

	public List<Comments> getListComment() {
		return listComment;
	}

	public void setListComment(List<Comments> listComment) {
		this.listComment = listComment;
	}

	public int[] getListFloor() {
		return listFloor;
	}

	public void setListFloor(int[] listFloor) {
		this.listFloor = listFloor;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getComFlag() {
		return comFlag;
	}

	public void setComFlag(int comFlag) {
		this.comFlag = comFlag;
	}

	public News getTnew() {
		return tnew;
	}

	public void setTnew(News tnew) {
		this.tnew = tnew;
	}

}
