package team.standardtest.dao;

import java.sql.*;

import javax.swing.JOptionPane;

public class Score {
	Connection con;
	Statement sta;
	ResultSet rs;
	String SQL;
	public Score(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null,"δ���ӵ�������","ʧ��",JOptionPane.ERROR_MESSAGE);
			System.exit(0);  //���������������
		}
	}

	
	/**
	 * �����ѧ���Ŀ��Գɼ���
	 * @id ����idΪѧ�����˺�
	 * @les ����lesΪ�γ̺�
	 * @x int�Ͳ���x�����Գɼ�
	 * */
	public void setScore(String id,String les,int x){
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();			
			SQL="update score set score="+x+" where sid="+id+" and leid="+les;  //��ѧ�����ο��Եķ����������ݿ���
			sta.executeUpdate(SQL);
			con.commit();
			con.close();
		}
		catch(SQLException e){
			e.getMessage();
		}
	}
	
	/**
	 * ��ѧ������Щ�γ�
	 * @id ѧ�����˺�
	 * @return ����һ�����и�ѧ���γ�����һά����
	 * */
	public String[] getLesson(String id){
		String lesson[];
		String s[]=new String[1];
		int row;
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();			
			SQL="select lesson.lename from lesson,score where lesson.leid=score.leid and score.sid="+id;
			rs=sta.executeQuery(SQL);
			rs.last();
			row=rs.getRow();
			lesson=new String[row];
			rs.beforeFirst();
			int i=0;
			while(rs.next()) {
				lesson[i]=rs.getString(1);
				i++;
			}
			con.close();
			return lesson;
		}
		catch(SQLException e){
			e.getMessage();
		}
		return s;
	}
	
	/**
	 * �÷������ڲ鿴ĳ���γ̵ĳɼ���int�Ͳ���a�����Ƿ�Ҫ���ɼ�����
	 * @a ֵΪ0��1��aΪ1ʱ���Ӹߵ�����ʾѧ�������ͳɼ���
	 * aΪ0ʱ����ѧ��˳����ʾѧ�������ͳɼ���
	 * @les ����lesΪ�γ̺�
	 * @return String[][]�����飬��ά�����д���ѧ���������ͷ���
	 * */
	public String[][] getScore(int a,String les){
		String score[][];
		String s[][]=new String[3][3];
		if(a==1){
			//�������Ӹߵ�������
			SQL="select student.sname,score.score from student,score where student.sid=score.sid and score.leid="+les+" order by score.score desc";
		}
		if(a==0){	
			//��ѧ����С��������
			SQL="select student.sname,score.score from student,score where student.sid=score.sid and score.leid="+les+" order by score.sid";
		}
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			rs=sta.executeQuery(SQL);
			rs.last();
			int row=rs.getRow();
			rs.beforeFirst();
			score=new String[row][2];
			int i=0;
			while(rs.next()){             
				score[i][0]=rs.getString(1);
				score[i][1]=String.valueOf(rs.getInt(2));
				i++;
			}
			con.close();
			
			return score;
		}catch(SQLException e){
			e.getMessage();
		}
		return s;
		
	}
	
	/**
	 * ��ø�ѧ���ÿ�Ŀ�Ŀ��Դ���
	 * @id ѧ�����˺�
	 * @les �γ̺�
	 * @return ����int�͵ĸ�ѧ���Ŀ��Դ���
	 * */
	public int getTimes(String id,String les) {
		int n=-1;
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();			
			SQL="select times from score where sid="+id+" and leid="+les;
			rs=sta.executeQuery(SQL);
			while(rs.next()) {
				n=rs.getInt(1);
			}
			con.close();
		}
		catch(SQLException e){
			e.getMessage();
		}
		return n;
	}
	
	/**
	 * �ı�ѧ���ÿ�Ŀ�Ŀ��Դ���
	 * @id ѧ�����˺�
	 * @les �γ̺�
	 * @times ����timesΪҪ����Ŀ��Դ���
	 * */
	public void setTimes(String id,String les,int times) {
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();			
			SQL="update score set times="+times+" where sid="+id+" and leid="+les;
			sta.executeUpdate(SQL);
			con.close();
		}
		catch(SQLException e){
			e.getMessage();
		}
	}
		
		
		/**
		 * ����һ��ѧ����ĳ���γ̵�һ����¼
		 * @sid ����sidΪѧ��
		 * @leid ����leidΪ�γ̺�
		 * */
		public void setOne(String sid,String leid) {
			try{
				con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
				sta=con.createStatement();			
				SQL="insert into score values("+sid+","+leid+",0,0)";
				sta.executeUpdate(SQL);
				con.close();
			}
			catch(SQLException e){
				e.getMessage();
			}
			
		}
		
		/**
		 * ���ÿγ�����ѧ���Ŀ��Դ�����Ϊ��
		 * @leid ����leidΪ�γ̺�
		 * */
		public void setScoreZero(String leid){
			try{
				con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
				sta=con.createStatement();			
				SQL="update score set times=0 where leid="+leid;  //���ÿγ�����ѧ���Ŀ��Դ�����Ϊ��
				sta.executeUpdate(SQL);
				con.commit();
				con.close();
			}
			catch(SQLException e){
				e.getMessage();
			}
		}
}


