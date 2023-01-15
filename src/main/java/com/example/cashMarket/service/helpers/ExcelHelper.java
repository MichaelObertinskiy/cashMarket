package com.example.cashMarket.service.helpers;

import com.example.cashMarket.domain.Commodity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "name", "barcode", "taxgroup", "groupOfGoods", "trademark", "cost" };
    static String SHEET = "Commodities";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Commodity> excelToCommodities(InputStream is) {

        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Commodity> commodities = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                Commodity commodity = new Commodity();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            commodity.setName(currentCell.getStringCellValue());
                            break;
                        case 1:
                            commodity.setBarcode(currentCell.getStringCellValue());
                            break;
                        case 2:
                            commodity.setTaxgroup(currentCell.getStringCellValue());
                            break;
                        case 3:
                            commodity.setGroupOfGoods(currentCell.getStringCellValue());
                            break;
                        case 4:
                            commodity.setTrademark(currentCell.getStringCellValue());
                            break;
                        case 5:
                            commodity.setCost(currentCell.getNumericCellValue());
                            break;
                        default:
                            break;
                    }


                    cellIdx++;
                }
                commodities.add(commodity);
            }

            workbook.close();

            return commodities;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }


}
