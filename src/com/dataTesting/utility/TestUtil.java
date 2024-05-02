package com.dataTesting.utility;

import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TestUtil {
	static Xls_Reader reader;

	
	public static ArrayList<Object[]> getDataExcel(){
		ArrayList<Object[]> myData = new ArrayList<Object[]>();
		try {
			reader = new Xls_Reader();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
