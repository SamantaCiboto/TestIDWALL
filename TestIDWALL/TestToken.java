import static org.junit.Assert.*;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;
import org.junit.Test;

public class TestToken {

	@Test
	public void token(){
		baseURI = "https://api-v2.idwall.co/relatorios";
		String tokenSamy = "{9a5c457e-d004-46d2-ad0b-b3f015af0c58}";
		   given()
           .contentType("application/json")
    	   .header(tokenSamy)
    	 .when()
    	   .post("/")
    	 .then()
    	   .statusCode(200)
	}

}
