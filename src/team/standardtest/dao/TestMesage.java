package team.standardtest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.xml.crypto.Data;

public class TestMesage {
	Connection con;
	Statement sta;
	ResultSet rs;
	String SQL;
	
	public TestMesage(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null,"未连接到服务器","失败",JOptionPane.ERROR_MESSAGE);
			System.exit(0);  //如果报错，结束运行
		}
	}
	
	/**
	 * 更新某课程是否考试
	 * @leid 参数leid为课程号
	 * @is 参数is为是否考试，1为考试，0为不考试
	 * */
	public void updateIstest(String leid,String is) {
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			SQL="update test set istest="+is+" where leid="+leid;
			sta.executeUpdate(SQL);
		}catch(SQLException e){
			e.getMessage();
		}
	}
	
	
	/**
	 * 更新某课程考试的题目数量
	 * @leid 参数leid为课程号
	 * @num 参数num为题量
	 * */
	public void updateNum(String leid,String num) {
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			SQL="update test set num="+num+" where leid="+leid;
			sta.executeUpdate(SQL);
		}catch(SQLException e){
			e.getMessage();
		}
	}
	
	
	/**
	 * 更新某课程每题考试的时间
	 * @leid 参数leid为课程号
	 * @time 参数time为每道题的时间
	 * @num 参数num为题量
	 * */
	public void updateDuration(String leid,String time,String num) {
		int duration=Integer.parseInt(time)*Integer.parseInt(num);
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			SQL="update test set duration="+duration+" where leid="+leid;
			sta.executeUpdate(SQL);
		}catch(SQLException e){
			e.getMessage();
		}
	}
	
	
	/**
	 * 更新某课程的考试最多次数
	 * @leid 参数leid为课程号
	 * @n 参数n为教师设置的考试次数
	 * */
	public void updateTimes(String leid,String n) {
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			SQL="update test set times="+n+" where leid="+leid;
			sta.executeUpdate(SQL);
		}catch(SQLException e){
			e.getMessage();
		}
	}
	
	/**
	 * 更新某课程的考试文件名
	 * @leid 参数leid为课程号
	 * @n 参数file为教师设置的考试次数
	 * */
	public void updateFile(String leid,String file) {
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			SQL="update test set file='"+file+"' where leid="+leid;
			sta.executeUpdate(SQL);
		}catch(SQLException e){
			e.getMessage();
		}
	}
	
	
	/**
	 * 该方法用于获取test表的内容
	 * @leid 参数leid为课程号
	 * @return 返回一个长度为5的数组,第一个为是否考试，第二个为题量，第三个为考试时长，第四个为最多考试次数，第五个为考试文件
	 * */
	public String[] getTestMesage(String leid) {
		String mesage[]=new String[5];
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			SQL="select * from test where leid="+leid;
			rs=sta.executeQuery(SQL);
			while(rs.next()){
				for(int i=0;i<mesage.length-1;i++) {
					mesage[i]=String.valueOf(rs.getInt(i+2));
//					System.out.println(mesage[i]);
				}
				mesage[4]=rs.getString(6);
			}
			
			con.close();
		}catch(SQLException e){
			e.getMessage();
		}
		return mesage;
	}
}
