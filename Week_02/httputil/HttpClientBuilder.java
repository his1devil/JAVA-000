package httputil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author qiujun
 * @Version @version $Id: HttpClientBuilder, v0.1 2020-10-28 5:29 下午 qiujun Exp $
 * @Description
 */
public class HttpClientBuilder {

    public static void buildClient(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            OkHttpClient copy = client.newBuilder().readTimeout(500, TimeUnit.MILLISECONDS).build();
            Response response = copy.newCall(request).execute();
            System.out.println("Response succeeded: " + response);
        } catch (IOException e) {
            System.out.println("Response failed: " + e);
        }
    }

    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8801";
       buildClient(url);
    }
}
