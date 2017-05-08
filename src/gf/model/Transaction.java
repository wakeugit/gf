package gf.model;

import java.util.Date;


import gf.util.DateUtil;
import lombok.Data;

@Data
public class Transaction {

    private long id;

    private Date dateCreation = new Date();

    private Date dateDerniereModification = new Date();

    private Date dateOperation;

    private Date dateRemboursement;

    private double montantOperation;

    private double montantAttendu;

    private double montantAvance;

    private double tauxInteret;

    private double montantPenalites;

    private Membre avaliseur1;

    private Membre avaliseur2;

    private TypeTransaction type;

    private Membre membre;

    private Cotisation cotisation;

	/*
    public Transaction(Cotisation cotisation, Membre membre, String dateModif, String date, int montantEmprunte, float tauxInterets, int montantInterets, int penalites, String dateRemb, Membre avaliseur1,String avaliseur2, TypeTransaction type){
		this.cotisation=cotisation;
		this.membre = membre;
		this.dateModif=dateModif;
		this.date = date;
		this.montant = montantEmprunte;
		this.tauxInterets= tauxInterets;
		this.montantInterets=montantInterets;
		this.penalites=penalites;
		this.dateRemb=dateRemb;
		this.avaliseur1=avaliseur1;
		this.avaliseur2=avaliseur2;
		this.type=type;
	}

	public Transaction(int id, Cotisation cotisation, Membre membre, String dateModif, String date, int montantEmprunte, int penalites, float tauxInterets, int montantInterets, String dateRemb, Membre avaliseur1,String avaliseur2, TypeTransaction type){
		this.cotisation=cotisation;
		this.membre = membre;
		this.dateModif=dateModif;
		this.date = date;
		this.montant = montantEmprunte;
		this.tauxInterets= tauxInterets;
		this.montantInterets=montantInterets;
		this.dateRemb=dateRemb;
		this.avaliseur1=avaliseur1;
		this.avaliseur2=avaliseur2;
		this.type=type;
		this.id=id;
	}*/

    public Transaction(TransactionFx transactionFx) {
        this.cotisation = new Cotisation(transactionFx.getCotisationFx());
        this.membre = new Membre(transactionFx.getMembreFx());
        this.dateCreation = DateUtil.parseToDate(transactionFx.getDateCreation());
        this.dateOperation = DateUtil.parseToDate(transactionFx.getDateOperation());
        this.dateDerniereModification = DateUtil.parseToDate(transactionFx.getDateDerniereModification());
        this.dateRemboursement = DateUtil.parseToDate(transactionFx.getDateRemboursement());
        this.montantOperation = transactionFx.getMontantEmprunte().get();
        this.tauxInteret = transactionFx.getTauxInterets().get();
        this.montantAttendu = transactionFx.getMontantInterets().get();
        this.montantAvance = transactionFx.getMontantAvance();
        this.montantPenalites = transactionFx.getMontantPenalites();
        this.avaliseur1 = new Membre(transactionFx.getAvaliseur1());
        this.avaliseur2 = new Membre(transactionFx.getAvaliseur2());
        this.type = TypeTransaction.valueOf(transactionFx.getType().getName());
        id = transactionFx.getId();
    }

}
