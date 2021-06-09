/**
 * 
 */
package com.chen.bean;

/**
 * @author wzh
 * 与servlet配合在UserServiceImpl.java用作展示输出实体类
 */
public class Grades {
	private int id;  //主键，用户名
	private String honor;  //用户称号

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHonor() {
		return honor;
	}

	public void setHonor(String honor) {
		this.honor = honor;
	}

}
