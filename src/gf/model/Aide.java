package gf.model;

import javafx.beans.property.SimpleStringProperty;

public class Aide {
	private final SimpleStringProperty motif;
	
	public Aide(){
		this(null);
	}
	public Aide(String motif){
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
