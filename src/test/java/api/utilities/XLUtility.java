package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook wb;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;

	public XLUtility(String path)
	{
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}

	public int getCellCount(String sheetName, int rownum) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellCount;
	}

	public String getCellData(String sheetName, int rownum, int column) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(column);

		DataFormatter formatter = new DataFormatter();
		String data;
		try {
		data= formatter.formatCellValue(cell); //Return formatted value of a cell as a String regardless of
		}catch(Exception e)
		{
			data="";
		}
		wb.close();
		fi.close();
		return data;
	}

	public void setCellData(String sheetName, int rownum, int column,String data) throws IOException
	{
		File xlfile = new File(path);
		if(!xlfile.exists()) //If file is not exists then create new file
		{
			wb=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			wb.write(fo);
		}

		fi=new FileInputStream(path);
		wb = new XSSFWorkbook(fi);

		if(wb.getSheetIndex(sheetName)==-1) //if sheet not exists then create new sheet

			wb.createSheet(sheetName);
		sheet=wb.getSheet(sheetName);

		if(sheet.getRow(rownum)==null) //if row not exists then create new Row
			sheet.createRow(rownum);
		row=sheet.getRow(rownum);

		cell=row.createCell(column);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}

	public void fillGreenColor(String sheetName,int rownum, int column) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);

		row=sheet.getRow(rownum);
		cell=row.getCell(column);

		style=wb.createCellStyle();

		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	public void fillRedColor(String sheetName, int rownum, int column) throws IOException
	{
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);

		row=sheet.getRow(rownum);
		cell=row.getCell(column);

		style = wb.createCellStyle();

		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cell.setCellStyle(style);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}

}
