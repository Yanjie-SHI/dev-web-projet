package com.blog.dao;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blog.entity.User;

public class Db {


	/**
	 * insert
	 * @param sql
	 * @return
	 */
	public static boolean insertUser(String sql) {
		int executeQuery = 0;
		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();
			executeQuery = stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return executeQuery>0;
	}

	/**
	 * search
	 * @param sql
	 * @return
	 */
	public static List<User> select(String sql){
		
		List<User> list = new ArrayList<>();
		
		try {
			
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();
			ResultSet resultSet = stat.executeQuery(sql);
			
			while(resultSet.next()) {
				User u = new User();
				
				u.setId(resultSet.getInt("id"));
				u.setUserName(resultSet.getString("userName"));
				u.setPwd(resultSet.getString("pwd"));
				list.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * login
	 * @param sql
	 * @param args
	 * @return
	 */
	public static List<Map<String,String>> select(String sql,String[] args){
		
		List<Map<String,String>> list = new ArrayList<>();
		
		try {
			
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();
			ResultSet resultSet = stat.executeQuery(sql);
			
			while(resultSet.next()) {
				Map<String,String> result=new HashMap<>();
				for(String str : args) {
					result.put(str, resultSet.getString(str));
				}
				list.add(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**

	 * @param <T>
	 * @param sql
	 * @param cl
	 * @return
	 */
	public static <T> List<T> select(String sql,Class<T> cl){
		
		List<T> list = new ArrayList<>();
		
		try {
			
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();
			ResultSet resultSet = stat.executeQuery(sql);
			
			while(resultSet.next()) {
				T t = cl.newInstance();
				Field[] declaredFields = cl.getDeclaredFields();
				AccessibleObject.setAccessible(declaredFields, true);
				for(Field field : declaredFields) {
					field.set(t, resultSet.getObject(field.getName()));
				}
				list.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	/**
	 * get all
	 * @param sql
	 * @return
	 */
	public static int countAll(String sql){
		
		int count  = 0;
		
		try {
			
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();
			ResultSet resultSet = stat.executeQuery(sql);
			
			while(resultSet.next()) {
				count = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
