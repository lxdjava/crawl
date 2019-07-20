package lxd.crawl.HttpClient;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientExeute {
    public void execute(CloseableHttpClient hc,Object T){
        CloseableHttpResponse response = null;
        try {
            response = hc.execute((HttpUriRequest) T);
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
            try {
                hc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
