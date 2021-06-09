/**
 * 
 */
package com.chen.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.chen.bean.Users;

/**
 * 用于超级管理员和其他用户的过滤器 用于限制其他用户的功能
 */
public class SuperAdminInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	@Override
	public String doIntercept(ActionInvocation invocation) throws Exception {
		ActionContext actx = invocation.getInvocationContext();
		Map session = actx.getSession();
		Users u = (Users) session.get("tu");
		if (u == null || u.getRoleId() == 0 || u.getRoleId() == 6) {
			return Action.LOGIN;
		}
		return invocation.invoke();
	}

}
