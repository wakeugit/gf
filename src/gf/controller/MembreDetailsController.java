package gf.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import gf.model.Membre;

/**
 * Dialog to edit details of a person.
 *
 */
public class MembreDetailsController {
	

	    @FXML
	    private TextField nomMembreField;
	    @FXML
	    private TextField prenomMembreField;
	    @FXML
	    private TextField telMembreField;
	    @FXML
	    private TextField adresseMembreField;
	    @FXML
	    private TextField cniMembreField;
	    @FXML
	    private ImageView photo;
	    


	    private Stage dialogStage;
	    private Membre membre;
	    private boolean validerClicked = false;

	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    	
	    }

	    /**
	     * Sets the stage of this dialog.
	     *
	     * @param dialogStage
	     */
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }

	    /**
	     * Sets the person to be edited in the dialog.
	     *
	     * @param membre
	     */
	    public void setMembre(Membre membre) {
	        this.membre = membre;

	        nomMembreField.setText(membre.getNom());
	        prenomMembreField.setText(membre.getPrenom());
	        telMembreField.setText(Integer.toString(membre.getTelephone()));
	        adresseMembreField.setText(membre.getAdresse());
	        cniMembreField.setText(Integer.toString(membre.getCni()));
	        photo.setImage(null);
	    }

	    /**
	     * Returns true if the user clicked OK, false otherwise.
	     *
	     * @return
	     */
	    public boolean isValiderClicked() {
	        return validerClicked;
	    }

	    /**
	     * Called when the user clicks valider.
	     */
	    @FXML
	    private void handleValider() {
	        if (isInputValid()) {
	            membre.setNom(nomMembreField.getText());
	            membre.setPrenom(prenomMembreField.getText());
	            membre.setTelephone(Integer.parseInt(telMembreField.getText()));
	            membre.setCni(Integer.parseInt(cniMembreField.getText()));
	            membre.setAdresse(adresseMembreField.getText());
	            membre.setPhoto("");

	            validerClicked = true;
	            dialogStage.close();
	        }
	    }
	    
	   
	    /**
	     * Called when the user clicks annuler.
	     */
	    @FXML
	    private void handleAnnuler() {
	        dialogStage.close();
	    }

	    /**
	     * Validates the user input in the text fields.
	     *
	     * @return true if the input is valid
	     */
	    private boolean isInputValid() {
	        String errorMessage = "";

	        if (nomMembreField.getText() == null || nomMembreField.getText().length() == 0) {
	            errorMessage += "Nom invalide!\n";
	        }
	        if (prenomMembreField.getText() == null || prenomMembreField.getText().length() == 0) {
	            errorMessage += "Prenom invalide!\n";
	        }
	        if (telMembreField.getText() == null || telMembreField.getText().length() == 0) {
	            errorMessage += "Numero de telephone invalide!\n";
	        } else {
	        	 // try to parse the telephone number into an int.
	            try {
	                Integer.parseInt(telMembreField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "Numero de telephone invalide (ne doit contenir que des chiffres)!\n";
	            }
	        }

	        if (adresseMembreField.getText() == null || adresseMembreField.getText().length() == 0) {
	            errorMessage += "Adresse invalide!\n";
	        } 

	        if (cniMembreField.getText() == null || cniMembreField.getText().length() == 0) {
	            errorMessage += "Numero de CNI invalide!\n";
	        }else {
	        	 // try to parse the telephone number into an int.
	            try {
	                Integer.parseInt(cniMembreField.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "Numero de CNI invalide (ne doit contenir que des chiffres)!\n";
	            }
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
	
}
