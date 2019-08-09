import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.TestUtil;

import java.io.IOException;
import java.util.HashMap;

import static util.TestUtil.dtt;

/**
 * Created by yolo on 2019/8/7.
 */
public class TestMessage {
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    String url="https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=3b35468f-0f4a-47d3-a30e-8681eca04c63";
    String excelData=System.getProperty("user.dir")+"/src/main/resources/ERP值班.xlsx";
    //header
    HashMap<String ,String> postHeader = new HashMap<String, String>();
    @BeforeClass
    public void setUp(){
        restClient = new RestClient();
        postHeader.put("Content-Type","application/json");
    }

    @DataProvider(name = "postData")
    public Object[][] post() throws IOException {
        return dtt(excelData,0);
    }

    @Test(dataProvider = "postData")
    public void callRobotTest(String user,String date, String phone) throws Exception {
        System.out.println("当前日期-----"+TestUtil.getCurrentDate());
        System.out.println("值班日期-----"+TestUtil.getDate(date));
        String msgJsonString = "{ \"msgtype\": \"text\",\"text\": {\"content\": \"ERP的bug群值班啦\",\"mentioned_mobile_list\":"+phone+"}}";
        //看当前日期和值班日期
        if(TestUtil.getCurrentDate().equals(TestUtil.getDate(date))){
            System.out.println("发送消息");
            closeableHttpResponse = restClient.doPost(url,msgJsonString,postHeader);
            int statusCode = TestUtil.getStatusCode(closeableHttpResponse);
            Assert.assertEquals(statusCode,0);
            Reporter.log("状态码："+statusCode,true);
        }
    }
}
