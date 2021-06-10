
package com.chen.dao;

import com.chen.bean.Grades;
//定义接口 GradeDao
public interface GradeDao {

	/**查找用户id的Grade
	 * @param id
	 * @return
	 */
	public Grades find(int id);

}
