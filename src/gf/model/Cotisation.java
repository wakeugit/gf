package gf.model;

import gf.util.DateUtil;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cotisation {
	
	private final SimpleStringProperty nomCotisation;
	private final SimpleStringProperty type;
	private final ObjectProperty<LocalDate> dateDebut;
	private final ObjectProperty<LocalDate> dateFin;
	private final SimpleStringProperty annee;
	
	
	public Cotisation(){
		this(null, null, null, null, null);
	}
	public Cotisation(String nomCotisation, String type, String dateDebut, String dateFin, String annee){
		this.nomCotisation= new SimpleStringProperty(nomCotisation);
		this.type= new SimpleStringProperty(type);
		this.dateDebut = new SimpleObjectProperty<LocalDate>(DateUtil.parse(dateDebut));
		this.dateFin = new SimpleObjectProperty<LocalDate>(DateUtil.parse(dateFin));
		this.annee= new SimpleStringProperty(annee);
	}
	
	public String getnomCotisation() {
		return nomCotisation.get();
	}
	
	public SimpleStringProperty getnomCotisationProperty() {
		return nomCotisation;
	}
	
	public void setnomCotisation(String nomCotisation) {
		this.nomCotisation.set(nomCotisation);
	}
	
	public ObjectProperty<LocalDate> getDateDebut() {
		return dateDebut;
	}
	
	public ObjectProperty<LocalDate> getDateFin() {
		return dateFin;
	}
	
	public SimpleStringProperty getNomCotisation() {
		return nomCotisation;
	}
	
	public SimpleStringProperty getType() {
		return type;
	}
	
	public SimpleStringProperty getAnnee() {
		return annee;
	}
	
	
}
