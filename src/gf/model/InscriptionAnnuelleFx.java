package gf.model;

import gf.util.DateUtil;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class InscriptionAnnuelleFx {
	
	private CotisationFx anneeFx;
	private MembreFx membreFx;
	private final ObjectProperty<LocalDate> dateInscription;
	private final SimpleIntegerProperty montant;
	private final SimpleLongProperty id;
	
	public InscriptionAnnuelleFx(){
		this(null);
	}
	
	public InscriptionAnnuelleFx(InscriptionAnnuelle inscriptionAnnuelle){
		if (inscriptionAnnuelle != null) {
			this.anneeFx = new CotisationFx(inscriptionAnnuelle.getCotisation());
			this.membreFx = new MembreFx(inscriptionAnnuelle.getMembre());
			this.dateInscription = new SimpleObjectProperty<LocalDate>(DateUtil.parseTimestamp(inscriptionAnnuelle.getDateInscription()));
			this.montant = new SimpleIntegerProperty(inscriptionAnnuelle.getMontant());
			this.id = new SimpleLongProperty(inscriptionAnnuelle.getId());
		} else {
			this.id = null;
			this.montant = null;
			this.dateInscription = null;
		}

	}
	
		
	public CotisationFx getAnneeFx() {
		return anneeFx;
	}
		
	public void setAnneefx(CotisationFx anneeFx) {
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
