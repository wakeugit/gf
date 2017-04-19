package gf.model;


import lombok.Data;

@Data

public class FaireUnEmprunt {

	private Cotisation cotisation;
	private Membre membre;
	private String date;
	private int montantEmprunte;
	private int tauxInterets;
	private int montantInterets;
	private String dateRemb;
	private int nombre;
	private long id = -1;

		
	public FaireUnEmprunt(Cotisation cotisation, Membre membre, String dateInscription, int montant, int nombre){
		this.cotisation=cotisation;
		this.membre = membre;
		this.date = dateInscription;
		this.montantEmprunte = montant;
		this.nombre = nombre;
	}
	
	public FaireUnEmprunt(long id, Cotisation cotisation, Membre membre, String dateInscription, int montant, int nombre){
		this.cotisation=cotisation;
		this.membre = membre;
		this.date = dateInscription;
		this.montantEmprunte = montant;
		this.nombre = nombre;
		this.id=id;
	}

	public FaireUnEmprunt(EffectuerFx effectuerFx) {
		this.cotisation = new Cotisation(effectuerFx.getCotisationFx());
		this.membre = new Membre(effectuerFx.getMembreFx());
		this.date = effectuerFx.getDate();
		this.montantEmprunte = effectuerFx.getMontant();
		this.nombre = effectuerFx.getNombre();
		id = effectuerFx.getId();
	}

}
