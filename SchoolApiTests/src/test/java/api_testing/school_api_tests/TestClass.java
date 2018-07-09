package api_testing.school_api_tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import api_testing.base.HttpClient;




public class TestClass {


	
	public static String baseURL = "https://www.facebook.com/linda.colombrita.37?fref=grp_mmbr_list";
    public static final Header JSONHeader[] = {
			new BasicHeader("accept", "text/html"),
			new BasicHeader("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.170 Safari/537.36 OPR/53.0.2907.110")
			};
	
	private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();
	
	public static HttpResponse get(String url, Header[] JSON) throws IOException {
        HttpGet getRequest = new HttpGet(url);
        getRequest.setHeaders(JSON);
        return httpClient.execute(getRequest);
	}
	
	@Test(description = "Experimental test", groups = { "disabled" })
	public static void Response() throws IOException {
		HttpResponse response = HttpClient.get(baseURL, JSONHeader);
		
/*		File newLog = new File("NewLogs.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(newLog));
		writer.write(EntityUtils.toString(response.getEntity()));
		writer.close();*/
		
		//System.out.println(!EntityUtils.toString(response.getEntity()).contains("id: '"+1+"'"));

		//System.out.println(response.getStatusLine().toString());
		
		
		//System.out.println(EntityUtils.toString(response.getEntity()));		
		
		
		Pattern regex = Pattern.compile("(?s)^.*\"entity_id\":\"(.*?)\".*");
		Matcher matcher = regex.matcher(EntityUtils.toString(response.getEntity()));
		String ID = matcher.group(1);
		System.out.println(ID);
		System.out.println(response.getParams().toString());
		System.out.println(response.getAllHeaders().toString());
		System.out.println(response.getFirstHeader("Content-type").toString());		

    	int expectedResponseCode = 200 ;
    	Assert.assertEquals(response.getStatusLine().getStatusCode(), expectedResponseCode);
	}
	
}
	
