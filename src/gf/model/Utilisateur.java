package gf.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class Utilisateur {

	private final SimpleStringProperty nomUtilisateur;
	private final SimpleIntegerProperty niveau;
	private final SimpleStringProperty nom;
	private final SimpleStringProperty prenom;
	private final SimpleStringProperty poste;

	/**
	 * Default constructor.
	 */
	public Utilisateur() {
		this(null, 0, null, null, null);
	}

	/**
	 * Constructor with some initial data.
	 * 
	 * @param nomUtilisateur
	 * @param nom
	 */
	public Utilisateur(String nomUtilisateur, int niveau, String nom, String prenom, String poste) {
		this.nomUtilisateur = new SimpleStringProperty(nomUtilisateur);
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.niveau = new SimpleIntegerProperty(niveau);
		this.poste = new SimpleStringProperty(poste);

	}

	public String getNomUtilisateur() {
		return nomUtilisateur.get();
	}

	public StringProperty getNomUtilisateurProperty() {
		return nomUtilisateur;
	}

	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur.set(nomUtilisateur);
	}

	public String getNom() {
		return nom.get();
	}

	public StringProperty getNomProperty() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom.set(nom);
	}

	public String getPrenom() {
		return prenom.get();
	}

	public StringProperty getPrenomProperty() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}

	public int getNiveau() {
		return niveau.get();
	}

	public IntegerProperty getNiveauProperty() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau.set(niveau);
	}

	public String getPoste() {
		return poste.get();
	}

	public StringProperty getPosteProperty() {
		return poste;
	}

	public void setPoste(String poste) {
		this.poste.set(poste);
	}
}