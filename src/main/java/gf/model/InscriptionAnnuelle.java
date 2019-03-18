package gf.model;


import lombok.Data;

@Data

public class InscriptionAnnuelle {

	private Cotisation cotisation;
	private Membre membre;
	private String dateInscription;
	private int montant;
	private long id = -1;
		
	public InscriptionAnnuelle(Cotisation cotisation, Membre membre, String dateInscription, int montant){
		this.cotisation =cotisation;
		this.membre = membre;
		this.dateInscription = dateInscription;
		this.montant = montant;
	}
	
	public InscriptionAnnuelle(long id, Cotisation cotisation, Membre membre, String dateInscription, int montant){
		this.cotisation = cotisation;
		this.membre = membre;
		this.dateInscription = dateInscription;
		this.montant = montant;
		this.id=id;
	}

	public InscriptionAnnuelle(InscriptionAnnuelleFx inscriptionAnnuelleFx) {
		this.cotisation = new Cotisation(inscriptionAnnuelleFx.getAnneeFx());
		this.membre = new Membre(inscriptionAnnuelleFx.getMembreFx());
		this.dateInscription = inscriptionAnnuelleFx.getDateInscrption();
		this.montant = inscriptionAnnuelleFx.getMontant();
		this.id = inscriptionAnnuelleFx.getId();
	}
	
	
}
