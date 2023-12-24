package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;

public class DeleteContactByIDTestRest implements Helper {
    String endpoint = "contacts";
    String id;
    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI=BASE_URI;
        RestAssured.basePath=PATH;

        ContactDTO contactDTO = ContactDTO.builder()
                .name("John"+i)
                .lastName("Silver")
                .phone("0123456789"+i)
                .email("john"+i+"@gmail.com")
                .address("Tel-Aviv")
                .description("Fred")
                .build();
        ContactResponseDTO responseDTO = given()
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .header(authHeader, TOKEN)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactResponseDTO.class);
        System.out.println(responseDTO.getMessage());
        this.id = responseDTO.getMessage().substring(responseDTO.getMessage().lastIndexOf(" ") + 1);
        System.out.println(id);
    }
    @Test
    public void testDeleteByIDPositiveTest(){

        ContactResponseDTO responseDTO = given()
                .header(authHeader, TOKEN)
                .when()
                .delete(endpoint+"/"+id)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactResponseDTO.class);
        System.out.println(responseDTO.getMessage());
    }


}
