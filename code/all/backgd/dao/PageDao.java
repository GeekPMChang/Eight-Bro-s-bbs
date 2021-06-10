
package com.chen.dao;

import java.util.List;

import org.hibernate.Query;

public interface PageDao {
	//获取行数
	public int getAllRowCount(String hql);
	//获取指定行
	public List query_Objects_ForPages(String hql, int offset, int length);
}
