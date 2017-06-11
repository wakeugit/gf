package gf.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import lombok.Data;

@Data

public class Service {

	private String motif="";
	private long id = -1;
	private TypeService typeService;
	LocalDate localDate = LocalDate.now();
	Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
	private long dateCreation=Date.from(instant).getTime();
	

		
	public Service(String motif,  TypeService typeService){
		this.motif=motif;
		this.typeService=typeService;
	}

	public Service(long id, String motif, TypeService typeService, long dateCreation){
		this.id=id;
		this.motif=motif;
		this.typeService=typeService;
		this.dateCreation=dateCreation;
	}
	
	public Service(ServiceFx serviceFx) {
		motif = serviceFx.getMotif();
		id = serviceFx.getId();
		typeService=TypeService.valueOf(serviceFx.getTypeService());
		dateCreation=serviceFx.getDateCreation();
	}
	
	

	
}
