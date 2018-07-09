package api_testing.school_api_tests;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

import api_testing.base.Tools;

public interface SchoolAPIVars {

    //URLs
    public static final String baseURL = "http://127.0.0.1:20007/api/users";
    public static final String adminURL = "http://127.0.0.1:20007/refreshAdmins";
    //User Name
    public static final String newName = "Denys TestName";
    public static final String editedName = "Edited ReName";
    //JSON
    public static final Header IncorrectHeader[] = {
			new BasicHeader("Content-type", "text/html")
	};
    public static final Header CorrectHeader[] = {
			new BasicHeader("Content-type", "application/json")
	};
    
    public static final String InitialServerState = 
			"{\"id\":\"1\",\"name\":\"James Logan Howlett\",\"phone\":\"+380670000003\",\"role\":\"Administrator\"},"
			+ "{\"id\":\"2\",\"name\":\"Charles Xavier\",\"phone\":\"+380670000002\",\"role\":\"Student\",\"strikes\":1},"
			+ "{\"id\":\"3\",\"name\":\"Erik Lehnsherr\",\"phone\":\"+380670000001\",\"role\":\"Support\",\"location\":\"Kiev\"}";
	
    public static final String newStudentData = "{\"name\":\"" + newName + "\",\"phone\":\"+380672222222\",\"strikes\":2}";
    public static final String newAdministratorData = "{\"name\":\"" + newName + "\",\"phone\":\"+380672222222\",\"role\":\"Administrator\"}";
    public static final String newSupportData = "{\"name\":\"" + newName + "\",\"phone\":\"+380672222222\",\"role\":\"Support\",\"location\":\"Kiev\"}";
    public static final String IncorrectData = "{\"name\":\"" + newName + "\",\"phone\":\"+380672222222\",\"role\":\"IncorrectRole\"}";
    public static final String editedAdministratorData = "\"name\":\"" + editedName + "\",\"phone\":\"+380674444444\",\"role\":\"Administrator\"}";
    public static final String editedSupportData = "\"name\":\"" + editedName + "\",\"phone\":\"+380674444444\",\"role\":\"Support\",\"location\":\"Kiev\"}";    
	//Without ending with "}" for finding data with adding role "Student" 
    public static final String editedStudentData = "\"name\":\"" + editedName + "\",\"phone\":\"+380674444444\",\"strikes\":4";

}