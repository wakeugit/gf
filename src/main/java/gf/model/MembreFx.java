package gf.model;

import java.util.Comparator;

import javafx.beans.property.*;

import java.util.Comparator;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class MembreFx{

	private final SimpleStringProperty photo;
	private final SimpleStringProperty nom;
	private final SimpleStringProperty prenom;
	private final SimpleLongProperty telephone;
	private final SimpleStringProperty adresse;
	private final SimpleLongProperty cni;
	private final SimpleLongProperty id;

	/**
	 * Default constructor.
	 */
	public MembreFx() {
		this(null);
	}

	/**
	 * Constructor with some initial data.
	 * 
	 */
	public MembreFx(Membre membre) {

		if (membre != null) {
			this.photo = new SimpleStringProperty(membre.getPhoto());
			this.nom = new SimpleStringProperty(membre.getNom());
			this.prenom = new SimpleStringProperty(membre.getPrenom());
			this.telephone = new SimpleLongProperty(membre.getTelephone());
			this.adresse = new SimpleStringProperty(membre.getAdresse());
			this.cni = new SimpleLongProperty(membre.getCni());
			this.id = new SimpleLongProperty(membre.getId());
		} else {
			this.photo = new SimpleStringProperty("");
			this.nom = new SimpleStringProperty("");
			this.prenom = new SimpleStringProperty("");
			this.telephone = new SimpleLongProperty(-1);
			this.adresse = new SimpleStringProperty("");
			this.cni = new SimpleLongProperty(-1);
			this.id = new SimpleLongProperty(-1);
		}


	}

	public String getPhoto() {
		return photo.get();
	}

	public StringProperty photoProperty() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo.set(photo);
	}

	public String getNom() {
		return nom.get();
	}

	public StringProperty nomProperty() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom.set(nom);
	}

	public String getPrenom() {
		return prenom.get();
	}

	public StringProperty prenomProperty() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}

	public long getTelephone() {
		return telephone.get();
	}

	public SimpleLongProperty telephoneProperty() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone.set(telephone);
	}

	public String getAdresse() {
		return adresse.get();
	}

	public StringProperty adresseProperty() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse.set(adresse);
	}

	public long getCni() {
		return cni.get();
	}

	public SimpleLongProperty cniProperty() {
		return cni;
	}

	public void setCni(int cni) {
		this.cni.set(cni);
	}

	public LongProperty idProperty() {
		return id;
	}

	public void setId(long id) {
		this.id.set(id);
	}

	public long getId() {
		return id.get();
	}

	@Override
	public String toString() {
		return getNom();
	}

}