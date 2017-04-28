package gf.model;

import gf.util.DateUtil;

import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class TransactionFx {
	
	private CotisationFx cotisationFx;
	private MembreFx membreFx;
	private final ObjectProperty<LocalDate> date;
	private final SimpleIntegerProperty montantEmprunte;
	private final SimpleFloatProperty tauxInterets;
	private final SimpleIntegerProperty montantInterets;
	private final ObjectProperty<LocalDate> dateRemb;
	private MembreFx avaliseur1;
	private SimpleStringProperty avaliseur2;
	private final SimpleLongProperty id;
	
	public TransactionFx(){
		this(null);
	}
	
	public TransactionFx(Transaction transaction){
		this.cotisationFx= new CotisationFx(transaction.getCotisation());
		this.membreFx= new MembreFx(transaction.getMembre());
		this.date = new SimpleObjectProperty<LocalDate>(DateUtil.parse(transaction.getDate()));
		this.montantEmprunte = new SimpleIntegerProperty(transaction.getMontantEmprunte());
		this.tauxInterets=new SimpleFloatProperty(transaction.getTauxInterets());
		this.montantInterets=new SimpleIntegerProperty(transaction.getMontantInterets());
		this.dateRemb=new SimpleObjectProperty<LocalDate>(DateUtil.parse(transaction.getDateRemb()));
		this.avaliseur1=new MembreFx(transaction.getAvaliseur1());
		this.avaliseur2=new SimpleStringProperty(transaction.getAvaliseur2());
		this.id = new SimpleLongProperty(transaction.getId());
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
	
	public MembreFx getAvaliseur1() {
		return avaliseur1;
	}

	public void setAvaliseur1(MembreFx avaliseur1) {
		this.avaliseur1 = avaliseur1;
	}

	public SimpleStringProperty getAvaliseur2() {
		return avaliseur2;
	}

	public void setAvaliseur2(SimpleStringProperty avaliseur2) {
		this.avaliseur2 = avaliseur2;
	}

	public SimpleIntegerProperty getMontantEmprunte() {
		return montantEmprunte;
	}

	public SimpleFloatProperty getTauxInterets() {
		return tauxInterets;
	}

	public SimpleIntegerProperty getMontantInterets() {
		return montantInterets;
	}

	public ObjectProperty<LocalDate> getDateRemb() {
		return dateRemb;
	}

	public void setMembreFx(MembreFx membreFx) {
		this.membreFx = membreFx;
	}

	public ObjectProperty<LocalDate> getDateProperty() {
		return date;
	}
	
	
	public String getDate() {
		return date.get().toString();
	}

	public SimpleLongProperty getIdProperty() {
		return id;
	}
	
	public long getId() {
		return id.get();
	}
	
}
