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
 * Created by yolo on 2019/10/18.
 */
public class Login {
    private RestClient restClient;
    private CloseableHttpResponse closeableHttpResponse;
    String url = "http://sg-en-web-api.65emall.net/api/nadesico.Customer/Login";
    private HashMap<String ,String> postHeader = new HashMap<String, String>();
    @BeforeClass
    public void setUp(){
        restClient = new RestClient();
        postHeader.put("charset", "utf-8");
        postHeader.put("Content-Type","application/json");
    }
    
    @Test
    public void loginTest() throws IOException {
        JSONObject msgJsonString = new JSONObject();
        JSONObject msgJsonString1 = new JSONObject();
        JSONObject msgJsonString2 = new JSONObject();
        msgJsonString2.put("platform","Website");
        msgJsonString1.put("device",msgJsonString2);
        msgJsonString.put("nicknameOrEmail","sgys301");
        msgJsonString.put("password","111111");
        msgJsonString.put("region","RegionSG");
        msgJsonString.put("meta",msgJsonString1);
        closeableHttpResponse = restClient.doPost(url,msgJsonString,postHeader);
        int statusCode = TestUtil.getStatusCode(closeableHttpResponse);
        Assert.assertEquals(statusCode,200);
        Reporter.log("状态码："+statusCode,true);

        HttpEntity httpEntity = closeableHttpResponse.getEntity();
        String entity = EntityUtils.toString(httpEntity);
        System.out.println(entity);
        String cookie = JsonPath.read(entity,"$.cookie");
        System.out.println(cookie);
    }
    
    public String getCookie() throws IOException {
        JSONObject msgJsonString = new JSONObject();
        JSONObject msgJsonString1 = new JSONObject();
        JSONObject msgJsonString2 = new JSONObject();
        msgJsonString2.put("platform","Website");
        msgJsonString1.put("device",msgJsonString2);
        msgJsonString.put("nicknameOrEmail","sgys301");
        msgJsonString.put("password","111111");
        msgJsonString.put("region","RegionSG");
        msgJsonString.put("meta",msgJsonString1);
        closeableHttpResponse = restClient.doPost(url,msgJsonString,postHeader);
        HttpEntity httpEntity = closeableHttpResponse.getEntity();
        String entity = EntityUtils.toString(httpEntity);
        String cookie = JsonPath.read(entity,"$.cookie");
        return cookie;
    }
}
