package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AideDetailsController {
    @FXML
    private TextField motif;
    @FXML
    private Button valider;

    private AidesWindowController aideWindowController;
    private Stage dialogStage;
    //private AideFx aideFx;
    private ServiceFx serviceFx;
    private int keyInArray = 0;
    private boolean validerClicked = false;

    @FXML
    private void initialize() {

    }

    public AideDetailsController() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isValiderClicked() {
        return validerClicked;
    }

    @FXML
    private void actionOnClickValider() {
        if (isInputValid()) {

            Service service = null;

            Response<Service> response;

            if (valider.getText().equals("Valider")) {

                service = new Service(motif.getText(), TypeService.AIDE);

                response = BackendInterface.createService(service);
                if (response.getBody() != null) {
                    aideWindowController.getListeAides().add(new ServiceFx(response.getBody()));

                } else {
                    // Todo Display error message
                }
            } else {

                service = new Service(serviceFx);
                if (service.getId() != -1) {
//					  service.setId(tontine.getId());
                    service.setMotif(motif.getText());
                    response = BackendInterface.updateService(service);
                    if (response.getBody() != null) {
                        aideWindowController.getListeAides().set(keyInArray, new ServiceFx(response.getBody()));

                    } else {
                        // Todo Display error message
                    }
                }

            }

            validerClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void actionOnClickAnnuler() {
        this.dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (motif.getText() == null || motif.getText().length() == 0) {
            errorMessage += "Motif invalide!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs invalides");
            alert.setHeaderText("SVP corrigez les champs inavlides");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    public AidesWindowController getAideWindowController() {
        return aideWindowController;
    }

    public void setAideWindowController(AidesWindowController aideWindowController) {
        this.aideWindowController = aideWindowController;
    }

    public ServiceFx getServiceFx() {
        return serviceFx;
    }

    public void setService(ServiceFx serviceFx) {
        this.serviceFx = serviceFx;
        valider.setText("Modifier");
        motif.setText(serviceFx.getMotif());
    }

    public int getKeyInArray() {
        return keyInArray;
    }

    public void setKeyInArray(int keyInArray) {
        this.keyInArray = keyInArray;
    }


}
