package gf.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 *
 * @author Marco Jakob
 */
public class UtilisateurFx {

	private final SimpleStringProperty nomUtilisateur;
	private final SimpleIntegerProperty niveau;
	private final SimpleStringProperty nom;
	private final SimpleStringProperty prenom;
	private final SimpleStringProperty poste;
	private final SimpleLongProperty id;

	/**
	 * Default constructor.
	 */
	public UtilisateurFx() {
		this(null);
	}

	/**
	 * Constructor with some initial data.
	 * 
	 * @param nomUtilisateur
	 * @param nom
	 */
	public UtilisateurFx(Utilisateur utilisateur) {
		this.nomUtilisateur = new SimpleStringProperty(utilisateur.getNomUtilisateur());
		this.nom = new SimpleStringProperty(utilisateur.getNom());
		this.prenom = new SimpleStringProperty(utilisateur.getPrenom());
		this.niveau = new SimpleIntegerProperty(utilisateur.getNiveau());
		this.poste = new SimpleStringProperty(utilisateur.getPoste());
		this.id = new SimpleLongProperty(utilisateur.getId());

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
	
	public SimpleLongProperty getIdProperty() {
		return id;
	}
	
	public long getId() {
		return id.get();
	}
	
}