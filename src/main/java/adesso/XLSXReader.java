package adesso;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLSXReader {

    public static void main(String[] args) throws IOException {

        File myFile = new File("C:\\Users\\baraz\\IdeaProjects\\Portfolio-Abgleich\\src\\main\\java\\files\\LOB Portfolio Erinnerung Stand 08.09.2020(mit Farben).xlsx");

        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook (new FileInputStream(myFile));

        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(0);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = mySheet.iterator();

        // Traversing over each row of XLSX file
        ArrayList<String> pElementListe = new ArrayList<String>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // For each row, iterate through each columns
            Iterator<Cell> cellIterator = row.cellIterator();
            int counter = 0;
            while (cellIterator.hasNext()) {

                Cell cell = cellIterator.next();

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_STRING:
                        if (counter == 3) {
                            pElementListe.add(cell.getStringCellValue());
                        }
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");

                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        System.out.print(cell.getBooleanCellValue() + "\t");

                        break;
                    default :

                }
                counter++;
            }
            System.out.println("");
        }

        for (String el : pElementListe) {
            System.out.println(el);
        }
        System.out.println(pElementListe.size());
    }
}
