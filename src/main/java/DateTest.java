import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yolo on 2019/8/7.
 */
public class DateTest {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat d = new SimpleDateFormat();
        d.applyPattern("yyyy-MM-dd");
        Date nowdate = new Date();
        String str_date = d.format(nowdate);
        System.out.println(str_date);
    }
}
