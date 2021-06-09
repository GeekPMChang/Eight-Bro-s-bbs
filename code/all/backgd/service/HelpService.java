/**
 * 
 */
package com.chen.service;

import java.util.List;

import com.chen.bean.Helps;
import com.chen.bean.Pages;


public interface HelpService {
	public List<Helps> getAll();

	/**
	 * @param pageSize
	 * @param nowPage
	 * @return
	 * 按page划分所有help
	 */
	public Pages ManageAllForPages(int pageSize, int nowPage);

	/**
	 * @param index
	 * @return
	 * 查找指定index的help
	 */
	public Helps find(int index);

	/**
	 * @param thelp
	 * 添加help
	 */
	public void ManageAdd(Helps thelp);

	/**
	 * @param thelp
	 * 更新help
	 */
	public void ManageUpdate(Helps thelp);
}
