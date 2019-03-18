package gf.model;


import lombok.Data;

@Data

public class Cotisation {

	private String nom = "";
	private TypeCotisation typeCotisation;
	private String annee="";
	private String dateDebut;
	private String dateFin;
	private int nombreSeance;
	private long id = -1;

	public Cotisation(String nomCotisation, TypeCotisation typeCotisation, String dateDebut, String dateFin, String annee) {
		this.nom = nomCotisation;
		this.typeCotisation = typeCotisation;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.annee=annee;
	}

	public Cotisation(long id, String nomCotisation, TypeCotisation typeCotisation, String dateDebut, String dateFin, String annee) {
		this.nom = nomCotisation;
		this.typeCotisation = typeCotisation;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.annee=annee;
		this.id=id;
	}

	public Cotisation(CotisationFx cotisationFx) {
		this.nom = cotisationFx.getnomCotisation();
		this.typeCotisation = TypeCotisation.valueOf(cotisationFx.getType().toUpperCase());
		this.dateDebut = cotisationFx.getDateDebut();
		this.dateFin = cotisationFx.getDateFin();
		this.annee = cotisationFx.getAnnee();
		this.id = cotisationFx.getId();
	}
	
}
