package com.nilesh.springExcelExport.excel;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.nilesh.springExcelExport.model.Student;

public class UserExcelExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	
	private List<Student> listStudent;
	
	
	public UserExcelExporter(List<Student> listStudent) {
		this.listStudent=listStudent;
		workbook = new XSSFWorkbook();
		
	}
	
	private void createCell(Row row,int columnCount, Object value,CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell=row.createCell(columnCount);
		if(value instanceof Long) {
			cell.setCellValue((Long) value);
		}else if(value instanceof Integer) {
			cell.setCellValue((Integer) value);
		}else if(value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		}else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}
	private void writeHeaderLine() {
		sheet=workbook.createSheet("product");
		
		Row row = sheet.createRow(0);
		CellStyle style = workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setBold(true);
		font.setFontHeight(20);
		style.setFont(font);
		style.setAlignment(HorizontalAlignment.CENTER);
		createCell(row,0,"product Information",style);
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
		font.setFontHeightInPoints((short)(10));
		
		row=sheet.createRow(1);
		font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        createCell(row, 0, "product Id", style);
        createCell(row, 1, "product Name", style);
       
	}
	
	private void writeDataLines() {
		int rowCount=2;
		
		CellStyle style=workbook.createCellStyle();
		XSSFFont font=workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		
		for(Student stu:listStudent) {
			Row row=sheet.createRow(rowCount++);
			int columnCount=0;
			createCell(row, columnCount++, stu.getPid(), style);
			createCell(row, columnCount++, stu.getPname(), style);
			
		}
		 Date date=new Date();
	      DateFormat dateformatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
	      String currentDateTime = dateformatter.format(date);
	      Row row = sheet.createRow(listStudent.size()+2);
	     
	      Cell cell = row.createCell(0);
	     cell.setCellValue(currentDateTime);
	     row.createCell(1).setCellValue(" Product count " +listStudent.size());
	    
	      
	}
	
	public void export(HttpServletResponse response) throws IOException{
		writeHeaderLine();
		writeDataLines();
		
		ServletOutputStream outputStream=response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
	
	
}
