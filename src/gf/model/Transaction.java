package gf.model;


import lombok.Data;

@Data

public class Transaction {

	private Cotisation cotisation;
	private Membre membre;
	private String date;
	private int montantEmprunte;
	private float tauxInterets;
	private int montantInterets;
	private String dateRemb;
	private Membre avaliseur1;
	private String avaliseur2;
	private long id = -1;

		
	public Transaction(Cotisation cotisation, Membre membre, String date, int montantEmprunte, float tauxInterets, int montantInterets, String dateRemb, Membre avaliseur1,String avaliseur2){
		this.cotisation=cotisation;
		this.membre = membre;
		this.date = date;
		this.montantEmprunte = montantEmprunte;
		this.tauxInterets= tauxInterets;
		this.montantInterets=montantInterets;
		this.dateRemb=dateRemb;
		this.avaliseur1=avaliseur1;
		this.avaliseur2=avaliseur2;
	} 
	
	public Transaction(int id, Cotisation cotisation, Membre membre, String date, int montantEmprunte, float tauxInterets, int montantInterets, String dateRemb, Membre avaliseur1,String avaliseur2){
		this.cotisation=cotisation;
		this.membre = membre;
		this.date = date;
		this.montantEmprunte = montantEmprunte;
		this.tauxInterets= tauxInterets;
		this.montantInterets=montantInterets;
		this.dateRemb=dateRemb;
		this.avaliseur1=avaliseur1;
		this.avaliseur2=avaliseur2;
		this.id=id;
	}

	public Transaction(TransactionFx transactionFx) {
		this.cotisation = new Cotisation(transactionFx.getCotisationFx());
		this.membre = new Membre(transactionFx.getMembreFx());
		this.date = transactionFx.getDate();
		this.montantEmprunte = transactionFx.getMontantEmprunte().get();
		this.tauxInterets= transactionFx.getTauxInterets().get();
		this.montantInterets=transactionFx.getMontantInterets().get();
		this.dateRemb=transactionFx.getDateRemb().get().toString();
		this.avaliseur1=new Membre(transactionFx.getAvaliseur1());
		this.avaliseur2=transactionFx.getAvaliseur2().get();	
		id = transactionFx.getId();
	}

}
