package team.standardtest.dao;

import java.sql.*;

import javax.swing.JOptionPane;

public class Teacher {
	
	/**
	 * 该方法用于确认教师身份，
	 * @id 参数id代表老师的账号
	 * @password 参数password代表老师的密码
	 * @return 0或1；若该教师不存在，即用户名或密码输入错误，
	 * 返回0；若正确则返回1；
	 * */
	public int identification(String id,String password){
		Connection con;
		Statement sta;
		ResultSet rs;
		
		/**
		 * String型SQL代表sql语句
		 */
		String SQL;
		
		/**@turepassword String型turepassword是从数据库中取出来的正确密码*/
		String turepassword="";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			SQL="select * from teacher where tid="+id;  //查询该老师的信息  
			rs=sta.executeQuery(SQL);
			while(rs.next()){
				turepassword=rs.getString(3);  //获取该老师的密码
			}
			con.close();
			if(password.equals(turepassword)){  //判断该老师输入的密码与数据库中的是否相同
				return 1;                       //相同返回1
			}
			else{
				return 0;                       //不相同返回0
			}
		}
		catch(SQLException e){
			e.getMessage();
		}
		catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null,"未连接到服务器","失败",JOptionPane.ERROR_MESSAGE);
			System.exit(0);  //如果报错，结束运行
		}
		return 0;
	}
	
}
