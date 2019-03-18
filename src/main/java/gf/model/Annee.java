package gf.model;


import lombok.Data;

@Data

public class Annee {

	private String nom = "";
	private String dateDebut;
	private String dateFin;
	private int nombreSeance;
	private long id = -1;

	private Annee() {
	}

	public Annee(String nom, String dateDebut, String dateFin) {
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public Annee(long id, String nom, String dateDebut, String dateFin) {
		this.nom = nom;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.id=id;
	}

	
	
	public Annee(AnneeFx anneeFx) {
		nom = anneeFx.getAnnee();
		this.dateDebut = anneeFx.getDateDebut();
		this.dateFin = anneeFx.getDateFin();
		id = anneeFx.getId();
	}
	
	
}
