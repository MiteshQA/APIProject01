package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//UserEndPoints.java
//Class created for perform Create, Read, Update, Delete requests to the API.
public class UserEndPoints {

	public static Response CreateUser(User Payload)
	{
		Response res = given()
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.body(Payload)
					  .when()
					  	.post(Routes.post_url);

					return res;
	}

	public static Response ReadUser(String userName)
	{
		Response res = given()
						.pathParam("username", userName)
					  .when()
					  	.get(Routes.get_url);

					return res;
	}

	public static Response UpdateUser(String userName, User Payload)
	{
		Response res = given()
						.contentType(ContentType.JSON)
						.accept(ContentType.JSON)
						.pathParam("username", userName)
						.body(Payload)
					  .when()
					  .put(Routes.put_url);

					return res;
	}

	public static Response DeleteUser(String userName)
	{
		Response res = given()
						.pathParam("username", userName)
					  .when()
					  	.delete(Routes.delete_url);

					return res;
	}

}
