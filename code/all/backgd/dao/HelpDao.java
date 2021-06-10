
package com.chen.dao;

import java.util.List;

import com.chen.bean.Helps;


public interface HelpDao {
	public List<Helps> getAll();

	/*查找帮助index对应的help
	 * @param index
	 * @return
	 */
	public Helps find(int index);

	/**添加thelp
	 * @param thelp
	 */
	public void add(Helps thelp);

	/**更新thelp
	 * @param thelp
	 */
	public void update(Helps thelp);

}
