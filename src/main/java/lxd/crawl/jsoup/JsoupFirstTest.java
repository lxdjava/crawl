package lxd.crawl.jsoup;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class JsoupFirstTest {
    @Test//jsoup解析UL
    public void testUrl() throws Exception {
        //解析URL
        Document document = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);

        //使用标签选择器获取title标签中的内容
        String title = document.getElementsByTag("title").first().text();

        System.out.println(title);
    }

    @Test//jsoup解析字符串
    public void testString() throws Exception{
        CloseableHttpClient hc  = HttpClients.createDefault();

        //创建HttpGet对象，设置url访问地址
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");

        CloseableHttpResponse response = null;
        String content =null;
        try {
            response = hc.execute((HttpUriRequest) httpGet);
            if (response.getStatusLine().getStatusCode() == 200){
                content = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                hc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Document document = Jsoup.parse(content);
        String title = document.getElementsByTag("title").first().text();

        System.out.println(title);
    }

    @Test//jsoup解析文件
    public void testFile() throws Exception{    }

    @Test//jsoup操作DOM
    public void testDOM() throws Exception{
        Document document = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);

        //操作DOM
        //1.    根据id查询元素getElementByID
        /*Element element = document.getElementById("webimclosebutton");
        System.out.println(element);*/
        //2.    根据标签获取元素getElementsByTag
        /*Elements elements = document.getElementsByTag("span");
        System.out.println(elements);*/
        //3.    根据class获取元素getElementsByClass
        /*Elements elements = document.getElementsByClass("s_name");
        System.out.println(elements.text());*/
        //4.    根据属性获取元素getElementsByAttribute
        Elements elementsByAttribute = document.getElementsByAttribute("id");
        System.out.println(elementsByAttribute);
        /*Elements elementsByAttributeValue = document.getElementsByAttributeValue("href", "http://www.itcast.cn/");
        System.out.println(elementsByAttributeValue);*/
    }

    @Test//jsoup元素选择器select
    public void testSelector() throws Exception{
        Document document = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);

        //tagname：通过标签查找元素，比如：span
        /*Elements elements = document.select("span");
        for (Element element: elements){
            System.out.println(element.text());
        }*/

        //#id：通过id查找元素，比如#video
        /*Element element = document.select("#video").first();
        System.out.println(element);*/

        //.class：通过class名称获取，比如.a_gd .innl
        /*Element element1 = document.select(".a_gd").first();
        System.out.println(element1);*/

        //[attribute]：利用属性查找元素，比如：[title]
        /*Element element = document.select("[title]").first();
        System.out.println(element);*/

        //[attr=value]：利用属性值来查找元素，比如：[class=s_name]
        Elements select = document.select("[href=http://www.itcast.cn/subject/webzly/index.shtml]");
        System.out.println(select.text());
    }

    @Test
    public void testSelector2() throws Exception{
        Document document = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);

        //el#id：元素+ID，比如：li#webimclosebutton

        //el.class：元素+class，比如：li.a_gd
        Elements select = document.select("li.a_gd.innl");
        System.out.println(select);

        //ancerstor child：查找某个父元素下的所有子元素，比如：.city_con li，查找"city_con"下的所有li

        //parent>child：查找某个父元素下的直接子元素，比如：.city_con>ul>li
        //parent>*：查找某个父元素下的所有直接子元素
    }
}
