package gf.model;


import lombok.Data;

@Data

public class Cotisation {

	private String nom = "";
	private Type type;
	private String annee="";
	private String dateDebut;
	private String dateFin;
	private long id = -1;


	public Cotisation(String nomCotisation, Type type, String dateDebut, String dateFin, String annee) {
		this.nom = nomCotisation;
		this.type = type;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.annee=annee;
	}

	public Cotisation(long id, String nomCotisation, Type type, String dateDebut, String dateFin, String annee) {
		this.nom = nomCotisation;
		this.type = type;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.annee=annee;
		this.id=id;
	}

	
	
	public Cotisation(CotisationFx cotisationFx) {
		this.nom = cotisationFx.getnomCotisation();
		this.type = Type.valueOf(cotisationFx.getType().toUpperCase());
		this.dateDebut = cotisationFx.getDateDebut();
		this.dateFin = cotisationFx.getDateFin();
		this.annee = cotisationFx.getAnnee();
		this.id = cotisationFx.getId();
	}
	
	
}
