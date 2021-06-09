/**
 * 
 */
package com.chen.service;

import java.util.List;

import com.chen.bean.Grades;
import com.chen.bean.Pages;
import com.chen.bean.Topics;
import com.chen.bean.Users;



public interface UserService {
	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean add(Users user);

	/**
	 * 禁用用户
	 * 
	 * @param index
	 * @return
	 */
	public void delete(int index);

	/**
	 * @param id
	 */
	public void unDelete(int id);

	/**
	 * 通过用户id查找是否存在该用户
	 * 
	 * @param index
	 * @return
	 */
	public Users find(int index);

	/**
	 * 更新修改了的用户信息
	 * 
	 * @param user
	 * @return
	 */
	public boolean update(Users user);

	/**
	 * 通过用户名和密码查找是否存在该用户
	 * 
	 * @param user
	 * @return
	 */
	public Users findByNP(Users user);

	/**
	 * 通过用户名查找是否存在该用户
	 * 
	 * @param userName
	 * @return
	 */
	public boolean findByName(String userName);

	public boolean findByNic(String userNic);

	/**
	 * @param pageSize
	 * @param nowPage
	 * @param id
	 * @return
	 * 按页得到帖子
	 */
	public Pages getTopicsForPages(int pageSize, int nowPage, int id);

	/**
	 * @param us
	 * @param pageSize
	 * @param nowPage
	 * @param id
	 * @return
	 * 按页得到评论
	 */
	public Pages getCommentsForPages(Users us, int pageSize, int nowPage, int id);

	/**
	 * @param pageSize
	 * @param nowPage
	 * @param id
	 * @return
	 * 按页得到消息
	 */
	public Pages getNewsForPages(int pageSize, int nowPage, int id);

	/**
	 * @param pageSize
	 * @param nowPage
	 * @return
	 * 按页得到用户
	 */
	public Pages ManageUsersForPage(int pageSize, int nowPage);

	/**
	 * @param id
	 * @return
	 * 寻找等级
	 */
	public Grades findGrade(int id);

}
