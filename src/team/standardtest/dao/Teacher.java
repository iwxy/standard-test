package team.standardtest.dao;

import java.sql.*;

import javax.swing.JOptionPane;

public class Teacher {
	
	/**
	 * �÷�������ȷ�Ͻ�ʦ��ݣ�
	 * @id ����id������ʦ���˺�
	 * @password ����password������ʦ������
	 * @return 0��1�����ý�ʦ�����ڣ����û����������������
	 * ����0������ȷ�򷵻�1��
	 * */
	public int identification(String id,String password){
		Connection con;
		Statement sta;
		ResultSet rs;
		
		/**
		 * String��SQL����sql���
		 */
		String SQL;
		
		/**@turepassword String��turepassword�Ǵ����ݿ���ȡ��������ȷ����*/
		String turepassword="";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/standardexam","root","1234");
			sta=con.createStatement();
			SQL="select * from teacher where tid="+id;  //��ѯ����ʦ����Ϣ  
			rs=sta.executeQuery(SQL);
			while(rs.next()){
				turepassword=rs.getString(3);  //��ȡ����ʦ������
			}
			con.close();
			if(password.equals(turepassword)){  //�жϸ���ʦ��������������ݿ��е��Ƿ���ͬ
				return 1;                       //��ͬ����1
			}
			else{
				return 0;                       //����ͬ����0
			}
		}
		catch(SQLException e){
			e.getMessage();
		}
		catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null,"δ���ӵ�������","ʧ��",JOptionPane.ERROR_MESSAGE);
			System.exit(0);  //���������������
		}
		return 0;
	}
	
}
