package api.endpoints;

/*This class only contain URLs*/

public class Routes {

	public static String base_uri = "https://petstore.swagger.io/v2";


	//User Module

	public static String post_url = base_uri + "/user";
	public static String get_url = base_uri + "/user/{username}";
	public static String put_url = base_uri + "/user/{username}";
	public static String delete_url = base_uri + "/user/{username}";

}
