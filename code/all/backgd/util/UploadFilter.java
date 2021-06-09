/**
 * 
 */
package com.chen.util;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author chasonshi
 * @email chason0504@outlook.com
 */
public class UploadFilter implements Filter {

	public final static String DEFAULT_URI_ENCODE = "UTF-8"; //默认字符编码
   
	private FilterConfig config = null;   //FilterConfig 接口
	private String encode = null;  //编码方式

	//初始化
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		this.encode = config.getInitParameter("DEFAULT_URI_ENCODE");
		if (this.encode == null) {
			this.encode = DEFAULT_URI_ENCODE;
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req; //接收客户端请求
		String uri = request.getRequestURI();  // 获取URL
		String ch = URLDecoder.decode(uri, encode); //解析URL
		if (uri.equals(ch)) {
			chain.doFilter(req, res); // 转发下一个filter处理
			return;
		}
		//获取路径
		ch = ch.substring(request.getContextPath().length());
		config.getServletContext().getRequestDispatcher(ch).forward(req, res);//请求转发
	}

	@Override
	public void destroy() {
		config = null; //释放Filter 对象打开的资源
	}
}
