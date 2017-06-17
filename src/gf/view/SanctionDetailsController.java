package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.Service;
import gf.model.ServiceFx;
import gf.model.TypeService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class SanctionDetailsController {
    @FXML
    private TextField motif;
    @FXML
    private Button valider;

    private SanctionWindowController sanctionWindowController;
    private Stage dialogStage;
    //private SanctionFx serviceFx;
    private ServiceFx serviceFx;
    private int keyInArray = 0;
    private boolean validerClicked = false;

    @FXML
    private void initialize() {

    }

    public SanctionDetailsController() {

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
            Service service = new Service(motif.getText(), TypeService.SANCTION);

            Response<Service> response;

            if (valider.getText().equals("Valider")) {

                response = BackendInterface.createService(service);
                if (response.getBody() != null) {
                    sanctionWindowController.getListeSanctions().add(new ServiceFx(response.getBody()));

                } else {
                    // Todo Display error message
                }
            } else {
                if (service.getId() != -1) {
//					  service.setId(tontine.getId());
                    response = BackendInterface.updateService(service);
                    if (response.getBody() != null) {
                        sanctionWindowController.getListeSanctions().set(keyInArray, new ServiceFx(response.getBody()));

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
        motif.setText("");
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

    public SanctionWindowController getSanctionWindowController() {
        return sanctionWindowController;
    }

    public void setSanctionWindowController(SanctionWindowController sanctionWindowController) {
        this.sanctionWindowController = sanctionWindowController;
    }

    public ServiceFx getService() {
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
