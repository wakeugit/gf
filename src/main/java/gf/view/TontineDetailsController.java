package gf.view;


import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.*;
import gf.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class TontineDetailsController {
    @FXML
    private TextField nomCotisation;
    @FXML
    private TextField type;
    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateFin;
    @FXML
    private TextField anneeTxt;
    @FXML
    private Button valider;


    private TontineWindowController tontineWindowController;
    private Stage dialogStage;
    private CotisationFx tontine;
    private int keyInArray = 0;

    @FXML
    private void initialize() {

    }

    public TontineDetailsController() {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    @FXML
    private void actionOnClickValider() {

        if (isInputValid()) {
            Cotisation cotisation = new Cotisation(nomCotisation.getText(), TypeCotisation.valueOf(type.getText().toUpperCase()), DateUtil.format(dateDebut
                    .getValue()), DateUtil.format(dateFin.getValue()), anneeTxt.getText());

            Response<Cotisation> response;

            if (valider.getText().equals("Valider")) {

                response = BackendInterface.createCotisation(cotisation);
                if (response.getBody() != null) {
                    tontineWindowController.getListeCotisations().add(new CotisationFx(response.getBody()));

                } else {
                    // Todo Display error message
                }
            } else {
                if (tontine.getId() != -1) {
                    cotisation.setId(tontine.getId());
                    response = BackendInterface.updateCotisation(cotisation);
                    if (response.getBody() != null) {
                        tontineWindowController.getListeCotisations().set(keyInArray, new CotisationFx(response.getBody()));

                    } else {
                        // Todo Display error message
                    }
                }

            }
            dialogStage.close();
        }
    }

    @FXML
    private void actionOnClickAnnuler() {
        nomCotisation.setText("");
        type.setText("");
        dateDebut.setValue(null);
        dateFin.setValue(null);
        anneeTxt.setText("");
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (nomCotisation.getText() == null || nomCotisation.getText().length() == 0) {
            errorMessage += "nom cotisation invalide!\n";
        }
        if (type.getText() == null || type.getText().length() == 0) {
            errorMessage += "type cotisation invalide!\n";
        }
        if (dateDebut.getValue() == null) {
            errorMessage += "Date debut invalide : dd.mm.yyyy !\n";
        }
        if (dateFin.getValue() != null) {
			if (dateDebut.getValue().isAfter(dateFin.getValue())) {
				errorMessage += "La Date fin doit être supérieure à la date de debut !\n";
			}
		}
        if (anneeTxt.getText() == null || anneeTxt.getText().length() == 0) {
            errorMessage += "AnneeFx invalide!\n";
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

    public TontineWindowController getTontineWindowController() {
        return tontineWindowController;
    }

    public void setTontineWindowCOntroller(
            TontineWindowController tontineWindowController) {
        this.tontineWindowController = tontineWindowController;
    }

    public CotisationFx getTontine() {
        return tontine;
    }

    public void setTontine(CotisationFx tontine) {
        this.tontine = tontine;
        valider.setText("Modifier");
        nomCotisation.setText(tontine.getnomCotisation());
        dateDebut.setValue(tontine.getDateDebutProperty().getValue());
        dateFin.setValue(tontine.getDateFinProperty().getValue());
        anneeTxt.setText(tontine.getAnneeProperty().get());
    }

    public int getKeyInArray() {
        return keyInArray;
    }

    public void setKeyInArray(int keyInArray) {
        this.keyInArray = keyInArray;
    }


}
