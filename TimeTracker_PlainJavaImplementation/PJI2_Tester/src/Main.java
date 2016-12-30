import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;



public class Main {
	
	private static Client client;
	private static String REST_SERVICE_URL = "http://localhost:13080/PJI2/rest/UserService/users";
    private static final String SUCCESS_RESULT="<result>success</result>";
    private static final String PASS = "pass";
    private static final String FAIL = "fail";
	
	public static void init(){
		client = ClientBuilder.newClient();
	}
	
	public static void test_registerUser(){
		Form form = new Form(); 
		
		form.param("id", "13");
		form.param("firstName", "hugo");
		form.param("lastName", "hugostin");
		form.param("email", "hugo.hugosting@g3tfvckdmail.gf");
		form.param("password", "passwort");
		
		String callResult = client.target(REST_SERVICE_URL)
							.request(MediaType.APPLICATION_XML)
							.put(Entity.entity(form,
									MediaType.APPLICATION_FORM_URLENCODED_TYPE),
									String.class);
		
		int dummy = 2;
		dummy++;
	}
	
	public static void main(String[] args){
		init();
		test_registerUser();
	}
	
}
