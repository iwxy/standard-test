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
		setTitle("��׼������");
		setVisible(true);
		setBounds(100,100,700,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**ʵ������ʦ�������*/
	TeacherPanel teacher=new TeacherPanel();
	/**ʵ�����������������*/
	Score score=new Score();
	/**ʵ���������γ̱����*/
	Lesson lesson=new Lesson();
	/**ʵ��������������Ϣ�����*/
	TestMesage test=new TestMesage();
	
	/**���ݿ������пγ̵�����*/
	int lesnum;
	/**��ѡ��γ������ѡ��Ŀγ̵�id*/
	String leid;
	/**���ƽ���*/
	int q=0;
	
	
	private void init() {
		
		teacher.selectLessonP(lesson.getAllLename());  
		teacher.setTestP();    //���ô˷���ʵ������������ϵĸ������������Ӽ�����
		add(teacher.selectlesson_jp);
		lesnum=teacher.selectlesson_jrb.length;
		for(int i=0;i<lesnum;i++) {     //��ѡ��γ�����ϵĵ�ѡ��ť�Ӽ�����
			teacher.selectlesson_jrb[i].addActionListener(this);
		}
		for(int i=0;i<5;i++) {      //�����ÿ������İ�ť�Ӽ�����
			teacher.settest_jb[i].addActionListener(this);
		}
		teacher.lookscore_jcb.addActionListener(this);   //���鿴�ɼ����Ľ���Ӽ�����
		teacher.lookscore_jb1.addActionListener(this);   //���鿴�ɼ����ĵ���������ť�Ӽ�����
		teacher.lookscore_jb2.addActionListener(this);   //���鿴�ɼ����ķ��ذ�ť�Ӽ�����
		
	}


	public void actionPerformed(ActionEvent a) {
		// TODO Auto-generated method stub
		
		//���ѡ��γ̵ĵ�ѡ��ťѡ��γ̣������������������õ�ѡ��ť
		for(int i=0;i<lesnum;i++) {
			if(a.getSource()==teacher.selectlesson_jrb[i]) {
				leid=lesson.getLessonId(a.getActionCommand());   //��ʦѡ��Ŀγ̺�
				teacher.lookScoreP(score.getScore(0,leid));     //���ò鿴�ÿ�Ŀ�ĳɼ������
				remove(teacher.selectlesson_jp);     
				add(teacher.settest_jp);
				repaint();
				validate();
			}
		}
		
		//������ÿ��Եĵ����ļ���ť
		if(a.getSource()==teacher.settest_jb[0]) {
			importFile();
		}
		
		//������ÿ��Ե�ȷ����ť
		if(a.getSource()==teacher.settest_jb[1]) {
			String fileMes=teacher.settest_jtf[0].getText();
			String  questionNum=teacher.settest_jtf[1].getText();
			String eachTime=teacher.settest_jtf[2].getText();
			String times=teacher.settest_jtf[3].getText();
			test.updateIstest(leid,"1");
			test.updateNum(leid,questionNum);
			test.updateDuration(leid,eachTime,questionNum);
			test.updateTimes(leid,times);
			fileMes=fileMes.replace("\\","\\\\");    //���ļ���·����\\����String��\��ת����������ٴ�����ݿ�
			test.updateFile(leid,fileMes);
			JOptionPane.showMessageDialog(this,"�������","��Ϣ�Ի���",JOptionPane.INFORMATION_MESSAGE);
		}
		
		
		//������ÿ��Եķ��ذ�ť������ѡ��γ�
		if(a.getSource()==teacher.settest_jb[3]) {
			for(int i=0;i<4;i++) {        //����������ѡ��γ̵�ʱ��������ÿ��������ı����е�����
				teacher.settest_jtf[i].setText("");
			}
			remove(teacher.settest_jp);
			add(teacher.selectlesson_jp);
			teacher.lookscore_jp.removeAll();    //ѡ�ε�ʱ������lookscoreP()���������¸��鿴�ɼ������������������Ҫ���Ƴ����
			repaint();
			validate();
		}
		
		//������ÿ��Ե�ȡ�����԰�ť
		if(a.getSource()==teacher.settest_jb[4]) {
			test.updateIstest(leid,"0");
			score.setScoreZero(leid);
			JOptionPane.showMessageDialog(this,"�ÿγ̵Ŀ����ѽ�����","��Ϣ�Ի���",JOptionPane.INFORMATION_MESSAGE);
		}
		
		//������ÿ��� �ķ�����ť����ת���鿴��������
		if(a.getSource()==teacher.settest_jb[2]) {
			remove(teacher.settest_jp);
			add(teacher.lookscore_jp);
			repaint();
			validate();
		}
		
		//�����ʾ�����ĵ���������ť
		if(a.getSource()==teacher.lookscore_jb1) {
			exportFile();
		}
		
		//�����ʾ�����Ľ���
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
		
		//�����ʾ�����ķ��ذ�ť����ת�����ÿ��Խ���
		if(a.getSource()==teacher.lookscore_jb2) {
			remove(teacher.lookscore_jp);
			add(teacher.settest_jp);
			repaint();
			validate();
		}
		
	}
	
	
	JFileChooser fileDialog=new JFileChooser();
	FileNameExtensionFilter filter=new FileNameExtensionFilter("Excel�ļ�","xls");
	
	/**�����ļ�*/
	private void importFile() {
		fileDialog.setFileFilter(filter);      //ֻ����Excel�ļ�
		File filedir;
		String filename;
		int state=fileDialog.showOpenDialog(this);     //���ļ��ĶԻ���
		if(state==JFileChooser.APPROVE_OPTION) {
			filedir=fileDialog.getCurrentDirectory();    //�õ��ļ���·��
			filename=fileDialog.getSelectedFile().getName();    //�õ��ļ���
			teacher.settest_jtf[0].setText(filedir+"\\"+filename);    //��·�������ַŵ��ı�����
		}		
	}
	
	/**�����ļ�*/
	private void exportFile() {
		File filedir;
		String filename;
		fileDialog.setFileFilter(filter);       //ֻ����Excel�ļ�
		int state=fileDialog.showSaveDialog(this);     //�����ļ��ĶԻ���
		if(state==JFileChooser.APPROVE_OPTION) {
			filedir=fileDialog.getCurrentDirectory();     //�õ��ļ���·��
			filename=fileDialog.getSelectedFile().getName();       //�õ��ļ�������
			QuestionBank.createTable(String.valueOf(filedir),filename,score.getScore(0,leid));      //����һ������ļ�
		}
	}

}
