package gf.model;

import java.util.Date;


import gf.util.DateUtil;
import lombok.Data;

@Data
public class Transaction {

    private long id;

    private long dateCreation;

    private long dateDerniereModification;

    private long dateTransaction;

    private long dateRemboursement;

    private double montantTransaction;

    private int duree;

    private double nombre; // Durée * Montant

    private double montantAttendu;

    private double montantAvance;

    private double tauxInteret;

    private double montantInteret;

    private double montantPenalites;

    private double montantBeneficie;

    private double montantRetenu;

    private double montantPlace;

    private Membre avaliseur1;

    private String avaliseur2;

    private TypeTransaction type;

    private Membre membre;

    private Cotisation cotisation;



    public Transaction() {
    }


    public Transaction(TransactionFx transactionFx) {
        this.cotisation = new Cotisation(transactionFx.getCotisationFx());
        this.membre = new Membre(transactionFx.getMembreFx());
        this.dateCreation = DateUtil.parseToLong(transactionFx.dateCreationProperty().getValue());
        this.dateTransaction = DateUtil.parseToLong(transactionFx.dateTransactionProperty().getValue());
        this.dateDerniereModification = DateUtil.parseToLong(transactionFx.dateDerniereModificationProperty().getValue());
        this.dateRemboursement = DateUtil.parseToLong(transactionFx.dateRemboursementProperty().getValue());
        this.montantTransaction = transactionFx.getMontantTransaction();
        this.tauxInteret = transactionFx.getTauxInterets();
        this.montantAttendu = transactionFx.getMontantAttendu();
        this.montantAvance = transactionFx.getMontantAvance();
        this.montantPenalites = transactionFx.getMontantPenalites();
        this.montantPlace = transactionFx.getMontantPlace();
        this.montantRetenu = transactionFx.getMontantRetenu();
        this.montantBeneficie = transactionFx.getMontantBeneficie();
        this.montantInteret = transactionFx.getMontantInterets();
        this.nombre = transactionFx.getNombre();
        this.duree = transactionFx.getDuree();
        this.avaliseur1 = new Membre(transactionFx.getAvaliseur1());
        this.avaliseur2 = transactionFx.getAvaliseur2();
        this.type = TypeTransaction.valueOf(transactionFx.getType());
        this.id = transactionFx.getId();
    }

}
