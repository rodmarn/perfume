package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
	private static final String driver="com.mysql.jdbc.Driver";//���ݿ�����
	private static final String url="jdbc:mysql://106.14.209.83:3306/perfume";
	private static final String username="perfume_reader";//���ݿ���û���
	private static final String password="perfume@123";//���ݿ������
	
	private static Connection conn=null;//����˽�����͵����Ӷ���
	
	//��̬����鸺���������
	static
	{
		try
		{
			Class.forName(driver);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//����ģʽ�������ݿ����Ӷ���
	public static Connection getConnection() throws Exception
	{
		if(conn==null)
		{
			conn=DriverManager.getConnection(url, username, password);
			return conn;
		}
		return conn;
	}
	
	public static void main(String[] args) {
		try
		{
			Connection connection=DBHelper.getConnection();
			if(conn!=null)
			{
				System.out.println("���ݿ�����������");
			}
			else
			{
				System.out.println("���ݿ������쳣��");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace(); 
		}
	}
}