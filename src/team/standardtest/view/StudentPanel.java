package team.standardtest.view;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.*;
import team.standardtest.dao.Lesson;
import team.standardtest.dao.Score;
import team.standardtest.dao.TestMesage;

public class StudentPanel {
	
	/**��ѡ�γ̵ĵ�ѡ��ť*/
	public JRadioButton studentlesson_jrb[];
	/**ѧ��ѡ��γ̵����*/
	public JPanel studentlesson_jp=new JPanel();
	/**��ӿγ̵������б�*/
	public JComboBox studentlesson_jcb=new JComboBox();
	/**
	 * ѧ��ѡ���ԵĿγ̵����,��ѡ�Ŀγ��������ѧ���ж��ٿγ̱仯
	 * @lesson ����lesson[]�����ѧ���е����пγ�
	 * */
	public void studentLessonP(String lesson[]) {
		int n=lesson.length;     //��ѧ���ж��ٸ��γ�
		Box basebox,box[]=new Box[n+2];
		studentlesson_jrb=new JRadioButton[n];
		ButtonGroup bg=new ButtonGroup();
		for(int i=0;i<n+2;i++) {
			if(i<n) {
				studentlesson_jrb[i]=new JRadioButton(lesson[i]);   //�����пγ������뵥ѡ��ť
				bg.add(studentlesson_jrb[i]);
			}
			box[i]=Box.createHorizontalBox();      //ʵ��������,���������ȿγ�����������
		}
		JLabel studentlesson_jl=new JLabel("ѡ��γ�");
		JLabel studentlesson_jl1=new JLabel("��ӿγ�");
		studentlesson_jcb.addItem("    ");      //�����б��һ��Ϊ��
		Lesson le=new Lesson();
		int x=le.getAllLename().length;
		for(int i=0;i<x;i++) {
			studentlesson_jcb.addItem(le.getAllLename()[i]);
		}
		studentlesson_jl.setFont(new Font("Serif",Font.PLAIN,25));   //���ñ�ǩ������
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
	
	
	/**ѧ���������İ�ť*/
	public JButton test_jb[]=new JButton[4];
	/**ABCD�ĵ�ѡ��ť*/
	public JRadioButton test_jrb[]=new JRadioButton[4];
	/**��ʾ��ŵ��ı���*/
	public JTextField test_jtf=new JTextField(5);
	/**��ʾʱ����ı���*/
	public JTextField test_time=new JTextField(10);
	/**ѧ�����Ե����*/
	public JPanel test_jp=new JPanel();
	public JLabel test_jl1;
	
	/**
	 * ѧ�����Ե����
	 * @question ����question��ʾ��Ŀ
	 * @A ����A��ʾAѡ��
	 * @B ����B��ʾBѡ��
	 * @C ����C��ʾCѡ��
	 * @D ����D��ʾDѡ��
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
		test_jb[0]=new JButton("����");
		test_jb[1]=new JButton("��һ��");
		test_jb[2]=new JButton("��һ��");
		test_jb[3]=new JButton("�ύ");
		box[0].add(test_jl1);
		box[0].add(Box.createHorizontalStrut(5));
		box[0].add(test_time);
		box[0].add(Box.createHorizontalStrut(450));
		box[1].add(test_jl2);
		box[3].add(Box.createHorizontalStrut(200));     //�̶���ť��λ��
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
		box[3].add(Box.createHorizontalStrut(200));      //�̶���ť��λ��
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
