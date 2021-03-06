
package com.chen.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.chen.bean.News;
import com.chen.dao.NewDao;


public class NewDaoImpl implements NewDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean add(News tnew) {//添加
		Session session = sessionFactory.getCurrentSession();
		session.save(tnew);
		return true;
	}

	@Override
	public boolean update(News tnew) {//更新New
		Session session = sessionFactory.getCurrentSession();
		session.update(tnew);
		return true;
	}
	@Override
	public News find(int id) { //查找
		Session session = sessionFactory.getCurrentSession();
		News tnew = (News) session.get(News.class,id);
		return tnew;
	}

	@Override
	public void delete(News tnew) {  //删除
		Session session = sessionFactory.getCurrentSession();
		session.delete(tnew);
	}

}
