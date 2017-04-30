package gf.model;


import lombok.Data;

@Data

public class Transaction {

	private Cotisation cotisation;
	private Membre membre;
	private String dateModif;
	private String date;
	private int montant;
	private float tauxInterets;
	private int montantInterets;
	private int penalites;
	private String dateRemb;
	private Membre avaliseur1;
	private String avaliseur2;
	private TypeTransaction type;
	private long id = -1;

		
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
	}

	public Transaction(TransactionFx transactionFx) {
		this.cotisation = new Cotisation(transactionFx.getCotisationFx());
		this.membre = new Membre(transactionFx.getMembreFx());
		this.dateModif = transactionFx.getDateModif();
		this.date = transactionFx.getDate();
		this.montant = transactionFx.getMontantEmprunte().get();
		this.tauxInterets= transactionFx.getTauxInterets().get();
		this.montantInterets=transactionFx.getMontantInterets().get();
		this.penalites=transactionFx.getPenalites();
		this.dateRemb=transactionFx.getDateRemb().get().toString();
		this.avaliseur1=new Membre(transactionFx.getAvaliseur1());
		this.avaliseur2=transactionFx.getAvaliseur2().get();	
		id = transactionFx.getId();
	}

}
