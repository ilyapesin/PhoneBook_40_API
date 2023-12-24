package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthResponseDTO;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class AddNewContactsTestsRest implements Helper {
    String endpoint="contacts";
    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI=BASE_URI;
        RestAssured.basePath=PATH;
    }
    @Test
    public void testAddNewContactPositive(){
        ContactDTO contactDTO= ContactDTO.builder()
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
        String id = responseDTO.getMessage().substring(responseDTO.getMessage().lastIndexOf(" ") + 1);
        System.out.println(id);
    }

}
