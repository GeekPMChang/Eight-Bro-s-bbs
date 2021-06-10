
package com.chen.dao;

import java.util.List;

import com.chen.bean.Categorys;


public interface CategoryDao {
	public boolean add(Categorys cate);//添加category
	public boolean delete(int index);//删除
	public boolean update(Categorys cate);//更新
	public List<Categorys> getAll();
	/**查找
	 * @param id
	 * @return
	 */
	public Categorys find(int id);
}
