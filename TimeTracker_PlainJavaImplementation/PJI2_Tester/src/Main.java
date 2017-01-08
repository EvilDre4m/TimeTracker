import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



public class Main {
	
	private static Client client;
	private static String REST_USERSERVICE_URL = "http://localhost:13080/PJI2/rest/UserService/users";
	private static String REST_PROJECTSERVICE_URL = "http://localhost:13080/PJI2/rest/ProjectService/projects";
	private static String REST_TASKSERVICE_URL = "http://localhost:13080/PJI2/rest/TaskService/tasks";
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
		
		String callResult = client.target(REST_USERSERVICE_URL)
							.request(MediaType.APPLICATION_XML)
							.put(Entity.entity(form,
									MediaType.APPLICATION_FORM_URLENCODED_TYPE),
									String.class);
		
		int dummy = 2;
		dummy++;
	}
	
	public static void test_getAllUsers(){
		Form form = new Form();
		
		Response callResult = client.target(REST_USERSERVICE_URL + "/0")
							.request(MediaType.APPLICATION_XML)
							.get();
		
		int dummy = 2;
		dummy++;
	}
	
	public static void test_loginUser(){
		
//		Response callResult = client.target(REST_USERSERVICE_URL)
//							.request(MediaType.APPLICATION_XML)
//							.get();
		
		String targetSTring = REST_USERSERVICE_URL + "?email=hugo.hugosting@g3tfvckdmail.gf&password=passwort";
		Object retUser = client
						.target(targetSTring)
						//.resolveTemplate("email", "hugo.hugosting@g3tfvckdmail.gf")
						//.resolveTemplate("password", "passwort")
						.request(MediaType.APPLICATION_XML)
						.get();
						
	}
	
	public static void test_logoutUser(){
		Response retVal = client
						.target(REST_USERSERVICE_URL + "?email=hugo.hugosting@g3tfvckdmail.gf")
						//.resolveTemplate("email", "hugo.hugosting@g3tfvckdmail.gf")
						.request(MediaType.APPLICATION_XML)
						.delete();
	}
	
	public static void test_addProject(){
		Form form = new Form(); 
		
		form.param("id", "13");
		form.param("owningUserId", "13");
		form.param("description", "dont care");
		form.param("name", "testProj"); 
		
		String callResult = client.target(REST_PROJECTSERVICE_URL)
							.request(MediaType.APPLICATION_XML)
							.put(Entity.entity(form,
									MediaType.APPLICATION_FORM_URLENCODED_TYPE),
									String.class);
		
		int dummy = 2;
		dummy++;
	}
	
	public static void test_editProject(){
		Form form = new Form();
		
		form.param("id", "13");
		form.param("owningUserId", "13");
		form.param("description", "dont care");
		form.param("name", "testProj"); 
		
		String callResult = client.target(REST_PROJECTSERVICE_URL)
							.request(MediaType.APPLICATION_XML)
							.post(Entity.entity(form,
									MediaType.APPLICATION_FORM_URLENCODED_TYPE),
									String.class);
	}
	
	public static void test_searchProject(){
		String targetSTring = REST_PROJECTSERVICE_URL + "?desc=care";
		Object retProj = client
						.target(targetSTring)
						.request(MediaType.APPLICATION_XML)
						.get();
	}
	
	public static void test_getAllProjects(){
		Object retProj = client
						.target(REST_PROJECTSERVICE_URL + "/0")
						.request(MediaType.APPLICATION_XML)
						.get();
	}
	
	public static void test_deleteProject(){
		Form form = new Form();
		
		form.param("id", "13");
		
		Response callResult = client.target(REST_PROJECTSERVICE_URL)
							.request(MediaType.APPLICATION_XML)
							.delete();
	}
	
	public static void test_createTask(){
		Form form = new Form(); 
		
		form.param("id", "13");
		form.param("startTime", "");
		form.param("endTime", "");
		form.param("description", "myfirsttask sucks");
		form.param("containingProjID", "13");
		form.param("creatingUserID", "13");
		
		String callResult = client.target(REST_TASKSERVICE_URL)
							.request(MediaType.APPLICATION_XML)
							.put(Entity.entity(form,
									MediaType.APPLICATION_FORM_URLENCODED_TYPE),
									String.class);
	}
	
	public static void test_editTask(){
		Form form = new Form(); 
		
		form.param("taskid", "13");
		form.param("startTime", "");
		form.param("endTime", "");
		form.param("description", "myfirsttask sucks");
		form.param("containingProjID", "13");
		form.param("creatingUserID", "13");
		
		String targetString = REST_TASKSERVICE_URL + "/edit";
		String callResult = client.target(targetString)
							.request(MediaType.APPLICATION_XML)
							.put(Entity.entity(form,
									MediaType.APPLICATION_FORM_URLENCODED_TYPE),
									String.class);
	}
	
	public static void main(String[] args){
		init();
		//test_registerUser();
		//test_getAllUsers();
		//test_loginUser();
		//test_logoutUser();
		
		//test_addProject();
		//test_editProject();
		//test_searchProject();
		//test_getAllProjects();
		//test_deleteProject();
		
		//test_createTask();
		test_editTask();
	}
	
}
