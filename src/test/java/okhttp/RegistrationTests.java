package okhttp;

import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTests implements Helper {

    String endpoint = "user/registration/usernamepassword";

    @Test
    public void registrationPositive() throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("vasya_pupkin" + i + "@gmail.com")
                .password("$Abcdef12345")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url(BASE_URI + "/" + PATH + "/" + endpoint)
                .post(requestBody)
                .build();

        Response response = CLIENT.newCall(request).execute();
        AuthResponseDTO responseDTO=GSON.fromJson(response.body().string(),AuthResponseDTO.class);
        System.out.println("Token: "+responseDTO.getToken());
        System.out.println("Response code: "+response.code());

        Assert.assertTrue(response.isSuccessful());
    }


    @Test
    public void registrationNegativeWrongEmail() throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("vasya_pupkin" + i + "gmail.com")
                .password("$Abcdef12345")
                .build();

        RequestBody requestBody = RequestBody.create(GSON.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url(BASE_URI + "/" + PATH + "/" + endpoint)
                .post(requestBody)
                .build();

        Response response = CLIENT.newCall(request).execute();
        System.out.println("Response code is: " + response.code());
        ErrorDTO errorDTO = GSON.fromJson(response.body().string(), ErrorDTO.class);
        System.out.println(errorDTO.getStatus() + " " + errorDTO.getError()+" "+errorDTO.getMessage());
        Assert.assertTrue(!response.isSuccessful());

    }

}