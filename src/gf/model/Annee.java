package gf.model;


import lombok.Data;

@Data

public class Annee {

	private String annee="";
	private String dateDebut;
	private String dateFin;
	private long id = -1;

		
	public Annee(String annee, String dateDebut, String dateFin){
		this.annee=annee;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	
	public Annee(long id,String annee, String dateDebut, String dateFin){
		this.annee=annee;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.id=id;
	}

	
	
	public Annee(AnneeFx anneeFx) {
		annee = anneeFx.getAnnee();
		this.dateDebut = anneeFx.getDateDebut();
		this.dateFin = anneeFx.getDateFin();
		id = anneeFx.getId();
	}
	
	
}
