package com.etouch.taf.core.datamanager.excel;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;




import com.etouch.taf.util.LogUtil;

public abstract class ExcelReader {
	
	private static Log log = LogUtil.getLog(ExcelReader.class);
	
	protected abstract String getCellData(String sheetName,String colName,int rowNum);
	protected abstract String getCellData(String sheetName,int colNum,int rowNum);
	protected abstract boolean addColumn(String sheetName,String colName);
	protected abstract boolean removeColumn(String sheetName, int colNum);
	protected abstract boolean addHyperLink(String sheetName,String screenShotColName,String testCaseName,int index,String url,String message);
	protected abstract int getCellRowNum(String sheetName,String colName,String cellValue);
	protected abstract boolean setCellData(String sheetName,String colName,int rowNum, String data,String url);	
	
	protected String[][] readXLSData(Workbook workbook, String sheetName)	
	{
		String[][] data = null;
		Sheet sheet = getSheet(workbook, sheetName);
				
		if(sheet!=null)
		{
								        
		    
		    int rowCount = getRowCount(workbook, sheetName);
		    int colCount = getColumnCount(workbook, sheetName);
		     
		    data = new String[rowCount][colCount];
		      
		    //Iterate through each rows from first sheet
		    Iterator<Row> rowIterator = sheet.iterator();
		    for(int rowIndex=0;rowIterator.hasNext();rowIndex++) {
		        Row row = rowIterator.next();
		          
		    //For each row, iterate through each columns
		    Iterator<Cell> cellIterator = row.cellIterator();
		    for(int colIndex=0;cellIterator.hasNext();colIndex++){
		        Cell cell = cellIterator.next();	        
		        data[rowIndex][colIndex]=getCellText(cell);		       	          
		       }		        
		    } 
		}		
	    return data;     
	}
	
	protected String getCellText(Cell cell)
	{
		String cellText = "";
		
		switch(cell.getCellType()){
	        case Cell.CELL_TYPE_BOOLEAN:
	        	cellText = String.valueOf(cell.getBooleanCellValue());
	            break;
	        case Cell.CELL_TYPE_NUMERIC:
	        	cellText = String.valueOf(cell.getNumericCellValue());
	            break;
	        case Cell.CELL_TYPE_STRING:
	        	cellText = String.valueOf(cell.getStringCellValue());
	            break;      
        }
		return cellText;
	}
	
	protected Sheet getSheet(Workbook workbook, String sheetName)
	{
		Sheet sheet = null;
		int index = workbook.getSheetIndex(sheetName);
		if(index!=-1)
			sheet = workbook.getSheetAt(index);	
		
		return sheet;		
	}
	
	// returns the row count in a sheet
	protected int getRowCount(Workbook workbook, String sheetName){
		
		Sheet sheet =getSheet(workbook, sheetName);
		if(sheet==null)
			return 0;
		else		
		return sheet.getLastRowNum()+1;		
		
	}
	
	public String getCellData(Workbook workbook, String sheetName, String colName, int rowNum){
		try{
			
			Sheet sheet = getSheet(workbook, sheetName);
			if(sheet!=null)
			{
				int col_Num = getColNumber(sheet, colName);				
				if((sheet==null) || rowNum<=0 || col_Num<0)
					return "";	
				
				Row row = sheet.getRow(rowNum-1);
				if(row==null)
					return "";
				
				else
					return getCellData(row, col_Num);
			}
			return "";
			
      	}catch(Exception e){
			
			log.error(e.getMessage());
			return "row "+rowNum+" or column "+colName +" does not exist in xls";
		}
	}
	
	//returns cell data using Row object and Column number
	public String getCellData(Row row, int col_Num)
	{
		String cellText = "";	
		Cell cell = row.getCell(col_Num);			
		if(cell==null)
			return "";
		
		switch(cell.getCellType())
		{
			case Cell.CELL_TYPE_STRING: cellText = cell.getStringCellValue(); 
										break;
			case Cell.CELL_TYPE_BLANK:  cellText = ""; 
										break;
			case Cell.CELL_TYPE_NUMERIC:
			case Cell.CELL_TYPE_FORMULA: cellText = getNumericCellText(cell);
										 break;
			case Cell.CELL_TYPE_BOOLEAN: cellText = String.valueOf(cell.getBooleanCellValue());
										 break;
		}
		
		return cellText;
	}		
	
	//returns the cell data from numeric or calendar type. 
	public String getNumericCellText(Cell cell)
	{
		 String cellText  = String.valueOf(cell.getNumericCellValue());
		  if (HSSFDateUtil.isCellDateFormatted(cell)) {
	           // format in form of M/D/YY
			  double d = cell.getNumericCellValue();

			  Calendar cal =Calendar.getInstance();
			  cal.setTime(HSSFDateUtil.getJavaDate(d));
	            cellText =
	             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
	            cellText = cal.get(Calendar.MONTH)+1 + "/" +
	                      cal.get(Calendar.DAY_OF_MONTH) + "/" +
	                      cellText;
	           
	           //CommonUtil.sop(cellText)
	         
		  }
		  return cellText;
	}
	
		
	// returns the data from a cell
	public String getCellData(Workbook workbook, String sheetName,int colNum,int rowNum){
			try{
				
					Sheet sheet = getSheet(workbook, sheetName);
					if((sheet==null) || rowNum<=0 || colNum<0)
						return "";		
					
					Row row = sheet.getRow(rowNum-1);
					if(row==null)
						return "";			
					else
						return getCellData(row, colNum);		
			 
				}catch(Exception e){
				
					log.error(e.getMessage());
				return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
			}
		}
	
	// returns true if data is set successfully else false
	protected  boolean setCellData(String path, Workbook workbook, String sheetName,String colName,int rowNum, String data){
			Sheet sheet = null;
			boolean isSet = false;
			try{
				sheet = getSheet(workbook, sheetName);
				if((sheet!=null))
				{
					int colNum = getColNumber(sheet, colName);				
					if( rowNum<=0 || colNum<0)
						return false;
					else	
					{
						isSet = setCellData(path, workbook, sheet, rowNum, colNum, data);					
					}
				}
			}catch(Exception e){
				log.error(e.getMessage());
				return false;
			}
			return isSet;
		}
	
	    
    protected int getColNumber(Sheet sheet, String colName)
    {
    	int colNum =-1;
    	Row row=sheet.getRow(0);
    	if(row!=null)
    	{
			for(int i=0;i<row.getLastCellNum();i++){
				//System.out.println(row.getCell(i).getStringCellValue().trim());
				if((row.getCell(i)!=null) &&(row.getCell(i).getStringCellValue().trim().equals(colName)))
				{
					colNum=i;
					break;
				}
			}
    	}
		return colNum;
    }
	    
	    
   protected boolean setCellData(String path, Workbook workbook, Sheet sheet, int rowNum, int colNum, String data)
   {
	   try{
		    if(sheet!=null)
		    {			    	
			    setCellValue(sheet, rowNum, colNum, data);
	
			    FileOutputStream fileOut = new FileOutputStream(path);
	
				workbook.write(fileOut);
	
			    fileOut.close();
		    }			    
	   	}catch(Exception e){
	   		log.error(e.getMessage());
			return false;
		}
	   
	   return true;
   }
   
   
   public Cell setCellValue(Sheet sheet, int rowNum, int colNum, String data)
	{
		sheet.autoSizeColumn(colNum); //Lavanya
		Row row = sheet.getRow(rowNum-1);
		
		if (row == null)
			row = sheet.createRow(rowNum-1);
		
		Cell cell = row.getCell(colNum);	
		if (cell == null)
	        cell = row.createCell(colNum);
			
	    cell.setCellValue(data);
	    
	    return cell;		
	}
	
	
	// returns true if sheet is created successfully else false
	protected  boolean addSheet(String path, Workbook workbook, String  sheetname){		
		FileOutputStream fileOut;
		try {
			 if(!(isSheetExist(workbook, sheetname)))
			 {
				 fileOut = new FileOutputStream(path);
				 workbook.createSheet(sheetname);
				 workbook.write(fileOut);
			     fileOut.close();		
			 }
			 else
				 log.debug("Sheet "+ sheetname + "already exists" );
		} catch (Exception e) {			
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	// returns true if sheet is removed successfully else false if sheet does not exist
	protected  boolean removeSheet(String path, Workbook workbook, String sheetName){		
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1)
			return false;
		
		FileOutputStream fileOut;
		try {
			workbook.removeSheetAt(index);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
		    fileOut.close();		    
		} catch (Exception e) {			
			log.error(e.getMessage());
			return false;
		}
		return true;
	}
	
	 // find whether sheets exists	
	protected  boolean isSheetExist(Workbook workbook, String sheetName){
		int index = workbook.getSheetIndex(sheetName);
		if(index==-1){
			index=workbook.getSheetIndex(sheetName.toUpperCase());
				if(index==-1)
					return false;
				else
					return true;
		}
		else
			return true;
	}
	
	// returns number of columns in a sheet	
	protected  int getColumnCount(Workbook workbook, String sheetName){
		// check if sheet exists
		Sheet sheet = null;
		if(!isSheetExist(workbook, sheetName))
		 return -1;
		
		sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(0);
		
		if(row==null)
			return -1;
		
		return row.getLastCellNum();			
		
	}	
	
	protected void closeFileObjects(FileInputStream fis, FileOutputStream fos)
	{
		try{
			if(fis!=null)
				fis.close();
			if(fos!=null)
				fos.close();
		}catch(Exception ex)
		{
			log.error(ex.getMessage());
		}
	}
		
}



