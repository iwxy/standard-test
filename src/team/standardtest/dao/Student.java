package team.standardtest.dao;

import java.sql.*;

import javax.swing.JOptionPane;
public class Student {
	Connection con;
	Statement sta;
	ResultSet rs;
	String SQL;
	public Student(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null,"未连接到服务器","失败",JOptionPane.ERROR_MESSAGE);
			System.exit(0);  //如果报错，结束运行
		}
	}

	/**
	 * 判断该学生是否存在，
	 * @return 0或1 ；若存在返回1，不存在返回0
	 * @id 参数id表示学生的id
	 * @password 参数password表示学生的密码
	 * */
	public int identification(String id,String password){
		
		String turepassword="";  //该学生在数据库中的名字
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			SQL="select * from student where sid="+id;  //获取该学生的信息
			rs=sta.executeQuery(SQL);
			while(rs.next()){
				turepassword=rs.getString(3);  //获取该学生在数据库中的名字
			}
			con.close();
			if(password.equals(turepassword)){  //判断该学生输入的名字与数据库中的名字是否相同
				return 1;               //相同返回1
			}
			else{
				return 0;               //不相同返回0
			}
			
		}catch(SQLException e){
			e.getMessage();
		}
		return 0;
	}
	
	
	/**
	 * 判断该学生的用户名是否存在，
	 * @return 0或1 ；若存在返回1，不存在返回0
	 * @id 参数id表示学生的id
	 * */
	public int isExist(String id){
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			SQL="select * from student";  //获取该学生的信息
			rs=sta.executeQuery(SQL);
			while(rs.next()){
				if(id.equals(rs.getString(1))) {
					return 1;
				}
			}
			con.close();
			
		}catch(SQLException e){
			e.getMessage();
		}
		return 0;
	}
	
	
	/**
	 * 插入一个学生的记录
	 * @id 参数id为该学生的账号
	 * @name 参数name为该学生的名字
	 * @password 参数password为该学生的密码
	 * @leid 参数leid为该学生选的课程号
	 * */
	public void insertStudent(String id,String name,String password,String leid) {
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			SQL="insert into student values("+Integer.parseInt(id)+",'"+name+"',"+Integer.parseInt(password)+")"; 
			sta.executeUpdate(SQL);
		}catch(SQLException e){
			e.getMessage();
		}
	}
	
}