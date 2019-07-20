package lxd.crawl.HttpClient;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpGetParamTest {
    public static void main(String[] args) throws Exception {
        //创建HttpClient对象
        CloseableHttpClient hc = HttpClients.createDefault();

        //设置请求地址是https://search.bilibili.com/all?keyword=java%E7%88%AC%E8%99%AB&from_source=banner_search

        //1、创建URIBuilder
        URIBuilder uriBuilder = new URIBuilder("https://search.bilibili.com/all");
        //2、设置参数
        uriBuilder.setParameter("keyword", "java爬虫").setParameter("from_source", "banner_search");
        //创建HttpGet对象，设置url访问地址
        HttpGet httpGet = new HttpGet(uriBuilder.build());


        new HttpClientExeute().execute(hc, httpGet);
    }
}