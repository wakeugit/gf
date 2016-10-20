package gf.model;


import lombok.Data;

@Data

public class InscriptionCotisation {

	private Cotisation cotisation;
	private Membre membre;
	private String dateInscription;
	private int numeroTirage;
	private long id = -1;

		
	public InscriptionCotisation(Cotisation cotisation, Membre membre, String dateInscription, int numeroTirage){
		this.cotisation=cotisation;
		this.membre = membre;
		this.dateInscription = dateInscription;
		this.numeroTirage = numeroTirage;
	}
	
	public InscriptionCotisation(long id, Cotisation cotisation, Membre membre, String dateInscription, int numeroTirage){
		this.cotisation=cotisation;
		this.membre = membre;
		this.dateInscription = dateInscription;
		this.numeroTirage = numeroTirage;
		this.id=id;
	}

	public InscriptionCotisation(InscriptionCotisationFx inscriptionCotisationFx) {
		this.cotisation = new Cotisation(inscriptionCotisationFx.getCotisationFx());
		this.membre = new Membre(inscriptionCotisationFx.getMembreFx());
		this.dateInscription = inscriptionCotisationFx.getDateInscrption();
		this.numeroTirage = inscriptionCotisationFx.getNumeroTirage();
		id = inscriptionCotisationFx.getId();
	}
	
	
}
