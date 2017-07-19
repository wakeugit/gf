package gf.view;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.Cotisation;
import gf.model.CotisationFx;
import gf.model.TypeCotisation;
import gf.util.DateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	private ComboBox<String> frequence;
	@FXML
	private Button valider;

	ObservableList<String> frequenceSeance = FXCollections.observableArrayList();
	private AnneesWindowController anneeWindowController;
	private Stage dialogStage;
	private CotisationFx anneeFx;
	private int keyInArray=0;
	private boolean validerClicked = false;

	@FXML
	private void initialize() {
		frequenceSeance.add("1 fois/semaine");
		frequenceSeance.add("1 fois/mois");
		frequenceSeance.add("2 fois/mois");
		frequenceSeance.add("3 fois/mois");
		frequenceSeance.add("4 fois/mois");
		
		frequence.setItems(frequenceSeance);
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
			LocalDate dateTmp =dateDebut.getValue();
			System.out.println("date "+ dateTmp.getYear()+" "+ dateTmp.getMonthValue()+" "+ dateTmp.getDayOfMonth());
			Calendar debut = new GregorianCalendar(dateTmp.getYear(), dateTmp.getMonthValue(), dateTmp.getDayOfMonth());
			dateTmp =dateFin.getValue();
			Calendar fin = new GregorianCalendar(dateTmp.getYear(), dateTmp.getMonthValue(), dateTmp.getDayOfMonth());

	        
	        int debPointeur = 0;
	        int finPointeur = 0;
	        
	        int anneeDebut=debut.get(Calendar.YEAR);
	        int anneeFin=fin.get(Calendar.YEAR);
	        int seances=0;
	       
	       
			//Calculer le nombre de seances
			if(frequence.getSelectionModel().getSelectedItem().equals("1 fois/semaine")){
				
				debPointeur = debut.get(Calendar.WEEK_OF_YEAR);
			    finPointeur = fin.get(Calendar.WEEK_OF_YEAR);
			   if(anneeDebut==anneeFin)
				   seances=finPointeur-debPointeur+1;	
			   else
				   seances=finPointeur+52*(anneeFin-anneeDebut)-debPointeur;
				System.out.println("pointeur debut: " + debPointeur);
			    System.out.println("pointeur fin: " + finPointeur);
			}
			if(frequence.getSelectionModel().getSelectedItem().equals("1 fois/mois")){
				debPointeur = debut.get(Calendar.MONTH);
			    finPointeur = fin.get(Calendar.MONTH);
			    if(anneeDebut==anneeFin)
					   seances=finPointeur-debPointeur+1;	
				   else
					   seances=finPointeur+12*(anneeFin-anneeDebut)-debPointeur;
			}
			
			if(frequence.getSelectionModel().getSelectedItem().equals("2 fois/mois")){
				debPointeur = debut.get(Calendar.MONTH);
			    finPointeur = fin.get(Calendar.MONTH);
			    if(anneeDebut==anneeFin)
					   seances=finPointeur-debPointeur+1;	
				   else
					   seances=finPointeur+52*(anneeFin-anneeDebut)-debPointeur;
				seances*=2;	
			}
			
			if(frequence.getSelectionModel().getSelectedItem().equals("3 fois/mois")){
				debPointeur = debut.get(Calendar.MONTH);
			    finPointeur = fin.get(Calendar.MONTH);
			    if(anneeDebut==anneeFin)
					   seances=finPointeur-debPointeur+1;	
				   else
					   seances=finPointeur+52*(anneeFin-anneeDebut)-debPointeur;
				seances*=3;		
			}
			
			if(frequence.getSelectionModel().getSelectedItem().equals("4 fois/mois")){
				debPointeur = debut.get(Calendar.MONTH);
			    finPointeur = fin.get(Calendar.MONTH);
			    if(anneeDebut==anneeFin)
					   seances=finPointeur-debPointeur+1;	
				   else
					   seances=finPointeur+52*(anneeFin-anneeDebut)-debPointeur;
				seances*=4;		
			}
			
			Cotisation annee = new Cotisation("Cotisation Annuelle", TypeCotisation.ANNEE , DateUtil.format(dateDebut
					.getValue()), DateUtil.format(dateFin.getValue()), anneeTxt.getText());
			
			
			System.out.println("nombre de seances: " + seances);
			
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
