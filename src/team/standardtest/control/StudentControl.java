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
	/**���ڴ��ѧ����id*/
	String id;
	/**��ʱ��*/
	Timer time;
	
	public StudentControl(String id) {
		this.id=id;
		init();
		setTitle("��׼������");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,700,600);
	}
	
	/**ʵ����ѧ�������*/
	StudentPanel student=new StudentPanel();
	/**ʵ�����������������*/
	Score score=new Score();
	/**ʵ���������γ̱����*/
	Lesson lesson=new Lesson();
	/**ʵ��������������Ϣ�����*/
	TestMesage test=new TestMesage();
	/**ʵ�������������ļ�����*/
	QuestionBank question;
	
	/**���ڴ洢ѧ��ӵ�еĿγ̵�����*/
	int n=0;
	/**���ڴ洢��ѧ��ѡ��Ŀ��Կγ�*/
	String leid="";    
	/**���ڴ洢���Կ�Ŀ�Ŀ�������*/
	int x=0;
	/**���ڴ��ѧ�����Ե����*/
	int tihao[];
	/**���ڴ��ѧ��ѡ��Ĵ𰸣��½Ǳ�Ϊ0ʱΪ��һ��Ĵ�*/
	String stuanswer[];
	/**ѧ���ĵ÷�*/
	int stuscore=0;
	/**ÿ��ķ������ܷ�100*/
	int eachscore=0;
	/**������ݿ��еĿ���ʱ��*/
	int duration; 
	
	private void init() {
		student.studentLessonP(score.getLesson(id));
		add(student.studentlesson_jp);
		student.studentlesson_jcb.addActionListener(this);  //��ѧ��ѡ���Կγ̵���ӿγ̵������б�Ӽ�����
		n=score.getLesson(id).length;
		for(int i=0;i<n;i++) {
			student.studentlesson_jrb[i].addActionListener(this);     //��ѧ��ѡ���Կγ̵ĵ�ѡ��ť�Ӽ�����
		}
		student.test_jtf.addActionListener(this);
		time=new Timer(1000*60,this);    //����ʱ���Ӽ�������һ���Ӵ���һ��
		
	}
	
	
	public void actionPerformed(ActionEvent a) {
		
		//�����ӿγ̵������б�
		if(a.getSource()==student.studentlesson_jcb) {
			String soname=String.valueOf(student.studentlesson_jcb.getSelectedItem());     //ѡ��Ŀγ���
			boolean is=false;
			for(int i=0;i<score.getLesson(id).length;i++) {
				if(soname.equals(score.getLesson(id)[i])) {     //�ж���ӵĸÿγ��Ƿ����
					is=true;
				}
			}
			if(is==false) {
				score.setOne(id,lesson.getLessonId(soname));
				remove(student.studentlesson_jp);         //��ӿγ̺��Ȱ�ԭ��������Ƴ���
				student.studentlesson_jp.removeAll();     //�����ø����ķ������ڱ�����ϼ����������Ҫ��ԭ�������ȫ���Ƴ�
				student.studentlesson_jcb.removeAllItems();    //ͬ����
				student.studentLessonP(score.getLesson(id));   //��������ѡ����壬����ӿ�ѡ�γ�
				n=score.getLesson(id).length;       //������˿γ̣���ѧ��ӵ�еĿγ��������ӣ�����ѡ��γ̼Ӽ������Ĵ��벻����ִ�У������¼ӵĿγ�û�мӼ�����
				for(int i=0;i<n;i++) {
					student.studentlesson_jrb[i].addActionListener(this);     //��ѧ��ѡ���Կγ̵ĵ�ѡ��ť�Ӽ�����
				}
				add(student.studentlesson_jp);
				repaint();
				validate();
			}
			else {
				JOptionPane.showMessageDialog(this,"�ÿγ��Ѵ��ڣ�","������Ϣ",JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//���ѡ��γ̵ĵ�ѡ��ť,�ð�ť��������
		for(int i=0;i<n;i++) {     //���ѧ���Ŀγ���������������дgetSource�м����γ̣���forѭ������
			if(a.getSource()==student.studentlesson_jrb[i]) {
				leid=lesson.getLessonId(a.getActionCommand());   //��ȡ��ѧ��ѡ��Ŀ��Կγ̵Ŀγ̺�
				if(test.getTestMesage(leid)[0].equals("1")) {   //����ÿγ�������
					question=new QuestionBank(test.getTestMesage(leid)[4]);
					int testtime=score.getTimes(id,leid);
					if(testtime<Integer.parseInt(test.getTestMesage(leid)[3])) {   //�����ѧ�����п��Ի���
						remove(student.studentlesson_jp);
						score.setTimes(id,leid,testtime+1);   //��ѧ�����ſγ����ݿ��еĿ��Դ�����һ
						x=Integer.parseInt(test.getTestMesage(leid)[1]);   //��ȡ��ѧ��ѡ��Ŀ��Կγ̵�����
						stuanswer=new String[x];
						eachscore=100/x;
						int num=question.rows;     //��ȡ������ж��ٵ���
						
						tihao=new int[x];
						for(int p=0;p<x;p++) {     //�����ȡx��������
							stuanswer[p]="";       //��ʼ��ѧ��ѡ��Ĵ����飬���ѧ��ûѡ��Ҳ��Ϊ�գ��Ա���ȷ�𰸵�ʱ�򲻻ᱨ��
							int sj=(int)(Math.random()*num+1);     //������������������
							tihao[p]=sj;
							for(int q=0;q<p;q++) {
								if(tihao[q]==tihao[p]) {    //���ȡ������Ѵ��ڣ����»�ȡ����������ظ�����
									p=p-1;
								}
							}
						}
						duration=Integer.parseInt(test.getTestMesage(leid)[2]); 
						student.test_time.setText(duration+"����");    //��ʾһ��ʼ���ݿ��еĿ���ʱ��
						setQuestion(0);
						time.start();     //��ʼ��ʱ
						student.test_jtf.setText("1");
						add(student.test_jp);
						repaint();
						validate();
					}
					else {      //��ѧ���ÿγ̵Ŀ��Ի���������
						JOptionPane.showConfirmDialog(this,"��Ŀ��Ի���������!!!","�Ƿ���",JOptionPane.DEFAULT_OPTION);
					}
				}
				
				else{   //����ÿγ̲�������
					JOptionPane.showConfirmDialog(this,"�ÿγ̲�������!!!","�Ƿ���",JOptionPane.DEFAULT_OPTION);
				}
		
			}
		}
		
		//ÿ��һ���ӣ���ʱ������������
		if(a.getSource()==time) {
			duration--;        //һ���Ӻ󴥷���������ʱ���һ����
			student.test_time.setText(String.valueOf(duration)+"����");
			if(duration==0) {
				JOptionPane.showConfirmDialog(this,"����ʱ���ѵ������Զ��ύ������","���Խ���",JOptionPane.DEFAULT_OPTION);
				submit();
			}
		}
		
		
		//����������ķ��أ�����ѡ���Կγ̵����
		if(a.getSource()==student.test_jb[0]) {
			remove(student.test_jp);
			add(student.studentlesson_jp);
			repaint();
			validate();
		}
		
		
		
		//�������������һ��
		else if(a.getSource()==student.test_jb[1]) {
			int t=Integer.parseInt(student.test_jtf.getText());
			try {
				setQuestion(t-2);     //tΪ0ʱ����������еĵ�1��
				student.test_jtf.setText(String.valueOf(t-1));
				repaint();
				validate();
			}catch(ArrayIndexOutOfBoundsException e) {    // �Ѿ�Ϊ��һ�⣬������һ��ʱ��t-1������setQuestion(t)����Խ��
				JOptionPane.showConfirmDialog(this,"�Ѿ��ǵ�һ����","��ʾ",JOptionPane.DEFAULT_OPTION);
				setQuestion(t-1);       //���¼���壬��Ϣ�Ի���֮��ԭ������ϵİ�ť�ò�����
				student.test_jtf.setText(String.valueOf(t));
				repaint();
				validate();
			}
		}
		
		//�������������һ��
		else if(a.getSource()==student.test_jb[2]) {
			int t=Integer.parseInt(student.test_jtf.getText());
			try {
				setQuestion(t);     //tΪ0ʱ��������еĵ�1��
				student.test_jtf.setText(String.valueOf(t+1));
				repaint();
				validate();
			}catch(ArrayIndexOutOfBoundsException e) {    // �Ѿ�Ϊ���һ�⣬������һ��ʱ��t+1������setQuestion(t)����Խ��
				JOptionPane.showConfirmDialog(this,"�Ѿ������һ����","��ʾ",JOptionPane.DEFAULT_OPTION);
				setQuestion(t-1);       //���¼���壬��Ϣ�Ի���֮��ԭ������ϵİ�ť�ò�����
				student.test_jtf.setText(String.valueOf(t));
				repaint();
				validate();
			}
		}
		
		//������������ύ
		else if(a.getSource()==student.test_jb[3]) {
			int p=JOptionPane.showConfirmDialog(this,"�Ƿ��ύ��","�ύ",JOptionPane.CANCEL_OPTION);  //�����Ƿ��ύ�ĶԻ���
			if(p==JOptionPane.YES_OPTION) {    //ȷ���ύ֮��
				submit();
			}
			
		}
		
		
		//���뿼������ϵ�ѡ����ı���ѡ�⣬�س�
		else if(a.getSource()==student.test_jtf) {
			String ti=student.test_jtf.getText();
			int w=Integer.parseInt(ti);    //��ȡ��������
			setQuestion(w-1);
			repaint();
			validate();
			
		}
		
		
		//���A����A���������𰸵�����
		else if(a.getSource()==student.test_jrb[0]) {
			int tihao=Integer.parseInt(student.test_jtf.getText());
			stuanswer[tihao-1]="A";
		}
		//���B����B���������𰸵�����
		else if(a.getSource()==student.test_jrb[1]) {
			int tihao=Integer.parseInt(student.test_jtf.getText());
			stuanswer[tihao-1]="B";
		}
		//���C����C���������𰸵�����
		else if(a.getSource()==student.test_jrb[2]) {
			int tihao=Integer.parseInt(student.test_jtf.getText());
			stuanswer[tihao-1]="C";
		}
		//���D����D���������𰸵�����
		else if(a.getSource()==student.test_jrb[3]) {
			int tihao=Integer.parseInt(student.test_jtf.getText());
			stuanswer[tihao-1]="D";
		}
		
	}
	
	
	
	/**
	 * ���ÿ�����Ŀ����壬���⣬���Ƴ���ԭ��������ϵ����У����ø÷���ʱ������������
	 * @n ����n����tihao������½Ǳ꣬�½Ǳ�Ϊ0ʱ�����1��
	 * */
	private void setQuestion(int n) {
		student.test_jp.removeAll();
		student.test_jl1=new JLabel("�������Թ���"+String.valueOf(x)+"�⣬��ʣ");
		student.test(question.getQuestion(tihao[n])[0],question.getQuestion(tihao[n])[1],question.getQuestion(tihao[n])[2],question.getQuestion(tihao[n])[3],question.getQuestion(tihao[n])[4]);
		//����test_jb[]������ʵ�������ܼӼ����������Ե�����test()�������ټӼ�����
		for(int i=0;i<4;i++) {
			student.test_jb[i].addActionListener(this);     //��ѧ������ķ��أ��ύ����һ�⣬��һ��Ӽ�����
			student.test_jrb[i].addActionListener(this);    //��ѧ�������ABCDѡ��Ӽ�����
		}
	}
	
	
	
	/**�ύ���Դ𰸣�����÷�*/
	private void submit() {
		for(int i=0;i<x;i++) {
			if(stuanswer[i].equals(question.getQuestion(i+1)[5])) {     //ѧ��ѡ��Ĵ����������ó�������ȷ�𰸱Ƚ�
				stuscore+=eachscore;
			}
		}
		score.setScore(id,leid,stuscore);    //��ѧ���ĳɼ�����score��
		
		int lesstime=Integer.parseInt(test.getTestMesage(leid)[3])-score.getTimes(id,leid);
		JOptionPane.showMessageDialog(this,"���յ÷֣�"+String.valueOf(stuscore)+"���㻹��"+lesstime+"�ο��Ի��ᣡ","�÷�",JOptionPane.INFORMATION_MESSAGE);
		remove(student.test_jp);     //����֮�����»ص�ѡ���Կγ̵Ľ���
		add(student.studentlesson_jp);
		repaint();
		validate();
	}

}
