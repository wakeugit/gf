package gf.model;

import lombok.Data;

@Data
public class Membre {

	private String nom="";
	private String prenom="";
	private int telephone=0;
	private int cni=0;
	private String adresse="";
	private String photo="";
	private long id = -1;

		
	public Membre(String nom, String prenom, int telephone, int cni, String adresse, String photo){
		this.nom=nom;
		this.prenom=prenom;
		this.telephone=telephone;
		this.cni=cni;
		this.adresse=adresse;
		this.photo=photo;
	}

	public Membre(long id, String nom, String prenom, int telephone, int cni, String adresse, String photo){
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;
		this.telephone=telephone;
		this.cni=cni;
		this.adresse=adresse;
		this.photo=photo;
	}
	
	public Membre(MembreFx membreFx) {
		nom = (membreFx.getNom());
		prenom = (membreFx.getPrenom());
		telephone = membreFx.getTelephone();
		cni = membreFx.getCni();
		adresse = ("" + membreFx.getAdresse());
		photo = membreFx.getPhoto();
		id = membreFx.getId();
	}

	
}
