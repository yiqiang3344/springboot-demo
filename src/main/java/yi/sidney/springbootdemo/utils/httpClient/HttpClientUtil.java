package yi.sidney.springbootdemo.utils.httpClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.log4j.Log4j2;
import net.sf.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class HttpClientUtil {

    public static String doGet(String url, Map<String, String> param, int timeout) throws IOException, URISyntaxException {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        Map<String, String> requestLogMap = new HashMap<String, String>();
        requestLogMap.put("url", url);
        requestLogMap.put("timeout", String.valueOf(timeout));
        requestLogMap.put("body", JSONObject.fromObject(param).toString());
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
            httpGet.setConfig(requestConfig);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
            requestLogMap.put("response", resultString);
        } catch (Exception e) {
            e.printStackTrace();
            requestLogMap.put("error", e.getLocalizedMessage());
            throw e;
        } finally {
            log.info(JSONObject.fromObject(requestLogMap).toString());
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    public static String doGet(String url, Map<String, String> param) throws IOException, URISyntaxException {

        return doGet(url, param, 5000);
    }

    public static String doGet(String url) throws IOException, URISyntaxException {

        return doGet(url, null, 5000);
    }

    public static String doPost(String url, Map<String, String> param, int timeout) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        Map<String, String> requestLogMap = new HashMap<String, String>();
        requestLogMap.put("url", url);
        requestLogMap.put("timeout", String.valueOf(timeout));
        requestLogMap.put("body", JSONObject.fromObject(param).toString());
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
            httpPost.setConfig(requestConfig);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            requestLogMap.put("response", resultString);
        } catch (Exception e) {
            e.printStackTrace();
            requestLogMap.put("error", e.getLocalizedMessage());
            throw e;
        } finally {
            log.info(JSONObject.fromObject(requestLogMap).toString());
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPost(String url, Map<String, String> param) throws IOException {

        return doPost(url, param, 5000);
    }

    public static String doPost(String url) throws IOException {

        return doPost(url, null, 5000);
    }

    public static String doPostJson(String url, String json, int timeout) throws Exception {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        Map<String, String> requestLogMap = new HashMap<String, String>();
        requestLogMap.put("url", url);
        requestLogMap.put("timeout", String.valueOf(timeout));
        requestLogMap.put("body", json);
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(timeout).setConnectionRequestTimeout(timeout).setSocketTimeout(timeout).build();
            httpPost.setConfig(requestConfig);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
            requestLogMap.put("response", resultString);
        } catch (Exception e) {
            e.printStackTrace();
            requestLogMap.put("error", e.getLocalizedMessage());
            throw e;
        } finally {
            log.info(JSONObject.fromObject(requestLogMap).toString());
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPostJson(String url, String json) throws Exception {

        return doPostJson(url, json, 5000);
    }
}
