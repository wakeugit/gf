package gf.view;

import gf.model.Aide;
import gf.model.AideFx;
import gf.model.ServiceFx;
import gf.model.Service;
import gf.model.TypeService;
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
    //private AideFx aideFx;
    private ServiceFx serviceFx;
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
		//aideFx = new AideFx(new Aide(motif.getText()));
		serviceFx = new ServiceFx(new Service(motif.getText(), TypeService.AIDE));
		
		
		if (valider.getText().equals("Valider")) {
			aideWindowController.getListeAides().add(serviceFx); 
		} else {
			aideWindowController.getListeAides().set(keyInArray, serviceFx);
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

	public ServiceFx getServiceFx() {
		return serviceFx;
	}

	public void setService(ServiceFx serviceFx) {
		this.serviceFx = serviceFx;
		valider.setText("Modifier");
		motif.setText(serviceFx.getMotif());
	}

	public int getKeyInArray() {
		return keyInArray;
	}

	public void setKeyInArray(int keyInArray) {
		this.keyInArray = keyInArray;
	}
	
	
     
}
