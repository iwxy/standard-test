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
			JOptionPane.showMessageDialog(null,"δ���ӵ�������","ʧ��",JOptionPane.ERROR_MESSAGE);
			System.exit(0);  //���������������
		}
	}
	
	/**
	 * ����ĳ�γ��Ƿ���
	 * @leid ����leidΪ�γ̺�
	 * @is ����isΪ�Ƿ��ԣ�1Ϊ���ԣ�0Ϊ������
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
	 * ����ĳ�γ̿��Ե���Ŀ����
	 * @leid ����leidΪ�γ̺�
	 * @num ����numΪ����
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
	 * ����ĳ�γ�ÿ�⿼�Ե�ʱ��
	 * @leid ����leidΪ�γ̺�
	 * @time ����timeΪÿ�����ʱ��
	 * @num ����numΪ����
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
	 * ����ĳ�γ̵Ŀ���������
	 * @leid ����leidΪ�γ̺�
	 * @n ����nΪ��ʦ���õĿ��Դ���
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
	 * ����ĳ�γ̵Ŀ����ļ���
	 * @leid ����leidΪ�γ̺�
	 * @n ����fileΪ��ʦ���õĿ��Դ���
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
	 * �÷������ڻ�ȡtest�������
	 * @leid ����leidΪ�γ̺�
	 * @return ����һ������Ϊ5������,��һ��Ϊ�Ƿ��ԣ��ڶ���Ϊ������������Ϊ����ʱ�������ĸ�Ϊ��࿼�Դ����������Ϊ�����ļ�
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
