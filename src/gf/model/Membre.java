package gf.model;

import lombok.Data;

@Data
public class Membre {

	private String nom="";
	private String prenom="";
	private long telephone=0;
	private long cni=0;
	private String adresse="";
	private String photo="";
	private long id = -1;
	private Statut statut = Statut.Actif;

		
	public Membre(String nom, String prenom, long telephone, long cni, String adresse, String photo){
		this.nom=nom;
		this.prenom=prenom;
		this.telephone=telephone;
		this.cni=cni;
		this.adresse=adresse;
		this.photo=photo;
	}

	public Membre(long id, String nom, String prenom, long telephone, long cni, String adresse, String photo){
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
