package util;

import org.apache.commons.lang.time.DateUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by yolo on 2019/8/7.
 */
public class TestUtil {
    public final static String COOKIE = "65_admin=\"140003A592594F44,erp:1iMTSn:SfwSIsBbOwaicRAl9RKgQdzDlN4\"" ;
    //遍历excel
    public static Object[][] dtt(String filePath) throws IOException{

        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sh = wb.getSheetAt(0);
        int numberrow = sh.getPhysicalNumberOfRows();
        int numbercell = sh.getRow(0).getLastCellNum();

        Object[][] dttData = new Object[numberrow][numbercell];
        for(int i=0;i<numberrow;i++){
            if(null==sh.getRow(i)||"".equals(sh.getRow(i))){
                continue;
            }
            for(int j=0;j<numbercell;j++) {
                if(null==sh.getRow(i).getCell(j)||"".equals(sh.getRow(i).getCell(j))){
                    continue;
                }
                XSSFCell cell = sh.getRow(i).getCell(j);
                cell.setCellType(CellType.STRING);
                dttData[i][j] = cell.getStringCellValue();
            }
        }
        return dttData;
    }

    //遍历excel,sheet参数
    public static Object[][] dtt(String filePath,int sheetId) throws IOException {

        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sh = wb.getSheetAt(sheetId);
        int numberrow = sh.getPhysicalNumberOfRows();
        int numbercell = sh.getRow(0).getLastCellNum();

        Object[][] dttData = new Object[numberrow][numbercell];
        for(int i=0;i<numberrow;i++){
            if(null==sh.getRow(i)||"".equals(sh.getRow(i))){
                continue;
            }
            for(int j=0;j<numbercell;j++) {
                if(null==sh.getRow(i).getCell(j)||"".equals(sh.getRow(i).getCell(j))){
                    continue;
                }
                XSSFCell cell = sh.getRow(i).getCell(j);
                cell.setCellType(CellType.STRING);
                dttData[i][j] = cell.getStringCellValue();
            }
        }
        return dttData;
    }

    public static String getDate(String date){
        Calendar calendar = new GregorianCalendar(1900,0,-1);
        Date d = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd");
        Date dd = DateUtils.addDays(d,Integer.valueOf(date));

        String str_date = sdf.format(dd);
        return str_date;
    }

    /**
     * 获取当前天数的星期
     * @return
     */
    public static String getWeekByDate(String date){
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        int w=0;
        try{
            Calendar calendar = new GregorianCalendar(1900,0,-1);
            Date d = calendar.getTime();
            Date dd = DateUtils.addDays(d,Integer.valueOf(date));
            Calendar cal = Calendar.getInstance();
            cal.setTime(dd);
             w= cal.get(Calendar.DAY_OF_WEEK) - 1;
            if(w<0){
                w=0;
            }
            System.out.println(weekDays[w]);
        }catch (Exception e){
            e.printStackTrace();
        }
        return weekDays[w];
        
    }
    
    public static String getCurrentDate(){
        SimpleDateFormat d = new SimpleDateFormat();
        d.applyPattern("yyyy-MM-dd");
        Date nowdate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(nowdate);
        c.add(Calendar.DAY_OF_MONTH, 1);
        String current_date = d.format(c.getTime());//获取当前时间+1天
        return current_date;
    }
    //获取状态码
    public static int getStatusCode(CloseableHttpResponse closeableHttpResponse){
        int StatusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        return StatusCode;
    }
    
    public static Boolean isWeekend(){
        Date date = new Date();
        Calendar  calendar = Calendar.getInstance();
        calendar.setTime(date);
        if(calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            return true;
        }else{
            return false;
        }
    }
}