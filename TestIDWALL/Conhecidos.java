import static org.junit.Assert.*;
import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;
import org.junit.Test;
import TestIDWALL.TestToken;

public class Conhecidos {
	
	TestToken token = new TestToken();
	token.TestToken("9a5c457e-d004-46d2-ad0b-b3f015af0c58")		
	baseURI = "https://api-v2.idwall.co/relatorios";
	String matriz = "consultaPessoaDefault";

	@Test
	public void DadosVazios() {
		   given()
		   .relaxedHTTPSValidation()
		   .body(matriz)
		   .param("cpf_data_de_nascimento", "")
		   .param("cpf_nome", "")
		   .param("cpf_numero", "")
        	
		   .when()
		   .post("/")
		   
	    	 .then()
	    	   .statusCode(400)
	    	   .body("message", containsString("É necessário enviar ao menos um parâmetro para criação do relatório."));
		}
	
	@Test
	public void DataDiferente() {
		   given()
		   .relaxedHTTPSValidation()
		   .body(matriz)
		   .param("cpf_data_de_nascimento", "28/09/1988")
		   .param("cpf_nome", "Gabriel Oliveira")
		   .param("cpf_numero", "07614917677")
        	
		   .when()
		   .post("/")
		   
	    	 .then()
	    	   .statusCode(200)
	    	   .body("message", containsString("Inválido. [ERROR] Não foi possível validar: Data de nascimento informada está divergente da constante na base de dados da Secretaria da Receita Federal do Brasil."));
		       .body("resultado", containsString("INVALID"));
		}
	
	@Test
	public void NomeDiferente() {
		   given()
		   .relaxedHTTPSValidation()
		   .body(matriz)
		   .param("cpf_data_de_nascimento", "25/05/1987")
		   .param("cpf_nome", "Gabriel Oliveira")
		   .param("cpf_numero", "07614917677")
        	
		   .when()
		   .post("/")
		   
	    	 .then()
	    	   .statusCode(200)
	    	   .body("message", containsString("Inválido. [INVALID] Nome diferente do cadastrado na Receita Federal."));
		       .body("resultado", containsString("INVALID"));
		}

	
	}

}
