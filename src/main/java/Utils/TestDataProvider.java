package Utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

public class TestDataProvider {

    @DataProvider(name = "TestData")
    public static Object[][] getTestDatafromExcel() throws IOException, GeneralSecurityException {

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = TestDataProvider.class.getResourceAsStream("/TestData.xlsx");
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        Row row = null;
        int totalRows = sheet.getLastRowNum();
        int totalObj = (totalRows);
        System.out.println("Value of totalObj "+totalObj);
        Object[][] ob = new Object[totalObj][2];

        System.out.println("Total rows " + totalRows);


        for (int j = 1; j <= totalRows; j++) {
            row = sheet.getRow(j);
            String name = row.getCell(0).getStringCellValue();
            String job = row.getCell(1).getStringCellValue();

            ob[j - 1][0] = name;
            ob[j - 1][1] = job;

        }
        return ob;

    }
}
