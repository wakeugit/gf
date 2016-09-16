package gf.view.controller;


import gf.model.Cotisation;
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
	private Cotisation tontine;
	private int keyInArray=0;

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
			tontine = new Cotisation(nomCotisation.getText(), type.getText(), DateUtil.format(dateDebut
					.getValue()), DateUtil.format(dateFin.getValue()), anneeTxt.getText());
			if (valider.getText().equals("Valider")) {
				tontineWindowController.getListeCotisations().add(tontine);
			} else {
				tontineWindowController.getListeCotisations().set(keyInArray, tontine);
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
//		if (dateFin.getValue() == null) {
//			errorMessage += "Date fin invalide : dd.mm.yyyy !\n";
//		}
		if (anneeTxt.getText() == null || anneeTxt.getText().length() == 0) {
			errorMessage += "Annee invalide!\n";
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

	public Cotisation getTontine() {
		return tontine;
	}

	public void setTontine(Cotisation tontine) {
		this.tontine = tontine;
		valider.setText("Modifier");
		nomCotisation.setText(tontine.getnomCotisation());
		dateDebut.setValue(tontine.getDateDebut().getValue());
		dateFin.setValue(tontine.getDateFin().getValue());
		anneeTxt.setText(tontine.getAnnee().get());
	}

	public int getKeyInArray() {
		return keyInArray;
	}

	public void setKeyInArray(int keyInArray) {
		this.keyInArray = keyInArray;
	}
	
	

}
