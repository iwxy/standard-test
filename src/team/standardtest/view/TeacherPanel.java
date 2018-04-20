package team.standardtest.view;

import java.awt.*;

import javax.swing.*;

public class TeacherPanel {
	
	
	public JButton settest_jb[]=new JButton[5];
	/**老师设置考试面板的文本框*/
	public JTextField settest_jtf[]=new JTextField[4];
	/**老师设置考试的面板*/
	public JPanel settest_jp=new JPanel();
	/**
	 * 教师设置考试项目的面板，    组件settext_ 代表该面板上的组件
	 * */
	public void setTestP() {
		Box basebox,box[]=new Box[6];
		settest_jp.setLayout(new FlowLayout());
		JLabel settest_jl[]=new JLabel[4];
		settest_jl[0]=new JLabel("导入考试题库：");
		settest_jl[1]=new JLabel("每张卷子的题量：");
		settest_jl[2]=new JLabel("每题的答题时长：");
		settest_jl[3]=new JLabel("最大考试次数：");
		settest_jtf[0]=new JTextField(20);
		for(int i=0;i<6;i++) {
			if(i<4 && i>0) {
				settest_jtf[i]=new JTextField(10);
			}
			box[i]=Box.createHorizontalBox();
		}
		/**从文件中导入*/
		settest_jb[0]=new JButton("从文件中导入");
		/**确定*/
		settest_jb[1]=new JButton("确定");
		/**返回*/
		settest_jb[2]=new JButton("分数");
		settest_jb[3]=new JButton("返回");
		settest_jb[4]=new JButton("取消考试");
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
	
	/**查看分数面板上的导出分数按钮*/
	public JButton lookscore_jb1=new JButton("       导出分数           ");
	/**查看分数面板上的返回按钮*/
	public JButton lookscore_jb2=new JButton("返回");
	/**查看分数面板上的降序选择框*/
	public JCheckBox lookscore_jcb=new JCheckBox("降序");
	/**查看分数的面板*/
	public JPanel lookscore_jp=new JPanel();
	/**
	 * 教师查看分数的面板,lookscore_ 代表该面板的组件
	 * @data[][] 参数data表示表格的内容
	 * */
	public void lookScoreP(String data[][]) {
		lookscore_jp.setLayout(new FlowLayout());
		Box box[]=new Box[3];
		box[0]=Box.createHorizontalBox();
		box[1]=Box.createHorizontalBox();
		box[2]=Box.createHorizontalBox();
		
		String column[]= {"学生","分数"};
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
	
	

	/**老师选择考试课程的面板上的课程单选按钮*/
	public JRadioButton selectlesson_jrb[];
	/**老师选择考试课程的面板*/
	public JPanel selectlesson_jp=new JPanel();
	/**
	 * 老师选择考试的课程,可选的课程数量随数据库中有多少课程变化
	 * @lesson 参数lesson[]代表所有的科目
	 * */
	public void selectLessonP(String lesson[]) {
		int n=lesson.length;     //lesson中有多少个课程
		Box basebox,box[]=new Box[n+1];
		selectlesson_jrb=new JRadioButton[n];
		ButtonGroup bg=new ButtonGroup();
		for(int i=0;i<n+1;i++) {
			if(i<n) {
				selectlesson_jrb[i]=new JRadioButton(lesson[i]);   //将所有课程名放入单选按钮
				bg.add(selectlesson_jrb[i]);
			}
			box[i]=Box.createHorizontalBox();      //实例化盒子,盒子数量比课程数量多一个
		}
		JLabel selectlesson_jl=new JLabel("选择课程");
		selectlesson_jl.setFont(new Font("Serif",Font.PLAIN,25));  //设置标签的字体
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
