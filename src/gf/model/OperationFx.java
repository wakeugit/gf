package gf.model;

import gf.util.DateUtil;

import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;

public class OperationFx {

    private ServiceFx serviceFx;
    private MembreFx membreFx;
    private final ObjectProperty<LocalDate> dateDerniereModification;
    private final ObjectProperty<LocalDate> dateOperation;
    private final ObjectProperty<LocalDate> dateCreation;
    private final ObjectProperty<LocalDate> dateRemboursement;
    private final SimpleDoubleProperty montantOperation;
    private final SimpleDoubleProperty montantAttendu;
    private final SimpleDoubleProperty montantAvance;
    final SimpleDoubleProperty montantPenalites;
    private SimpleStringProperty type;
    private final SimpleLongProperty id;

    public OperationFx() {
        this(null);
    }

    public OperationFx(Operation operation) {


        this.serviceFx = new ServiceFx(operation.getService());
        this.membreFx = new MembreFx(operation.getMembre());
        this.dateCreation = new SimpleObjectProperty<>(DateUtil.parse(new Date(operation.getDateCreation())));
        this.dateDerniereModification = new SimpleObjectProperty<>(DateUtil.parse(new Date(operation.getDateDerniereModification())));
        this.dateRemboursement = new SimpleObjectProperty<>(DateUtil.parse(new Date(operation.getDateRemboursement())));
        this.dateOperation = new SimpleObjectProperty<>(DateUtil.parse(new Date(operation.getDateOperation())));
        this.montantOperation = new SimpleDoubleProperty(operation.getMontantOperation());
        this.montantPenalites = new SimpleDoubleProperty(operation.getMontantPenalites());
        this.montantAttendu = new SimpleDoubleProperty(operation.getMontantAttendu());
        this.montantAvance = new SimpleDoubleProperty(operation.getMontantAvance());
        this.type = new SimpleStringProperty(operation.getType().name());
        this.id = new SimpleLongProperty(operation.getId());
    }

    public ServiceFx getServiceFx() {
        return serviceFx;
    }

    public void setServiceFx(ServiceFx serviceFx) {
        this.serviceFx = serviceFx;
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

    public void setDateTransaction(LocalDate dateOperation) {
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

    public double getMontantPenalites() {
        return montantPenalites.get();
    }

    public SimpleDoubleProperty montantPenalitesProperty() {
        return montantPenalites;
    }

    public void setMontantPenalites(double montantPenalites) {
        this.montantPenalites.set(montantPenalites);
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
    
    @Override
	public String toString() {
		return getMembreFx().getNom()+" "+ getServiceFx().getMotif();
	}
    
    public SimpleStringProperty getOperation(){
    	return new SimpleStringProperty(toString());
    }
    
}
