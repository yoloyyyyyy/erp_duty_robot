import com.jayway.jsonpath.JsonPath;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.TestUtil;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by yolo on 2019/10/17.
 */
public class TestBranch {
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    String url = "http://uerp.65emall.net/api/mithril.TMS/ParcelDetail";
    HashMap<String ,String> postHeader = new HashMap<String, String>();
    @BeforeClass
    public void setUp(){
        restClient = new RestClient();
        postHeader.put("charset", "utf-8");
        postHeader.put("Content-Type","application/json");
        postHeader.put("Cookie","65_admin=\"140003A592594F44,erp:1iLIUA:jKAmajKPTHytmg3IZKt8evCr3PI\"");
    }
    
    @Test
    public void parcelDetailTest() throws IOException {
        JSONObject msgJsonString = new JSONObject();
        msgJsonString.put("parcelCode","QIB171440852");
        closeableHttpResponse = restClient.doPost(url,msgJsonString,postHeader);
        HttpEntity httpEntity = closeableHttpResponse.getEntity();
        String responseEntity = EntityUtils.toString(httpEntity);
        String parcelStatus = JsonPath.read(responseEntity,"$.info.parcelStatus");//返回结果获取对应的值
        int statusCode = TestUtil.getStatusCode(closeableHttpResponse);
        Assert.assertEquals(statusCode,200);
        Reporter.log("状态码："+statusCode,true);
        Assert.assertEquals(parcelStatus,"9000");
        Reporter.log("包裹状态："+parcelStatus,true);
    }
}
