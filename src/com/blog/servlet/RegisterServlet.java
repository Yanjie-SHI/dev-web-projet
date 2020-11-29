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
//注册操作控制器
/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String user = request.getParameter("username");
		String pwd = request.getParameter("password");
		System.out.println(user + "  " + pwd);
		
		String sql = "select id,userName from t_user where userName='"+user+"'";
		
		List<Map<String,String>> list = Db.select(sql,new String[] {"id"});
		String msg = "";
		if(list.size()<=0) {
			msg +="available";
			String sqlInsert = "insert into t_user(userName,pwd) values ('"+user+"','"+pwd+"')";
			System.out.println(sqlInsert);
			boolean insert = Db.insertUser(sqlInsert);
			if(insert) {

				String json = "{\"code\":\"200\",\"msg\":\""+user+"!success\"}";
				response.getWriter().write(json);
			}
		}else {

			String json = "{\"code\":\"500\",\"msg\":\"user name not available!\"}";
			response.getWriter().write(json);
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
