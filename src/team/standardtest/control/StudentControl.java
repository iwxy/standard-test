package team.standardtest.control;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.swing.*;
import javax.swing.plaf.synth.SynthStyle;

import team.standardtest.dao.*;
import team.standardtest.view.*;

public class StudentControl extends JFrame implements ActionListener{
	/**用于存放学生的id*/
	String id;
	/**计时器*/
	Timer time;
	
	public StudentControl(String id) {
		this.id=id;
		init();
		setTitle("标准化考试");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,700,600);
	}
	
	/**实例化学生面板类*/
	StudentPanel student=new StudentPanel();
	/**实例化操作分数表的类*/
	Score score=new Score();
	/**实例化操作课程表的类*/
	Lesson lesson=new Lesson();
	/**实例化操作考试信息表的类*/
	TestMesage test=new TestMesage();
	/**实例化操作考试文件的类*/
	QuestionBank question;
	
	/**用于存储学生拥有的课程的数量*/
	int n=0;
	/**用于存储该学生选择的考试课程*/
	String leid="";    
	/**用于存储考试科目的考试题量*/
	int x=0;
	/**用于存放学生考试的题号*/
	int tihao[];
	/**用于存放学生选择的答案，下角标为0时为第一题的答案*/
	String stuanswer[];
	/**学生的得分*/
	int stuscore=0;
	/**每题的分数，总分100*/
	int eachscore=0;
	/**存放数据库中的考试时长*/
	int duration; 
	
	private void init() {
		student.studentLessonP(score.getLesson(id));
		add(student.studentlesson_jp);
		student.studentlesson_jcb.addActionListener(this);  //给学生选择考试课程的添加课程的下拉列表加监视器
		n=score.getLesson(id).length;
		for(int i=0;i<n;i++) {
			student.studentlesson_jrb[i].addActionListener(this);     //给学生选择考试课程的单选按钮加监视器
		}
		student.test_jtf.addActionListener(this);
		time=new Timer(1000*60,this);    //给计时器加监视器，一分钟触发一次
		
	}
	
	
	public void actionPerformed(ActionEvent a) {
		
		//点击添加课程的下拉列表
		if(a.getSource()==student.studentlesson_jcb) {
			String soname=String.valueOf(student.studentlesson_jcb.getSelectedItem());     //选择的课程名
			boolean is=false;
			for(int i=0;i<score.getLesson(id).length;i++) {
				if(soname.equals(score.getLesson(id)[i])) {     //判断添加的该课程是否存在
					is=true;
				}
			}
			if(is==false) {
				score.setOne(id,lesson.getLessonId(soname));
				remove(student.studentlesson_jp);         //添加课程后，先把原来的面板移除掉
				student.studentlesson_jp.removeAll();     //因设置该面板的方法会在本面板上加组件，所以要把原来的组件全部移除
				student.studentlesson_jcb.removeAllItems();    //同上理
				student.studentLessonP(score.getLesson(id));   //重新设置选课面板，会添加可选课程
				n=score.getLesson(id).length;       //如添加了课程，该学生拥有的课程数会增加，而给选择课程加监视器的代码不会再执行，所以新加的课程没有加监视器
				for(int i=0;i<n;i++) {
					student.studentlesson_jrb[i].addActionListener(this);     //给学生选择考试课程的单选按钮加监视器
				}
				add(student.studentlesson_jp);
				repaint();
				validate();
			}
			else {
				JOptionPane.showMessageDialog(this,"该课程已存在！","错误消息",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//点击选择课程的单选按钮,该按钮数量不定
		for(int i=0;i<n;i++) {     //因该学生的课程数量不定，不好写getSource有几个课程，用for循环控制
			if(a.getSource()==student.studentlesson_jrb[i]) {
				leid=lesson.getLessonId(a.getActionCommand());   //获取该学生选择的考试课程的课程号
				if(test.getTestMesage(leid)[0].equals("1")) {   //如果该课程允许考试
					question=new QuestionBank(test.getTestMesage(leid)[4]);
					int testtime=score.getTimes(id,leid);
					if(testtime<Integer.parseInt(test.getTestMesage(leid)[3])) {   //如果该学生还有考试机会
						remove(student.studentlesson_jp);
						score.setTimes(id,leid,testtime+1);   //该学生该门课程数据库中的考试次数加一
						x=Integer.parseInt(test.getTestMesage(leid)[1]);   //获取该学生选择的考试课程的题量
						stuanswer=new String[x];
						eachscore=100/x;
						int num=question.rows;     //获取题库中有多少道题
						
						tihao=new int[x];
						for(int p=0;p<x;p++) {     //随机获取x道题的题号
							stuanswer[p]="";       //初始化学生选择的答案数组，如果学生没选，也不为空，对比正确答案的时候不会报错
							int sj=(int)(Math.random()*num+1);     //从题库中随机出题的题号
							tihao[p]=sj;
							for(int q=0;q<p;q++) {
								if(tihao[q]==tihao[p]) {    //如获取的题号已存在，重新获取，避免出现重复的题
									p=p-1;
								}
							}
						}
						duration=Integer.parseInt(test.getTestMesage(leid)[2]); 
						student.test_time.setText(duration+"分钟");    //显示一开始数据库中的考试时长
						setQuestion(0);
						time.start();     //开始计时
						student.test_jtf.setText("1");
						add(student.test_jp);
						repaint();
						validate();
					}
					else {      //该学生该课程的考试机会已用完
						JOptionPane.showConfirmDialog(this,"你的考试机会已用完!!!","是否考试",JOptionPane.DEFAULT_OPTION);
					}
				}
				
				else{   //如果该课程不允许考试
					JOptionPane.showConfirmDialog(this,"该课程不允许考试!!!","是否考试",JOptionPane.DEFAULT_OPTION);
				}
		
			}
		}
		
		//每隔一分钟，计时器触发监视器
		if(a.getSource()==time) {
			duration--;        //一分钟后触发监视器，时间减一分钟
			student.test_time.setText(String.valueOf(duration)+"分钟");
			if(duration==0) {
				JOptionPane.showConfirmDialog(this,"考试时间已到！已自动提交！！！","考试结束",JOptionPane.DEFAULT_OPTION);
				submit();
			}
		}
		
		
		//点击考试面板的返回，返回选择考试课程的面板
		if(a.getSource()==student.test_jb[0]) {
			remove(student.test_jp);
			add(student.studentlesson_jp);
			repaint();
			validate();
		}
		
		
		
		//点击考试面板的上一题
		else if(a.getSource()==student.test_jb[1]) {
			int t=Integer.parseInt(student.test_jtf.getText());
			try {
				setQuestion(t-2);     //t为0时，代表题库中的第1题
				student.test_jtf.setText(String.valueOf(t-1));
				repaint();
				validate();
			}catch(ArrayIndexOutOfBoundsException e) {    // 已经为第一题，继续上一题时，t-1，导致setQuestion(t)数组越界
				JOptionPane.showConfirmDialog(this,"已经是第一题了","提示",JOptionPane.DEFAULT_OPTION);
				setQuestion(t-1);       //重新加面板，消息对话框之后，原来面板上的按钮用不了了
				student.test_jtf.setText(String.valueOf(t));
				repaint();
				validate();
			}
		}
		
		//点击考试面板的下一题
		else if(a.getSource()==student.test_jb[2]) {
			int t=Integer.parseInt(student.test_jtf.getText());
			try {
				setQuestion(t);     //t为0时代表题库中的第1题
				student.test_jtf.setText(String.valueOf(t+1));
				repaint();
				validate();
			}catch(ArrayIndexOutOfBoundsException e) {    // 已经为最后一题，继续下一题时，t+1，导致setQuestion(t)数组越界
				JOptionPane.showConfirmDialog(this,"已经是最后一题了","提示",JOptionPane.DEFAULT_OPTION);
				setQuestion(t-1);       //重新加面板，消息对话框之后，原来面板上的按钮用不了了
				student.test_jtf.setText(String.valueOf(t));
				repaint();
				validate();
			}
		}
		
		//点击考试面板的提交
		else if(a.getSource()==student.test_jb[3]) {
			int p=JOptionPane.showConfirmDialog(this,"是否提交？","提交",JOptionPane.CANCEL_OPTION);  //弹出是否提交的对话框
			if(p==JOptionPane.YES_OPTION) {    //确定提交之后
				submit();
			}
			
		}
		
		
		//输入考试面板上的选题的文本框选题，回车
		else if(a.getSource()==student.test_jtf) {
			String ti=student.test_jtf.getText();
			int w=Integer.parseInt(ti);    //获取输入的题号
			setQuestion(w-1);
			repaint();
			validate();
			
		}
		
		
		//点击A，将A存入该问题答案的数组
		else if(a.getSource()==student.test_jrb[0]) {
			int tihao=Integer.parseInt(student.test_jtf.getText());
			stuanswer[tihao-1]="A";
		}
		//点击B，将B存入该问题答案的数组
		else if(a.getSource()==student.test_jrb[1]) {
			int tihao=Integer.parseInt(student.test_jtf.getText());
			stuanswer[tihao-1]="B";
		}
		//点击C，将C存入该问题答案的数组
		else if(a.getSource()==student.test_jrb[2]) {
			int tihao=Integer.parseInt(student.test_jtf.getText());
			stuanswer[tihao-1]="C";
		}
		//点击D，将D存入该问题答案的数组
		else if(a.getSource()==student.test_jrb[3]) {
			int tihao=Integer.parseInt(student.test_jtf.getText());
			stuanswer[tihao-1]="D";
		}
		
	}
	
	
	
	/**
	 * 设置考试题目的面板，换题，先移除掉原来的面板上的所有，调用该方法时会重新添加组件
	 * @n 参数n代表tihao数组的下角标，下角标为0时代表第1题
	 * */
	private void setQuestion(int n) {
		student.test_jp.removeAll();
		student.test_jl1=new JLabel("本场考试共有"+String.valueOf(x)+"题，还剩");
		student.test(question.getQuestion(tihao[n])[0],question.getQuestion(tihao[n])[1],question.getQuestion(tihao[n])[2],question.getQuestion(tihao[n])[3],question.getQuestion(tihao[n])[4]);
		//由于test_jb[]必须先实例化才能加监视器，所以调用完test()方法后，再加监视器
		for(int i=0;i<4;i++) {
			student.test_jb[i].addActionListener(this);     //给学生答题的返回，提交，上一题，下一题加监视器
			student.test_jrb[i].addActionListener(this);    //给学生答题的ABCD选项加监视器
		}
	}
	
	
	
	/**提交考试答案，计算得分*/
	private void submit() {
		for(int i=0;i<x;i++) {
			if(stuanswer[i].equals(question.getQuestion(i+1)[5])) {     //学生选择的答案与从题库中拿出来的正确答案比较
				stuscore+=eachscore;
			}
		}
		score.setScore(id,leid,stuscore);    //将学生的成绩输入score表
		
		int lesstime=Integer.parseInt(test.getTestMesage(leid)[3])-score.getTimes(id,leid);
		JOptionPane.showMessageDialog(this,"最终得分："+String.valueOf(stuscore)+"，你还有"+lesstime+"次考试机会！","得分",JOptionPane.INFORMATION_MESSAGE);
		remove(student.test_jp);     //考完之后重新回到选择考试课程的界面
		add(student.studentlesson_jp);
		repaint();
		validate();
	}

}
