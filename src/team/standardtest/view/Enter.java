package team.standardtest.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import team.standardtest.control.*;
import team.standardtest.dao.*;

public class Enter extends JFrame implements ActionListener{
	
	/**登录界面的输入用户名的文本框*/
	JTextField jtf=new JTextField(10);
	/**登录界面输入密码的密码框*/
	JPasswordField jpf=new JPasswordField(10);
	/**登录界面的登录注册按钮*/
	JButton jb[]=new JButton[2];
	/**登录界面的学生教师单选按钮*/
	JRadioButton jrb[]=new JRadioButton[2];
	public Enter() {
		init();
		setTitle("登录窗口");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,400,500);
		setLayout(new FlowLayout());
	}
	private void init() {
		JPanel jp=new JPanel();
		Box basebox,box[]=new Box[4];
		for(int i=0;i<4;i++) {
			box[i]=Box.createHorizontalBox();
		}
		basebox=Box.createVerticalBox();
		JLabel jl[]=new JLabel[2];
		ButtonGroup bg=new ButtonGroup();
		jl[0]=new JLabel("用户名：");
		box[0].add(jl[0]);
		box[0].add(Box.createHorizontalStrut(10));
		box[0].add(jtf);
		jl[1]=new JLabel("密码：");
		box[1].add(jl[1]);
		box[1].add(Box.createHorizontalStrut(22));
		box[1].add(jpf);
		jrb[0]=new JRadioButton("学生");
		jrb[1]=new JRadioButton("教师");
		bg.add(jrb[0]);
		bg.add(jrb[1]);
		box[2].add(jrb[0]);
		box[2].add(Box.createHorizontalStrut(10));
		box[2].add(jrb[1]);
		jb[0]=new JButton("登录");
		jb[1]=new JButton("学生注册");
		box[3].add(jb[0]);
		box[3].add(Box.createHorizontalStrut(30));
		box[3].add(jb[1]);
		for(int i=0;i<4;i++) {
			basebox.add(Box.createVerticalStrut(50));
			basebox.add(box[i]);
		}
		jp.add(basebox);
		add(jp);
		jb[0].addActionListener(this);
		jb[1].addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		String id=jtf.getText();
		String password=String.valueOf(jpf.getPassword());
		String ident;
		
		if(e.getActionCommand()=="学生注册") {
			this.dispose();
			Register r=new Register();
		}
		if(e.getActionCommand()=="登录") {
			if(jrb[0].isSelected()) {
				Student stu=new Student();
				int n=stu.identification(id,password);
				if(n==0) {
					JOptionPane.showMessageDialog(this,"请检查用户名和密码是否正确！！！","登录失败",JOptionPane.DEFAULT_OPTION);
				}
				if(n==1) {
					JOptionPane.showMessageDialog(this,"欢迎使用本系统","登录成功",JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
					StudentControl sc=new StudentControl(id);
				}
			}
			if(jrb[1].isSelected()) {
				Teacher tea=new Teacher();
				int n=tea.identification(id,password);
				if(n==0) {
					JOptionPane.showMessageDialog(this,"请检查用户名和密码是否正确！！！","登录失败",JOptionPane.DEFAULT_OPTION);
				}
				if(n==1) {
					JOptionPane.showMessageDialog(this,"欢迎使用本系统","登录成功",JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
					TeacherControl tc=new TeacherControl(id);
				}
			}
			
		}
	}

	
}
