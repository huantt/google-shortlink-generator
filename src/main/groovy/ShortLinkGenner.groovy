import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.bson.Document

import java.util.concurrent.TimeUnit

/**
 * Created by huantt on 27/02/2017.
 */
class ShortLinkGenner {
    static String WINDOWS_UA = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.19 (KHTML, like Gecko) Chrome/1.0.154.53 Safari/525.19"

    static void main(String[] args) {
        String url = "https://www.googleapis.com/urlshortener/v1/url?key={YOUR_API_KEY}"

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build()

         MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody formBody = RequestBody.create(JSON, "{\"longUrl\": \"{link}"}");

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("User-Agent", WINDOWS_UA)
                .post(formBody)
                .build()

        def response = Document.parse(client.newCall(request).execute().body().string())
        println response.toJson()
    }
}
