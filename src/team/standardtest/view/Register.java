package team.standardtest.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import team.standardtest.dao.*;

public class Register extends JFrame implements ActionListener{
	/**ע�����������û������ı���*/
	JTextField jtf=new JTextField(10);
	/**ע�����������������ı���*/
	JTextField jtf1=new JTextField(10);
	/**ע��������������������*/
	JPasswordField jpf[]=new JPasswordField[2];
	/**ע������ѡ��γ̵������б�*/
	JComboBox jcb=new JComboBox();
	/**ע������ע���ȡ����ť*/
	JButton jb[]=new JButton[2];
	Lesson lesson=new Lesson();
	public Register() {
		init();
		setTitle("ע�ᴰ��");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,400,500);
		setLayout(new FlowLayout());
	}
	private void init() {
		int n=lesson.getAllLename().length;
		jcb.addItem("");
		for(int i=0;i<n;i++) {
			jcb.addItem(lesson.getAllLename()[i]);   //�����ݿ������еĿγ̷��������б�
		}
		JPanel jp=new JPanel();
		JLabel jl[]=new JLabel[5];
		Box basebox,box[]=new Box[6];
		for(int i=0;i<6;i++) {
			box[i]=Box.createHorizontalBox();
		}
		basebox=Box.createVerticalBox();
		jl[0]=new JLabel("�û�����");
		box[0].add(jl[0]);
		box[0].add(Box.createHorizontalStrut(24));
		box[0].add(jtf);
		jl[1]=new JLabel("������");
		box[1].add(jl[1]);
		box[1].add(Box.createHorizontalStrut(36));
		box[1].add(jtf1);
		jl[2]=new JLabel("���룺");
		jpf[0]=new JPasswordField(10);
		box[2].add(jl[2]);
		box[2].add(Box.createHorizontalStrut(36));
		box[2].add(jpf[0]);
		jl[3]=new JLabel("ȷ�����룺");
		jpf[1]=new JPasswordField(10);
		box[3].add(jl[3]);
		box[3].add(Box.createHorizontalStrut(10));
		box[3].add(jpf[1]);
		jl[4]=new JLabel("�γ̣�");
		box[4].add(jl[4]);
		box[4].add(Box.createHorizontalStrut(10));
		box[4].add(jcb);
		jb[0]=new JButton("ע��");
		jb[1]=new JButton("ȡ��");
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
		if(e.getActionCommand()=="ȡ��") {
			this.dispose();
		}
		if(e.getActionCommand()=="ע��") {
			if(password.equals(apassword)) {
				Student stu=new Student();
				int n=stu.isExist(id);
				if(n==1) {
					JOptionPane.showConfirmDialog(this,"���û����Ѵ��ڣ�����","ע��ʧ��",JOptionPane.DEFAULT_OPTION);
				}
				else {
					String leid=lesson.getLessonId(lename);
					stu.insertStudent(id,name,apassword,leid);
					Score sco=new Score();
					sco.setOne(id,leid);
					JOptionPane.showMessageDialog(this,"��ӭʹ�ñ�ϵͳ��","ע��ɹ�",JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
					Enter enter=new Enter();
				}
			}
			else{
				JOptionPane.showConfirmDialog(this,"������������벻һ��������","ע��ʧ��",JOptionPane.DEFAULT_OPTION);
			}
		}
		
	}
}
