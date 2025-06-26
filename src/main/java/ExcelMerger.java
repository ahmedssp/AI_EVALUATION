//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.*;
//
//public class ExcelMerger {
//
//    public static void main(String[] args) throws IOException {
//        // Replace "path/to/your/files" with the directory containing your Excel files
//        String filePath = "path/to/your/files";
//        // Replace "merged_data.xlsx" with your desired output filename
//        String outputFilename = "merged_data.xlsx";
//
//        // Create a new workbook for the merged data
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        Sheet mergedSheet = workbook.createSheet("Merged Data");
//        int currentRow = 0; // Keeps track of the current row in the merged sheet
//
//        // Get a list of Excel files in the specified directory
//        List<File> excelFiles = getExcelFiles(filePath);
//
//        // Loop through each Excel file
//        for (File excelFile : excelFiles) {
//            // Open the current Excel file
//            XSSFWorkbook tempWorkbook = null;
//            try {
//                tempWorkbook = new XSSFWorkbook(excelFile);
//            } catch (InvalidFormatException e) {
//                throw new RuntimeException(e);
//            }
//
//            // Get the first sheet from the current Excel file (modify if needed)
//            Sheet tempSheet = tempWorkbook.getSheetAt(0);
//
//            // Copy headers from the first file only
//            if (currentRow == 0) {
//                copyRow(tempSheet.getRow(0), mergedSheet.createRow(currentRow++));
//            }
//
//            // Copy data rows from the current Excel file
//            for (int i = 1; i <= tempSheet.getLastRowNum(); i++) {
//                Row tempRow = tempSheet.getRow(i);
//                if (tempRow != null) {
//                    mergedSheet.createRow(currentRow++).copyRowFrom(tempRow);
//                }
//            }
//
//            // Close the temporary workbook
//            tempWorkbook.close();
//        }
//
//        // Write the merged data to a new Excel file
//        FileOutputStream outputStream = new FileOutputStream(outputFilename);
//        workbook.write(outputStream);
//        workbook.close();
//        outputStream.close();
//
//        System.out.println("Excel files merged successfully!");
//    }
//
//    private static List<File> getExcelFiles(String filePath) {
//        File directory = new File(filePath);
//        List<File> excelFiles = new java.util.ArrayList<>();
//        for (File file : directory.listFiles()) {
//            if (file.isFile() && file.getName().endsWith(".xlsx")) {
//                excelFiles.add(file);
//            }
//        }
//        return excelFiles;
//    }
//
//    private static void copyRow(Row sourceRow, Row targetRow) {
//        for (int i = 0; i < sourceRow.getLastCellNum(); i++) {
//            Cell sourceCell = sourceRow.getCell(i);
//            Cell targetCell = targetRow.createCell(i);
//            if (sourceCell != null) {
//                copyCellValue(sourceCell, targetCell);
//            }
//        }
//    }
//
//    private static void copyCellValue(Cell sourceCell, Cell targetCell) {
//        switch (sourceCell.getCellType()) {
//            case STRING:
//                targetCell.setCellValue(sourceCell.getStringCellValue());
//                break;
//            case NUMERIC:
//                targetCell.setCellValue(sourceCell.getNumericCellValue());
//                break;
//            case BOOLEAN:
//                targetCell.setCellValue(sourceCell.getBooleanCellValue());
//                break;
//            case FORMULA:
//                targetCell.setCellFormula(sourceCell.getCellFormula());
//                break;
//            default:
//                // Handle other cell types as needed
//        }
//    }
//}
