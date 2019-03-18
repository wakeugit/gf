package gf.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class AideFx {
	private final SimpleStringProperty motif;
	private final SimpleLongProperty id;
	
	public AideFx(){
		this(null);
	}
	
	public AideFx(Aide aide){
		this.motif = new SimpleStringProperty(aide.getMotif());
		this.id = new SimpleLongProperty(aide.getId());
	}
	
	
	public String getMotif() {
		return motif.get();
	}
	
	public SimpleStringProperty getMotifProperty() {
		return motif;
	}
	
	public void setMotif(String motif) {
		this.motif.set(motif);
	}

	public SimpleLongProperty getIdProperty() {
		return id;
	}
	
	public long getId() {
		return id.get();
	}
	
	@Override
	public String toString() {
		return getMotif();
	}
	
}
