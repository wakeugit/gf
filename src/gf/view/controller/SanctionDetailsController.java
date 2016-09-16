package gf.view.controller;

import gf.model.Sanction;
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
    private Sanction sanction;
    private int keyInArray=0;
    private boolean validerClicked = false;
    
    @FXML
	private void initialize(){
		
	}
    
    public SanctionDetailsController(){
    	
    }
	
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

 public boolean isValiderClicked() {
        return validerClicked;
    }
	@FXML
	private void actionOnClickValider(){
		  if (isInputValid()) {
		sanction = new Sanction(motif.getText());
		
		
		if (valider.getText().equals("Valider")) {
			sanctionWindowController.getListeSanctions().add(sanction); 
		} else {
			sanctionWindowController.getListeSanctions().set(keyInArray, sanction);
		}
		 validerClicked = true;
      dialogStage.close();
		  }
	}
	
	@FXML
	private void actionOnClickAnnuler(){
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

	public Sanction getSanction() {
		return sanction;
	}

	public void setSanction(Sanction Sanction) {
		this.sanction = Sanction;
		valider.setText("Modifier");
		motif.setText(Sanction.getMotif());
	}

	public int getKeyInArray() {
		return keyInArray;
	}

	public void setKeyInArray(int keyInArray) {
		this.keyInArray = keyInArray;
	}
	
	
     
}
