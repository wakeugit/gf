package gf.view;

import java.io.IOException;

import gf.model.Aide;
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

public class AidesWindowCOntroller {

	private ObservableList<Aide> listeAides = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Aide> aideTable;
    @FXML
    private TableColumn<Aide, String> motif;
    
    public AidesWindowCOntroller () {
    	listeAides.add(new Aide("Mort"));
    	listeAides.add(new Aide("Naissance"));
    }
    
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.

    	motif.setCellValueFactory(cellData -> cellData.getValue().getMotifProperty());
    	
    	aideTable.setItems(listeAides);
        
    }
    
    @FXML
    private void showAidesDetails() {
    	
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppGF.class.getResource("/gf/view/aideDetails.fxml"));
            BorderPane page = (BorderPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nouvelle Aide");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            //dialogStage.initOwner(getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AideDetailsController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAideWindowController(this);
            
            // Show the dialog and wait until the user closes it

            dialogStage.showAndWait();

            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        } 
        	
 }
    @FXML
    private void showAidesDetailsModifier() {
    	
    	int selectedIndex = aideTable.getSelectionModel().getSelectedIndex();
    	
    	if(selectedIndex >= 0){
    		Aide aide = aideTable.getItems().get(selectedIndex);
    		int keyInArrayList = listeAides.indexOf(aide);
    		 try {
    	            // Load the fxml file and create a new stage for the popup dialog.
    	            FXMLLoader loader = new FXMLLoader();
    	            loader.setLocation(MainAppGF.class.getResource("/gf/view/aideDetails.fxml"));
    	            BorderPane page = (BorderPane) loader.load();

    	            // Create the dialog Stage.
    	            Stage dialogStage = new Stage();
    	            dialogStage.setTitle("Modifier Aide");
    	            dialogStage.initModality(Modality.WINDOW_MODAL);
    	            //dialogStage.initOwner(getPrimaryStage());
    	            Scene scene = new Scene(page);
    	            dialogStage.setScene(scene);

    	            AideDetailsController controller = loader.getController();
    	            controller.setDialogStage(dialogStage);
    	            controller.setAide(aide);
    	            controller.setKeyInArray(keyInArrayList);
    	            controller.setAideWindowController(this);
    	            
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


	public ObservableList<Aide> getListeAides() {
		return listeAides;
	}

	public void setListeAides(ObservableList<Aide> listeAides) {
		this.listeAides = listeAides;
	}
    
    
}
