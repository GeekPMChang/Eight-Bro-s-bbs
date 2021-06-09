/**
 * 
 */
package com.chen.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wzh
 * 与servlet配合在CategoryServiceImpl.java用作展示输出的实体类
 */
public class Categorys {
	private int id;
	private String name;
	private int countTopics = 0;  //帖子话题数量
	private int countComments = 0; //评论计数
	private Set<Types> categoryTypes = new HashSet<Types>(); //分类链表

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Types> getCategoryTypes() {
		return categoryTypes;
	}

	public void setCategoryTypes(Set<Types> categoryTypes) {
		this.categoryTypes = categoryTypes;
	}

	public int getCountTopics() {
		return countTopics;
	}

	public void setCountTopics(int countTopics) {
		this.countTopics = countTopics;
	}

	public int getCountComments() {
		return countComments;
	}

	public void setCountComments(int countComments) {
		this.countComments = countComments;
	}

}
