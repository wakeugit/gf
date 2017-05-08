package gf.model;

import java.util.Date;


import lombok.Data;

@Data
public class Transaction {

	private long id;
	
    private Date dateCreation = new Date();
	
    private Date dateDerniereModification = new Date();

	private Date dateOperation ;

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

}
