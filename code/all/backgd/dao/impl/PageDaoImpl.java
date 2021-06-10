
package com.chen.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chen.bean.Topics;
import com.chen.dao.PageDao;


@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class PageDaoImpl implements PageDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public int getAllRowCount(String hql) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery(hql);//执行hql
		// session.close();
		return q.list().size();//返回size
	}

	@Override
	public List query_Objects_ForPages(String hql, int offset, int length) {
		Session session = this.sessionFactory.getCurrentSession();
		Query q = session.createQuery(hql);
		q.setFirstResult(offset);//设置其实地址
		q.setMaxResults(length);//获取length行
		List list = q.list();
		// session.close();
		return list;
	}

}
