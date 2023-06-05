import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Validation {
    @Test
    public void certificatesValidation() throws InterruptedException, IOException {


        // Create a new instance of EdgeDriver
        EdgeOptions option = new EdgeOptions();
        option.addArguments("--remote-allow-origins=*");
        WebDriver driver = new EdgeDriver(option);
        driver.manage().window().maximize();
        JavascriptExecutor j = (JavascriptExecutor) driver;
        Duration timeout = Duration.ofSeconds(1000);

        Attributes attributes = new Attributes();



        // Navigate to the Salesforce login page
        driver.get(attributes.getURL());

        //Read Excel file
        File excelFile = new File(attributes.getExcelPath());
        FileInputStream inputStream = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        String Username = "";
        String Password ="";

        while (rowIterator.hasNext())
        {
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) {
                row.setRowNum(1);
                continue;
            }
            String filePath = "";
            if(!attributes.getCertificationPath().equals("False")) {
                filePath = attributes.getCertificationPath();
            }
            Username = row.getCell(1).toString();
            Password = row.getCell(2).toString();

            if(!attributes.getCertificationPath().equals("False")) {
                filePath += "/" + row.getCell(3).toString();
            }

            // Get URL
            String URL = j.executeScript("return document.URL;").toString();

            if(URL.equals(attributes.getURL()))
            {
                // Find the username and password fields and enter your credentials
                driver.findElement(By.id("username")).sendKeys(Username);
                driver.findElement(By.id("password")).sendKeys(Password);
                // Click the login button
                driver.findElement(By.id("Login")).click();
                //Wait until the searchbar appears
                WebDriverWait wait = new WebDriverWait(driver,timeout);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("brandBand_1")));

                // Click on Search bar and insert Certificate
                TimeUnit.SECONDS.sleep(4);
                WebElement paragraph = driver.findElement(By.id("brandBand_1"));
                List<WebElement> inputs = paragraph.findElements(By.tagName("input"));
                for (WebElement input : inputs) {
                    if ("Quick Find".equals(input.getAttribute("placeholder"))) {
                        input.sendKeys("cert");
                        break;
                    }
                }

                //Click on Certificate And Management Keys page
                TimeUnit.MILLISECONDS.sleep(500);
                driver.findElement(By.partialLinkText("Certificate")).click();
                TimeUnit.SECONDS.sleep(4);

            }

            TimeUnit.MILLISECONDS.sleep(5000);
            driver.switchTo().frame(0);
            List<WebElement> found = driver.findElements(By.partialLinkText("old"));
            if(found.size() == 0)
            {
                Functions.updateCell(attributes.getExcelPath(),0, row.getRowNum(), 5,"TRUE");


            }

            driver.switchTo().defaultContent();

            //logout
            Functions.logout(driver);



        }
        inputStream.close();
        workbook.close();


    }
}
