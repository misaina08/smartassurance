package modeles.guichet;

import java.util.Date;

import modeles.BaseModele;

/**
 * Created by misa on 9/5/2017.
 */

public class AttenteView extends BaseModele {
    private Integer id;
    private Integer numero;
    private Date daty;
    private String heure;
    private String token;
    private String nom;
    private Integer idAgence;
    private Integer idGuichet;
    private Integer duree;
    private Integer termine;
    private Integer noGuichet;
    private String responsable;
    private String agence;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(Integer idAgence) {
        this.idAgence = idAgence;
    }

    public Integer getIdGuichet() {
        return idGuichet;
    }

    public void setIdGuichet(Integer idGuichet) {
        this.idGuichet = idGuichet;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Integer getTermine() {
        return termine;
    }

    public void setTermine(Integer termine) {
        this.termine = termine;
    }

    public Integer getNoGuichet() {
        return noGuichet;
    }

    public void setNoGuichet(Integer noGuichet) {
        this.noGuichet = noGuichet;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getAgence() {
        return agence;
    }

    public void setAgence(String agence) {
        this.agence = agence;
    }
}
