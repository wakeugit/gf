package gf.model;

import lombok.Data;

@Data

public class Sanction {

	private String motif="";
	private long id = -1;

		
	public Sanction(String motif){
		this.motif=motif;
	}

	public Sanction(long id, String motif){
		this.id=id;
		this.motif=motif;
	}
	
	public Sanction(SanctionFx sanctionFx) {
		motif = sanctionFx.getMotif();
		id = sanctionFx.getId();
	}
	
	

	
}
