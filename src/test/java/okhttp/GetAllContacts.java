package okhttp;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.ContactListDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllContacts {
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();
    String token="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidmFzeWFfcHVwa2luQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNzAzNjAxNzQzLCJpYXQiOjE3MDMwMDE3NDN9.VCDwOjC_yjFcMferJu62X3PuMlFBLQrKEFCVRuOKBMo";

    @Test
    public void getAllContactsPositive() throws IOException {
        Request request= new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization",token)
                .build();
        Response response=client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());
        ContactListDTO contacts=gson.fromJson(response.body().string(),ContactListDTO.class);

        for(ContactDTO contactListDTO: contacts.getContacts()) {

            System.out.println(contactListDTO.getId());
            System.out.println(contactListDTO.getName());
            System.out.println(contactListDTO.getLastName());
            System.out.println(contactListDTO.getEmail());
            System.out.println(contactListDTO.getPhone());
            System.out.println("================================================================");


        }

    }
}
