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
			JOptionPane.showMessageDialog(null,"δ���ӵ�������","ʧ��",JOptionPane.ERROR_MESSAGE);
			System.exit(0);  //���������������
		}
	}

	/**
	 * �жϸ�ѧ���Ƿ���ڣ�
	 * @return 0��1 �������ڷ���1�������ڷ���0
	 * @id ����id��ʾѧ����id
	 * @password ����password��ʾѧ��������
	 * */
	public int identification(String id,String password){
		
		String turepassword="";  //��ѧ�������ݿ��е�����
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			SQL="select * from student where sid="+id;  //��ȡ��ѧ������Ϣ
			rs=sta.executeQuery(SQL);
			while(rs.next()){
				turepassword=rs.getString(3);  //��ȡ��ѧ�������ݿ��е�����
			}
			con.close();
			if(password.equals(turepassword)){  //�жϸ�ѧ����������������ݿ��е������Ƿ���ͬ
				return 1;               //��ͬ����1
			}
			else{
				return 0;               //����ͬ����0
			}
			
		}catch(SQLException e){
			e.getMessage();
		}
		return 0;
	}
	
	
	/**
	 * �жϸ�ѧ�����û����Ƿ���ڣ�
	 * @return 0��1 �������ڷ���1�������ڷ���0
	 * @id ����id��ʾѧ����id
	 * */
	public int isExist(String id){
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			SQL="select * from student";  //��ȡ��ѧ������Ϣ
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
	 * ����һ��ѧ���ļ�¼
	 * @id ����idΪ��ѧ�����˺�
	 * @name ����nameΪ��ѧ��������
	 * @password ����passwordΪ��ѧ��������
	 * @leid ����leidΪ��ѧ��ѡ�Ŀγ̺�
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