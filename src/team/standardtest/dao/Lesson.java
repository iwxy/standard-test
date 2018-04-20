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
			JOptionPane.showMessageDialog(null,"δ���ӵ�������","ʧ��",JOptionPane.ERROR_MESSAGE);
			System.exit(0);  //���������������
		}
	}
	
	/**
	 * ��ĳһ�γ̺�ת��Ϊ�γ���
	 * @return ����һ���γ���
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
	 * ��ĳһ�γ���ת��Ϊ�γ̺�
	 * @return ����һ���γ̺�
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
	 * ������ݿ����е����пγ���
	 * @return ����һ��String��һά����
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
