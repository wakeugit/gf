package gf.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class ServiceFx {
	private final SimpleStringProperty motif;
	private final SimpleStringProperty typeService;
	private final SimpleLongProperty id;
	private final SimpleLongProperty dateCreation;
	
	
	public ServiceFx(){
		this(null);
	}
	
	public ServiceFx(Service service){
		this.motif = new SimpleStringProperty(service.getMotif());
		this.id = new SimpleLongProperty(service.getId());
		this.typeService=new SimpleStringProperty(service.getTypeService().name());
		this.dateCreation=new SimpleLongProperty(service.getDateCreation());
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
	
	public SimpleLongProperty getDateCreationProperty() {
		return dateCreation;
	}
	
	public long getDateCreation() {
		return dateCreation.get();
	}
	
	public String getTypeService(){
		return typeService.get();
	}
	
	public void setTypeService(String type){
		this.typeService.set(type);
	}
	
	public SimpleStringProperty typeServiceProperty(){
		return typeService;
	}
	
	@Override
	public String toString() {
		return getMotif();
	}
	
	
}
