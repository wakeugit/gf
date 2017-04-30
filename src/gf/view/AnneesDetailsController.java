package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.Cotisation;
import gf.model.CotisationFx;
import gf.model.TypeCotisation;
import gf.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
	private CotisationFx anneeFx;
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
			
			Cotisation annee = new Cotisation("Cotisation Annuelle", TypeCotisation.ANNEE , DateUtil.format(dateDebut
					.getValue()), DateUtil.format(dateFin.getValue()), anneeTxt.getText());

			if (valider.getText().equals("Valider")) {
				Response<Cotisation> response = BackendInterface.createCotisation(annee);
				if (response.getBody() != null) {
					anneeWindowController.getListeAnnees().add(new CotisationFx(response.getBody()));
				} else {
					// Todo Display error message
				}

			} else {
				if (anneeFx.getId() != -1) {
					annee.setId(anneeFx.getId());
					Response<Cotisation> response = BackendInterface.updateCotisation(annee);
					if (response.getBody() != null) {
						anneeWindowController.getListeAnnees().set(keyInArray, new CotisationFx(response.getBody()));
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
		anneeTxt.setText("");
		dateDebut.setValue(null);
		dateFin.setValue(null);
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (anneeTxt.getText() == null || anneeTxt.getText().length() == 0) {
			errorMessage += "AnneeFx invalide!\n";
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

	public CotisationFx getAnnee() {
		return anneeFx;
	}

	public void setAnnee(CotisationFx anneeFx) {
		this.anneeFx = anneeFx;
		valider.setText("Modifier");
		anneeTxt.setText(anneeFx.getAnnee());
		dateDebut.setValue(anneeFx.getDateDebutProperty().getValue());
		dateFin.setValue(anneeFx.getDateFinProperty().getValue());
	}

	public int getKeyInArray() {
		return keyInArray;
	}

	public void setKeyInArray(int keyInArray) {
		this.keyInArray = keyInArray;
	}
	
	

}
