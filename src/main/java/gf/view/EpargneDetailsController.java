package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.Cotisation;
import gf.model.CotisationFx;
import gf.model.TypeCotisation;
import gf.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class EpargneDetailsController {
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

	private EpargneWindowController epargneWindowController;
	private Stage dialogStage;
	private CotisationFx epargne;
	private int keyInArray = 0;

	@FXML
	private void initialize() {

	}

	public EpargneDetailsController() {

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void actionOnClickValider() {

		if (isInputValid()) {

			Cotisation cotisation = new Cotisation(nomCotisation.getText(),
					TypeCotisation.valueOf(type.getText().toUpperCase()),
					DateUtil.format(dateDebut.getValue()),
					DateUtil.format(dateFin.getValue()), anneeTxt.getText());

			Response<Cotisation> response;

			if (valider.getText().equals("Valider")) {

				response = BackendInterface.createCotisation(cotisation);
				if (response.getBody() != null) {
					epargneWindowController.getListeCotisations().add(
							new CotisationFx(response.getBody()));

				} else {
					// Todo Display error message
				}
			} else {
				if (epargne.getId() != -1) {
					cotisation.setId(epargne.getId());
					response = BackendInterface.updateCotisation(cotisation);
					if (response.getBody() != null) {
						epargneWindowController.getListeCotisations().set(
								keyInArray,
								new CotisationFx(response.getBody()));

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

		if (nomCotisation.getText() == null
				|| nomCotisation.getText().length() == 0) {
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

	public EpargneWindowController getEpargneWindowController() {
		return epargneWindowController;
	}

	public void setEpargneWindowCOntroller(
			EpargneWindowController epargneWindowController) {
		this.epargneWindowController = epargneWindowController;
	}

	public CotisationFx getEpargne() {
		return epargne;
	}

	public void setEpargne(CotisationFx epargne) {
		this.epargne = epargne;
		valider.setText("Modifier");
		nomCotisation.setText(epargne.getnomCotisation());
		dateDebut.setValue(epargne.getDateDebutProperty().getValue());
		dateFin.setValue(epargne.getDateFinProperty().getValue());
		anneeTxt.setText(epargne.getAnneeProperty().get());
	}

	public int getKeyInArray() {
		return keyInArray;
	}

	public void setKeyInArray(int keyInArray) {
		this.keyInArray = keyInArray;
	}

}
