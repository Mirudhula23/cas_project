package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
 
import org.apache.commons.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.streaming.SheetDataWriter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.TotalDigitsDocument.TotalDigits;
 
public class ExcelUtils {
	public static FileInputStream fi;
	public static XSSFWorkbook xb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
	{
		fi = new FileInputStream(xlfile);
	    xb = new XSSFWorkbook(fi);
	    ws = xb.getSheet(xlsheet);
	    // Check if the row exists, and create one if it doesn't
	    row = ws.getRow(rownum);
	    if (row == null) {
	        row = ws.createRow(rownum);
	    }
	    // Create a cell in the row and set its value
	    cell = row.createCell(colnum);
	    cell.setCellValue(data);
	    // Write the changes back to the file
	    FileOutputStream fo = new FileOutputStream(xlfile);
	    xb.write(fo);
	    // Close all the resources to prevent memory leaks
	    xb.close();
	    fi.close();
	    fo.close();
	}
	public static void createExcel() throws IOException {
	    // Define the path to the Excel file
	    String file = System.getProperty("user.dir") + "\\TestData\\outputfile.xlsx";
	    // Create a file output stream to write to the Excel file
	    FileOutputStream fo = new FileOutputStream(file);
	    // Create a new Excel workbook
	    XSSFWorkbook xb = new XSSFWorkbook();
	    // Create a new sheet named "profile"
	    XSSFSheet sheet1 = xb.createSheet("Be cognizant");
	    XSSFSheet sheet2 = xb.createSheet("News Contents");
	    Font font = xb.createFont();//creating
		font.setBold(true);//setting font as Bold
		CellStyle style = xb.createCellStyle();//creating cellStyle
		style.setFont(font);//Applying cell style as Bold
	    // Create the first row in the sheet
	    XSSFRow rowSheet1 = sheet1.createRow(0);
	    XSSFRow rowSheet2 = sheet2.createRow(0);
	    // Create cells for "NAME" and "EMAIL"
	    XSSFCell name = rowSheet1.createCell(0);
	    XSSFCell email = rowSheet1.createCell(1);
	    // Create cells for "Title","ToolTip", "Expected Output" and "Actual Output"
	    XSSFCell title = rowSheet1.createCell(2);
	    XSSFCell tooltip = rowSheet1.createCell(3);
	    XSSFCell result = rowSheet1.createCell(4);
	    XSSFCell exp = rowSheet1.createCell(5);
	    XSSFCell act = rowSheet1.createCell(6);
	    //Validate the news are displayed in Be. Cognizant are displayed When user clicks on See All.
	    XSSFCell expected = rowSheet1.createCell(7);
	    XSSFCell actual = rowSheet1.createCell(8);
	    //Display the contents of the news
	    XSSFCell news_heading = rowSheet2.createCell(0);
	    XSSFCell  tooltips= rowSheet2.createCell(1);
	    XSSFCell  contents= rowSheet2.createCell(2);
	    // Set the value of the cells
	    name.setCellValue("NAME");
	    email.setCellValue("EMAIL");
	    title.setCellValue("TITLE");
	    tooltip.setCellValue("TOOLTIP");
	    result.setCellValue("RESULT");
	    exp.setCellValue("EXP RESULT");
	    act.setCellValue("ACT RESULT");
	    expected.setCellValue("EXPECTED BE COGNIZANT NEWS");
        actual.setCellValue("ACTUAL BE COGNIZANT NEWS");
        news_heading.setCellValue("NEWS TITLE");
        tooltips.setCellValue("TOOLTIP");
        contents.setCellValue("CONTENTS");
        
	    //Setting cell values as bold
        
	    name.setCellStyle(style);
	    email.setCellStyle(style);
	    title.setCellStyle(style);
	    tooltip.setCellStyle(style);
	    result.setCellStyle(style);
	    exp.setCellStyle(style);
	    act.setCellStyle(style);
	    expected.setCellStyle(style);
        actual.setCellStyle(style);
        news_heading.setCellStyle(style);
        tooltips.setCellStyle(style);
        contents.setCellStyle(style);
	    // Write the workbook to the file output stream
	    xb.write(fo);
	    // Close the workbook and the file output stream
	    xb.close();
	    fo.close();
	}
}
