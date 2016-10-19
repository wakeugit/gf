package gf.model;


import lombok.Data;

@Data

public class Cotisation {
	
	private String nomCotisation="";
	private String type="";
	private String annee="";
	private String dateDebut;
	private String dateFin;
	private long id = -1;

		
	public Cotisation(String nomCotisation, String type, String dateDebut, String dateFin, String annee){
		this.nomCotisation = nomCotisation;
		this.type = type;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.annee=annee;
	}
	
	public Cotisation(long id, String nomCotisation, String type, String dateDebut, String dateFin, String annee){
		this.nomCotisation = nomCotisation;
		this.type = type;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.annee=annee;
		this.id=id;
	}

	
	
	public Cotisation(CotisationFx cotisationFx) {
		this.nomCotisation = cotisationFx.getnomCotisation();
		this.type = cotisationFx.getType();
		this.dateDebut = cotisationFx.getDateDebut();
		this.dateFin = cotisationFx.getDateFin();
		this.annee = cotisationFx.getAnnee();
		this.id = cotisationFx.getId();
	}
	
	
}
