package lxd.crawl.HttpClient;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class HttpPostParamTest {
    public static void main(String[] args) throws Exception {
        //创建HttpClient对象
        CloseableHttpClient hc  = HttpClients.createDefault();

        //设置请求地址是https://search.bilibili.com/all?keyword=java%E7%88%AC%E8%99%AB&from_source=banner_search
        HttpPost httpPost =new HttpPost("https://yun.itheima.com/search");
        //声明List集合，封装表单中的参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("keys","java"));


        //创建表单的Entity对象,params是表单数据，"utf8"是编码
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params,"utf8");

        //设置表单但Entity对象到Post请求中
        httpPost.setEntity(formEntity);

        new HttpClientExeute().execute(hc,httpPost);
    }
}