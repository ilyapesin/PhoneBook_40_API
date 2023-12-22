package okhttp;

import dto.ContactDTO;
import dto.ContactResponseDTO;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class DeleteContactByIDTests implements Helper {

    String endpoint = "contacts";
    String id;
    @BeforeMethod
    public void precondition() throws IOException {
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
        id=message.substring(message.lastIndexOf(" ") + 1);
        System.out.println("ID is :"+id);
        Assert.assertTrue(response.isSuccessful());
    }

    @Test
    public void testDeleteByIDPositiveTests() throws Exception {

        Request request = new Request.Builder()
                .url(BASE_URI+"/"+PATH+"/"+endpoint+"/"+id)
                .addHeader(authHeader,TOKEN)
                .delete()
                .build();
        Response response=CLIENT.newCall(request).execute();
        ContactResponseDTO contactResponseDTO=GSON.fromJson(response.body().string(), ContactResponseDTO.class);
        String message=contactResponseDTO.getMessage();
        System.out.println("Response code: " + response.code());
        System.out.println("Message: " + message);
    }


}
