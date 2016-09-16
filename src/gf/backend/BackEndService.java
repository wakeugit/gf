package gf.backend;


import com.google.gson.*;
import gf.model.Membre;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.*;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

public interface BackEndService {

    String APP_URL = "localhost:8082";

    JsonDeserializer<Date> deserializer = new JsonDeserializer<Date>() {
        @Override
        public Date deserialize(JsonElement json, Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {
            return json == null ? null : new Date(json.getAsLong());
        }
    };

    Gson gson = new GsonBuilder()
            .setLenient()
            .registerTypeAdapter(Date.class, deserializer)
            .create();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(APP_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    @POST("/membre")
    Call<Membre> createMembre(@Body Membre membre);

    @PUT("/membre/password")
    Call<Membre> resetPassword(@Body Membre membre);

    @PUT("/membre")
    Call<Membre> updateMembre(@Body Membre membre);

    @GET("/membre")
    Call<Membre> getMembre();

    @GET("/membres")
    Call<List<Membre>> getMembres();





}
