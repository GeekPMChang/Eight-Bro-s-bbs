/**
 * 
 */
package com.chen.service;

import java.util.List;

import com.chen.bean.Announces;
import com.chen.bean.Pages;

/**
 * 
 */
public interface AnnounceService {
	public List<Announces> getIndexAnno(int index);//取得指定index的通知

	/**
	 * @return
	 * 获取所有通知
	 */
	public List<Announces> getAll();

	/**
	 * @param pageSize 一页面展示的announce数
	 * @param nowPage 当前的页
	 * 将所有announce按升序排列,然后分页展示
	 * @return null
	 */
	public Pages ManageAllForPages(int pageSize, int nowPage);

	/**
	 * @param id
	 * 查找是否有标识号为id的通知
	 */
	public Announces find(int id);

	/**
	 * @param announce
	 * 更新通知
	 */
	public void ManageUpdate(Announces announce);

	/**
	 * @param announce
	 * 添加通知
	 */
	public void ManageAdd(Announces announce);
}
