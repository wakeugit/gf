package gf.model;

import gf.util.DateUtil;

import java.time.LocalDate;

import javafx.beans.property.*;

public class TransactionFx {

    private CotisationFx cotisationFx;
    private MembreFx membreFx;
    private final ObjectProperty<LocalDate> dateDerniereModification;
    private final ObjectProperty<LocalDate> dateOperation;
    private final ObjectProperty<LocalDate> dateCreation;
    private final ObjectProperty<LocalDate> dateRemboursement;
    private final SimpleDoubleProperty montantOperation;
    private final SimpleDoubleProperty montantAttendu;
    private final SimpleDoubleProperty tauxInterets;
    private final SimpleDoubleProperty montantInterets;
    private final SimpleDoubleProperty montantPenalites;
    private MembreFx avaliseur1;
    private MembreFx avaliseur2;
    private SimpleStringProperty type;
    private final SimpleLongProperty id;

    public TransactionFx() {
        this(null);
    }

    public TransactionFx(Transaction transaction) {
        this.cotisationFx = new CotisationFx(transaction.getCotisation());
        this.membreFx = new MembreFx(transaction.getMembre());
        this.dateDerniereModification = new SimpleObjectProperty<LocalDate>(DateUtil.parse(transaction.getDateDerniereModification()));
        this.dateCreation = new SimpleObjectProperty<LocalDate>(DateUtil.parse(transaction.getDateCreation()));
        this.dateRemboursement = new SimpleObjectProperty<LocalDate>(DateUtil.parse(transaction.getDateRemboursement()));
        this.dateOperation = new SimpleObjectProperty<LocalDate>(DateUtil.parse(transaction.getDateOperation()));
        this.montantOperation = new SimpleDoubleProperty(transaction.getMontantOperation());
        this.tauxInterets = new SimpleDoubleProperty(transaction.getTauxInteret());
        this.montantInterets = new SimpleDoubleProperty(transaction.getMontantAttendu());
        this.montantPenalites = new SimpleDoubleProperty(transaction.getMontantPenalites());
        this.montantAttendu = new SimpleDoubleProperty(transaction.getMontantAttendu());
        this.avaliseur1 = new MembreFx(transaction.getAvaliseur1());
        this.avaliseur2 = new MembreFx(transaction.getAvaliseur2());
        this.type = new SimpleStringProperty(transaction.getType().name());
        this.id = new SimpleLongProperty(transaction.getId());
    }

    public ObjectProperty<LocalDate> getDateModifProperty() {
        return dateDerniereModification;
    }

    public SimpleDoubleProperty getMontantProperty() {
        return montantOperation;
    }

    public SimpleDoubleProperty getPenalitesProperty() {
        return montantPenalites;
    }

    public String getDateDerniereModification() {
        return dateDerniereModification.get().toString();
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

    public MembreFx getAvaliseur2() {
        return avaliseur2;
    }

    public void setAvaliseur2(MembreFx avaliseur2) {
        this.avaliseur2 = avaliseur2;
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

    public ObjectProperty<LocalDate> getDateRemboursement() {
        return dateRemboursement;
    }

    public void setMembreFx(MembreFx membreFx) {
        this.membreFx = membreFx;
    }

    public ObjectProperty<LocalDate> getDateProperty() {
        return dateCreation;
    }


    public String getDateCreation() {
        return dateCreation.get().toString();
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

    public LocalDate getDateOperation() {
        return dateOperation.get();
    }

    public ObjectProperty<LocalDate> dateOperationProperty() {
        return dateOperation;
    }

    public void setDateOperation(LocalDate dateOperation) {
        this.dateOperation.set(dateOperation);
    }
}
