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
        this.montantInterets = new SimpleDoubleProperty(transaction.getMontantAttendu());
        this.montantPenalites = new SimpleDoubleProperty(transaction.getMontantPenalites());
        this.montantAttendu = new SimpleDoubleProperty(transaction.getMontantAttendu());
        this.montantAvance = new SimpleDoubleProperty(transaction.getMontantAvance());
        this.avaliseur1 = new MembreFx(transaction.getAvaliseur1());
        this.avaliseur2 = new SimpleStringProperty(transaction.getAvaliseur2());
        this.type = new SimpleStringProperty(transaction.getType().name());
        this.id = new SimpleLongProperty(transaction.getId());
    }


    public SimpleDoubleProperty getMontantProperty() {
        return montantOperation;
    }

    public SimpleDoubleProperty getPenalitesProperty() {
        return montantPenalites;
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

    public double getMontantOperation() {
        return montantOperation.get();
    }

    public double getMontantPenalites() {
        return montantPenalites.get();
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

    public void setMembrefx(MembreFx membreFx) {
        this.membreFx = membreFx;
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

    public void setAvaliseur2(String avaliseur2) {
        this.avaliseur2 = new SimpleStringProperty(avaliseur2);
    }

    public SimpleDoubleProperty getMontantEmprunte() {
        return montantOperation;
    }

    public SimpleDoubleProperty getTauxInterets() {
        return tauxInterets;
    }

    public SimpleDoubleProperty getMontantInterets() {
        return montantInterets;
    }


    public void setMembreFx(MembreFx membreFx) {
        this.membreFx = membreFx;
    }


    public SimpleLongProperty getIdProperty() {
        return id;
    }

    public long getId() {
        return id.get();
    }

    public SimpleStringProperty getType() {
        return type;
    }

    public void setType(SimpleStringProperty type) {
        this.type = type;
    }

    public double getMontantAttendu() {
        return montantAttendu.get();
    }

    public SimpleDoubleProperty montantAttenduProperty() {
        return montantAttendu;
    }

    public void setMontantAttendu(int montantAttendu) {
        this.montantAttendu.set(montantAttendu);
    }

    public LocalDate getDateDerniereModification() {
        return dateDerniereModification.get();
    }

    public ObservableValue<LocalDate> dateDerniereModificationProperty() {
        return dateDerniereModification;
    }

    public void setDateDerniereModification(LocalDate dateDerniereModification) {
        this.dateDerniereModification.set(dateDerniereModification);
    }

    public LocalDate getDateOperation() {
        return dateOperation.get();
    }

    public ObservableValue<LocalDate> dateOperationProperty() {
        return dateOperation;
    }

    public void setDateOperation(LocalDate dateOperation) {
        this.dateOperation.set(dateOperation);
    }

    public ObservableValue<LocalDate> dateCreationProperty() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation.set(dateCreation);
    }

    public LocalDate getDateRemboursement() {
        return dateRemboursement.get();
    }

    public ObservableValue<LocalDate> dateRemboursementProperty() {
        return dateRemboursement;
    }

    public void setDateRemboursement(LocalDate dateRemboursement) {
        this.dateRemboursement.set(dateRemboursement);
    }
}
