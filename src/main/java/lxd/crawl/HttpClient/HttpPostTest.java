package lxd.crawl.HttpClient;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpPostTest {
    public static void main(String[] args) {
        //创建HttpClient对象
        CloseableHttpClient hc  = HttpClients.createDefault();

        //创建HttpPost对象，设置url访问地址
        HttpPost httpPost = new HttpPost("http://www.itcast.cn");

        new HttpClientExeute().execute(hc,httpPost);
    }
}
