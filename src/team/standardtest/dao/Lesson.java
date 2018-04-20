package team.standardtest.dao;

import java.sql.*;

import javax.swing.JOptionPane;


public class Lesson {
	Connection con;
	Statement sta;
	ResultSet rs;
	String SQL;
	public Lesson(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null,"未连接到服务器","失败",JOptionPane.ERROR_MESSAGE);
			System.exit(0);  //如果报错，结束运行
		}
	}
	
	/**
	 * 将某一课程号转变为课程名
	 * @return 返回一个课程名
	 * */
	public String getLessonName(String leid) {
		String name="";
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			SQL="select lename from lesson where leid="+leid; 
			rs=sta.executeQuery(SQL);
			while(rs.next()) {
				name=rs.getString(1);
			}
			con.close();
		}catch(SQLException e){
			e.getMessage();
		}
		return name;
	}
	
	
	/**
	 * 将某一课程名转变为课程号
	 * @return 返回一个课程号
	 * */
	public String getLessonId(String lename) {
		String id="";
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			SQL="select leid from lesson where lename='"+lename+"'";
			rs=sta.executeQuery(SQL);
			while(rs.next()) {
				id=String.valueOf(rs.getInt(1));
			}
			con.close();
		}catch(SQLException e){
			e.getMessage();
		}
		return id;
	}
	
	
	/**
	 * 获得数据库中有的所有课程名
	 * @return 返回一个String型一维数组
	 * */
	public String[] getAllLename() {
		String name[];
		String s[]=new String[1];
		try {
		con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
		sta=con.createStatement();
		SQL="select * from lesson";
		rs=sta.executeQuery(SQL);
		rs.last();
		int row=rs.getRow();
		rs.beforeFirst();
		name=new String[row];
		int i=0;
		while(rs.next()) {
			name[i]=rs.getString(2);
			i++;
		}
		con.close();
		return name;
		}catch(SQLException e){
			e.getMessage();
		}
		return s;
	}

	
}
