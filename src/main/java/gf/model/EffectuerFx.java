package gf.model;

import gf.util.DateUtil;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class EffectuerFx {
	
	private CotisationFx cotisationFx;
	private MembreFx membreFx;
	private final ObjectProperty<LocalDate> date;
	private final SimpleIntegerProperty montant;
	private final SimpleIntegerProperty nombre;
	private final SimpleLongProperty id;
	
	public EffectuerFx(){
		this(null);
	}
	
	public EffectuerFx(Effectuer effectuer){
		this.cotisationFx= new CotisationFx(effectuer.getCotisation());
		this.membreFx= new MembreFx(effectuer.getMembre());
		this.date = new SimpleObjectProperty<LocalDate>(DateUtil.parse(effectuer.getDate()));
		this.montant = new SimpleIntegerProperty(effectuer.getMontant());
		this.nombre = new SimpleIntegerProperty(effectuer.getNombre());
		this.id = new SimpleLongProperty(effectuer.getId());
	}
			
	public CotisationFx getCotisationFx() {
		return cotisationFx;
	}
	
	public void setCotisationFx(CotisationFx cotisationFx) {
		this.cotisationFx=cotisationFx;
	}
	
	public MembreFx getMembreFx() {
		return membreFx;
	}
		
	public void setMembrefx(MembreFx membreFx) {
		this.membreFx=membreFx;
	}
	
	public ObjectProperty<LocalDate> getDateProperty() {
		return date;
	}
	
	
	public String getDate() {
		return date.get().toString();
	}
	
	public SimpleIntegerProperty getMontantProperty() {
		return montant;
	}
	
	public int getMontant() {
		return montant.get();
	}
	
	public SimpleIntegerProperty getNombreProperty() {
		return nombre;
	}
	
	public int getNombre() {
		return nombre.get();
	}

	public SimpleLongProperty getIdProperty() {
		return id;
	}
	
	public long getId() {
		return id.get();
	}
	
}
