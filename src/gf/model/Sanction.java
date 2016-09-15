package gf.model;

import javafx.beans.property.SimpleStringProperty;

public class Sanction {
	private final SimpleStringProperty motif;
	
	public Sanction(){
		this(null);
	}
	public Sanction(String motif){
		this.motif= new SimpleStringProperty(motif);
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
	
}
