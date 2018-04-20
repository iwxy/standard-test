package team.standardtest.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import team.standardtest.dao.*;

public class Register extends JFrame implements ActionListener{
	/**注册界面的输入用户名的文本框*/
	JTextField jtf=new JTextField(10);
	/**注册界面的输入姓名的文本框*/
	JTextField jtf1=new JTextField(10);
	/**注册界面的输入密码的密码框*/
	JPasswordField jpf[]=new JPasswordField[2];
	/**注册界面的选择课程的下拉列表*/
	JComboBox jcb=new JComboBox();
	/**注册界面的注册和取消按钮*/
	JButton jb[]=new JButton[2];
	Lesson lesson=new Lesson();
	public Register() {
		init();
		setTitle("注册窗口");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,400,500);
		setLayout(new FlowLayout());
	}
	private void init() {
		int n=lesson.getAllLename().length;
		jcb.addItem("");
		for(int i=0;i<n;i++) {
			jcb.addItem(lesson.getAllLename()[i]);   //将数据库中所有的课程放入下拉列表
		}
		JPanel jp=new JPanel();
		JLabel jl[]=new JLabel[5];
		Box basebox,box[]=new Box[6];
		for(int i=0;i<6;i++) {
			box[i]=Box.createHorizontalBox();
		}
		basebox=Box.createVerticalBox();
		jl[0]=new JLabel("用户名：");
		box[0].add(jl[0]);
		box[0].add(Box.createHorizontalStrut(24));
		box[0].add(jtf);
		jl[1]=new JLabel("姓名：");
		box[1].add(jl[1]);
		box[1].add(Box.createHorizontalStrut(36));
		box[1].add(jtf1);
		jl[2]=new JLabel("密码：");
		jpf[0]=new JPasswordField(10);
		box[2].add(jl[2]);
		box[2].add(Box.createHorizontalStrut(36));
		box[2].add(jpf[0]);
		jl[3]=new JLabel("确认密码：");
		jpf[1]=new JPasswordField(10);
		box[3].add(jl[3]);
		box[3].add(Box.createHorizontalStrut(10));
		box[3].add(jpf[1]);
		jl[4]=new JLabel("课程：");
		box[4].add(jl[4]);
		box[4].add(Box.createHorizontalStrut(10));
		box[4].add(jcb);
		jb[0]=new JButton("注册");
		jb[1]=new JButton("取消");
		box[5].add(jb[0]);
		box[5].add(Box.createHorizontalStrut(50));
		box[5].add(jb[1]);
		for(int i=0;i<5;i++) {
			basebox.add(Box.createVerticalStrut(30));
			basebox.add(box[i]);
		}
		basebox.add(Box.createVerticalStrut(60));
		basebox.add(box[5]);
		jp.add(basebox);
		add(jp);
		jb[0].addActionListener(this);
		jb[1].addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		String id=jtf.getText();
		String name=jtf1.getText();
		String password=String.valueOf(jpf[0].getPassword());
		String apassword=String.valueOf(jpf[1].getPassword());
		String lename=String.valueOf(jcb.getSelectedItem());
		if(e.getActionCommand()=="取消") {
			this.dispose();
		}
		if(e.getActionCommand()=="注册") {
			if(password.equals(apassword)) {
				Student stu=new Student();
				int n=stu.isExist(id);
				if(n==1) {
					JOptionPane.showConfirmDialog(this,"该用户名已存在！！！","注册失败",JOptionPane.DEFAULT_OPTION);
				}
				else {
					String leid=lesson.getLessonId(lename);
					stu.insertStudent(id,name,apassword,leid);
					Score sco=new Score();
					sco.setOne(id,leid);
					JOptionPane.showMessageDialog(this,"欢迎使用本系统！","注册成功",JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
					Enter enter=new Enter();
				}
			}
			else{
				JOptionPane.showConfirmDialog(this,"两遍输入的密码不一样！！！","注册失败",JOptionPane.DEFAULT_OPTION);
			}
		}
		
	}
}
