package team.standardtest.dao;

import java.io.*;

import java.util.*;

import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.*;

public class QuestionBank {
	/**
	 * 构造方法
	 * @file 参数file为文件名
	 * */
	public QuestionBank(String file){
		getAllQuestion(file);
	}
	
	/**二维数组list用于存储所有的题目*/
	String list[][];
	/**整个考试文件有多少行*/
	public int rows; 
	/**整个考试文件有多少列*/
	int clos;
	/**
	 * 该方法为私有方法，将表中的题目全部拿出来放到数组中
	 * @file 参数file代表文件名
	 **/
	 private void getAllQuestion(String file){
		   
		try {
			Workbook book=Workbook.getWorkbook(new File(file));  //打开Excel文件，获取对象
			Sheet sheet=book.getSheet(0);  //获取第一张工作表
			rows=sheet.getRows();  //得到该工作表有多少行
			clos=sheet.getColumns();  //得到该工作表有多少列
			list=new String[rows][clos];   
			for(int j=0;j<rows;j++){
				for(int i=0;i<clos;i++){  //将表中所有的题目，选项，答案放到二维数组list中
					list[j][i]=sheet.getCell(i,j).getContents();
				}
			}
			book.close();    //关闭excel
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
	 /**
		 * 从二维数组list中获得一个题目，包括选项，答案
		 * @n int型参数n表示题号
		 * @return question[]，将返回一个长度为6的数组，question[0]代表题目，
		 * question[1]代表A选项，question[2]代表B选项，question[3]代表C选项，
		 * question[4]代表D选项，question[5]代表答案；
		 * */
	 public String[] getQuestion(int n){
		 String question[]=new String[6];
		 for(int i=0;i<6;i++){
			 question[i]=list[n-1][i];
		 }
		 return question;
	 }
	
	/**
	 * 创建一张表，并在表中加数据
	 * @location String型参数location代表新创建表的地址；
	 * @filename String型参数filename代表新创建表的名字；
	 * @data String型二维数组是要输入表中的数据
	 * */
	 public static void createTable(String location,String filename,String data[][]){
		try{
			WorkbookSettings seting=new WorkbookSettings();
			WritableWorkbook book=Workbook.createWorkbook(new File(filename+".xls"));
			WritableSheet sheet=book.createSheet("my",0);  //创建一个名为my的工作表，0代表第一页
			int row=data.length;   //获得插入表中的数据有多少行
			int col=data[0].length;  //获得插入表中的数据有多少列
			for(int i=0;i<row;i++){
				for(int j=0;j<col;j++){  //i为表的行标，j为表的列标
					Label label=new Label(j,i,data[i][j]);  //将要插入的数据放到tabel中
					sheet.addCell(label);  //将tabel插入工作表中
				}
			}
			book.write();  //将工作表中的内容写入表格
			book.close();  //关闭excel
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	

}
