package gf.model;


import lombok.Data;

@Data

public class Effectuer {

	private Cotisation cotisation;
	private Membre membre;
	private String date;
	private int montant;
	private int nombre;
	private long id = -1;

		
	public Effectuer(Cotisation cotisation, Membre membre, String dateInscription, int montant, int nombre){
		this.cotisation=cotisation;
		this.membre = membre;
		this.date = dateInscription;
		this.montant = montant;
		this.nombre = nombre;
	}
	
	public Effectuer(long id, Cotisation cotisation, Membre membre, String dateInscription, int montant, int nombre){
		this.cotisation=cotisation;
		this.membre = membre;
		this.date = dateInscription;
		this.montant = montant;
		this.nombre = nombre;
		this.id=id;
	}

	public Effectuer(EffectuerFx effectuerFx) {
		this.cotisation = new Cotisation(effectuerFx.getCotisationFx());
		this.membre = new Membre(effectuerFx.getMembreFx());
		this.date = effectuerFx.getDate();
		this.montant = effectuerFx.getMontant();
		this.nombre = effectuerFx.getNombre();
		id = effectuerFx.getId();
	}

}
