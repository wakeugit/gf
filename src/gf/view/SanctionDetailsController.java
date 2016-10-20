package gf.view;

import gf.model.Sanction;
import gf.model.SanctionFx;
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
    private SanctionFx sanctionFx;
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
		sanctionFx = new SanctionFx(new Sanction(motif.getText()));
		
		
		if (valider.getText().equals("Valider")) {
			sanctionWindowController.getListeSanctions().add(sanctionFx); 
		} else {
			sanctionWindowController.getListeSanctions().set(keyInArray, sanctionFx);
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

	public SanctionFx getSanction() {
		return sanctionFx;
	}

	public void setSanction(SanctionFx SanctionFx) {
		this.sanctionFx = SanctionFx;
		valider.setText("Modifier");
		motif.setText(SanctionFx.getMotif());
	}

	public int getKeyInArray() {
		return keyInArray;
	}

	public void setKeyInArray(int keyInArray) {
		this.keyInArray = keyInArray;
	}
    
}
