import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Functions {

    public static boolean isElementPresent(WebDriver driver, By elem) {
        try {
            driver.findElement(elem);
            return true;
        }catch(NoSuchElementException e) {
            return false;
        }
    }

    public static void logout(WebDriver driver) throws InterruptedException {
        //Click on Profile button
        TimeUnit.SECONDS.sleep(3);
        driver.findElement(By.xpath("//button[contains(@class,'branding-userProfile-button')]")).click();
        TimeUnit.MILLISECONDS.sleep(500);


        //Click on logout button
        TimeUnit.MILLISECONDS.sleep(500);
        driver.findElement(By.xpath("//a[contains(@class,'logout')]")).click();
        TimeUnit.MILLISECONDS.sleep(500);

        // Navigate to login page
        TimeUnit.MILLISECONDS.sleep(1000);
        driver.navigate().to( new Attributes().getURL());
        TimeUnit.MILLISECONDS.sleep(1000);
    }

    public static void updateCell(String filePath, int sheetIndex, int rowNum, int colNum, String newValue) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);

            // Load the workbook
            Workbook workbook = new XSSFWorkbook(fileInputStream);

            // Get the sheet by name
            Sheet sheet = workbook.getSheetAt(sheetIndex);

            // Get the row and cell
            Row row = sheet.getRow(rowNum);
            Cell cell = row.getCell(colNum);

            // Update the cell value
            cell.setCellValue(newValue);

            // Save the changes back to the Excel file
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);

            // Close the streams
            fileOutputStream.close();
            fileInputStream.close();

            System.out.println("Cell updated successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

