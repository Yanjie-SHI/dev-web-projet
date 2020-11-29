package com.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//删除操作
public class Delete {
	/**
	 * delete
	 * @param sql
	 * @return
	 */
	public static boolean DeleteBlog(String sql) {
		int executeUpdate=0;
		try {
			Connection conn = DbConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			executeUpdate = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return executeUpdate>0;
	}
}
