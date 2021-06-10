
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
import com.chen.bean.Users;
import com.chen.dao.UserDao;


@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
public class UserDaoImpl implements UserDao {
	private SessionFactory sessionFactory; /*SessionFactory维护的最重要的东西就是数据库连接池*/

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean add(Users user) {
		 /**
         * getCurrentSession()：
         *1， 如果当前上下文有session，就拿来当前的session。如果没有，就创建一个新的session
         * 2，不写session.close();
         * 注意：当session  commit()提交的时候自动close()，写了会报错。
         * 提交同时也会关闭当前session，上下文将不存在该session，此时getCurrentSession()就会创建一个新的session。
         */
		Session session = this.sessionFactory.getCurrentSession();
		session.save(user);//session.save()给数据库添加user信息
		return true;

	}

	@Override
	public boolean delete(Users user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.delete(user);//session.delete()给数据库添加user信息
		return true;
	}

	@Override
	public Users find(int index) {
		Session session = this.sessionFactory.getCurrentSession();
		Users user = (Users) session.get(Users.class, index);//session.get()从数据库获取用户id的信息
		return user;
	}

	@Override
	public boolean update(Users user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(user);//session.update()更新user信息
		return true;
	}

	@Override
	// 通过用户名和密码查找是否存在该用户
	public Users findByNP(Users user) {
		String hql = "from Users u where u.username  = ? and u.password = ?";
		Session session = this.sessionFactory.getCurrentSession();
		List<Users> listUser = null;
		Users us = null;
		try {
			Query q = session.createQuery(hql);
			q.setString(0, user.getUsername());
			q.setString(1, user.getPassword());
			listUser = q.list();
			if (listUser.size() > 0) {
				us = listUser.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		return us;
	}

	@Override
		
	 // 通过用户名查找是否存在该用户

	public boolean findByName(String userName) {
		String hql = "from Users u where u.username  = ?";
		Session session = this.sessionFactory.getCurrentSession();
		List<Users> listUser = null;
		try {
			Query q = session.createQuery(hql);
			q.setString(0, userName);
			listUser = q.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		if (listUser.size() > 0) {
			return true;
		} else
			return false;
	}

	@Override
	public boolean findByNic(String userNic) {
		String hql = "from Users u where u.nickname  = ?";
		Session session = this.sessionFactory.getCurrentSession();
		List<Users> listUser = null;
		try {
			Query q = session.createQuery(hql);
			q.setString(0, userNic);
			listUser = q.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			// session.close();
		}
		if (listUser.size() > 0) {
			return true;
		} else
			return false;
	}

	@Override
	//获取用户id的Toipcs
	public List<Topics> getToipcs(int id) {
		System.out.println("通知：here id user dao impl");
		Session session = this.sessionFactory.getCurrentSession();
		String hql = "from Topics as topic  where topic.topicsUser.id =" + id
				+ " order by topic.id desc";
		List<Topics> listTopic = null;
		try {
			Query q = session.createQuery(hql);
			listTopic = q.list();
		} catch (HibernateException e) {
			System.out.println("获取失败，here is user dao impl");
			e.printStackTrace();
		} finally {
			// session.close();
		}
		System.out.println("通知：user dao impl over");
		return listTopic;
	}
}
