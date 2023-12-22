package restassured;

import com.jayway.restassured.RestAssured;
import dto.ContactDTO;
import dto.ContactListDTO;
import helpers.Helper;
import okhttp3.Request;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class GetAllContactsRestTests implements Helper {
    String endpoint="contacts";
    @BeforeMethod
    public void preconditions() throws Exception {
        RestAssured.baseURI =BASE_URI;
        RestAssured.basePath=PATH;
    }
    @Test
    public  void testGetAllContactsPositive(){
        ContactListDTO contactListDTO = given()
                .header(authHeader, TOKEN)
                .when()
                .get(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(ContactListDTO.class);
        for(ContactDTO contactDTO : contactListDTO.getContacts()) {
            System.out.println(contactDTO.getId());
            System.out.println(contactDTO.getName());
            System.out.println(contactDTO.getEmail());
            System.out.println("================================================================");
        }

    }
}
