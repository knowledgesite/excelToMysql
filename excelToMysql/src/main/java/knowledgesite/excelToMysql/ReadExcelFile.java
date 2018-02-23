package knowledgesite.excelToMysql;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import dao.PersonDao;
import entity.Person;

public class ReadExcelFile {
	    public void readExcelFile(String filepath)
	    {
	        try
	        {
	            FileInputStream file = new FileInputStream(new File(filepath));
	 
	            //Create Workbook instance holding reference to .xlsx file
	            XSSFWorkbook workbook = new XSSFWorkbook(file);
	 
	            //Get first/desired sheet from the workbook
	            XSSFSheet sheet = workbook.getSheetAt(0);
	            XSSFRow row; 
	    		XSSFCell cell;
	            //Iterate through each rows one by one
	            Iterator<Row> rowIterator = sheet.iterator();
	            while (rowIterator.hasNext())
	            {
	                row = (XSSFRow) rowIterator.next();
	                //For each row, iterate through all the columns
	                Iterator<Cell> cellIterator = row.cellIterator();
	                Person person = new Person();
	                while (cellIterator.hasNext())
	                {
	                	cell = (XSSFCell) cellIterator.next();
	                    //Check the cell type and format accordingly
	                	if (cell.getCellTypeEnum() == CellType.STRING)
	    				{
	                		person.setName(cell.getStringCellValue());
	    				}
	    				else if(cell.getCellTypeEnum() == CellType.NUMERIC)
	    				{
	    					person.setPhone((long) cell.getNumericCellValue());
	    				}
	    				else
	    				{
	    					//U Can Handel Boolean, Formula, Errors
	    				}
	                	PersonDao dao = new PersonDao();
	                	dao.persist(person);
	                }
	            }
	            file.close();
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	}
}
