/**
 * 
 */
package com.chen.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * @author chasonshi
 * @email chason0504@outlook.com
 */
public class ResponseUtil {
	public static void write(HttpServletResponse response,Object obj) throws IOException{
		response.setContentType("text/html;charset=utf-8"); //规定文本类型 字符集类型
		//写入响应内容
		PrintWriter out = response.getWriter();
		out.print(obj.toString());
		out.flush();
		out.close();
	}

}
