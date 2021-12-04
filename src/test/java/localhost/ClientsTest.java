package localhost;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;

public class ClientsTest {

    @BeforeAll
    static void setup(){
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/clients";
    }


    @Test
    public void testListaCliente_TodosOsClientesSaoListados(){
        RestAssured.given()
                .log().all()
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200);

    }

    @Test
    public void testeValidaUmCliente(){

        int id = 4;

        RestAssured.given()
                .log().all()
                .when()
                .get("/" + id)
                .then()
                .log().all()
                .statusCode(200)
                .body("id", is(id));
    }

    @Test
    public void NovoUmCliente(){


        RestAssured.given()
                .body("{\n" +
                        "  \"email\": \"fulano3@teste.com\",\n" +
                        "  \"name\": \"fulano3\"\n" +
                        "}")
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then()
                .log().all()
                .statusCode(201)
                .body("email", is("fulano3@teste.com"))
                .body("name", is("fulano3"));
    }

    @Test
    public void AtualizaUmCliente(){


        RestAssured.given()
                .body("{\n" +
                        "  \"email\": \"eu@teste.com\",\n" +
                        "  \"id\": 13,\n" +
                        "  \"name\": \"eu\"\n" +
                        "}")
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .put("/13")
                .then()
                .log().all()
                .statusCode(200)
                .body("email", is("eu@teste.com"))
                .body("name", is("eu"));
    }

    @Test
    public void DeletaUmCliente(){
        RestAssured.given()
                .log().all()
                .when()
                .delete("/17")
                .then()
                .log().all()
                .statusCode(200);
    }


}
