package gf.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class SanctionFx {
	private final SimpleStringProperty motif;
	private final SimpleLongProperty id;
	
	
	public SanctionFx(){
		this(null);
	}
	
	public SanctionFx(Sanction sanction){
		this.motif = new SimpleStringProperty(sanction.getMotif());
		this.id = new SimpleLongProperty(sanction.getId());
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
