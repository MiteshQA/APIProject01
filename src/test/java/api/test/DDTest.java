package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTest {

	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostuser(String userID, String userName,String fname,String lname,String useremail,String pwd,String ph)
	{
		User userPayload = new User();
		userPayload.setId(Integer.parseInt(userID));
		userPayload.setFirstname(fname);
		userPayload.setLastname(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setUsername(userName);
		userPayload.setPhone(ph);

		Response response=UserEndPoints.CreateUser(userPayload);
		Assert.assertEquals(response.getStatusCode(), 200);
	}

	@Test(priority=2, dataProvider="UserName", dataProviderClass=DataProviders.class )
	public void testGetUser(String user)
	{
		Response response = UserEndPoints.ReadUser(user);
		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority=3, dataProvider="UserName", dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String userName)
	{
		Response response = UserEndPoints.DeleteUser(userName);
		Assert.assertEquals(response.getStatusCode(),200);
	}
}
