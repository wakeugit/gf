package gf.model;

import lombok.Data;

@Data
public class Utilisateur {

	private String nomUtilisateur="";
	private int niveau=0;
	private String nom="";
	private String prenom="";
	private String poste="";
	private long id = -1;

		
	public Utilisateur(String nomUtilisateur,  int niveau, String nom, String prenom, String poste){
		this.nomUtilisateur=nomUtilisateur;
		this.niveau=niveau;
		this.nom=nom;
		this.prenom=prenom;
		this.poste=poste;
	}

	public Utilisateur(long id, String nomUtilisateur, int niveau, String nom, String prenom, String poste){
		this.nomUtilisateur=nomUtilisateur;
		this.niveau=niveau;
		this.nom=nom;
		this.prenom=prenom;
		this.poste=poste;
		this.id=id;
	}
	
	public Utilisateur(UtilisateurFx utilisateurFx) {
		nom = utilisateurFx.getNom();
		prenom = utilisateurFx.getPrenom();
		niveau = utilisateurFx.getNiveau();
		nomUtilisateur = utilisateurFx.getNomUtilisateur();
		poste = utilisateurFx.getPoste();
		id = utilisateurFx.getId();
	}

	
}
