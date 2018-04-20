package team.standardtest.dao;

import java.io.*;

import java.util.*;

import jxl.*;
import jxl.read.biff.BiffException;
import jxl.write.*;

public class QuestionBank {
	/**
	 * ���췽��
	 * @file ����fileΪ�ļ���
	 * */
	public QuestionBank(String file){
		getAllQuestion(file);
	}
	
	/**��ά����list���ڴ洢���е���Ŀ*/
	String list[][];
	/**���������ļ��ж�����*/
	public int rows; 
	/**���������ļ��ж�����*/
	int clos;
	/**
	 * �÷���Ϊ˽�з����������е���Ŀȫ���ó����ŵ�������
	 * @file ����file�����ļ���
	 **/
	 private void getAllQuestion(String file){
		   
		try {
			Workbook book=Workbook.getWorkbook(new File(file));  //��Excel�ļ�����ȡ����
			Sheet sheet=book.getSheet(0);  //��ȡ��һ�Ź�����
			rows=sheet.getRows();  //�õ��ù������ж�����
			clos=sheet.getColumns();  //�õ��ù������ж�����
			list=new String[rows][clos];   
			for(int j=0;j<rows;j++){
				for(int i=0;i<clos;i++){  //���������е���Ŀ��ѡ��𰸷ŵ���ά����list��
					list[j][i]=sheet.getCell(i,j).getContents();
				}
			}
			book.close();    //�ر�excel
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	 
	 /**
		 * �Ӷ�ά����list�л��һ����Ŀ������ѡ���
		 * @n int�Ͳ���n��ʾ���
		 * @return question[]��������һ������Ϊ6�����飬question[0]������Ŀ��
		 * question[1]����Aѡ�question[2]����Bѡ�question[3]����Cѡ�
		 * question[4]����Dѡ�question[5]����𰸣�
		 * */
	 public String[] getQuestion(int n){
		 String question[]=new String[6];
		 for(int i=0;i<6;i++){
			 question[i]=list[n-1][i];
		 }
		 return question;
	 }
	
	/**
	 * ����һ�ű����ڱ��м�����
	 * @location String�Ͳ���location�����´�����ĵ�ַ��
	 * @filename String�Ͳ���filename�����´���������֣�
	 * @data String�Ͷ�ά������Ҫ������е�����
	 * */
	 public static void createTable(String location,String filename,String data[][]){
		try{
			WorkbookSettings seting=new WorkbookSettings();
			WritableWorkbook book=Workbook.createWorkbook(new File(filename+".xls"));
			WritableSheet sheet=book.createSheet("my",0);  //����һ����Ϊmy�Ĺ�����0�����һҳ
			int row=data.length;   //��ò�����е������ж�����
			int col=data[0].length;  //��ò�����е������ж�����
			for(int i=0;i<row;i++){
				for(int j=0;j<col;j++){  //iΪ����б꣬jΪ����б�
					Label label=new Label(j,i,data[i][j]);  //��Ҫ��������ݷŵ�tabel��
					sheet.addCell(label);  //��tabel���빤������
				}
			}
			book.write();  //���������е�����д����
			book.close();  //�ر�excel
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch(Exception e){
			e.getMessage();
		}
		
	}
	
	

}
