
package com.chen.service.impl;

import java.util.List;

import com.chen.bean.Announces;
import com.chen.bean.Pages;
import com.chen.bean.Topics;
import com.chen.dao.AnnounceDao;
import com.chen.dao.PageDao;
import com.chen.service.AnnounceService;

// 公告服务实现
public class AnnounceServiceImpl implements AnnounceService {

	private AnnounceDao announceDao;	// 公告数据访问对象
	private PageDao pageDao;			// 页面数据访问对象
	
	// setters and getters
	public AnnounceDao getAnnounceDao() {
		return announceDao;
	}

	public void setAnnounceDao(AnnounceDao announceDao) {
		this.announceDao = announceDao;
	}

	public PageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

	// 通过指定index获取公告列表
	@Override
	public List<Announces> getIndexAnno(int index) {
		return this.announceDao.getIndexAnno(index);
	}
	
	// 获得所有公告
	@Override
	public List<Announces> getAll() {
		return this.announceDao.getAll();
	}
	
	// 按页处理公告
	@Override
	public Pages ManageAllForPages(int pageSize, int nowPage) {
		final String sql = "from Announces as anno order by anno.id desc";
		int allRecords = this.pageDao.getAllRowCount(sql);
		int totalPage = Pages.calculateTotalPage(pageSize, allRecords);// 总页数
		final int currentoffset = Pages.currentPage_startRecord(pageSize,
				nowPage);// 当前页的开始记录
		final int length = pageSize;
		final int currentPage = Pages.judgeCurrentPage(nowPage);
		List<Announces> listAnnounces = this.pageDao.query_Objects_ForPages(sql,
				currentoffset, length);
		Pages pagebean = new Pages();
		pagebean.setPageSize(pageSize);
		pagebean.setAllRecords(allRecords);
		pagebean.setCurrentPage(currentPage);
		pagebean.setTotalPages(totalPage);
		pagebean.setListAnno(listAnnounces);
		pagebean.init();
		return pagebean;
	}
	
	// 使用id作为索引查找公告
	@Override
	public Announces find(int id) {
		return this.announceDao.find(id);
	}
	// 更新指定公告
	@Override
	public void ManageUpdate(Announces announce) {
		this.announceDao.update(announce);
	}
	// 增加指定公告
	@Override
	public void ManageAdd(Announces announce) {
		this.announceDao.add(announce);
		
	}

}
