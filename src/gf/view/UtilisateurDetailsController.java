package gf.view;


import gf.model.Utilisateur;
import gf.model.UtilisateurFx;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class UtilisateurDetailsController {
	@FXML
	private TextField nomUtilisateur;
	@FXML
	private TextField niveau;
	@FXML
	private TextField nom;
	@FXML
	private TextField prenom;
	@FXML
	private TextField poste;
	@FXML
	private Button valider;


	private UtilisateurWindowController utilisateurWindowController;
	private Stage dialogStage;
	private UtilisateurFx utilisateurFx;
	private int keyInArray=0;

	@FXML
	private void initialize() {

	}

	public UtilisateurDetailsController() {

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}


	@FXML
	private void actionOnClickValider() {

		if (isInputValid()) {
			utilisateurFx = new UtilisateurFx(new Utilisateur(nomUtilisateur.getText(), Integer.parseInt(niveau.getText()),nom.getText(),prenom.getText(), poste.getText()));
			if (valider.getText().equals("Valider")) {
				utilisateurWindowController.getListeUtilisateurs().add(utilisateurFx);
			} else {
				utilisateurWindowController.getListeUtilisateurs().set(keyInArray, utilisateurFx);
			}
			dialogStage.close();
		}
	}

	@FXML
	private void actionOnClickAnnuler() {
		nomUtilisateur.setText("");
		niveau.setText("");
		nom.setText("");
		prenom.setText("");
		poste.setText("");
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (nomUtilisateur.getText() == null || nomUtilisateur.getText().length() == 0) {
			errorMessage += "Nom utilisateurFx invalide!\n";
		}
		if (niveau.getText() == null || niveau.getText().length() == 0) {
			errorMessage += "type UtilisateurFx invalide!\n";
		}
		if (nom.getText() == null || nom.getText().length() == 0) {
			errorMessage += "nom invalide!\n";
		}
		if (prenom.getText() == null || prenom.getText().length() == 0) {
			errorMessage += "Prenom invalide!\n";
		}
		if (poste.getText() == null || poste.getText().length() == 0) {
			errorMessage += "poste invalide!\n";
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

	public UtilisateurWindowController getUtilisateurWindowController() {
		return utilisateurWindowController;
	}

	public void setUtilisateurWindowCOntroller(
			UtilisateurWindowController utilisateurWindowController) {
		this.utilisateurWindowController = utilisateurWindowController;
	}

	public UtilisateurFx getUtilisateur() {
		return utilisateurFx;
	}

	public void setUtilisateur(UtilisateurFx utilisateurFx) {
		this.utilisateurFx = utilisateurFx;
		valider.setText("Modifier");
		nomUtilisateur.setText(utilisateurFx.getNomUtilisateur());
		niveau.setText(""+utilisateurFx.getNiveau());
		nom.setText(utilisateurFx.getNom());
		prenom.setText(utilisateurFx.getPrenom());
		poste.setText(utilisateurFx.getPoste());
	}

	public int getKeyInArray() {
		return keyInArray;
	}

	public void setKeyInArray(int keyInArray) {
		this.keyInArray = keyInArray;
	}
	
	

}
