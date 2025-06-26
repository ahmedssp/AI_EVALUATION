package DataMethod;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;

import java.io.*;

import static Listeners.testNg_listeners_simple.PassMessege;
import static Listeners.testNg_listeners_simple.failledMessege;

public class ApachePOI {
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getSheetName() {
        return SheetName;
    }

    public void setSheetName(String sheetName) {
        SheetName = sheetName;
    }

    public String path;
    public String SheetName;

    public static Object[][] Read_ExcelSheet(String path_new, String SheetName_new) {

        try {
            //path=getPath();SheetName=getSheetName();
            //0- addd dependency apachi-poi>> https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
            //1- read file
            File x = new File(System.getProperty("user.dir") + path_new);
            //2-read sheet
            XSSFSheet sheet = null;
            try {
                sheet = new XSSFWorkbook(x).getSheet(SheetName_new);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InvalidFormatException e) {
                throw new RuntimeException(e);
            }
            //3-define number of Rows and coulums
            int rows = sheet.getPhysicalNumberOfRows();
            int colums = sheet.getRow(0).getLastCellNum();
            //4- define Two dimentianl array
            Object[][] data;
            data = new String[rows - 1][colums];
            //5- read valuse form cells rows an coulums
            DataFormatter formatter = new DataFormatter();
            for (int x1 = 1; x1 < rows; x1++) {
                for (int y = 0; y < colums; y++) {
                    XSSFRow R_IN = sheet.getRow(x1);
                    data[x1 - 1][y] = formatter.formatCellValue(R_IN.getCell(y));
                }
            }
            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + PassMessege);

            return data;

        } catch (Exception e) {
            Reporter.log(new Object() {
            }.getClass().getEnclosingMethod().getName() + failledMessege);

            throw e;
        }
    }

    public static Object[][] ReadExcelSheetmulty(String filePath, String sheetName) {
        try {
            File file = new File(System.getProperty("user.dir") + filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in the workbook");
            }

            int rows = sheet.getPhysicalNumberOfRows();
            int columns = sheet.getRow(0).getLastCellNum();
            Object[][] data = new Object[rows - 1][columns];

            DataFormatter formatter = new DataFormatter();
            for (int rowIndex = 1; rowIndex < rows; rowIndex++) {
                XSSFRow row = sheet.getRow(rowIndex);
                if (row != null) {
                    for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                        XSSFCell cell = row.getCell(columnIndex);
                        if (cell != null) {
                            data[rowIndex - 1][columnIndex] = formatter.formatCellValue(cell);
                        } else {
                            data[rowIndex - 1][columnIndex] = ""; // or any default value
                        }
                    }
                }
            }

            return data;

        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading Excel sheet", e);
        }
    }

    @DataProvider(name = "Visiting_Request_data")
    public Object[][] Visiting_Request_2() {
        //0- addd dependency apachi-poi>> https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
        //1- read file
        File x = new File(System.getProperty("user.dir") + "/resources/Visiting_Request_DataProvider.xlsx");
        //2-read sheet
        XSSFSheet sheet = null;
        try {
            sheet = new XSSFWorkbook(x).getSheet("sheet1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        //3-define number of Rows and coulums
        int rows = sheet.getPhysicalNumberOfRows();
        int colums = sheet.getRow(0).getLastCellNum();
        //4- define Two dimentianl array
        Object[][] data;
        data = new String[rows - 1][colums];
        //5- read valuse form cells rows an coulums
        DataFormatter formatter = new DataFormatter();
        for (int x1 = 1; x1 < rows; x1++) {
            for (int y = 0; y < colums; y++) {
                XSSFRow R_IN = sheet.getRow(x1);
                data[x1 - 1][y] = formatter.formatCellValue(R_IN.getCell(y));
            }
        }
        return data;
    }

    @DataProvider(name = "exceldata")
    public Object[][] dataexcelm() {
        //0- addd dependency apachi-poi>> https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
        //1- read file
        File x = new File(System.getProperty("user.dir") + "/resources/Data.xlsx");
        //2-read sheet
        XSSFSheet sheet = null;
        try {
            sheet = new XSSFWorkbook(x).getSheet("sheet1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        //3-define number of Rows and coulums
        int rows = sheet.getPhysicalNumberOfRows();
        int colums = sheet.getRow(0).getLastCellNum();
        //4- define Two dimentianl array
        Object[][] data;
        data = new String[rows - 1][colums];
        //5- read valuse form cells rows an coulums
        DataFormatter formatter = new DataFormatter();
        for (int x1 = 1; x1 < rows; x1++) {
            for (int y = 0; y < colums; y++) {
                XSSFRow R_IN = sheet.getRow(x1);
                data[x1 - 1][y] = formatter.formatCellValue(R_IN.getCell(y));
            }
        }
        return data;
    }
    @DataProvider(name = "Dataentry")
    public Object[][] dataexcelm2() {
        //0- addd dependency apachi-poi>> https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
        //1- read file
        File x = new File(System.getProperty("user.dir") + "/resources/DataEntry_CRUD.xlsx");
        //2-read sheet
        XSSFSheet sheet = null;
        try {
            sheet = new XSSFWorkbook(x).getSheet("sheet1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        //3-define number of Rows and coulums
        int rows = sheet.getPhysicalNumberOfRows();
        int colums = sheet.getRow(0).getLastCellNum();
        //4- define Two dimentianl array
        Object[][] data;
        data = new String[rows - 1][colums];
        //5- read valuse form cells rows an coulums
        DataFormatter formatter = new DataFormatter();
        for (int x1 = 1; x1 < rows; x1++) {
            for (int y = 0; y < colums; y++) {
                XSSFRow R_IN = sheet.getRow(x1);
                data[x1 - 1][y] = formatter.formatCellValue(R_IN.getCell(y));
            }
        }
        return data;
    }

    public static void  incert_excell(String dataToSave,int Row, int column ){

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");
            Row row = sheet.createRow(Row);
            Cell cell = row.createCell(column);
            cell.setCellValue(dataToSave);

            // Specify the file path where you want to save the Excel file
            String filePath = System.getProperty("user.dir") + "/resources/variable_test.xlsx";

            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
                System.out.println("Excel file saved successfully!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void insertExcel(String dataToSave, int row, int column ,String pathRoot) {
        // Specify the file path where you want to save the Excel file
        String filePath = System.getProperty("user.dir") + pathRoot;

        try (Workbook workbook = getWorkbook(filePath)) {
            Sheet sheet = workbook.getSheet("Sheet1");

            if (sheet == null) {
                sheet = workbook.createSheet("Sheet1");
            }

            Row existingRow = sheet.getRow(row);
            if (existingRow == null) {
                existingRow = sheet.createRow(row);
            }

            Cell cell = existingRow.createCell(column);
            cell.setCellValue(dataToSave);

            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
                System.out.println("Excel file updated successfully!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void InsertExcelMulty(String dataToSave, int row, int column , String pathRoot , String Sheet) {
        // Specify the file path where you want to save the Excel file
        String filePath = System.getProperty("user.dir") + pathRoot;

        try (Workbook workbook = getWorkbook(filePath)) {
            Sheet sheet = workbook.getSheet(Sheet);

            if (sheet == null) {
                sheet = workbook.createSheet(Sheet);
            }

            Row existingRow = sheet.getRow(row);
            if (existingRow == null) {
                existingRow = sheet.createRow(row);
            }

            Cell cell = existingRow.createCell(column);
            cell.setCellValue(dataToSave);

            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
                System.out.println("Excel file updated successfully!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Workbook getWorkbook(String filePath) throws IOException {
        Workbook workbook;
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(fileInputStream);
        } catch (FileNotFoundException e) {
            // If the file doesn't exist, create a new workbook
            workbook = new XSSFWorkbook();
        }
        return workbook;
    }

    public static void main(String[] args) {
//        insertExcel("datao??????", 1, 0,"/resources/variable_test.xlsx");
        System.out.println((String) Read_ExcelSheet("/resources/variable_test.xlsx", "Sheet1")[0][0]);
    }

}