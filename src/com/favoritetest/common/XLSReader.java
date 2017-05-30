package com.favoritetest.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author Petr Skala
 */
public class XLSReader {

    protected FileInputStream file;
    protected Workbook workbook;
    protected Sheet sheet;
    protected Iterator<Row> rowIterator;
    protected Row actualRow;

    public XLSReader(int sheetNumber) {

        try {

            file = new FileInputStream(new File(XMLReader.getValue("properties.excelFile")));

            //Get the workbook instance for XLS file 
            workbook = WorkbookFactory.create(file);

            //Get first sheet from the workbook
            sheet = workbook.getSheetAt(sheetNumber);

            //Iterate through each rows from first sheet
            rowIterator = sheet.iterator();

        } catch (FileNotFoundException e) {
            
            e.printStackTrace();

        } catch (IOException eIo) {

            eIo.printStackTrace();

        } catch (InvalidFormatException | EncryptedDocumentException ex) {

            ex.printStackTrace();

        }

    }

    public void close() {

        try {

            file.close();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public boolean hasNextRow() {

        return rowIterator.hasNext();

    }

    public boolean nextRow() {

        if (rowIterator.hasNext()) {

            actualRow = rowIterator.next();
            return true;

        } else {

            return false;

        }

    }

    public String getCell(int position) {

        return actualRow.getCell(position).getStringCellValue();

    }

    public short getLastCellNum() {

        return actualRow.getLastCellNum();

    }

}
