package gf.model;

import gf.util.DateUtil;
import javafx.beans.property.*;

import java.time.LocalDate;

public class CotisationFx {
	
	private final SimpleStringProperty nomCotisation;
	private final SimpleStringProperty type;
	private final ObjectProperty<LocalDate> dateDebut;
	private final ObjectProperty<LocalDate> dateFin;
	private final ObjectProperty<Integer> nombre;
	private final SimpleStringProperty annee;
	private final SimpleLongProperty id;
	private final SimpleIntegerProperty nombreSeance;



	public CotisationFx(){
		this(null);
	}
	public CotisationFx(Cotisation cotisation){
		this.nomCotisation = new SimpleStringProperty(cotisation.getNom());
		this.type = new SimpleStringProperty(cotisation.getTypeCotisation().name());
		this.dateDebut = new SimpleObjectProperty<LocalDate>(DateUtil.parse(cotisation.getDateDebut()));
		this.dateFin = new SimpleObjectProperty<LocalDate>(DateUtil.parse(cotisation.getDateFin()));
		this.annee= new SimpleStringProperty(cotisation.getAnnee());
		this.nombreSeance = new SimpleIntegerProperty(cotisation.getNombreSeance());
		this.nombre = new SimpleObjectProperty<Integer>(cotisation.getNombreSeance());

		this.id = new SimpleLongProperty(cotisation.getId());
	}
	
	public String getnomCotisation() {
		return nomCotisation.get();
	}
	
	public SimpleStringProperty getnomCotisationProperty() {
		return nomCotisation;
	}
	
	public void setnomCotisation(String nomCotisation) {
		this.nomCotisation.set(nomCotisation);
	}
	
	
	public SimpleStringProperty getNomCotisation() {
		return nomCotisation;
	}
	
	public SimpleStringProperty getTypeProperty() {
		return type;
	}
	
	public String getType() {
		return type.get();
	}
	
	public String getAnnee() {
		return annee.get();
	}
	
	public SimpleStringProperty getAnneeProperty() {
		return annee;
	}
	
	public void setAnnee(String annee) {
		this.annee.set(annee);
	}
	
	public ObjectProperty<LocalDate> getDateDebutProperty() {
		return dateDebut;
	}
	public ObjectProperty<LocalDate> getDateFinProperty() {
		return dateFin;
	}

	public ObjectProperty<Integer> getNombreSeanceProperty() {
		return nombre;
	}
	
	public String getDateDebut() {
		return dateDebut.get().toString();
	}
	
	public String getDateFin() {
		return dateFin.get().toString();
	}
	
	public SimpleLongProperty getIdProperty() {
		return id;
	}
	
	public long getId() {
		return id.get();
	}

	public int getNombreSeance() {
		return nombreSeance.get();
	}

	public SimpleIntegerProperty nombreSeanceProperty() {
		return nombreSeance;
	}

	public void setNombreSeance(int nombreSeance) {
		this.nombreSeance.set(nombreSeance);
	}

	@Override
	public String toString() {
		return getnomCotisation();
	}
	
}
