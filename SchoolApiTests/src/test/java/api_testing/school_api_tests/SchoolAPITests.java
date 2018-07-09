package api_testing.school_api_tests;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api_testing.base.HttpClient;
import api_testing.base.Tools;

public class SchoolAPITests {
	
	int userID;
	//For editing 3 last users IDs
	int i = 0;
	
	@DataProvider
	public Object[][] newUserData() {
    return new Object[][]{{SchoolAPIVars.newStudentData}, {SchoolAPIVars.newAdministratorData}, {SchoolAPIVars.newSupportData}};
	}
	
	@DataProvider
	public Object[][] editedUserData() {
    return new Object[][]{{SchoolAPIVars.editedSupportData}, {SchoolAPIVars.editedAdministratorData}, {SchoolAPIVars.editedStudentData}};
	}

    public void CorrectResponseContentType(HttpResponse response) throws IOException {
    	String expectedResponseContentType = "Content-type: application/json";
    	Assert.assertTrue(Tools.getContentType(response).contains(expectedResponseContentType));
    }
    
    @Test(description = "1. Correct Initial Server State", priority = 1, groups = { "Sanity" })
    public void InitialServerState() throws IOException {
    	HttpResponse ActualResponse = HttpClient.get(SchoolAPIVars.baseURL, SchoolAPIVars.CorrectHeader);
    	Assert.assertTrue(Tools.getData(ActualResponse)[1].contains(SchoolAPIVars.InitialServerState));
    	CorrectResponseContentType(ActualResponse);
    } 
    
    @Test(description = "2. Create New Users", priority = 2, dataProvider = "newUserData",  groups = { "Sanity" })
    public void ResponseToCreateNewUser(String userInformation) throws IOException {
    	HttpResponse ActualResponse = HttpClient.post(SchoolAPIVars.baseURL, SchoolAPIVars.CorrectHeader, userInformation);
    	String responseData = Tools.getData(ActualResponse)[1];
    	Assert.assertTrue(responseData.contains("id"));
    	CorrectResponseContentType(ActualResponse);
    	userID = Tools.getUserID(responseData);
    }
    
    @Test(description = "3. Save User", dependsOnMethods = { "ResponseToCreateNewUser" }, priority = 3, dataProvider = "editedUserData", groups = { "Sanity" })    
    public void ResponseToSaveUser(String userInformation) throws IOException {
    	String expectedResponseContent = "{\"id\":\"" + (userID-i) + "\","+userInformation;
    	if (userInformation == SchoolAPIVars.editedStudentData) {
    		userInformation = expectedResponseContent+"}";    		
        } else {
    	userInformation = expectedResponseContent;
    	}
    	HttpClient.put(SchoolAPIVars.baseURL+"/"+(userID-i), SchoolAPIVars.CorrectHeader, userInformation);
    	HttpResponse ActualResponse = HttpClient.get(SchoolAPIVars.baseURL, SchoolAPIVars.CorrectHeader);
    	Assert.assertTrue(Tools.getData(ActualResponse)[1].contains(expectedResponseContent));
    	CorrectResponseContentType(ActualResponse);
    	i++; 
    }
    
    @Test(description = "4. Delete User", dependsOnMethods = { "ResponseToSaveUser" }, priority = 4, groups = { "Sanity" })
    public void ResponseToUserDelete() throws IOException {
    	String UNexpectedResponseContent = "\"id\":\""+userID+"\"";
		HttpClient.delete(SchoolAPIVars.baseURL+"/"+userID, SchoolAPIVars.CorrectHeader);
		HttpResponse ActualResponse = HttpClient.get(SchoolAPIVars.baseURL, SchoolAPIVars.CorrectHeader);
    	Assert.assertFalse(Tools.getData(ActualResponse)[1].contains(UNexpectedResponseContent));
    } 
    
    @Test(description = "5. Incorrect Role in POST Request", priority = 5, groups = { "Sanity" })
    public void ResponseToIncorrectRole() throws IOException {
    	int expectedResponseCode = 401;
    	HttpResponse ActualResponse = HttpClient.post(SchoolAPIVars.baseURL, SchoolAPIVars.CorrectHeader,  SchoolAPIVars.IncorrectData);
    	Assert.assertEquals(Tools.getStatusCode(ActualResponse), expectedResponseCode);
    	CorrectResponseContentType(ActualResponse);
    }
    
    @Test(description = "6. Incorrect Header in GET Request", priority = 6, groups = { "Sanity" })
    public void ResponseToIncorrectHeader() throws IOException {
    	int expectedResponseCode = 401;
    	HttpResponse ActualResponse = HttpClient.get(SchoolAPIVars.baseURL, SchoolAPIVars.IncorrectHeader);
    	Assert.assertEquals(Tools.getStatusCode(ActualResponse), expectedResponseCode);
    	CorrectResponseContentType(ActualResponse);
    }    
        
/*    @Test(description = "7. Save Incorrect User", priority = 7, groups = { "Sanity" })
    public void ResponseToSaveIncorrectUser() throws IOException {
    	int expectedResponseCode = 404;
    	String userInformation = "{\"id\":\"" + (userID+10000) + "\","+SchoolAPIVars.editedSupportData;
    	HttpResponse ActualResponse = HttpClient.put(SchoolAPIVars.baseURL+"/"+userID+10000, SchoolAPIVars.CorrectHeader, userInformation);
    	Assert.assertEquals(Tools.getStatusCode(ActualResponse), expectedResponseCode);
    	CorrectResponseContentType(ActualResponse);
    }

    @Test(description = "8. Delete Incorrect User", priority = 8, groups = { "Sanity" })
    public void ResponseToDeleteIncorrectUser() throws IOException {
    	int expectedResponseCode = 404;
    	HttpResponse ActualResponse = HttpClient.delete(SchoolAPIVars.baseURL+"/"+(userID+10000), SchoolAPIVars.CorrectHeader);
    	Assert.assertEquals(Tools.getStatusCode(ActualResponse), expectedResponseCode);
    	CorrectResponseContentType(ActualResponse);
    }*/
}