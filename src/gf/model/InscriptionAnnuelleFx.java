package gf.model;

import gf.util.DateUtil;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class InscriptionAnnuelleFx {
	
	private AnneeFx anneeFx;
	private MembreFx membreFx;
	private final ObjectProperty<LocalDate> dateInscription;
	private final SimpleIntegerProperty montant;
	private final SimpleLongProperty id;
	
	public InscriptionAnnuelleFx(){
		this(null);
	}
	
	public InscriptionAnnuelleFx(InscriptionAnnuelle inscriptionAnnuelle){
		this.anneeFx= new AnneeFx(inscriptionAnnuelle.getAnnee());
		this.membreFx= new MembreFx(inscriptionAnnuelle.getMembre());
		this.dateInscription = new SimpleObjectProperty<LocalDate>(DateUtil.parse(inscriptionAnnuelle.getDateInscription()));
		this.montant = new SimpleIntegerProperty(inscriptionAnnuelle.getMontant());
		this.id = new SimpleLongProperty(inscriptionAnnuelle.getId());
	}
	
		
	public AnneeFx getAnneeFx() {
		return anneeFx;
	}
		
	public void setAnneefx(AnneeFx anneeFx) {
		this.anneeFx=anneeFx;
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
	
	public SimpleIntegerProperty getMontantProperty() {
		return montant;
	}
	
	public int getMontant() {
		return montant.get();
	}
	
	
	public SimpleLongProperty getIdProperty() {
		return id;
	}
	
	public long getId() {
		return id.get();
	}
	
}