import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yolo on 2019/8/7.
 */
public class RestClient {
    
    public  CloseableHttpResponse doPost(String url, String entityString, HashMap<String,String> headermap) throws IOException {
        //创建一个可关闭的HttpClient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(new StringEntity(entityString));
        //加载请求头到httppost对象
        for(Map.Entry<String, String> entry : headermap.entrySet()) {
            httppost.addHeader(entry.getKey(), entry.getValue());
        }
        //发送post请求
        CloseableHttpResponse httpResponse = httpclient.execute(httppost);

        return httpResponse;
    }
}
