/**
 * @author chang
 * @email geekzhangchang@gmail.com
 * 类别操作
 */
package com.chen.action;

import java.util.List;

import com.chen.bean.Categorys;
import com.chen.bean.Pages;
import com.chen.bean.Topics;
import com.chen.bean.Types;
import com.chen.service.CategoryService;

public class CategoryAction {

	private Categorys category;
	private CategoryService cateService;
	private List<Categorys> listCate;
	private List<Types> listType;
	private List<Topics> listTopic;
	private Pages pageBean;
	private int nowPage = 1;
	
	//新建一个帖子时，获取所有大类
	public String new_getAll() throws Exception {
		this.listCate = this.cateService.getAll();
		return "cate_new_getAll_ok";
	}

	//新建一个帖子时，获取所有小类
	public String new_getType() throws Exception {
		this.listType = this.cateService.new_getType(category.getId());
		return "cate_new_getType_ok_json";
	}

	//获取所有类型
	public String getAll() throws Exception {
		this.listCate = this.cateService.getAll();
		return "cate_getAll_ok";
	}

	//获取目的对象类型
	public String getType() throws Exception {
		this.listType = this.cateService.getType(category.getId());
		return "cate_getType_ok";
	}

	//跳转到对应的类型
	public String goCate() throws Exception {
		this.listType = this.cateService.getType(category.getId());
		if(listType.size()==0){
			return "error";
		}
		category.setName(listType.get(0).getTypesCategory().getName());
		this.pageBean = this.cateService.getAllForPages(10, nowPage, listType);
		this.listTopic = this.pageBean.getListTopics();
		return "cate_go_ok";
	}
	
//---------------------- 管理员 -------------------------

	//管理大类
	public String ManageAll() throws Exception {
		this.listCate = this.cateService.getAll();
		return "cate_manageAll_ok";
	}

	//管理所有类型
	public String ManageAllType() throws Exception {
		this.category = this.cateService.find(category.getId());
		this.listType = this.cateService.manageType(category.getId());
		return "cate_manageAllType_ok_json";
	}
	
	//类别添加
	public String ManageAdd() throws Exception{
		this.cateService.add(category);
		return "cate_manageAdd_ok";
	}
	
	//类别更新
	public String ManageUpdate() throws Exception{
		this.cateService.update(category);
		return "cate_manageUpdate_ok";
	}

//------------------- 获取公告类中的类内对象 --------------------

	public Categorys getCategory() {
		return category;
	}

	public void setCategory(Categorys category) {
		this.category = category;
	}

	public CategoryService getCateService() {
		return cateService;
	}

	public void setCateService(CategoryService cateService) {
		this.cateService = cateService;
	}

	public List<Categorys> getListCate() {
		return listCate;
	}

	public void setListCate(List<Categorys> listCate) {
		this.listCate = listCate;
	}

	public List<Types> getListType() {
		return listType;
	}

	public void setListType(List<Types> listType) {
		this.listType = listType;
	}

	public List<Topics> getListTopic() {
		return listTopic;
	}

	public void setListTopic(List<Topics> listTopic) {
		this.listTopic = listTopic;
	}

	public Pages getPageBean() {
		return pageBean;
	}

	public void setPageBean(Pages pageBean) {
		this.pageBean = pageBean;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

}
