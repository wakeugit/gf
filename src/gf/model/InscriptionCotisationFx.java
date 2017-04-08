package gf.model;

import gf.util.DateUtil;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class InscriptionCotisationFx {
	
	private CotisationFx cotisationFx;
	private MembreFx membreFx;
	private final ObjectProperty<LocalDate> dateInscription;
	private final SimpleIntegerProperty numeroTirage;
	private final SimpleLongProperty id;
	
	public InscriptionCotisationFx(){
		this(null);
	}
	
	public InscriptionCotisationFx(InscriptionCotisation inscriptionCotisation){
		this.cotisationFx= new CotisationFx(inscriptionCotisation.getCotisation());
		this.membreFx= new MembreFx(inscriptionCotisation.getMembre());
		this.dateInscription = new SimpleObjectProperty<LocalDate>(DateUtil.parse(inscriptionCotisation.getDateInscription()));
		this.numeroTirage = new SimpleIntegerProperty(inscriptionCotisation.getNumeroTirage());
		this.id = new SimpleLongProperty(inscriptionCotisation.getId());
	}
		
	public CotisationFx getCotisationFx() {
		return cotisationFx;
	}
		
	public void setCotisationfx(CotisationFx cotisationFx) {
		this.cotisationFx=cotisationFx;
	}
	
	public MembreFx getMembreFx() {
		return membreFx;
	}
		
	public void setMembrefx(MembreFx membreFx) {
		this.membreFx=membreFx;
	}
	
	public ObjectProperty<LocalDate> getDateInscrptionProperty() {
		return dateInscription;
	}
	
	public String getDateInscrption() {
		return dateInscription.get().toString();
	}
	
	public SimpleIntegerProperty getNumeroTirageProperty() {
		return numeroTirage;
	}
	
	public int getNumeroTirage() {
		return numeroTirage.get();
	}
	
	public SimpleLongProperty getIdProperty() {
		return id;
	}
	
	public long getId() {
		return id.get();
	}
	
	
}
