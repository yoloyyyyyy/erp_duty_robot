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

import static util.TestUtil.COOKIE;

/**
 * Created by yolo on 2019/10/21.
 */
public class SearchOrder {
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    String url = "http://uerp.65emall.net/api/oms.Oms/SearchOrder";
    HashMap<String ,String> postHeader = new HashMap<String, String>();
    @BeforeClass
    public void setUp(){
        restClient = new RestClient();
        postHeader.put("charset", "utf-8");
        postHeader.put("Content-Type","application/json");
        postHeader.put("Cookie",COOKIE);
    }
    @Test
    public void searchOrderTest()throws IOException {
        JSONObject msgJsonString = new JSONObject();
        msgJsonString.put("limit","20");
        msgJsonString.put("offset","0");
        msgJsonString.put("payTypeId","-1");
        msgJsonString.put("partyId","1");
        msgJsonString.put("isASC",false);
        msgJsonString.put("orderType","OrderItemTypeNone");
        msgJsonString.put("regionId","100000000");
        msgJsonString.put("skuNo","26367741992633856-1");
        msgJsonString.put("startPayDate","1563638400");
        msgJsonString.put("endPayDate","1571673599");
        closeableHttpResponse = restClient.doPost(url,msgJsonString,postHeader);

        HttpEntity httpEntity = closeableHttpResponse.getEntity();
        String responseEntity = EntityUtils.toString(httpEntity);
        String itemStatus = JsonPath.read(responseEntity,"$.items[0].status");//获取item状态
        Boolean isCustomize = JsonPath.read(responseEntity,"$.items[0].isCustomize");//获取是否定制品
        Boolean unCustomize = false;
        int statusCode = TestUtil.getStatusCode(closeableHttpResponse);
        Assert.assertEquals(statusCode,200);
        Reporter.log("状态码："+statusCode,true);
        Assert.assertEquals(itemStatus,"2700");
        Reporter.log("订单状态："+itemStatus,true);
        Assert.assertEquals(isCustomize,unCustomize);
        Reporter.log("定制品："+isCustomize,true);
    }
}
