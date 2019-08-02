package lxd.crawl.HttpClient;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientPoolTest {
    public static void main(String[] args) throws Exception {
        //创建连接池管理器
        PoolingHttpClientConnectionManager httpPool = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        httpPool.setMaxTotal(100);
        //设置每个host的最大连接数，也可以说每个网址
        httpPool.setDefaultMaxPerRoute(10);

        //使用连接池管理器发送请求
        doGet(httpPool);
        doGet(httpPool);
    }

    private static void doGet(PoolingHttpClientConnectionManager httpPool) throws Exception {
        //不再创建新的HttpClient，而是从连接池中获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(httpPool).build();
        //配置请求信息
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)//设置连接的最长时间，单位是毫秒
                .setConnectionRequestTimeout(500)//设置获取连接的最长时间
                .setSocketTimeout(10*1000)//设置传输的最长时间
                .build();

        //设置请求地址是https://search.bilibili.com/all?keyword=java%E7%88%AC%E8%99%AB&from_source=banner_search
        HttpPost httpPost =new HttpPost("https://yun.itheima.com/search");

        httpPost.setConfig(config);

        //声明List集合，封装表单中的参数
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("keys","java"));


        //创建表单的Entity对象,params是表单数据，"utf8"是编码
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params,"utf8");

        //设置表单但Entity对象到Post请求中
        httpPost.setEntity(formEntity);

        //new HttpClientExeute().execute(httpClient,httpPost);
        //可以关闭response，不能关闭HttpClient
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity());
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
