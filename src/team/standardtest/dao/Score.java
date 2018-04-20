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
			JOptionPane.showMessageDialog(null,"未连接到服务器","失败",JOptionPane.ERROR_MESSAGE);
			System.exit(0);  //如果报错，结束运行
		}
	}

	
	/**
	 * 输入该学生的考试成绩；
	 * @id 参数id为学生的账号
	 * @les 参数les为课程号
	 * @x int型参数x代表考试成绩
	 * */
	public void setScore(String id,String les,int x){
		try{
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();			
			SQL="update score set score="+x+" where sid="+id+" and leid="+les;  //将学生本次考试的分数插入数据库中
			sta.executeUpdate(SQL);
			con.commit();
			con.close();
		}
		catch(SQLException e){
			e.getMessage();
		}
	}
	
	/**
	 * 该学生有哪些课程
	 * @id 学生的账号
	 * @return 返回一个存有该学生课程名的一维数组
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
	 * 该方法用于查看某个课程的成绩，int型参数a控制是否要按成绩排序，
	 * @a 值为0或1；a为1时，从高到低显示学生姓名和成绩；
	 * a为0时，按学号顺序显示学生姓名和成绩；
	 * @les 参数les为课程号
	 * @return String[][]的数组，二维数组中存着学生的姓名和分数
	 * */
	public String[][] getScore(int a,String les){
		String score[][];
		String s[][]=new String[3][3];
		if(a==1){
			//按分数从高到低排序
			SQL="select student.sname,score.score from student,score where student.sid=score.sid and score.leid="+les+" order by score.score desc";
		}
		if(a==0){	
			//按学生从小到大排序
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
	 * 获得该学生该科目的考试次数
	 * @id 学生的账号
	 * @les 课程号
	 * @return 返回int型的该学生的考试次数
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
	 * 改变学生该科目的考试次数
	 * @id 学生的账号
	 * @les 课程号
	 * @times 参数times为要输入的考试次数
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
		 * 插入一个学生和某个课程的一条记录
		 * @sid 参数sid为学号
		 * @leid 参数leid为课程号
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
		 * 将该课程所有学生的考试次数改为零
		 * @leid 参数leid为课程号
		 * */
		public void setScoreZero(String leid){
			try{
				con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
				sta=con.createStatement();			
				SQL="update score set times=0 where leid="+leid;  //将该课程所有学生的考试次数变为零
				sta.executeUpdate(SQL);
				con.commit();
				con.close();
			}
			catch(SQLException e){
				e.getMessage();
			}
		}
}


