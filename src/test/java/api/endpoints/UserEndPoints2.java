package api.endpoints;

import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//UserEndPoints.java
//Class created for perform Create, Read, Update, Delete requests to the API.
public class UserEndPoints2 {

	//method created for getting URLs
	public static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); //Load Properties file
		return routes;
	}

	public static Response CreateUser(User Payload)
	{
		String post_url = getURL().getString("post_url");
		Response res = given()
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.body(Payload)
					  .when()
					  	.post(post_url);

					return res;
	}

	public static Response ReadUser(String userName)
	{
		String get_url = getURL().getString("get_url");
		Response res = given()
						.pathParam("username", userName)
					  .when()
					  	.get(get_url);

					return res;
	}

	public static Response UpdateUser(String userName, User Payload)
	{
		String put_url = getURL().getString("put_url");
		Response res = given()
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.pathParam("username", userName)
						.body(Payload)
					  .when()
					  .put(put_url);

					return res;
	}

	public static Response DeleteUser(String userName)
	{
		String delete_url = getURL().getString("delete_url");
		Response res = given()
						.pathParam("username", userName)
					  .when()
					  	.delete(delete_url);

					return res;
	}

}
