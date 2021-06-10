
package com.chen.dao;

import java.util.List;

import com.chen.bean.Topics;
import com.chen.bean.Types;
//定义接口TopicDao
public interface TopicDao {
	public boolean add(Topics topic);
    /*查找用户id的topic
	 * @param id
	 * @return
	 */
	public Topics find(int id);
	/*更新topic
	 * @param id
	 * @return
	 */
	public boolean update(Topics topic);

	/**按topic.id下降顺序获取Index条记录
	 * @param index
	 * @return
	 */
	public List<Topics> getIndexFresh(int index);

	/*获取所有Topics
	 * @return
	 */
	public List<Topics> getAll();

	/**按topic.countComment下降顺序并且topic.countComment>=3获取index条记录
	 * @param index
	 * @return
	 */
	public List<Topics> getIndexHot(int index);

	/**按照topic.id下降顺序获取listType中对应id的i对应的记录（index条）
	 * @param listType
	 * @return
	 */
	public List<Topics> getByType(List<Types> listType);

	/**删除
	 * @param topic
	 */
	public void delete(Topics topic);

	/**按照 topic.id 下降顺序topic.niceTopic =1  （获取index条记录）
	 * @param index
	 * @return
	 */
	public List<Topics> getIndexNice(int index);

}
