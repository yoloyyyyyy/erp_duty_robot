import net.sf.json.JSONObject;
import org.apache.http.client.methods.CloseableHttpResponse;
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
    String erp_url="https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=03ace3df-6169-4a82-ba66-645810dbcd7f";
    String duty_url="https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=34234527-b46a-4dca-98c5-810f682a4f10";
    String excelData=System.getProperty("user.dir")+"/src/main/resources/ERP值班.xlsx";
    //header
    HashMap<String ,String> postHeader = new HashMap<String, String>();
    @BeforeClass
    public void setUp(){
        restClient = new RestClient();
        postHeader.put("charset", "utf-8");
        postHeader.put("Content-Type","application/json");
    }

    @DataProvider(name = "postData")
    public Object[][] post() throws IOException {
        return dtt(excelData,0);
    }

    @Test(dataProvider = "postData")
    public void callRobotTest(String user,String date, String phone) throws Exception {
        System.out.println("当前日期加一天1-----"+TestUtil.getNextDate());
        System.out.println("值班日期-----"+TestUtil.getDate(date));
        if(TestUtil.getNextDate().equals(TestUtil.getDate(date))){
            JSONObject msgJsonString = new JSONObject();
            JSONObject jsonObject2 = new JSONObject();
            msgJsonString.put("msgtype","text");
            jsonObject2.put("mentioned_mobile_list",phone);
            if(TestUtil.getWeekByDate(date).equals("星期六") || TestUtil.getWeekByDate(date).equals("星期日")){
                jsonObject2.put("content","明天是"+TestUtil.getWeekByDate(date)+"~~~ERP值班别忘记了");
            }else {
                jsonObject2.put("content","明天ERP值班啦");
            }
            msgJsonString.put("text",jsonObject2);
            System.out.println(msgJsonString);
/*            closeableHttpResponse = restClient.doPost(duty_url,msgJsonString,postHeader);
            int statusCode = TestUtil.getStatusCode(closeableHttpResponse);
            Assert.assertEquals(statusCode,200);
            Reporter.log("状态码："+statusCode,true);*/
        }else{
            System.out.println("明天不值班");
        }
    }
    
    @Test
    public void taskRemindTest() throws IOException{
        JSONObject msgJsonString = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        msgJsonString.put("msgtype","text");
        jsonObject2.put("content","下班前记得填下任务花费，报进度~~");
        msgJsonString.put("text",jsonObject2);
        System.out.println(msgJsonString);
        if (TestUtil.isWeekend()){
            Reporter.log(TestUtil.getNextDate()+"今天是周末");
        }else {
/*            closeableHttpResponse = restClient.doPost(erp_url,msgJsonString,postHeader);
            int statusCode = TestUtil.getStatusCode(closeableHttpResponse);
            Assert.assertEquals(statusCode,200);
            Reporter.log("状态码："+statusCode,true); */
        }
    }
}
