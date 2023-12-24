package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class RegistrationTestsRest implements Helper {
    String endpoint="user/registration/usernamepassword";
    @BeforeMethod
    public void preceding(){
        RestAssured.baseURI=BASE_URI;
        RestAssured.basePath=PATH;
    }
    @Test
    public void registrationPositive() throws Exception {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("vasya_pupkin"+i+"@gmail.com")
                .password("Vp12345$")
                .build();
        AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(AuthResponseDTO.class);
        System.out.println(responseDTO.getToken());

    }
    @Test
    public void registrationNegative() throws Exception {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("vasya_pupkin@gmail.com")
                .password("Vp12345$")
                .build();
        ErrorDTO responseDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(409)
                .extract()
                .as(ErrorDTO.class);
        System.out.println(responseDTO.getError()+" "+responseDTO.getMessage());
    }
}
