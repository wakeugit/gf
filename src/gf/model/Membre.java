package gf.model;

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
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public int getCni() {
		return cni;
	}
	public void setCni(int cni) {
		this.cni = cni;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
}
