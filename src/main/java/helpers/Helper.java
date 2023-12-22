package helpers;

import com.google.gson.Gson;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.util.Random;

public interface Helper {

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    Gson GSON = new Gson();
    OkHttpClient CLIENT = new OkHttpClient();
    String TOKEN="eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoidmFzeWFfcHVwa2luQGdtYWlsLmNvbSIsImlzcyI6IlJlZ3VsYWl0IiwiZXhwIjoxNzAzNjAxNzQzLCJpYXQiOjE3MDMwMDE3NDN9.VCDwOjC_yjFcMferJu62X3PuMlFBLQrKEFCVRuOKBMo";
    String BASE_URI = "https://contactapp-telran-backend.herokuapp.com";
    String PATH = "v1";

    String authHeader = "Authorization";

    int i = new Random().nextInt(1000) + 1000;
}
