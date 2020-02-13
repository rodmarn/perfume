package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.org.apache.regexp.internal.recompile;

import entity.Items;
import util.DBHelper;

public class ItemsDAO {
	
	
	// 一、获得所有的商品信息
		public ArrayList<Items> getAllItems() {
			Connection conn = null;// 连接对象
			PreparedStatement stmt = null;// 语句对象
			ResultSet rs = null;// 数据集
			ArrayList<Items> list = new ArrayList<Items>();// 商品集合
			try {
				conn = DBHelper.getConnection();// 获得一个连接对象
				String sql = "select * from perfume;"; // SQL语句，从perfume表中获得所有的商品信息
				stmt = conn.prepareStatement(sql);// 将该sql语句传进去
				rs = stmt.executeQuery();// 获得一个数据集
				while (rs.next()) {
					Items item = new Items();
					item.setId(rs.getInt("id"));
					item.setName(rs.getString("name"));
					item.setEngname(rs.getString("engname"));
					item.setBrand(rs.getString("brand"));
					item.setPrice(rs.getDouble("price"));
					item.setPhoto(rs.getString("photo"));
					item.setDesc(rs.getString("desc"));
					item.setStock(rs.getInt("stock"));
					list.add(item);// 每次遍历时，把一个商品信息加到集合里面
				}
				return list;// 返回集合
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			} finally // 从小到大释放
			{
				// 释放数据集对象
				if (rs != null) {
					try {
						rs.close();
						rs = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				// 释放语句对象
				if (stmt != null) {
					try {
						stmt.close();
						stmt = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				// 连接对象conn不释放，因为是单例模式，如果释放了后续则无法使用
			}

		}

		// 二、根据商品编号获得商品资料
		public Items getItemsById(int id) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = DBHelper.getConnection();
				String sql = "select * from perfume where id="+id+";"; // SQL语句，根据id号来选择商品
				stmt = conn.prepareStatement(sql);
				//stmt.setInt(1, id);// 接受一个参数id,是从1开始的
				rs = stmt.executeQuery();
				// 每次获得一条记录，所以用if循环而不用while
				if (rs.next()) {
					Items item = new Items();
					item.setId(rs.getInt("id"));
					item.setName(rs.getString("name"));
					item.setEngname(rs.getString("engname"));
					item.setBrand(rs.getString("brand"));
					item.setPrice(rs.getDouble("price"));
					item.setPhoto(rs.getString("photo"));
					item.setDesc(rs.getString("desc"));
					item.setStock(rs.getInt("stock"));
					return item;
				} 
				else {
					return null;//没有找到该编号的商品则返回空
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			} finally // 从小到大释放
			{
				// 释放数据集对象
				if (rs != null) {
					try {
						rs.close();
						rs = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				// 释放语句对象
				if (stmt != null) {
					try {
						stmt.close();
						stmt = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}
		
		public static void main(String[] args) {
			ItemsDAO itemsDAO=new ItemsDAO();
			Items items=itemsDAO.getItemsById(10);
			System.out.println(items.getName());
		}
}
