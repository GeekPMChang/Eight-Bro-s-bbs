/**
 * 
 */
package com.chen.service;

import java.util.List;


import com.chen.bean.Categorys;
import com.chen.bean.Pages;
import com.chen.bean.Topics;
import com.chen.bean.Types;

/*
*  对版块的一系列管理操作
*/ 
public interface CategoryService {
	//添加一个版块
	public boolean add(Categorys cate);

	//删除一个版块
	public boolean delete(int index);

	//更新一个版块
	public boolean update(Categorys cate);

	//列出所有版块
	public List<Categorys> getAll();

	/**
	 * @param id 
	 * 获取版块的类型
	 * @return
	 */
	public List<Types> getType(int id);

	public List<Types> new_getType(int id);

	/**
	 * @param listType
	 * 获取帖子
	 * @return
	 */
	public List<Topics> getTopic(List<Types> listType);

	/**
	 * @param i
	 * @param nowPage
	 * 按page划分
	 * @param listType
	 * @return
	 */
	public Pages getAllForPages(int pageSize, int nowPage, List<Types> listType);

	/**
	 * @param id
	 * 查找指定id的版块
	 * @return
	 */
	public Categorys find(int id);

	/**
	 * @param id
	 * @return
	 */
	public List<Types> manageType(int id);
}
