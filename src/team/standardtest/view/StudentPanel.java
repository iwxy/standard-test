package team.standardtest.view;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;
import team.standardtest.dao.Lesson;
import team.standardtest.dao.Score;
import team.standardtest.dao.TestMesage;

public class StudentPanel {
	
	/**可选课程的单选按钮*/
	public JRadioButton studentlesson_jrb[];
	/**学生选择课程的面板*/
	public JPanel studentlesson_jp=new JPanel();
	/**添加课程的下拉列表*/
	public JComboBox studentlesson_jcb=new JComboBox();
	/**
	 * 学生选择考试的课程的面板,可选的课程数量随该学生有多少课程变化
	 * @lesson 参数lesson[]代表改学生有的所有课程
	 * */
	public void studentLessonP(String lesson[]) {
		int n=lesson.length;     //该学生有多少个课程
		Box basebox,box[]=new Box[n+2];
		studentlesson_jrb=new JRadioButton[n];
		ButtonGroup bg=new ButtonGroup();
		for(int i=0;i<n+2;i++) {
			if(i<n) {
				studentlesson_jrb[i]=new JRadioButton(lesson[i]);   //将所有课程名放入单选按钮
				bg.add(studentlesson_jrb[i]);
			}
			box[i]=Box.createHorizontalBox();      //实例化盒子,盒子数量比课程数量多两个
		}
		JLabel studentlesson_jl=new JLabel("选择课程");
		JLabel studentlesson_jl1=new JLabel("添加课程");
		studentlesson_jcb.addItem("    ");      //下拉列表第一个为空
		Lesson le=new Lesson();
		int x=le.getAllLename().length;
		for(int i=0;i<x;i++) {
			studentlesson_jcb.addItem(le.getAllLename()[i]);
		}
		studentlesson_jl.setFont(new Font("Serif",Font.PLAIN,25));   //设置标签的字体
		box[0].add(studentlesson_jl);
		for(int i=1;i<n+1;i++) {
			box[i].add(studentlesson_jrb[i-1]);
		}
		box[n+1].add(studentlesson_jl1);
		box[n+1].add(Box.createHorizontalStrut(20));
		box[n+1].add(studentlesson_jcb);
		basebox=Box.createVerticalBox();
		basebox.add(Box.createVerticalStrut(20));
		basebox.add(box[0]);
		basebox.add(Box.createVerticalStrut(30));
		for(int i=1;i<n+1;i++) {
			basebox.add(Box.createVerticalStrut(20));
			basebox.add(box[i]);
		}
		basebox.add(Box.createVerticalStrut(80));
		basebox.add(box[n+1]);
		studentlesson_jp.add(basebox);
	}
	
	
	/**学生考试面板的按钮*/
	public JButton test_jb[]=new JButton[4];
	/**ABCD的单选按钮*/
	public JRadioButton test_jrb[]=new JRadioButton[4];
	/**显示题号的文本框*/
	public JTextField test_jtf=new JTextField(5);
	/**显示时间的文本框*/
	public JTextField test_time=new JTextField(10);
	/**学生考试的面板*/
	public JPanel test_jp=new JPanel();
	public JLabel test_jl1;
	
	/**
	 * 学生考试的面板
	 * @question 参数question表示题目
	 * @A 参数A表示A选项
	 * @B 参数B表示B选项
	 * @C 参数C表示C选项
	 * @D 参数D表示D选项
	 * */
	public void test(String question,String A,String B,String C,String D) {
		JPanel jp=new JPanel();
		test_jp.setLayout(new BorderLayout());
		
		JLabel test_jl2=new JLabel(question);
		test_jrb[0]=new JRadioButton(A);
		test_jrb[1]=new JRadioButton(B);
		test_jrb[2]=new JRadioButton(C);
		test_jrb[3]=new JRadioButton(D);
		Box basebox,box[]=new Box[4];
		ButtonGroup bg=new ButtonGroup();
		for(int i=0;i<4;i++) {
			box[i]=Box.createHorizontalBox();
			bg.add(test_jrb[i]);
		}
		test_jb[0]=new JButton("返回");
		test_jb[1]=new JButton("上一题");
		test_jb[2]=new JButton("下一题");
		test_jb[3]=new JButton("提交");
		box[0].add(test_jl1);
		box[0].add(Box.createHorizontalStrut(5));
		box[0].add(test_time);
		box[0].add(Box.createHorizontalStrut(450));
		box[1].add(test_jl2);
		box[3].add(Box.createHorizontalStrut(200));     //固定按钮的位置
		for(int i=0;i<5;i++) {
			if(i<4) {
				box[2].add(test_jrb[i]);
				box[2].add(Box.createHorizontalStrut(20));
			}
			if(i<2) {
				box[3].add(test_jb[i]);
				box[3].add(Box.createHorizontalStrut(20));
			}
			if(i==2) {
				box[3].add(test_jtf);
				box[3].add(Box.createHorizontalStrut(20));
			}
			if(i>2) {
				box[3].add(test_jb[i-1]);
				box[3].add(Box.createHorizontalStrut(20));
			}
		}
		box[3].add(Box.createHorizontalStrut(200));      //固定按钮的位置
		basebox=Box.createVerticalBox();
		for(int i=1;i<3;i++) {
			basebox.add(Box.createVerticalStrut(50));
			basebox.add(box[i]);
		}
		basebox.add(Box.createVerticalStrut(80));
		basebox.add(box[3]);
		jp.add(basebox);
		test_jp.add(box[0],BorderLayout.NORTH);
		test_jp.add(jp,BorderLayout.CENTER);
		
	}

}
