import com.jayway.jsonpath.JsonPath;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

import static util.TestUtil.COOKIE;

/**
 * Created by yolo on 2019/10/21.
 */
public class OrderDetail {
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    String url = "http://uerp.65emall.net/api/oms.Oms/OrderDetail";
    HashMap<String ,String> postHeader = new HashMap<String, String>();
    @BeforeClass
    public void setUp(){
        restClient = new RestClient();
        postHeader.put("charset", "utf-8");
        postHeader.put("Content-Type","application/json");
        postHeader.put("Cookie",COOKIE);
    }
    
    @Test
    public void orderDetailTest() throws IOException{
        JSONObject msgJsonString = new JSONObject();
        msgJsonString.put("skuNo","26360471854552320-1");
        closeableHttpResponse = restClient.doPost(url,msgJsonString,postHeader);
        HttpEntity httpEntity = closeableHttpResponse.getEntity();
        String responseEntity = EntityUtils.toString(httpEntity);
        String shipmentTypeId = JsonPath.read(responseEntity,"$.shipmentTypeId");
        Assert.assertEquals(shipmentTypeId,"7");
        Reporter.log("运输类型id："+shipmentTypeId,true);
    }
}
