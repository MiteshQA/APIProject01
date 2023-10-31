package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


import api.endpoints.UserEndPoints2;
import api.payloads.User;

import io.restassured.response.Response;

public class UserTest2 {

	Faker faker;
	User userPayload;


	public Logger logger; //Create logger object

	@BeforeClass
	void setup()
	{
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setUsername(faker.name().username());
		userPayload.setPhone(faker.phoneNumber().cellPhone());

		//Logs
		logger= LogManager.getLogger(this.getClass()); //Initiate logger object


	}

	@Test(priority = 1)
	public void testPostUser()
	{

		logger.info("***************** Create User *********************");
		Response response=UserEndPoints2.CreateUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***************** User is Created *********************");



	}

	@Test(priority =2)
	public void testGetUser()
	{
		logger.info("***************** Reading User info *********************");
		Response response = UserEndPoints2.ReadUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***************** User info is displaying  *********************");
	}

	@Test(priority=3)
	public void testUpdateUserByName()
	{
		logger.info("***************** Updating User *********************");
		//update date using payload
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().emailAddress());

		Response response = UserEndPoints2.UpdateUser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();

		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("***************** User Updated *********************");

		//Checking data after update
		Response responseAfterupdate = UserEndPoints2.ReadUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(responseAfterupdate.getStatusCode(), 200);



	}

	@Test(priority=4)
	public void testDeleteUserByName()
	{
		logger.info("***************** Deleting User *********************");
		Response response = UserEndPoints2.DeleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("***************** User Deleted *********************");
	}


}
