package gf.view.controller;

import gf.model.Annee;
import gf.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AnneesDetailsController {
	@FXML
	private TextField anneeTxt;
	@FXML
	private DatePicker dateDebut;
	@FXML
	private DatePicker dateFin;
	@FXML
	private Button valider;

	private AnneesWindowController anneeWindowController;
	private Stage dialogStage;
	private Annee annee;
	private int keyInArray=0;
	private boolean validerClicked = false;

	@FXML
	private void initialize() {

	}

	public AnneesDetailsController() {

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
			annee = new Annee(anneeTxt.getText(), DateUtil.format(dateDebut
					.getValue()), DateUtil.format(dateFin.getValue()));
			if (valider.getText().equals("Valider")) {
				anneeWindowController.getListeAnnees().add(annee);
			} else {
				anneeWindowController.getListeAnnees().set(keyInArray, annee);
			}
			validerClicked = true;
			dialogStage.close();
		}
	}

	@FXML
	private void actionOnClickAnnuler() {
		anneeTxt.setText("");
		dateDebut.setValue(null);
		dateFin.setValue(null);
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (anneeTxt.getText() == null || anneeTxt.getText().length() == 0) {
			errorMessage += "Annee invalide!\n";
		}
		if (dateDebut.getValue() == null) {
			errorMessage += "Date debut invalide : dd.mm.yyyy !\n";
		}
		if (dateFin.getValue() == null) {
			errorMessage += "Date fin invalide : dd.mm.yyyy !\n";
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

	public AnneesWindowController getAnneeWindowController() {
		return anneeWindowController;
	}

	public void setAnneesWindowCOntroller(
			AnneesWindowController anneeWindowController) {
		this.anneeWindowController = anneeWindowController;
	}

	public Annee getAnnee() {
		return annee;
	}

	public void setAnnee(Annee annee) {
		this.annee = annee;
		valider.setText("Modifier");
		anneeTxt.setText(annee.getAnnee());
		dateDebut.setValue(annee.getDateDebut().getValue());
		dateFin.setValue(annee.getDateFin().getValue());
	}

	public int getKeyInArray() {
		return keyInArray;
	}

	public void setKeyInArray(int keyInArray) {
		this.keyInArray = keyInArray;
	}
	
	

}
