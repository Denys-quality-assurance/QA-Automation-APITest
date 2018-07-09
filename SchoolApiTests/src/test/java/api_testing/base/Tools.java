package api_testing.base;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;


public class Tools {
	
	//Content Type Header in Response (in every Tests)
	public static String getContentType(HttpResponse response) throws IOException {
		return response.getFirstHeader("Content-type").toString();
	}
	
	//StatusCode in Response 
	public static int getStatusCode(HttpResponse response) throws IOException {
		return response.getStatusLine().getStatusCode();
	}
	
	//Data in Response 
	public static String[] getData(HttpResponse response) throws IOException {
	    HttpEntity entity = response.getEntity();
	    String[] responseData = new String[2];
	    responseData[0] = response.getEntity().toString();
	    responseData[1] = entity != null ? EntityUtils.toString(entity) : "No response data.";
	    return responseData;
	}
	
	//User ID in Response
	public static int getUserID(String responseData) throws IOException {
		String regex = ".*id\":\"(.*)\".*";
		return Integer.parseInt(responseData.replaceAll(regex, "$1"));
	}
	
	public static String timeStamp() {
    	return new SimpleDateFormat("dd/MM/yy_HH_mm").format(new Date());
   }
}
