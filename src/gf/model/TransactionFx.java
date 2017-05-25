package gf.model;

import gf.util.DateUtil;

import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

public class TransactionFx {

    private CotisationFx cotisationFx;
    private MembreFx membreFx;
    private final ObjectProperty<LocalDate> dateDerniereModification;
    private final ObjectProperty<LocalDate> dateOperation;
    private final ObjectProperty<LocalDate> dateCreation;
    private final ObjectProperty<LocalDate> dateRemboursement;
    private final SimpleDoubleProperty montantOperation;
    private final SimpleDoubleProperty montantAttendu;
    private final SimpleDoubleProperty montantAvance;
    private final SimpleDoubleProperty tauxInterets;
    private final SimpleDoubleProperty montantInterets;
    private final SimpleDoubleProperty montantPenalites;
    private final SimpleDoubleProperty montantPlace;
    private final SimpleDoubleProperty montantBeneficie;
    private final SimpleDoubleProperty montantRetenu;
    private final SimpleDoubleProperty nombre;
    private final SimpleIntegerProperty duree;
    private MembreFx avaliseur1;
    private SimpleStringProperty avaliseur2;
    private SimpleStringProperty type;
    private final SimpleLongProperty id;

    public TransactionFx() {
        this(null);
    }

    public TransactionFx(Transaction transaction) {


        this.cotisationFx = new CotisationFx(transaction.getCotisation());
        this.membreFx = new MembreFx(transaction.getMembre());
        this.dateCreation = new SimpleObjectProperty<>(DateUtil.parse(new Date(transaction.getDateCreation())));
        this.dateDerniereModification = new SimpleObjectProperty<>(DateUtil.parse(new Date(transaction.getDateDerniereModification())));
        this.dateRemboursement = new SimpleObjectProperty<>(DateUtil.parse(new Date(transaction.getDateRemboursement())));
        this.dateOperation = new SimpleObjectProperty<>(DateUtil.parse(new Date(transaction.getDateOperation())));
        this.montantOperation = new SimpleDoubleProperty(transaction.getMontantOperation());
        this.tauxInterets = new SimpleDoubleProperty(transaction.getTauxInteret());
        this.montantInterets = new SimpleDoubleProperty(transaction.getMontantInteret());
        this.montantPenalites = new SimpleDoubleProperty(transaction.getMontantPenalites());
        this.montantAttendu = new SimpleDoubleProperty(transaction.getMontantAttendu());
        this.montantAvance = new SimpleDoubleProperty(transaction.getMontantAvance());
        this.montantPlace = new SimpleDoubleProperty(transaction.getMontantPlace());
        this.montantBeneficie = new SimpleDoubleProperty(transaction.getMontantBeneficie());
        this.montantRetenu = new SimpleDoubleProperty(transaction.getMontantRetenu());
        this.nombre = new SimpleDoubleProperty(transaction.getNombre());
        this.duree = new SimpleIntegerProperty(transaction.getDuree());
        this.avaliseur1 = new MembreFx(transaction.getAvaliseur1());
        this.avaliseur2 = new SimpleStringProperty(transaction.getAvaliseur2());
        this.type = new SimpleStringProperty(transaction.getType().name());
        this.id = new SimpleLongProperty(transaction.getId());
    }

    public CotisationFx getCotisationFx() {
        return cotisationFx;
    }

    public void setCotisationFx(CotisationFx cotisationFx) {
        this.cotisationFx = cotisationFx;
    }

    public MembreFx getMembreFx() {
        return membreFx;
    }

    public void setMembreFx(MembreFx membreFx) {
        this.membreFx = membreFx;
    }

    public LocalDate getDateDerniereModification() {
        return dateDerniereModification.get();
    }

    public ObjectProperty<LocalDate> dateDerniereModificationProperty() {
        return dateDerniereModification;
    }

    public void setDateDerniereModification(LocalDate dateDerniereModification) {
        this.dateDerniereModification.set(dateDerniereModification);
    }

    public LocalDate getDateOperation() {
        return dateOperation.get();
    }

    public ObjectProperty<LocalDate> dateOperationProperty() {
        return dateOperation;
    }

    public void setDateOperation(LocalDate dateOperation) {
        this.dateOperation.set(dateOperation);
    }

    public LocalDate getDateCreation() {
        return dateCreation.get();
    }

    public ObjectProperty<LocalDate> dateCreationProperty() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation.set(dateCreation);
    }

    public LocalDate getDateRemboursement() {
        return dateRemboursement.get();
    }

    public ObjectProperty<LocalDate> dateRemboursementProperty() {
        return dateRemboursement;
    }

    public void setDateRemboursement(LocalDate dateRemboursement) {
        this.dateRemboursement.set(dateRemboursement);
    }

    public double getMontantOperation() {
        return montantOperation.get();
    }

    public SimpleDoubleProperty montantOperationProperty() {
        return montantOperation;
    }

    public void setMontantOperation(double montantOperation) {
        this.montantOperation.set(montantOperation);
    }

    public double getMontantAttendu() {
        return montantAttendu.get();
    }

    public SimpleDoubleProperty montantAttenduProperty() {
        return montantAttendu;
    }

    public void setMontantAttendu(double montantAttendu) {
        this.montantAttendu.set(montantAttendu);
    }

    public double getMontantAvance() {
        return montantAvance.get();
    }

    public SimpleDoubleProperty montantAvanceProperty() {
        return montantAvance;
    }

    public void setMontantAvance(double montantAvance) {
        this.montantAvance.set(montantAvance);
    }

    public double getTauxInterets() {
        return tauxInterets.get();
    }

    public SimpleDoubleProperty tauxInteretsProperty() {
        return tauxInterets;
    }

    public void setTauxInterets(double tauxInterets) {
        this.tauxInterets.set(tauxInterets);
    }

    public double getMontantInterets() {
        return montantInterets.get();
    }

    public SimpleDoubleProperty montantInteretsProperty() {
        return montantInterets;
    }

    public void setMontantInterets(double montantInterets) {
        this.montantInterets.set(montantInterets);
    }

    public double getMontantPenalites() {
        return montantPenalites.get();
    }

    public SimpleDoubleProperty montantPenalitesProperty() {
        return montantPenalites;
    }

    public void setMontantPenalites(double montantPenalites) {
        this.montantPenalites.set(montantPenalites);
    }

    public double getMontantPlace() {
        return montantPlace.get();
    }

    public SimpleDoubleProperty montantPlaceProperty() {
        return montantPlace;
    }

    public void setMontantPlace(double montantPlace) {
        this.montantPlace.set(montantPlace);
    }

    public double getMontantBeneficie() {
        return montantBeneficie.get();
    }

    public SimpleDoubleProperty montantBeneficieProperty() {
        return montantBeneficie;
    }

    public void setMontantBeneficie(double montantBeneficie) {
        this.montantBeneficie.set(montantBeneficie);
    }

    public double getMontantRetenu() {
        return montantRetenu.get();
    }

    public SimpleDoubleProperty montantRetenuProperty() {
        return montantRetenu;
    }

    public void setMontantRetenu(double montantRetenu) {
        this.montantRetenu.set(montantRetenu);
    }

    public double getNombre() {
        return nombre.get();
    }

    public SimpleDoubleProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(double nombre) {
        this.nombre.set(nombre);
    }

    public int getDuree() {
        return duree.get();
    }

    public SimpleIntegerProperty dureeProperty() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree.set(duree);
    }

    public MembreFx getAvaliseur1() {
        return avaliseur1;
    }

    public void setAvaliseur1(MembreFx avaliseur1) {
        this.avaliseur1 = avaliseur1;
    }

    public String getAvaliseur2() {
        return avaliseur2.get();
    }

    public SimpleStringProperty avaliseur2Property() {
        return avaliseur2;
    }

    public void setAvaliseur2(String avaliseur2) {
        this.avaliseur2.set(avaliseur2);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }
}
