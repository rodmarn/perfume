package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.org.apache.regexp.internal.recompile;

import entity.Items;
import util.DBHelper;

public class ItemsDAO {
	
	
	// һ��������е���Ʒ��Ϣ
		public ArrayList<Items> getAllItems() {
			Connection conn = null;// ���Ӷ���
			PreparedStatement stmt = null;// ������
			ResultSet rs = null;// ���ݼ�
			ArrayList<Items> list = new ArrayList<Items>();// ��Ʒ����
			try {
				conn = DBHelper.getConnection();// ���һ�����Ӷ���
				String sql = "select * from perfume;"; // SQL��䣬��perfume���л�����е���Ʒ��Ϣ
				stmt = conn.prepareStatement(sql);// ����sql��䴫��ȥ
				rs = stmt.executeQuery();// ���һ�����ݼ�
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
					list.add(item);// ÿ�α���ʱ����һ����Ʒ��Ϣ�ӵ���������
				}
				return list;// ���ؼ���
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			} finally // ��С�����ͷ�
			{
				// �ͷ����ݼ�����
				if (rs != null) {
					try {
						rs.close();
						rs = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				// �ͷ�������
				if (stmt != null) {
					try {
						stmt.close();
						stmt = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
				// ���Ӷ���conn���ͷţ���Ϊ�ǵ���ģʽ������ͷ��˺������޷�ʹ��
			}

		}

		// ����������Ʒ��Ż����Ʒ����
		public Items getItemsById(int id) {
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			try {
				conn = DBHelper.getConnection();
				String sql = "select * from perfume where id="+id+";"; // SQL��䣬����id����ѡ����Ʒ
				stmt = conn.prepareStatement(sql);
				//stmt.setInt(1, id);// ����һ������id,�Ǵ�1��ʼ��
				rs = stmt.executeQuery();
				// ÿ�λ��һ����¼��������ifѭ��������while
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
					return null;//û���ҵ��ñ�ŵ���Ʒ�򷵻ؿ�
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				return null;
			} finally // ��С�����ͷ�
			{
				// �ͷ����ݼ�����
				if (rs != null) {
					try {
						rs.close();
						rs = null;
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				// �ͷ�������
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
