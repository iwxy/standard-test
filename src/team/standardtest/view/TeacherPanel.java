package team.standardtest.view;

import java.awt.*;

import javax.swing.*;

public class TeacherPanel {
	
	
	public JButton settest_jb[]=new JButton[5];
	/**��ʦ���ÿ��������ı���*/
	public JTextField settest_jtf[]=new JTextField[4];
	/**��ʦ���ÿ��Ե����*/
	public JPanel settest_jp=new JPanel();
	/**
	 * ��ʦ���ÿ�����Ŀ����壬    ���settext_ ���������ϵ����
	 * */
	public void setTestP() {
		Box basebox,box[]=new Box[6];
		settest_jp.setLayout(new FlowLayout());
		JLabel settest_jl[]=new JLabel[4];
		settest_jl[0]=new JLabel("���뿼����⣺");
		settest_jl[1]=new JLabel("ÿ�ž��ӵ�������");
		settest_jl[2]=new JLabel("ÿ��Ĵ���ʱ����");
		settest_jl[3]=new JLabel("����Դ�����");
		settest_jtf[0]=new JTextField(20);
		for(int i=0;i<6;i++) {
			if(i<4 && i>0) {
				settest_jtf[i]=new JTextField(10);
			}
			box[i]=Box.createHorizontalBox();
		}
		/**���ļ��е���*/
		settest_jb[0]=new JButton("���ļ��е���");
		/**ȷ��*/
		settest_jb[1]=new JButton("ȷ��");
		/**����*/
		settest_jb[2]=new JButton("����");
		settest_jb[3]=new JButton("����");
		settest_jb[4]=new JButton("ȡ������");
		box[0].add(settest_jl[0]);
		box[0].add(Box.createHorizontalStrut(10));
		box[0].add(settest_jtf[0]);
		box[0].add(Box.createHorizontalStrut(10));
		box[0].add(settest_jb[0]);
		box[1].add(settest_jl[1]);
		box[1].add(Box.createHorizontalStrut(10));
		box[1].add(settest_jtf[1]);
		box[2].add(settest_jl[2]);
		box[2].add(Box.createHorizontalStrut(10));
		box[2].add(settest_jtf[2]);
		box[3].add(settest_jl[3]);
		box[3].add(Box.createHorizontalStrut(22));
		box[3].add(settest_jtf[3]);
		box[4].add(settest_jb[1]);
		box[4].add(Box.createHorizontalStrut(50));
		box[4].add(settest_jb[2]);
		box[4].add(Box.createHorizontalStrut(50));
		box[4].add(settest_jb[3]);
		box[5].add(settest_jb[4]);
		basebox=Box.createVerticalBox();
		basebox.add(Box.createVerticalStrut(20));
		basebox.add(box[0]);
		basebox.add(Box.createVerticalStrut(50));
		for(int i=1;i<4;i++) {
			basebox.add(Box.createVerticalStrut(20));
			basebox.add(box[i]);
		}
		basebox.add(Box.createVerticalStrut(70));
		basebox.add(box[4]);
		basebox.add(Box.createVerticalStrut(70));
		basebox.add(box[5]);
		settest_jp.add(basebox);
	}
	
	/**�鿴��������ϵĵ���������ť*/
	public JButton lookscore_jb1=new JButton("       ��������           ");
	/**�鿴��������ϵķ��ذ�ť*/
	public JButton lookscore_jb2=new JButton("����");
	/**�鿴��������ϵĽ���ѡ���*/
	public JCheckBox lookscore_jcb=new JCheckBox("����");
	/**�鿴���������*/
	public JPanel lookscore_jp=new JPanel();
	/**
	 * ��ʦ�鿴���������,lookscore_ ������������
	 * @data[][] ����data��ʾ��������
	 * */
	public void lookScoreP(String data[][]) {
		lookscore_jp.setLayout(new FlowLayout());
		Box box[]=new Box[3];
		box[0]=Box.createHorizontalBox();
		box[1]=Box.createHorizontalBox();
		box[2]=Box.createHorizontalBox();
		
		String column[]= {"ѧ��","����"};
		JTable lookscore_table=new JTable(data,column);
		JScrollPane lookscore_jsp=new JScrollPane(lookscore_table);
		box[0].add(lookscore_jb1);
		box[0].add(Box.createHorizontalStrut(50));
		box[0].add(lookscore_jcb);
		box[1].add(lookscore_jsp);
		box[2].add(lookscore_jb2);
		Box basebox=Box.createVerticalBox();
		basebox.add(Box.createVerticalStrut(20));
		basebox.add(box[0]);
		basebox.add(Box.createVerticalStrut(20));
		basebox.add(box[1]);
		basebox.add(Box.createVerticalStrut(30));
		basebox.add(box[2]);
		basebox.add(Box.createVerticalStrut(60));
		lookscore_jp.add(basebox);

	}
	
	

	/**��ʦѡ���Կγ̵�����ϵĿγ̵�ѡ��ť*/
	public JRadioButton selectlesson_jrb[];
	/**��ʦѡ���Կγ̵����*/
	public JPanel selectlesson_jp=new JPanel();
	/**
	 * ��ʦѡ���ԵĿγ�,��ѡ�Ŀγ����������ݿ����ж��ٿγ̱仯
	 * @lesson ����lesson[]�������еĿ�Ŀ
	 * */
	public void selectLessonP(String lesson[]) {
		int n=lesson.length;     //lesson���ж��ٸ��γ�
		Box basebox,box[]=new Box[n+1];
		selectlesson_jrb=new JRadioButton[n];
		ButtonGroup bg=new ButtonGroup();
		for(int i=0;i<n+1;i++) {
			if(i<n) {
				selectlesson_jrb[i]=new JRadioButton(lesson[i]);   //�����пγ������뵥ѡ��ť
				bg.add(selectlesson_jrb[i]);
			}
			box[i]=Box.createHorizontalBox();      //ʵ��������,���������ȿγ�������һ��
		}
		JLabel selectlesson_jl=new JLabel("ѡ��γ�");
		selectlesson_jl.setFont(new Font("Serif",Font.PLAIN,25));  //���ñ�ǩ������
		box[0].add(selectlesson_jl);
		for(int i=1;i<n+1;i++) {
			box[i].add(selectlesson_jrb[i-1]);
		}
		basebox=Box.createVerticalBox();
		basebox.add(Box.createVerticalStrut(20));
		basebox.add(box[0]);
		basebox.add(Box.createVerticalStrut(30));
		for(int i=1;i<n+1;i++) {
			basebox.add(Box.createVerticalStrut(20));
			basebox.add(box[i]);
		}
		selectlesson_jp.add(basebox);
	}
	
	

}
