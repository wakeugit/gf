package gf.view.controller;

import gf.model.Aide;
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
    private Aide aide;
    private int keyInArray=0;
    private boolean validerClicked = false;
    
    @FXML
	private void initialize(){
		
	}
    
    public AideDetailsController(){
    	
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
		aide = new Aide(motif.getText());
		
		
		if (valider.getText().equals("Valider")) {
			aideWindowController.getListeAides().add(aide); 
		} else {
			aideWindowController.getListeAides().set(keyInArray, aide);
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

	public AidesWindowController getAideWindowController() {
		return aideWindowController;
	}

	public void setAideWindowController(AidesWindowController aideWindowController) {
		this.aideWindowController = aideWindowController;
	}

	public Aide getAide() {
		return aide;
	}

	public void setAide(Aide aide) {
		this.aide = aide;
		valider.setText("Modifier");
		motif.setText(aide.getMotif());
	}

	public int getKeyInArray() {
		return keyInArray;
	}

	public void setKeyInArray(int keyInArray) {
		this.keyInArray = keyInArray;
	}
	
	
     
}
