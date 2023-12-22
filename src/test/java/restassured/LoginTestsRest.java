package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class LoginTestsRest implements Helper {
String endpoint="user/login/usernamepassword";
    @BeforeMethod
    public void preceding(){
        RestAssured.baseURI=BASE_URI;
        RestAssured.basePath=PATH;
    }
    @Test
    public void loginPositive() throws Exception {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("vasya_pupkin@gmail.com")
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
    public void loginNegative() throws Exception {
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("vasya_pupkingmail.com")
                .password("Vp12345$")
                .build();
        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDTO.class);
        System.out.println(errorDTO.getMessage());
    }
}
