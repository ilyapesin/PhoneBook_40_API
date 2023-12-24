package restassured;

import com.jayway.restassured.RestAssured;
import dto.ContactListDTO;
import dto.ContactResponseDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class DeleteAllContactsTests implements Helper {
    String endpoint = "contacts/clear";
    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI=BASE_URI;
        RestAssured.basePath=PATH;
    }
    @Test
    public void testDeleteAllContactsPositiveTest(){
        ContactResponseDTO contactResponseDTO = given()
                .header(authHeader, TOKEN)
                .when()
                .delete(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactResponseDTO.class);
        System.out.println(contactResponseDTO.getMessage());

    }



}
