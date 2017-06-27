package gf.view;

import gf.backend.BackendInterface;
import gf.backend.Response;
import gf.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;

public class SanctionPanelController {

    private MainAppGF mainAppGF;

    private ObservableList<InscriptionAnnuelleFx> listeInscritsAnnuel = FXCollections.observableArrayList();
    private ObservableList<InscriptionCotisationFx> listeInscritsCotisation = FXCollections.observableArrayList();
    private ObservableList<CotisationFx> listeAnnees = FXCollections.observableArrayList();
    private ObservableList<CotisationFx> listeCotisations = FXCollections.observableArrayList();
    private ObservableList<OperationFx> listeOperations = FXCollections.observableArrayList();
    private ObservableList<OperationFx> listeOperationsRemb = FXCollections.observableArrayList();
    private ObservableList<ServiceFx> listeServices = FXCollections.observableArrayList();

    @FXML
    private TableView<InscriptionAnnuelleFx> inscritsAnnuelTable;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, Long> idCol;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> nomCol;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> prenomCol;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> adresseCol;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, Integer> cniCol;

    @FXML
    private TableView<OperationFx> listeServiceTable;
    @FXML
    private TableColumn<OperationFx, Long> idService;
    @FXML
    private TableColumn<OperationFx, String> nomMembre;
    @FXML
    private TableColumn<OperationFx, String> prenomMembre;
    @FXML
    private TableColumn<OperationFx, String> motifService;
    @FXML
    private TableColumn<OperationFx, LocalDate> dateService;
    @FXML
    private TableColumn<OperationFx, Double> montantService;
    @FXML
    private TableColumn<OperationFx, LocalDate> dateRemb;

    @FXML
    private TableView<InscriptionAnnuelleFx> listeMembresTable;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, Long> idCol1;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> nomCol1;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> prenomCol1;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, String> adresseCol1;
    @FXML
    private TableColumn<InscriptionAnnuelleFx, Integer> cniCol1;

    @FXML
    private TableView<OperationFx> etatServiceTable;
    @FXML
    private TableColumn<OperationFx, Long> idRemb;
    @FXML
    private TableColumn<OperationFx, String> nomCol2;
    @FXML
    private TableColumn<OperationFx, String> prenomCol2;
    @FXML
    private TableColumn<OperationFx, String> serviceCol;
    @FXML
    private TableColumn<OperationFx, Double> montantService2;
    @FXML
    private TableColumn<OperationFx, LocalDate> dateRemb2;
    @FXML
    private TableColumn<OperationFx, Double> montantRemb;


    @FXML
    private ComboBox<CotisationFx> comboAnneeDonner;
    @FXML
    private ComboBox<CotisationFx> comboAnneeListe;
    @FXML
    private ComboBox<CotisationFx> comboAnneeRemb;
    @FXML
    private ComboBox<OperationFx> comboServiceRemb;
    @FXML
    private ComboBox<CotisationFx> comboAnneeEtat;
    @FXML
    private ComboBox<OperationFx> comboServiceEtat;
    @FXML
    private Button validerDonnerService;
    @FXML
    private Button validerListeService;
    @FXML
    private Button validerRembourserService;
    @FXML
    private Button validerEtatService;
    
    

    private Cotisation mCotisation;
    private Operation mOperation;

    public SanctionPanelController() {


        Response<Cotisation[]> response = BackendInterface.getCotisationsByType(TypeCotisation.ANNEE);
        if (response.getBody() != null) {
            for (Cotisation cotisation : response.getBody()) {
                listeAnnees.add(new CotisationFx(cotisation));
            }
        } else {
            //Todo Display error message
            System.out.println("An error occured - Annee");
        }

        Response<Service[]> response1 = BackendInterface.getServiceByType(TypeService.AIDE);
        if (response1.getBody() != null) {
            listeServices.clear();
            for (Service service : response1.getBody()) {
                listeServices.add(new ServiceFx(service));
            }
        } else {
            // Todo Display error message
            System.out.println("An error occured - ValiderService");
        }

/*
        Response<Cotisation[]> response1 = BackendInterface.getCotisationsByType(TypeCotisation.TONTINE);
        Response<Cotisation[]> response2 = BackendInterface.getCotisationsByType(TypeCotisation.EPARGNE);
        if (response1.getBody() != null || response2.getBody() != null ) {
        	
        	for (Cotisation cotisation : response2.getBody()) {
                listeCotisations.add(new CotisationFx(cotisation));
            }
            for (Cotisation cotisation : response1.getBody()) {
                listeCotisations.add(new CotisationFx(cotisation));
            }
        } else {
            //Todo Display error message
            System.out.println("An error occured - Cotisation");
        }
        */
    }


    @FXML
    private void initialize() {
        // Initialize the person table with the two columns
        if (comboAnneeDonner != null) {
            comboAnneeDonner.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getAnnee());
                        mCotisation = new Cotisation(item);
                    }
                }
            });

            comboAnneeDonner.setCellFactory(
                    new Callback<ListView<CotisationFx>, ListCell<CotisationFx>>() {

                        @Override
                        public ListCell<CotisationFx> call(ListView<CotisationFx> arg0) {
                            ListCell<CotisationFx> cell = new ListCell<CotisationFx>() {
                                @Override
                                protected void updateItem(CotisationFx item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setText("");
                                    } else {
                                        setText(item.getAnnee());
                                    }
                                }
                            };
                            return cell;
                        }


                    });
            comboAnneeDonner.setItems(listeAnnees);
        }
        if (comboAnneeListe != null) {
            comboAnneeListe.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getAnnee());
                        mCotisation = new Cotisation(item);
                    }
                }
            });

            comboAnneeListe.setCellFactory(
                    new Callback<ListView<CotisationFx>, ListCell<CotisationFx>>() {

                        @Override
                        public ListCell<CotisationFx> call(ListView<CotisationFx> arg0) {
                            ListCell<CotisationFx> cell = new ListCell<CotisationFx>() {
                                @Override
                                protected void updateItem(CotisationFx item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setText("");
                                    } else {
                                        setText(item.getAnnee());
                                    }
                                }
                            };
                            return cell;
                        }


                    });
            comboAnneeListe.setItems(listeAnnees);
        }

        if (comboAnneeRemb != null) {
            comboAnneeRemb.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getAnnee());
                        mCotisation = new Cotisation(item);
                    }
                }
            });

            comboAnneeRemb.setCellFactory(
                    new Callback<ListView<CotisationFx>, ListCell<CotisationFx>>() {

                        @Override
                        public ListCell<CotisationFx> call(ListView<CotisationFx> arg0) {
                            ListCell<CotisationFx> cell = new ListCell<CotisationFx>() {
                                @Override
                                protected void updateItem(CotisationFx item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setText("");
                                    } else {
                                        setText(item.getAnnee());
                                    }
                                }
                            };
                            return cell;
                        }


                    });
            comboAnneeRemb.setItems(listeAnnees);
        }

        if (comboServiceRemb != null) {
            comboServiceRemb.setButtonCell(new ListCell<OperationFx>() {
                @Override
                protected void updateItem(OperationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getServiceFx().getMotif() + " - " + item.getMembreFx().getNom() + " - " + item.getMontantOperation());
                        mOperation = new Operation(item);
                    }
                }
            });

            comboServiceRemb.setCellFactory(
                    new Callback<ListView<OperationFx>, ListCell<OperationFx>>() {

                        @Override
                        public ListCell<OperationFx> call(ListView<OperationFx> arg0) {
                            ListCell<OperationFx> cell = new ListCell<OperationFx>() {
                                @Override
                                protected void updateItem(OperationFx item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setText("");
                                    } else {
                                        setText(item.getServiceFx().getMotif() + " - " + item.getMembreFx().getNom() + " - " + item.getMontantOperation());
                                    }
                                }
                            };
                            return cell;
                        }


                    });
            comboServiceRemb.setItems(listeOperations);
        }

        if (comboAnneeEtat != null) {
            comboAnneeEtat.setButtonCell(new ListCell<CotisationFx>() {
                @Override
                protected void updateItem(CotisationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getAnnee());
                        mCotisation = new Cotisation(item);
                    }
                }
            });

            comboAnneeEtat.setCellFactory(
                    new Callback<ListView<CotisationFx>, ListCell<CotisationFx>>() {

                        @Override
                        public ListCell<CotisationFx> call(ListView<CotisationFx> arg0) {
                            ListCell<CotisationFx> cell = new ListCell<CotisationFx>() {
                                @Override
                                protected void updateItem(CotisationFx item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setText("");
                                    } else {
                                        setText(item.getAnnee());
                                    }
                                }
                            };
                            return cell;
                        }


                    });
            comboAnneeEtat.setItems(listeAnnees);

        }

        if (comboServiceEtat != null) {
            comboServiceEtat.setButtonCell(new ListCell<OperationFx>() {
                @Override
                protected void updateItem(OperationFx item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText("");
                    } else {
                        setText(item.getServiceFx().getMotif() + " - " + item.getMembreFx().getNom() + " - " + item.getMontantOperation());
                        mOperation = new Operation(item);
                    }
                }
            });

            comboServiceEtat.setCellFactory(
                    new Callback<ListView<OperationFx>, ListCell<OperationFx>>() {

                        @Override
                        public ListCell<OperationFx> call(ListView<OperationFx> arg0) {
                            ListCell<OperationFx> cell = new ListCell<OperationFx>() {
                                @Override
                                protected void updateItem(OperationFx item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setText("");
                                    } else {
                                        setText(item.getServiceFx().getMotif() + " - " + item.getMembreFx().getNom() + " - " + item.getMontantOperation());
                                    }
                                }
                            };
                            return cell;
                        }


                    });
            comboServiceEtat.setItems(listeOperations);
        }


        idCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().idProperty().asObject());
        nomCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        adresseCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().adresseProperty());
        cniCol.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().cniProperty().asObject());

        idCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().idProperty().asObject());
        nomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        adresseCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().adresseProperty());
        cniCol1.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().cniProperty().asObject());

        idService.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomMembre.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomMembre.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        motifService.setCellValueFactory(cellData -> cellData.getValue().getServiceFx().getMotifProperty());
        dateService.setCellValueFactory(cellData -> cellData.getValue().dateOperationProperty());
        montantService.setCellValueFactory(cellData -> cellData.getValue().montantOperationProperty().asObject());
        dateRemb.setCellValueFactory(cellData -> cellData.getValue().dateRemboursementProperty());


        idRemb.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomCol2.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().nomProperty());
        prenomCol2.setCellValueFactory(cellData -> cellData.getValue().getMembreFx().prenomProperty());
        serviceCol.setCellValueFactory(cellData -> cellData.getValue().getOperation());
        dateRemb2.setCellValueFactory(cellData -> cellData.getValue().dateOperationProperty());
        montantRemb.setCellValueFactory(cellData -> cellData.getValue().montantOperationProperty().asObject());
        montantService2.setCellValueFactory(cellData -> cellData.getValue().montantAttenduProperty().asObject());


        inscritsAnnuelTable.setItems(listeInscritsAnnuel);
        listeMembresTable.setItems(listeInscritsAnnuel);
        //listeServiceTable.setItems(listeOperations);
        //listeServiceTable.setItems();
        etatServiceTable.setItems(listeOperationsRemb);


    }


    @FXML
    private void actionOnClickValiderDonnerService() {

        if (mCotisation != null) {
            Response<InscriptionAnnuelle[]> response;

            response = BackendInterface.getInscriptionAnnuelle(mCotisation);
            if (response.getBody() != null) {
                if (mCotisation.getTypeCotisation() == TypeCotisation.ANNEE) {
                    listeInscritsAnnuel.clear();
                    for (InscriptionAnnuelle inscriptionCotisation : response.getBody()) {
                        listeInscritsAnnuel.add(new InscriptionAnnuelleFx(inscriptionCotisation));
                    }
                }


            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }
        }


    }

    @FXML
    private void actionOnClickValiderListeService() {

        if (mCotisation != null) {
            Response<Operation[]> response;

            response = BackendInterface.getOperationsByCotisationAndType(mCotisation, TypeOperation.AIDER);
            if (response.getBody() != null) {
                listeOperations.clear();
                for (Operation operation : response.getBody()) {
                    listeOperations.add(new OperationFx(operation));
                }

            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderListeAide");
            }
        }


    }

    @FXML
    private void actionOnClickValiderRembourserService() {

        if (mCotisation != null) {
            Response<InscriptionAnnuelle[]> response;

            response = BackendInterface.getMembresPourRemboursementAide(mCotisation, mOperation, TypeOperation.AIDER);
            if (response.getBody() != null) {
                listeInscritsAnnuel.clear();
                for (InscriptionAnnuelle inscriptionAnnuelle : response.getBody()) {
                    listeInscritsAnnuel.add(new InscriptionAnnuelleFx(inscriptionAnnuelle));
                }
                RembourserServiceController.effectif = listeInscritsAnnuel.size();
            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }
        }
    }

    @FXML
    private void actionOnClickValiderEtatServices() {

        if (mCotisation != null) {
            Response<Operation[]> response;

            response = BackendInterface.getMembresRembourseurtAide(mCotisation, mOperation, TypeOperation.REMBOURSER_AIDE);
            if (response.getBody() != null) {
                listeOperationsRemb.clear();
                for (Operation operation : response.getBody()) {
                    listeOperationsRemb.add(new OperationFx(operation));
                }
                RembourserServiceController.effectif = listeInscritsAnnuel.size();
            } else {
                // Todo Display error message
                System.out.println("An error occured - ValiderCotisation");
            }
        }


    }

    @FXML
    private void actionOnclickNouveauService() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            int selected = inscritsAnnuelTable.getSelectionModel().getSelectedIndex();

            if (selected >=0) {

                InscriptionAnnuelleFx tmpSelected = inscritsAnnuelTable.getItems().get(selected);

                EffectuerServiceController.tmpMembre = tmpSelected;
                EffectuerServiceController.typeService = TypeService.AIDE;
				
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/effectuerService.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Effectuer une sanction");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                EffectuerServiceController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setSanctionPanelController(this);

                // Show the dialog and wait until the user closes it

                dialogStage.showAndWait();
            } else {
                // todo alert no element selected

            }


            // return controller.isOkClicked();

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @FXML
    private void actionOnclickModifierService() {

        int selectedIndex = inscritsAnnuelTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            InscriptionAnnuelleFx mbreInscritFx = inscritsAnnuelTable.getItems().get(selectedIndex);
            int keyInArrayList = listeInscritsAnnuel.indexOf(mbreInscritFx);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/effectuerService.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier Inscription");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                InscriptionAnnuelleController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setInscriptionAnnuelle(mbreInscritFx);
                controller.setKeyInArray(keyInArrayList);
                //controller.setInscriptionsPanelController(this);

                // Show the dialog and wait until the user closes it

                dialogStage.showAndWait();

                // return controller.isOkClicked();

            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainAppGF.getPrimaryStage());
            alert.setTitle("Aucune ligne selectionnee");
            alert.setHeaderText("Aucune ligne selectionnee");
            alert.setContentText("Svp selectionnez un element dans la liste.");

            alert.showAndWait();
        }
    }


    @FXML
    private void actionOnclickSupprimerService() {

       /* int selectedIndex = inscritsAnnuelTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            //// TODO: 20/04/2017 Add alert before delete element
            BackendInterface.deleteInscriptionAnnuelle(inscritsAnnuelTable.getItems().get(selectedIndex).getId());

            actionOnClickValiderAnnuelle();
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainAppGF.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }*/
    }


    @FXML
    private void actionOnclickNouveauRemboursementService() {

        int selected = listeMembresTable.getSelectionModel().getSelectedIndex();
        if (selected >= 0) {
            try {
                InscriptionAnnuelleFx annuelle = listeMembresTable.getItems().get(selected);

                RembourserServiceController.tmpMembre = annuelle;
                RembourserServiceController.tmpOperation = mOperation;

                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/rembourserService.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Nouveau Remboursement");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                RembourserServiceController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setSanctionPanelController(this);

                // Show the dialog and wait until the user closes it

                dialogStage.showAndWait();

                // return controller.isOkClicked();

            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            //todo alert no element selected
        }

    }

    @FXML
    private void actionOnclickModifierRemboursementService() {

        /*int selectedIndex = inscritsCotisationTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
        	InscriptionCotisationFx mbreInscritFx = inscritsCotisationTable.getItems().get(selectedIndex);
        	 int keyInArrayList = listeInscritsCotisation.indexOf(mbreInscritFx);
            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainAppGF.class.getResource("/gf/view/inscriptionCotisation.fxml"));
                BorderPane page = (BorderPane) loader.load();

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Modifier Inscription");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(mainAppGF.getPrimaryStage());
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);

                // Set the Member into the controller.
                InscriptionCotisationController controller = loader.getController();
                controller.setDialogStage(dialogStage);
                controller.setInscriptionCotisation(mbreInscritFx);
                controller.setKeyInArray(keyInArrayList);
                controller.setInscriptionsPanelController(this);

                // Show the dialog and wait until the user closes it

                dialogStage.showAndWait();

                // return controller.isOkClicked();

            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainAppGF.getPrimaryStage());
            alert.setTitle("Aucune ligne selectionnee");
            alert.setHeaderText("Aucune ligne selectionnee");
            alert.setContentText("Svp selectionnez un element dans la liste.");

            alert.showAndWait();
        }*/
    }


    @FXML
    private void actionOnclickSupprimerRemboursementService() {

       /* int selectedIndex = inscritsCotisationTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            //// TODO: 20/04/2017 Add alert before delete element
            BackendInterface.deleteInscriptionCotisation(inscritsCotisationTable.getItems().get(selectedIndex).getId());
            actionOnClickValiderCotisation();
        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.initOwner(mainAppGF.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }*/
    }

    public MainAppGF getMainAppGF() {
        return mainAppGF;
    }

    public void setMainAppGF(MainAppGF mainAppGF) {
        this.mainAppGF = mainAppGF;
    }

    public ObservableList<InscriptionAnnuelleFx> getListMembreInscrits() {
        return listeInscritsAnnuel;
    }

    public ObservableList<InscriptionCotisationFx> getListMembreInscritsCotisation() {
        return listeInscritsCotisation;
    }

}
