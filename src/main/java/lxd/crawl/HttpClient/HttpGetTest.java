package lxd.crawl.HttpClient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpGetTest {
    public static void main(String[] args) {
        //创建HttpClient对象
        CloseableHttpClient hc  = HttpClients.createDefault();

        //创建HttpGet对象，设置url访问地址
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");

        new HttpClientExeute().execute(hc, httpGet);
    }
}
