import com.mashape.unirest.http.exceptions.UnirestException;
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by huantt on 27/02/2017.
 */
public class Test {
    final static String WINDOWS_UA = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.19 (KHTML, like Gecko) Chrome/1.0.154.53 Safari/525.19";

    public static void main(String[] args) throws UnirestException, IOException {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

       MediaType JSON= MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(JSON, "{\"longUrl\": \n" +
                "\"http://kiemtien.adflex.vn?mobile=0982678471\"\n" +
                "}");
        Request request = new Request.Builder()
                .url("https://www.googleapis.com/urlshortener/v1/url?key=AIzaSyD1nJZnOe7gw_-XXFeT7zicpIYuXhsf230")
                .addHeader("User-Agent", WINDOWS_UA)
                .addHeader("Content-Type","application/json")
                .post(requestBody)
                .build();

        System.out.println(client.newCall(request).execute().body().string());
    }
}
