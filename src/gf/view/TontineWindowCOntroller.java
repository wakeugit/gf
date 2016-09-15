package gf.view;

import java.io.IOException;
import java.time.LocalDate;

import gf.model.Aide;
import gf.model.Cotisation;
import gf.model.MembreFx;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TontineWindowCOntroller {

	private ObservableList<Cotisation> listeTontine = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Cotisation> tontineTable;
    @FXML
    private TableColumn<Cotisation, String> nomCotisation;
    @FXML
    private TableColumn<Cotisation, String> type;
    @FXML
    private TableColumn<Cotisation, LocalDate> dateDebut;
    @FXML
    private TableColumn<Cotisation, LocalDate> dateFin;
    @FXML
    private TableColumn<Cotisation, String> annee;
    
    public TontineWindowCOntroller () {
    	listeTontine.add(new Cotisation("Tontine 2015", "Tontine", "01.01.2015", "13.12.2015", "2015"));
    	listeTontine.add(new Cotisation("Tontine 2016", "Tontine", "01.01.2016", "12.12.2016", "2016"));
    	listeTontine.add(new Cotisation("Tontine 2014", "Tontine", "01.01.2014", "12.12.2014", "2014"));
    	listeTontine.add(new Cotisation("Tontine 2013", "Tontine", "01.01.2013", "14.12.2013", "2013"));
    }
    
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.

    	nomCotisation.setCellValueFactory(cellData -> cellData.getValue().getnomCotisationProperty());
    	type.setCellValueFactory(cellData -> cellData.getValue().getType());
    	dateDebut.setCellValueFactory(cellData -> cellData.getValue().getDateDebut());
    	dateFin.setCellValueFactory(cellData -> cellData.getValue().getDateFin());
    	annee.setCellValueFactory(cellData -> cellData.getValue().getAnnee());
    	
    	tontineTable.setItems(listeTontine);
        
    }
    
    @FXML
    private void showCotisationDetails() {
    	
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/tontineDetails.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nouvelle tontine");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            TontineDetailsController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setTontineWindowCOntroller(this);
            
            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        } 
        	
 }
    @FXML
    private void showCotisationDetailsModifier() {
    	
    	int selectedIndex = tontineTable.getSelectionModel().getSelectedIndex();
    	
    	if(selectedIndex >= 0){
    		Cotisation cotisation = tontineTable.getItems().get(selectedIndex);
    		int keyInArrayList = listeTontine.indexOf(cotisation);
    		 try {
    	            // Load the fxml file and create a new stage for the popup dialog.
    	            FXMLLoader loader = new FXMLLoader();
    	            loader.setLocation(MainAppGF.class.getResource("/gf/view/tontineDetails.fxml"));
    	            BorderPane page = (BorderPane) loader.load();

    	            // Create the dialog Stage.
    	            Stage dialogStage = new Stage();
    	            dialogStage.setTitle("Modifier Tontine");
    	            dialogStage.initModality(Modality.WINDOW_MODAL);
    	            //dialogStage.initOwner(getPrimaryStage());
    	            Scene scene = new Scene(page);
    	            dialogStage.setScene(scene);

    	            TontineDetailsController controller = loader.getController();
    	            controller.setDialogStage(dialogStage);
    	            controller.setTontine(cotisation);
    	            controller.setKeyInArray(keyInArrayList);
    	            controller.setTontineWindowCOntroller(this);
    	            
    	            // Show the dialog and wait until the user closes it

    	            dialogStage.showAndWait();

    	            // return controller.isOkClicked();

    	        } catch (IOException e) {
    	            e.printStackTrace();

    	        } 	
    	} else {
    		Alert alert = new Alert(AlertType.WARNING);
          //  alert.initOwner(this.getPrimaryStage());
            alert.setTitle("Aucune ligne selectionée");
            alert.setHeaderText("Aucune ligne selectionée");
            alert.setContentText("Svp selectionnez un élement dans la liste.");

            alert.showAndWait();
    	}
         
    	
 }


	public ObservableList<Cotisation> getListeCotisations() {
		return listeTontine;
	}

	public void setListeCotisations(ObservableList<Cotisation> listeCotisations) {
		this.listeTontine = listeCotisations;
	}
    
    
}
