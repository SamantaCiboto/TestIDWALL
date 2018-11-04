import static org.junit.Assert.*;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;
import org.junit.Test;
import TestIDWALL.TestToken;

public class Desconhecido{
	
	TestToken token = new TestToken();
	token.TestToken("9a5c457e-d004-46d2-ad0b-b3f015af0c58")		
	baseURI = "https://api-v2.idwall.co/relatorios";
	String matriz = "consultaPessoaDefault";
	
	@Test
	public void CenarioDesconhecido() {
		   given()
		   .relaxedHTTPSValidation()
		   .body(matriz)
		   .param("status", "CONCLUIDO");
		   .param("resultado", "VALID");
		  	   
		   .when()
		   .post("/")
		   
	    	 .then()
	    	   .statusCode(200);
			   .param("cpf_data_de_nascimento", "Data verdadeira")
			   .param("cpf_nome", "Nome Verdadeiro")
			   .param("cpf_numero", "CPF Verdadeiro")
	    	   .body("message", containsString("Válido"));
		}
	



}
