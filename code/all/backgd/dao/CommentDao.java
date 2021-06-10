
package com.chen.dao;

import com.chen.bean.Comments;


public interface CommentDao {
	public boolean add(Comments comment);

	/**更新评论
	 * @param comments
	 */
	public void update(Comments comments);

	/**查找用户id的评论
	 * @param id
	 * @return
	 */
	public Comments find(int id);

	/**删除评论
	 * @param comment
	 */
	public void delete(Comments comment);

}
