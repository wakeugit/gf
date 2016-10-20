package gf.model;

import lombok.Data;

@Data

public class Aide {

	private String motif="";
	private long id = -1;

		
	public Aide(String motif){
		this.motif=motif;
	}

	public Aide(long id, String motif){
		this.id=id;
		this.motif=motif;
	}
	
	public Aide(AideFx aideFx) {
		motif = aideFx.getMotif();
		id = aideFx.getId();
	}
	
	

	
}
