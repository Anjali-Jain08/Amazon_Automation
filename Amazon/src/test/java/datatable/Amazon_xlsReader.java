package datatable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import jxl.Cell;
import jxl.JXLException;
import jxl.Sheet;
import jxl.Workbook;

public class Amazon_xlsReader {
	String path= null;
	FileInputStream fin =null;
	Workbook wrkbk = null;
	Cell cell;
	
	public Amazon_xlsReader(String path) {

		this.path = path;
		try {
			
	    FileInputStream fin = new FileInputStream(path);
		fin.close();
		
		} catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	//Get total number of rows of the sheet
		public int getRowCount(String sheetName) throws IOException, JXLException {

			File file = new File(path);
			wrkbk = Workbook.getWorkbook(file);
			Sheet sheet = wrkbk.getSheet(sheetName);
			int row_count = 0;
			
			for(int i=0; i<sheet.getRows(); i++) {
				
				 row_count++;
			}
			return row_count;
		}
		
		//Get the sheet name according to the sheet index passed as a parameter.
		public String getFirstSheetname() throws IOException, JXLException {

			File file = new File(path);
			Workbook wrkbk;
			wrkbk = Workbook.getWorkbook(file);
			String sheet = wrkbk.getSheet(0).getName();
			return sheet;

		}
		
		//Get the cell data from the selected sheet
		public String getCellData(String SheetName, String colName, int rowNum) throws IOException, JXLException {

			File file = new File(path);
			Workbook wrkbk;
			wrkbk = Workbook.getWorkbook(file);
			Sheet sheet = wrkbk.getSheet(SheetName);
			int colNum=0;
			int i;

			for(i=0; i<sheet.getColumns(); i++) {

				if(sheet.getCell(i, 0).getContents().equalsIgnoreCase(colName)) {

					break;
				}
			}
	        
			colNum=i;
			cell = sheet.getCell(colNum, rowNum);
			return cell.getContents();


		}

}
