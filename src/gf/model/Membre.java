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
		
	public Membre(String nom, String prenom, int telephone, int cni, String adresse, String photo){
		this.nom=nom;
		this.prenom=prenom;
		this.telephone=telephone;
		this.cni=cni;
		this.adresse=adresse;
		this.photo=photo;
	}

}
