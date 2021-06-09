/**
 * 
 */
package com.chen.service;


import com.chen.bean.Pages;
import com.chen.bean.Types;



public interface TypeService {
	public Pages getAllForPages(int pageSize, int nowPage, int typeId);

	/**
	 * @param id
	 * @return
	 *查找指定id的type
	 */
	public Types find(int id);

	/**
	 * @param type
	 * 添加type
	 */
	public void add(Types type);

	/**
	 * @param type
	 * 更新type
	 */
	public void update(Types type);
	
}
