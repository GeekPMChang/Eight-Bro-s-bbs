package com.chen.bean;

import java.util.HashSet;
import java.util.Set;
//与servlet配合在TypeServiceImpl.java用作展示输出实体类
public class Types {

	private int id;
	private String name;   //类型名称
	private int countTopics = 0; //话题数量
	private int countComments = 0; //用户评论数量

	private Categorys typesCategory;

	private Set<Topics> typeTopics = new HashSet<Topics>();

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

	public int getCountTopics() {
		return countTopics;
	}

	public void setCountTopics(int countTopics) {
		this.countTopics = countTopics;
	}

	public Set<Topics> getTypeTopics() {
		return typeTopics;
	}

	public void setTypeTopics(Set<Topics> typeTopics) {
		this.typeTopics = typeTopics;
	}

	public Categorys getTypesCategory() {
		return typesCategory;
	}

	public void setTypesCategory(Categorys typesCategory) {
		this.typesCategory = typesCategory;
	}

	public int getCountComments() {
		return countComments;
	}

	public void setCountComments(int countComments) {
		this.countComments = countComments;
	}


}