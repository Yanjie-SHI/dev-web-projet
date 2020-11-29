package com.blog.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.blog.dao.Db;
//登录操作控制器
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String user = request.getParameter("username");
		String pwd = request.getParameter("password");
		System.out.println(user + "  " + pwd);
		String sql = "select * from t_user where userName='"+user+"' and pwd='"+pwd+"'";
		List<Map<String,String>> list = Db.select(sql, new String[] {"id","userName","pwd"});
		System.out.println(list);
		String msg="";
		if(list.size()>0) {

			String json = "{\"code\":\"200\",\"msg\":\""+user+"!success\"}";
			request.getSession().setAttribute("userid", list.get(0).get("id"));
			response.getWriter().print(json);

		}else {

			String json = "{\"code\":\"500\",\"msg\":\""+user+"!failed\"}";
			response.getWriter().print(json);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
