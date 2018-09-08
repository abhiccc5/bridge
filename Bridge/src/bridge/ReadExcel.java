/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridge;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Abhishek
 */
public class ReadExcel {
    
    public String readExcel(File f) 
    {
        String output="";
        try
        {
          FileInputStream file = new FileInputStream(f);
                //Create Workbook instance holding reference to .xlsx file
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                
                //Get first/desired sheet from the workbook
                XSSFSheet sheet = workbook.getSheetAt(0);
                
                //Iterate through each rows one by one
                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) 
                {
                    Row row = rowIterator.next();
                    //For each row, iterate through all the columns
                    Iterator<Cell> cellIterator = row.cellIterator(); 
                    
                    while (cellIterator.hasNext())
                    {
                        Cell cell = cellIterator.next();
                        //Check the cell type and format accordingly
                        switch (cell.getCellType())
                        {
                            case Cell.CELL_TYPE_NUMERIC:
                                output += cell.getNumericCellValue() + "   ";
                                break;
                            case Cell.CELL_TYPE_STRING:
                                output += cell.getStringCellValue() + "   ";
                                break;
                        }
                    }
                    output += "\n";
                }
                file.close();
            }
        
        catch (Exception e) 
        {
        }
        return output;
    }

    
    public String getcnames(File f) 
    {
        String output="";
        try
        {
          FileInputStream file = new FileInputStream(f);
                //Create Workbook instance holding reference to .xlsx file
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                
                //Get first/desired sheet from the workbook
                XSSFSheet sheet = workbook.getSheetAt(0);
                
                //Iterate through each rows one by one
                Iterator<Row> rowIterator = sheet.iterator();
                if (rowIterator.hasNext()) 
                {
                    Row row = rowIterator.next();
                    //For each row, iterate through all the columns
                    Iterator<Cell> cellIterator = row.cellIterator(); 
                    
                    while (cellIterator.hasNext())
                    {
                        Cell cell = cellIterator.next();
                        //Check the cell type and format accordingly
                        switch (cell.getCellType())
                        {
                            case Cell.CELL_TYPE_NUMERIC:
                                output +=cell.getNumericCellValue() + ",";
                                break;
                            case Cell.CELL_TYPE_STRING:
                                output += cell.getStringCellValue() + ",";
                                break;
                        }
                    }
                  int len = output.length();
                  output = output.substring(0,len-1);
                }
                file.close();
            }
        
        catch (Exception e) 
        {
        }
        System.out.println("cnames : "+output);
        return output;
    }
    
    
    public String getdata(File f) 
    {
        String output="";
        try
        {
          FileInputStream file = new FileInputStream(f);
                //Create Workbook instance holding reference to .xlsx file
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                
                //Get first/desired sheet from the workbook
                XSSFSheet sheet = workbook.getSheetAt(0);
                
                //Iterate through each rows one by one
                Iterator<Row> rowIterator = sheet.iterator();
                rowIterator.next();
                while (rowIterator.hasNext()) 
                {
                    Row row = rowIterator.next();
                    //For each row, iterate through all the columns
                    Iterator<Cell> cellIterator = row.cellIterator(); 
                    output+="(";
                    while (cellIterator.hasNext())
                    {
                        Cell cell = cellIterator.next();
                        //Check the cell type and format accordingly
                        switch (cell.getCellType())
                        {
                            case Cell.CELL_TYPE_NUMERIC:
                                int num = (int)cell.getNumericCellValue();
                                output += "'"+num + "',";
                                break;
                            case Cell.CELL_TYPE_STRING:
                                output += "'" + cell.getStringCellValue() + "',";
                                break;
                        }
                    }
                    output = output.substring(0,output.length()-1)+"),";
                }
                output = output.substring(0,output.length()-1);
                file.close();
            }
        catch (Exception e) 
        {
        }
        System.out.println("data : "+output);
        return output;
    }
    
}
