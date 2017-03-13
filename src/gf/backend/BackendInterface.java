package gf.backend;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import gf.model.*;

import java.util.List;

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

    public static Response<Annee[]> getAnnees() {
        initRequest();
        Response<Annee[]> response = new Response<>();
        try {
            HttpResponse<Annee[]> bookResponse = Unirest.get(APP_URL + "/annee/").asObject(Annee[].class);
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

    public static Response<Annee> createAnnee(Annee annee) {
        initRequest();
        Response<Annee> response = new Response<>();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.post(APP_URL + "/annee/")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(annee)
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
            if (nodeHttpResponse.getStatus() == 200) {
                Gson gson = new Gson();
                Annee created = gson.fromJson(nodeHttpResponse.getBody().getObject().toString(), Annee.class);

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


    public static Response<Annee> updateAnnee(Annee annee) {
        initRequest();
        Response<Annee> response = new Response<>();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.put(APP_URL + "/annee/")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(annee)
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
            if (nodeHttpResponse.getStatus() == 200) {
                Gson gson = new Gson();
                Annee created = gson.fromJson(nodeHttpResponse.getBody().getObject().toString(), Annee.class);

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

    public static void deleteAnnee(long anneeId) {
        initRequest();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.delete(APP_URL + "/annee/" + anneeId)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }


    public static Response<Cotisation[]> getCotisations(Type type) {
        initRequest();
        Response<Cotisation[]> response = new Response<>();
        try {
            HttpResponse<Cotisation[]> bookResponse = null;

            if (type == Type.TONTINE)
                bookResponse = Unirest.get(APP_URL + "/cotisation/tontine").asObject(Cotisation[].class);
            else if (type == Type.EPARGNE)
                bookResponse = Unirest.get(APP_URL + "/cotisation/epargne").asObject(Cotisation[].class);
            else if (type == Type.ANNEE)
            	bookResponse = Unirest.get(APP_URL + "/cotisation/annee").asObject(Cotisation[].class);
            
            if (bookResponse.getStatus() == 200) {
                response.setBody(bookResponse.getBody());
            } else {
                response.getExceptions().add(new RuntimeException(bookResponse.getStatusText()));
            }

        } catch (UnirestException e) {
            response.getExceptions().add(e);
            return response;
        }
        return response;
    }

    public static Response<Cotisation> createCotisation(Cotisation cotisation) {
        initRequest();
        Response<Cotisation> response = new Response<>();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.post(APP_URL + "/cotisation/")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(cotisation)
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
            if (nodeHttpResponse.getStatus() == 200) {
                Gson gson = new Gson();
                Cotisation created = gson.fromJson(nodeHttpResponse.getBody().getObject().toString(), Cotisation.class);
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

    public static Response<Cotisation> updateCotisation(Cotisation cotisation) {
        initRequest();
        Response<Cotisation> response = new Response<>();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.put(APP_URL + "/cotisation/")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(cotisation)
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
            if (nodeHttpResponse.getStatus() == 200) {
                Gson gson = new Gson();
                Cotisation created = gson.fromJson(nodeHttpResponse.getBody().getObject().toString(), Cotisation.class);
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


    public static Response<InscriptionCotisation> createInscriptionCotisation(InscriptionCotisation inscriptionCotisation) {
        initRequest();
        Response<InscriptionCotisation> response = new Response<>();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.post(APP_URL + "/inscription/cotisation/")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(inscriptionCotisation)
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
            if (nodeHttpResponse.getStatus() == 200) {
                Gson gson = new Gson();
                InscriptionCotisation created = gson.fromJson(nodeHttpResponse.getBody().getObject().toString(), InscriptionCotisation.class);
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

    public static Response<InscriptionCotisation> updateInscriptionCotisation(InscriptionCotisation inscriptionCotisation) {
        initRequest();
        Response<InscriptionCotisation> response = new Response<>();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.put(APP_URL + "/inscription/cotisation/")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(inscriptionCotisation)
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
            if (nodeHttpResponse.getStatus() == 200) {
                Gson gson = new Gson();
                InscriptionCotisation created = gson.fromJson(nodeHttpResponse.getBody().getObject().toString(), InscriptionCotisation.class);
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

    public static Response<InscriptionCotisation[]> getInscriptionCotisation(Cotisation mCotisation) {
        Response<InscriptionCotisation[]> response = new Response<>();
        try {
//            HttpResponse<InscriptionCotisation[]> bookResponse = Unirest
//                    .post(APP_URL + "/inscription/cotisation/element")
//                    .body(mCotisation)
//                    .asObject(InscriptionCotisation[].class);
//            if (bookResponse.getStatus() == 200) {
//                response.setBody(bookResponse.getBody());
//            } else {
//                response.getExceptions().add(new RuntimeException(bookResponse.getStatusText()));
//            }
//            return response;

            HttpResponse<JsonNode> nodeHttpResponse = Unirest.post(APP_URL + "/inscription/cotisation/element")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(mCotisation)
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
            if (nodeHttpResponse.getStatus() == 200) {
                Gson gson = new Gson();
                InscriptionCotisation[] created = gson.fromJson(nodeHttpResponse.getBody().getArray().toString(), InscriptionCotisation[].class);
                response.setBody(created);
            } else {
                response.getExceptions().add(new RuntimeException(nodeHttpResponse.getStatusText()));
            }
            return response;
        } catch (UnirestException e) {
            response.getExceptions().add(e);
            return response;
        }
    }
}
