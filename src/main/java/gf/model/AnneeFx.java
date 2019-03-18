package gf.model;

import gf.util.DateUtil;
import javafx.beans.property.*;

import java.time.LocalDate;

public class AnneeFx {

    private final SimpleStringProperty annee;
    private final ObjectProperty<LocalDate> dateDebut;
    private final ObjectProperty<LocalDate> dateFin;
    private final SimpleLongProperty id;
    private final SimpleIntegerProperty nombreSeance;

    public AnneeFx() {
        this(null);
    }

    public AnneeFx(Annee annee) {
        this.annee = new SimpleStringProperty(annee.getNom());
        this.dateDebut = new SimpleObjectProperty<LocalDate>(DateUtil.parse(annee.getDateDebut()));
        this.dateFin = new SimpleObjectProperty<LocalDate>(DateUtil.parse(annee.getDateFin()));
        this.id = new SimpleLongProperty(annee.getId());
        this.nombreSeance = new SimpleIntegerProperty(annee.getNombreSeance());
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
        return getAnnee();
    }

}
