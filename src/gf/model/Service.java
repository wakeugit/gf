package gf.model;

import lombok.Data;

@Data

public class Service {

	private String motif="";
	private long id = -1;
	private TypeService type;
	private long dateCreation=-1;
	private long dateDerniereModification=-1;


		
	public Service(String motif,  TypeService type){
		this.motif=motif;
		this.type = type;
	}

	public Service(long id, String motif, TypeService type, long dateCreation){
		this.id=id;
		this.motif=motif;
		this.type = type;
		this.dateCreation=dateCreation;
	}
	
	public Service(ServiceFx serviceFx) {
		motif = serviceFx.getMotif();
		id = serviceFx.getId();
		type =TypeService.valueOf(serviceFx.getTypeService());
		dateCreation=serviceFx.getDateCreation();
		dateDerniereModification=serviceFx.getDateDerniereModification();
	}
	
	

	
}
