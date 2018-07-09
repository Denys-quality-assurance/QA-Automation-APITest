package api_testing.base;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.google.gson.Gson;

public class HttpClient {
	private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	
    public static HttpResponse get(String url, Header[] JSON) throws IOException {
        HttpGet getRequest = new HttpGet(url);
        getRequest.setHeaders(JSON);
        return httpClient.execute(getRequest);
    }
    
    public static HttpResponse post(String url, Header[] JSON, String body) throws IOException {
        HttpPost postRequest = new HttpPost(url);
        postRequest.setHeaders(JSON);
        postRequest.setEntity(new StringEntity(body, StandardCharsets.UTF_8));
        return httpClient.execute(postRequest);
    }

    public static HttpResponse put(String url, Header[] JSON, String body) throws IOException {
        HttpPut putRequest = new HttpPut(url);
        putRequest.setHeaders(JSON);
        putRequest.setEntity(new StringEntity(body, StandardCharsets.UTF_8));
        return httpClient.execute(putRequest);
    }

    public static HttpResponse delete(String url, Header[] JSON) throws IOException {
        HttpDelete deleteRequest = new HttpDelete(url);
        deleteRequest.setHeaders(JSON);
        return httpClient.execute(deleteRequest);
    }
}


