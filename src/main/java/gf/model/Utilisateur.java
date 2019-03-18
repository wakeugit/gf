package gf.model;

import lombok.Data;

@Data
public class Utilisateur {

    private long id = -1;
    private String pseudo = "";
    private int niveau = 0;
    private String nom = "";
    private String prenom = "";
    private String poste = "";
    private String cni;
    private String photo;
    private String telephone;
    private String adresse;
    private String password;
    private String encodedPassword;


    public Utilisateur() {

    }


    public Utilisateur(String pseudo, int niveau, String nom, String prenom, String poste) {
        this.pseudo = pseudo;
        this.niveau = niveau;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
    }

    public Utilisateur(long id, String pseudo, int niveau, String nom, String prenom, String poste) {
        this.pseudo = pseudo;
        this.niveau = niveau;
        this.nom = nom;
        this.prenom = prenom;
        this.poste = poste;
        this.id = id;
    }

    public Utilisateur(UtilisateurFx utilisateurFx) {
        nom = utilisateurFx.getNom();
        prenom = utilisateurFx.getPrenom();
        niveau = utilisateurFx.getNiveau();
        pseudo = utilisateurFx.getNomUtilisateur();
        poste = utilisateurFx.getPoste();
        id = utilisateurFx.getId();
    }


}
