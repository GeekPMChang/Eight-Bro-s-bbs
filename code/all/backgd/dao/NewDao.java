
package com.chen.dao;

import com.chen.bean.News;


public interface NewDao {
	public boolean add(News tnew);

	public boolean update(News tnew);

	/*
	 * @param id
	 * @return
	 */
	public News find(int id);

	/**
	 * @param tnew
	 */
	public void delete(News tnew);
}
