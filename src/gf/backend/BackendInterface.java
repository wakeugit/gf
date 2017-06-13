package gf.backend;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import gf.model.*;
import org.json.JSONObject;

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
            HttpResponse<Annee[]> bookResponse = Unirest.get(APP_URL + "/cotisation/").asObject(Annee[].class);
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
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.post(APP_URL + "/cotisation/")
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
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.put(APP_URL + "/cotisation/")
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
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.delete(APP_URL + "/cotisation/" + anneeId)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static void deleteInscriptionCotisation(long id) {
        initRequest();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.delete(APP_URL + "/inscription/cotisation/" + id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static void deleteInscriptionAnnuelle(long id) {
        initRequest();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.delete(APP_URL + "/inscription/annuelle/" + id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCotisation(long id) {
        initRequest();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.delete(APP_URL + "/cotisation/" + id)
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }


    public static Response<Cotisation[]> getCotisationsByType(TypeCotisation typeCotisation) {
        initRequest();
        Response<Cotisation[]> response = new Response<>();
        try {
            HttpResponse<Cotisation[]> bookResponse = null;

            if (typeCotisation == TypeCotisation.TONTINE)
                bookResponse = Unirest.get(APP_URL + "/cotisation/tontine").asObject(Cotisation[].class);
            else if (typeCotisation == TypeCotisation.EPARGNE)
                bookResponse = Unirest.get(APP_URL + "/cotisation/epargne").asObject(Cotisation[].class);
            else if (typeCotisation == TypeCotisation.ANNEE)
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

    public static Response<Transaction[]> getWinneableTontinesByDate(long date) {
        initRequest();
        Response<Transaction[]> response = new Response<>();
        try {
            HttpResponse<Transaction[]> bookResponse

                    = Unirest.get(APP_URL + "/transaction/tontiner/" + date).asObject(Transaction[].class);

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

    public static Response<InscriptionAnnuelle> createInscriptionAnnuelle(InscriptionAnnuelle inscriptionCotisation) {
        initRequest();
        Response<InscriptionAnnuelle> response = new Response<>();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.post(APP_URL + "/inscription/annuelle/")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(inscriptionCotisation)
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
            if (nodeHttpResponse.getStatus() == 200) {
                Gson gson = new Gson();
                InscriptionAnnuelle created = gson.fromJson(nodeHttpResponse.getBody().getObject().toString(), InscriptionAnnuelle.class);
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

    public static Response<Transaction> createTransaction(Transaction transaction) {
        initRequest();
        Response<Transaction> response = new Response<>();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.post(APP_URL + "/transaction/")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(transaction)
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
            if (nodeHttpResponse.getStatus() == 200) {
                Gson gson = new Gson();
                Transaction created = gson.fromJson(nodeHttpResponse.getBody().getObject().toString(), Transaction.class);
                response.setBody(created);
            } else {
                JSONObject jsonObject = nodeHttpResponse.getBody().getObject();
                String causeMessage = jsonObject.get("exception") + " -- " + jsonObject.get("message");
                response.getExceptions().add(new RuntimeException(causeMessage));
            }
            return response;
        } catch (UnirestException e) {
            response.getExceptions().add(e);
            e.printStackTrace();
            return response;
        }
    }

    public static Response<InscriptionAnnuelle> updateInscriptionAnnuelle(InscriptionAnnuelle inscriptionCotisation) {
        initRequest();
        Response<InscriptionAnnuelle> response = new Response<>();
        try {
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.put(APP_URL + "/inscription/annuelle/")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(inscriptionCotisation)
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
            if (nodeHttpResponse.getStatus() == 200) {
                Gson gson = new Gson();
                InscriptionAnnuelle created = gson.fromJson(nodeHttpResponse.getBody().getObject().toString(), InscriptionAnnuelle.class);
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


    public static Response<Transaction[]> getTransactionByCotisationAndDateAndType(Cotisation mCotisation, long date, TypeTransaction typeTransaction) {
        Response<Transaction[]> response = new Response<>();
        try {
            GetRequest request = null;

            if (typeTransaction == TypeTransaction.TONTINER) {
                request = Unirest.get(APP_URL + "/transaction/tontiner/" + mCotisation.getId() + "/" + date);
            } else if (typeTransaction == TypeTransaction.BENEFICIER) {
                request = Unirest.get(APP_URL + "/transaction/beneficier/" + mCotisation.getId() + "/" + date);
            } else if (typeTransaction == TypeTransaction.EMPRUNTER) {
                request = Unirest.get(APP_URL + "/transaction/emprunter/" + mCotisation.getId() + "/" + date);
            } else if (typeTransaction == TypeTransaction.REMBOURSER) {
                request = Unirest.get(APP_URL + "/transaction/rembourser/" + mCotisation.getId() + "/" + date);
            } else if (typeTransaction == TypeTransaction.EPARGNER) {
                request = Unirest.get(APP_URL + "/transaction/epargner/" + mCotisation.getId() + "/" + date);
            }

            HttpResponse<Transaction[]> httpResponse = request.asObject(Transaction[].class);

            System.out.println("request = [" + httpResponse.getStatus() + "]");
            if (httpResponse.getStatus() == 200) {
                response.setBody(httpResponse.getBody());
            } else {
                response.getExceptions().add(new RuntimeException(httpResponse.getStatusText()));
            }
            return response;
        } catch (UnirestException e) {
            response.getExceptions().add(e);
            return response;
        }
    }

    public static Response<Transaction[]> getTransactionByCotisationAndType(Cotisation mCotisation, TypeTransaction typeTransaction) {
        Response<Transaction[]> response = new Response<>();
        try {
            GetRequest request = null;

            if (typeTransaction == TypeTransaction.EMPRUNTER) {
                request = Unirest.get(APP_URL + "/transaction/prets/suivi/" + mCotisation.getId());
            } else if (typeTransaction == TypeTransaction.REMBOURSER) {
                request = Unirest.get(APP_URL + "/transaction/remboursements/suivi/" + mCotisation.getId());
            }

            HttpResponse<Transaction[]> httpResponse = request.asObject(Transaction[].class);

            System.out.println("request = [" + httpResponse.getStatus() + "]");
            if (httpResponse.getStatus() == 200) {
                response.setBody(httpResponse.getBody());
            } else {
                response.getExceptions().add(new RuntimeException(httpResponse.getStatusText()));
            }
            return response;
        } catch (UnirestException e) {
            response.getExceptions().add(e);
            return response;
        }
    }

    public static Response<Transaction[]> getTransactionsToRemboursementByCotisation(Cotisation mCotisation) {
        Response<Transaction[]> response = new Response<>();
        try {
            GetRequest request = Unirest.get(APP_URL + "/transaction/prets/remboursement/" + mCotisation.getId());

            HttpResponse<Transaction[]> httpResponse = request.asObject(Transaction[].class);

            System.out.println("request = [" + httpResponse.getStatus() + "]");
            if (httpResponse.getStatus() == 200) {
                response.setBody(httpResponse.getBody());
            } else {
                response.getExceptions().add(new RuntimeException(httpResponse.getStatusText()));
            }
            return response;
        } catch (UnirestException e) {
            response.getExceptions().add(e);
            return response;
        }
    }

    public static Response<Transaction[]> getTransactionEpargneByCotisationAndMembre(Cotisation mCotisation, Membre membre) {
        Response<Transaction[]> response = new Response<>();
        try {
            GetRequest

                    request = Unirest.get(APP_URL + "/transaction/epargnes/" + mCotisation.getId() + "/" + membre.getId());

            HttpResponse<Transaction[]> httpResponse = request.asObject(Transaction[].class);

            System.out.println("request = [" + httpResponse.getStatus() + "]");
            if (httpResponse.getStatus() == 200) {
                response.setBody(httpResponse.getBody());
            } else {
                response.getExceptions().add(new RuntimeException(httpResponse.getStatusText()));
            }
            return response;
        } catch (UnirestException e) {
            response.getExceptions().add(e);
            return response;
        }
    }

    public static Response<Transaction[]> getTransactionPretsByCotisationAndDate(Cotisation mCotisation, long date) {
        Response<Transaction[]> response = new Response<>();
        try {
            GetRequest

                    request = Unirest.get(APP_URL + "/transaction/prets/" + mCotisation.getId() + "/" + date);

            HttpResponse<Transaction[]> httpResponse = request.asObject(Transaction[].class);

            System.out.println("request = [" + httpResponse.getStatus() + "]");
            if (httpResponse.getStatus() == 200) {
                response.setBody(httpResponse.getBody());
            } else {
                response.getExceptions().add(new RuntimeException(httpResponse.getStatusText()));
            }
            return response;
        } catch (UnirestException e) {
            response.getExceptions().add(e);
            return response;
        }
    }

    public static Response<Transaction[]> getTransactionEpargneByCotisation(Cotisation mCotisation) {
        Response<Transaction[]> response = new Response<>();
        try {
            GetRequest

                    request = Unirest.get(APP_URL + "/transaction/epargnes/" + mCotisation.getId());

            HttpResponse<Transaction[]> httpResponse = request.asObject(Transaction[].class);

            System.out.println("request = [" + httpResponse.getStatus() + "]");
            if (httpResponse.getStatus() == 200) {
                response.setBody(httpResponse.getBody());
            } else {
                response.getExceptions().add(new RuntimeException(httpResponse.getStatusText()));
            }
            return response;
        } catch (UnirestException e) {
            response.getExceptions().add(e);
            return response;
        }
    }

    public static Response<Transaction[]> getSuiviTransactionEpargneByCotisation(Cotisation mCotisation) {
        Response<Transaction[]> response = new Response<>();
        try {
            GetRequest

                    request = Unirest.get(APP_URL + "/transaction/epargnes/suivi/" + mCotisation.getId());

            HttpResponse<Transaction[]> httpResponse = request.asObject(Transaction[].class);

            System.out.println("request = [" + httpResponse.getStatus() + "]");
            if (httpResponse.getStatus() == 200) {
                response.setBody(httpResponse.getBody());
            } else {
                response.getExceptions().add(new RuntimeException(httpResponse.getStatusText()));
            }
            return response;
        } catch (UnirestException e) {
            response.getExceptions().add(e);
            return response;
        }
    }

    public static Response<Transaction[]> getTransactionByCotisation(Cotisation mCotisation) {
        Response<Transaction[]> response = new Response<>();
        try {

            //// TODO: 27/04/2017 Update URL endpoint
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.post(APP_URL + "/transaction/cotisation/element")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(mCotisation)
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
            if (nodeHttpResponse.getStatus() == 200) {
                Gson gson = new Gson();
                Transaction[] created = gson.fromJson(nodeHttpResponse.getBody().getArray().toString(), Transaction[].class);
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

    public static Response<InscriptionCotisation[]> getTransactionByType(TypeTransaction typeTransaction) {
        Response<InscriptionCotisation[]> response = new Response<>();
        try {

            //// TODO: 27/04/2017 Update URL endpoint
            HttpResponse<JsonNode> nodeHttpResponse = Unirest.get(APP_URL + "/transaction/")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .asJson();

//            if (typeTransaction == TypeCotisation.TONTINE)
//                bookResponse = Unirest.get(APP_URL + "/cotisation/tontine").asObject(Cotisation[].class);
//            else if (typeTransaction == TypeCotisation.EPARGNE)
//                bookResponse = Unirest.get(APP_URL + "/cotisation/epargne").asObject(Cotisation[].class);
//            else if (typeTransaction == TypeCotisation.ANNEE)
//                bookResponse = Unirest.get(APP_URL + "/cotisation/annee").asObject(Cotisation[].class);

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

    public static Response<InscriptionAnnuelle[]> getInscriptionAnnuelle(Cotisation mCotisation) {
        Response<InscriptionAnnuelle[]> response = new Response<>();
        try {

            HttpResponse<JsonNode> nodeHttpResponse = Unirest.post(APP_URL + "/inscription/annuelle/element")
                    .header("accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(mCotisation)
                    .asJson();
            System.out.println("request = [" + nodeHttpResponse.getStatus() + "]");
            if (nodeHttpResponse.getStatus() == 200) {
                Gson gson = new Gson();
                InscriptionAnnuelle[] created = gson.fromJson(nodeHttpResponse.getBody().getArray().toString(), InscriptionAnnuelle[].class);
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
