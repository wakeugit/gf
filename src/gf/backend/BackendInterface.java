package gf.backend;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import gf.model.Membre;

public class BackendInterface {

    public static final String APP_URL = "http://localhost:8082";

    public static void initRequest() {

        Unirest.setObjectMapper(new ObjectMapper() {

            private Gson gson = new Gson();

            public <T> T readValue(String value, Class<T> valueType) {
                return gson.fromJson(value, valueType);
            }

            public String writeValue(Object value) {
                return gson.toJson(value);
            }
        });


    }

    public static Response<Membre[]> getMembres() {
        initRequest();
        Response<Membre[]> response = new Response<>();
        try {
            HttpResponse<Membre[]> bookResponse = Unirest.get(APP_URL + "/membre/").asObject(Membre[].class);
            if (bookResponse.getStatus() == 200) {
                response.setBody(bookResponse.getBody());
            } else {
                response.getExceptions().add(new RuntimeException(bookResponse.getStatusText()));
            }
            return response;
        } catch (UnirestException e) {
            response.getExceptions().add(e);
            return response;
        }
    }

    public static Response<Membre> createMembre(Membre membre) {
        initRequest();
        Response<Membre> response = new Response<>();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.post(APP_URL + "/membre/")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(membre)
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
            if (nodeHttpResponse.getStatus() == 200) {
                Gson gson = new Gson();
                Membre created = gson.fromJson(nodeHttpResponse.getBody().getObject().toString(), Membre.class);
                response.setBody(created);
            } else {
                response.getExceptions().add(new RuntimeException(nodeHttpResponse.getStatusText()));
            }
            return response;
        } catch (UnirestException e) {
            response.getExceptions().add(e);
            e.printStackTrace();
            return response;
        }
    }

    public static Response<Membre> updateMembre(Membre membre) {
        initRequest();
        Response<Membre> response = new Response<>();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.put(APP_URL + "/membre/")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(membre)
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
            if (nodeHttpResponse.getStatus() == 200) {
                Gson gson = new Gson();
                Membre created = gson.fromJson(nodeHttpResponse.getBody().getObject().toString(), Membre.class);
                response.setBody(created);
            } else {
                response.getExceptions().add(new RuntimeException(nodeHttpResponse.getStatusText()));
            }
            return response;
        } catch (UnirestException e) {
            response.getExceptions().add(e);
            e.printStackTrace();
            return response;
        }
    }

    public static void deleteMembre(long id) {
        initRequest();
        Response<Membre> response = new Response<>();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.delete(APP_URL + "/membre/" + id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
            if (nodeHttpResponse.getStatus() == 200) {
                Gson gson = new Gson();
                Membre created = gson.fromJson(nodeHttpResponse.getBody().getObject().toString(), Membre.class);
                response.setBody(created);
            } else {
                response.getExceptions().add(new RuntimeException(nodeHttpResponse.getStatusText()));
            }
        } catch (UnirestException e) {
            response.getExceptions().add(e);
            e.printStackTrace();
        }
    }
}