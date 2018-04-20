package team.standardtest.control;

import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import team.standardtest.dao.*;

import team.standardtest.view.*;

public class TeacherControl extends JFrame implements ActionListener{

	public TeacherControl(String id){
		init();
		setTitle("标准化考试");
		setVisible(true);
		setBounds(100,100,700,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**实例化教师界面的类*/
	TeacherPanel teacher=new TeacherPanel();
	/**实例化操作分数表的类*/
	Score score=new Score();
	/**实例化操作课程表的类*/
	Lesson lesson=new Lesson();
	/**实例化操作考试信息表的类*/
	TestMesage test=new TestMesage();
	
	/**数据库中所有课程的数量*/
	int lesnum;
	/**在选择课程面板上选择的课程的id*/
	String leid;
	/**控制降序*/
	int q=0;
	
	
	private void init() {
		
		teacher.selectLessonP(lesson.getAllLename());  
		teacher.setTestP();    //调用此方法实例化设置面板上的各个组件，方便加监视器
		add(teacher.selectlesson_jp);
		lesnum=teacher.selectlesson_jrb.length;
		for(int i=0;i<lesnum;i++) {     //给选择课程面板上的单选按钮加监视器
			teacher.selectlesson_jrb[i].addActionListener(this);
		}
		for(int i=0;i<5;i++) {      //给设置考试面板的按钮加监视器
			teacher.settest_jb[i].addActionListener(this);
		}
		teacher.lookscore_jcb.addActionListener(this);   //给查看成绩面板的降序加监视器
		teacher.lookscore_jb1.addActionListener(this);   //给查看成绩面板的导出分数按钮加监视器
		teacher.lookscore_jb2.addActionListener(this);   //给查看成绩面板的返回按钮加监视器
		
	}


	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		
		//点击选择课程的单选按钮选择课程，因数量不定，所以用单选按钮
		for(int i=0;i<lesnum;i++) {
			if(a.getSource()==teacher.selectlesson_jrb[i]) {
				leid=lesson.getLessonId(a.getActionCommand());   //老师选择的课程号
				teacher.lookScoreP(score.getScore(0,leid));     //设置查看该科目的成绩的面板
				remove(teacher.selectlesson_jp);     
				add(teacher.settest_jp);
				repaint();
				validate();
			}
		}
		
		//点击设置考试的导入文件按钮
		if(a.getSource()==teacher.settest_jb[0]) {
			importFile();
		}
		
		//点击设置考试的确定按钮
		if(a.getSource()==teacher.settest_jb[1]) {
			String fileMes=teacher.settest_jtf[0].getText();
			String  questionNum=teacher.settest_jtf[1].getText();
			String eachTime=teacher.settest_jtf[2].getText();
			String times=teacher.settest_jtf[3].getText();
			test.updateIstest(leid,"1");
			test.updateNum(leid,questionNum);
			test.updateDuration(leid,eachTime,questionNum);
			test.updateTimes(leid,times);
			fileMes=fileMes.replace("\\","\\\\");    //给文件的路径加\\，在String中\是转义符，加完再存进数据库
			test.updateFile(leid,fileMes);
			JOptionPane.showMessageDialog(this,"设置完成","消息对话框",JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		//点击设置考试的返回按钮，重新选择课程
		if(a.getSource()==teacher.settest_jb[3]) {
			for(int i=0;i<4;i++) {        //按返回重新选择课程的时候，清空设置考试面板的文本框中的内容
				teacher.settest_jtf[i].setText("");
			}
			remove(teacher.settest_jp);
			add(teacher.selectlesson_jp);
			teacher.lookscore_jp.removeAll();    //选课的时候会调用lookscoreP()方法，重新给查看成绩的面板加组件，所以需要先移除组件
			repaint();
			validate();
		}
		
		//点击设置考试的取消考试按钮
		if(a.getSource()==teacher.settest_jb[4]) {
			test.updateIstest(leid,"0");
			score.setScoreZero(leid);
			JOptionPane.showMessageDialog(this,"该课程的考试已结束！","消息对话框",JOptionPane.INFORMATION_MESSAGE);
		}
		
		//点击设置考试 的分数按钮，挑转到查看分数界面
		if(a.getSource()==teacher.settest_jb[2]) {
			remove(teacher.settest_jp);
			add(teacher.lookscore_jp);
			repaint();
			validate();
		}
		
		//点击显示分数的导出分数按钮
		if(a.getSource()==teacher.lookscore_jb1) {
			exportFile();
		}
		
		//点击显示分数的降序
		if(a.getSource()==teacher.lookscore_jcb) {
			if(q==0) {
				teacher.lookscore_jp.removeAll();
				teacher.lookScoreP(score.getScore(1,leid));
				validate();
				q=1;
			}
			else {
				teacher.lookscore_jp.removeAll();
				teacher.lookScoreP(score.getScore(0,leid));
				validate();
				q=0;
			}
			
		}
		
		//点击显示分数的返回按钮，挑转回设置考试界面
		if(a.getSource()==teacher.lookscore_jb2) {
			remove(teacher.lookscore_jp);
			add(teacher.settest_jp);
			repaint();
			validate();
		}
		
	}
	
	
	JFileChooser fileDialog=new JFileChooser();
	FileNameExtensionFilter filter=new FileNameExtensionFilter("Excel文件","xls");
	
	/**导入文件*/
	private void importFile() {
		fileDialog.setFileFilter(filter);      //只允许Excel文件
		File filedir;
		String filename;
		int state=fileDialog.showOpenDialog(this);     //打开文件的对话框
		if(state==JFileChooser.APPROVE_OPTION) {
			filedir=fileDialog.getCurrentDirectory();    //得到文件的路径
			filename=fileDialog.getSelectedFile().getName();    //得到文件名
			teacher.settest_jtf[0].setText(filedir+"\\"+filename);    //将路径和名字放到文本框中
		}		
	}
	
	/**导出文件*/
	private void exportFile() {
		File filedir;
		String filename;
		fileDialog.setFileFilter(filter);       //只允许Excel文件
		int state=fileDialog.showSaveDialog(this);     //保存文件的对话框
		if(state==JFileChooser.APPROVE_OPTION) {
			filedir=fileDialog.getCurrentDirectory();     //得到文件的路径
			filename=fileDialog.getSelectedFile().getName();       //得到文件的名字
			QuestionBank.createTable(String.valueOf(filedir),filename,score.getScore(0,leid));      //创建一个表格文件
		}
	}

}
