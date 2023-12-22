package okhttp;

import com.google.gson.Gson;
import dto.AuthRequestDTO;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import okhttp3.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddNewContact implements Helper {
//    Gson gson = new Gson();
//    OkHttpClient client = new OkHttpClient();
//    String token="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidmFzeWFfcHVwa2luQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNzAzNjAxNzQzLCJpYXQiOjE3MDMwMDE3NDN9.VCDwOjC_yjFcMferJu62X3PuMlFBLQrKEFCVRuOKBMo";

   // public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
String endpoint="contacts";
    @Test
    public void testAddNewContactPositive() throws IOException {
       // int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        ContactDTO contactDTO = ContactDTO.builder()
                .name("John"+i)
                .lastName("Silver")
                .phone("0123456789"+i)
                .email("john"+i+"@gmail.com")
                .address("Tel-Aviv")
                .description("Fred")
                .build();
        RequestBody requestBody=RequestBody.create(GSON.toJson(contactDTO), JSON);
        Request request = new Request.Builder()
                .url(BASE_URI+"/"+PATH+"/"+endpoint)
                .addHeader(authHeader,TOKEN)
                .post(requestBody)
                .build();
        Response response=CLIENT.newCall(request).execute();
        ContactResponseDTO contactResponseDTO=GSON.fromJson(response.body().string(), ContactResponseDTO.class);
        String message=contactResponseDTO.getMessage();
        System.out.println("Response code: " + response.code());
        System.out.println("Message: " + message);
        String id=message.substring(message.lastIndexOf(" ") + 1);
        System.out.println("ID is :"+id);
        Assert.assertTrue(response.isSuccessful());

    }
    @Test
    public void testAddNewContactNegative() throws IOException {
        // int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        ContactDTO contactDTO = ContactDTO.builder()
                .name("John"+i)
                .lastName("Silver")
                .phone("01"+i)
                .email("john"+i+"@gmail.com")
                .address("Tel-Aviv")
                .description("Fred")
                .build();
        RequestBody requestBody=RequestBody.create(GSON.toJson(contactDTO), JSON);
        Request request = new Request.Builder()
                .url(BASE_URI+"/"+PATH+"/"+endpoint)
                .addHeader(authHeader,TOKEN)
                .post(requestBody)
                .build();
        Response response=CLIENT.newCall(request).execute();
        System.out.println("Response code: " + response.code());
        ErrorDTO errorDTO = GSON.fromJson(response.body().string(), ErrorDTO.class);
        System.out.println(errorDTO.getStatus() + " " + errorDTO.getError()+" "+errorDTO.getMessage());
        Assert.assertTrue(!response.isSuccessful());

    }
}
