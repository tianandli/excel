package com.lj.excel.util;

import com.alibaba.fastjson.JSONObject;
import com.lj.excel.dao.ParamsEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpClientUtils {
    public static final String CONTENT_TYPE_JSON_CHARSET = "application/json; charset=UTF-8";
    private static final int CONNECT_TIMEOUT = 2000;
    private static final int SOCKET_TIMEOUT = 8000;
    private static final String DEFAULT_CHARSET_NAME = "UTF-8";
    private static final int MAX_CONNECTION_NUM = 2000;
    private static final int MAX_PER_ROUTE = 100;
    private static final Charset DEFAULT_CHARSET = Charset.forName(DEFAULT_CHARSET_NAME);
    private static PoolingHttpClientConnectionManager CONN_MANAGER = null;
    private static RequestConfig DEFAULT_REQUEST_CONFIG = null;

    static{
        try {
            SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
            sslContextBuilder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build());
            PlainConnectionSocketFactory plainConnectionSocketFactory = new PlainConnectionSocketFactory();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                    .register("https", sslConnectionSocketFactory)
                    .register("http", plainConnectionSocketFactory)
                    .build();
            CONN_MANAGER = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            CONN_MANAGER.setMaxTotal(MAX_CONNECTION_NUM);
            CONN_MANAGER.setDefaultMaxPerRoute(MAX_PER_ROUTE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CloseableHttpClient getHttpClient(){
        //使用连接池
        return HttpClients.custom().setConnectionManager(CONN_MANAGER).build();
//        不使用连接池
//        return HttpClients.createDefault();
    }

    public static CloseableHttpClient getHttpClient(RequestConfig requestConfig){
        //使用连接池
        return HttpClients.custom().setConnectionManager(CONN_MANAGER).setDefaultRequestConfig(requestConfig).build();
//        不使用连接池
//        return HttpClients.createDefault();
    }

    public static String dePost(String url, String paramJsonStr) throws Exception {
        return dePost(url, null, paramJsonStr, null);
    }

    public static String dePost(String url, Map<String, String> headerMap, String paramJsonStr) throws Exception {
        return dePost(url, headerMap, paramJsonStr, null);
    }

    public static String dePost(String url, Map<String, String> headerMap, String paramJsonStr, RequestConfig requestConfig)
            throws Exception {
        StringEntity stringEntity = new StringEntity(paramJsonStr, DEFAULT_CHARSET);
        stringEntity.setContentType(CONTENT_TYPE_JSON_CHARSET);

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(stringEntity);

        if (requestConfig != null) {
            httpPost.setConfig(requestConfig);
        }
        if (headerMap != null) {
            for (Iterator<Map.Entry<String, String>> it = headerMap.entrySet().iterator(); it.hasNext();) {
                Map.Entry<String, String> entry = it.next();
                httpPost.addHeader(entry.getKey(), entry.getValue());
            }
        }

        CloseableHttpResponse httpResponse = null;
        try {
            CloseableHttpClient httpClient = getHttpClient();
            httpResponse = httpClient.execute(httpPost);

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String respText = EntityUtils.toString(httpResponse.getEntity(), DEFAULT_CHARSET);
                EntityUtils.consume(httpResponse.getEntity());
                return respText;
            }else{
                return "http接口请求异常， statusCode = " + statusCode;
            }
        } catch (IOException e) {
            return "http接口请求异常";
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            httpPost.releaseConnection();
        }
    }

    public static void main(String[] args){
        System.out.println(queryPandora());
    }

    public static String queryPandora() {
        try {
            ParamsEntity entity = new ParamsEntity();
            entity.set_user_id("LA016220201020yhOE37Rd");
            entity.set_key_string_("wifimac");

            String params = JSONObject.toJSONString(entity);
            String url = "http://12.99.215.45:8050/customVar/query/wifimac";
            String response = dePost(url, params);
            HashMap hashMap = JSONObject.parseObject(response, HashMap.class);
            return hashMap.get("wifimac").toString();
        } catch (Exception e) {
            return "接口调用失败";
        }
    }
}
