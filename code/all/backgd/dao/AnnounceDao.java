
package com.chen.dao;

import java.util.List;

import com.chen.bean.Announces;

//定义AnnounceDao接口
public interface AnnounceDao {
	//获取index条公告
	public List<Announces> getIndexAnno(int index);

	/**获取所有公告内容
	 * @return
	 */
	public List<Announces> getAll();

	/**查找
	 * @param id
	 */
	public Announces find(int id);

	/**更新
	 * @param announce
	 */
	public void update(Announces announce);

	/**添加公告
	 * @param announce
	 */
	public void add(Announces announce);
}
