package gf.view.controller;



import gf.model.Membre;
import gf.model.MembreFx;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MembreDetailsController {
	@FXML
	private TextField nomMembre;
	@FXML
	private TextField prenomMembre;
	@FXML
	private TextField telephone;
	@FXML
	private TextField adresse;
	@FXML
	private TextField cni;
	@FXML
	private Button valider;
	
	private MembrePanelController membrePanelController;
	private Stage dialogStage;
    private Membre membre;
    private boolean validerClicked = false;

	@FXML
	private void initialize(){
		
	}
	
	public MembreDetailsController(){
		
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
		membre = new Membre(		nomMembre.getText(), 
									prenomMembre.getText(), 
									Integer.parseInt(telephone.getText()), 
									Integer.parseInt(cni.getText()), 
									adresse.getText(),
									"N/A");
		
		membrePanelController.getListMembre().add(new MembreFx(membre)); 
		
		 validerClicked = true;
         dialogStage.close();
		  }
	}
	
	@FXML
	private void actionOnClickAnnuler(){
		nomMembre.setText("");
		prenomMembre.setText(""); 
		telephone.setText(""); 
		cni.setText(""); 
		adresse.setText("");
	}

	  private boolean isInputValid() {
	        String errorMessage = "";

	        if (nomMembre.getText() == null || nomMembre.getText().length() == 0) {
	            errorMessage += "Nom invalide!\n";
	        }
	        if (prenomMembre.getText() == null || prenomMembre.getText().length() == 0) {
	            errorMessage += "Prenom invalide!\n";
	        }
	        if (telephone.getText() == null || telephone.getText().length() == 0 ||telephone.getText().length() > 9 ) {
	            errorMessage += "Numero de telephone invalide!\n";
	        } else {
	        	 // try to parse the telephone number into an int.
	            try {
	                Integer.parseInt(telephone.getText());
	            } catch (NumberFormatException e) {
	                errorMessage += "Numero de telephone invalide (ne doit contenir que des chiffres)!\n";
	            }
	        }

	        if (adresse.getText() == null || adresse.getText().length() == 0) {
	            errorMessage += "Adresse invalide!\n";
	        } 

	        if (cni.getText() == null || cni.getText().length() == 0 ||cni.getText().length() > 9 ) {
	            errorMessage += "Numero de CNI invalide!\n";
	        }else {
	        	 // try to parse the telephone number into an int.
	            try {
	                Integer.parseInt(cni.getText());
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
	
	public MembrePanelController getMembrePanelController() {
		return membrePanelController;
	}

	public void setMembrePanelController(MembrePanelController membrePanelController) {
		this.membrePanelController = membrePanelController;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(MembreFx membreFx) {
		valider.setText("Modifier");
		nomMembre.setText(membreFx.getNom());
		prenomMembre.setText(membreFx.getPrenom()); 
		telephone.setText(""+membreFx.getTelephone()); 
		cni.setText(""+membreFx.getCni()); 
		adresse.setText(""+membreFx.getAdresse());
	}
	
	
	
}
