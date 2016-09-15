package gf.model;

import gf.util.DateUtil;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Annee {
	
	private final SimpleStringProperty annee;
	private final ObjectProperty<LocalDate> dateDebut;
	private final ObjectProperty<LocalDate> dateFin;
	
	
	public Annee(){
		this(null, null, null);
	}
	public Annee(String annee, String dateDebut, String dateFin){
		this.annee= new SimpleStringProperty(annee);
		this.dateDebut = new SimpleObjectProperty<LocalDate>(DateUtil.parse(dateDebut));
		this.dateFin = new SimpleObjectProperty<LocalDate>(DateUtil.parse(dateFin));
	}
	
	public String getAnnee() {
		return annee.get();
	}
	
	public SimpleStringProperty getAnneeProperty() {
		return annee;
	}
	
	public void setAnnee(String annee) {
		this.annee.set(annee);
	}
	
	public ObjectProperty<LocalDate> getDateDebut() {
		return dateDebut;
	}
	public ObjectProperty<LocalDate> getDateFin() {
		return dateFin;
	}
}
