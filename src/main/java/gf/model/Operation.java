package gf.model;

import java.util.Date;


import gf.util.DateUtil;
import lombok.Data;

@Data
public class Operation {

    private long id;

    private long dateCreation;

    private long dateDerniereModification;

    private long dateOperation;

    private long dateRemboursement;

    private double montantOperation;

    private double montantAttendu;

    private double montantAvance;

    private double montantPenalites;

    private TypeOperation type;

    private Membre membre;

    private Service service;

    private long idOperationInitiale ;



    public Operation() {
    }


    public Operation(OperationFx operationFx) {
        this.service = new Service(operationFx.getServiceFx());
        this.membre = new Membre(operationFx.getMembreFx());
        this.dateCreation = DateUtil.parseToLong(operationFx.dateCreationProperty().getValue());
        this.dateOperation = DateUtil.parseToLong(operationFx.dateOperationProperty().getValue());
        this.dateDerniereModification = DateUtil.parseToLong(operationFx.dateDerniereModificationProperty().getValue());
        this.dateRemboursement = DateUtil.parseToLong(operationFx.dateRemboursementProperty().getValue());
        this.montantOperation = operationFx.getMontantOperation();
        this.montantAttendu = operationFx.getMontantAttendu();
        this.montantAvance = operationFx.getMontantAvance();
        this.montantPenalites = operationFx.getMontantPenalites();
        this.type = TypeOperation.valueOf(operationFx.getType());
        this.id = operationFx.getId();
        this.idOperationInitiale = operationFx.getIdOperationInitiale();
    }

}
