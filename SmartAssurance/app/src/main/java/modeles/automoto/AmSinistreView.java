package modeles.automoto;

import java.util.Date;

import modeles.BaseModele;

/**
 * Created by LENOVO on 8/10/2017.
 */

public class AmSinistreView extends BaseModele {
    private Integer id;
    private Integer souscriptionProduitId;
    private String details;
    private Date daty;
    private String heure;
    private String lieu;
    private Double latitude;
    private Double longitude;
    private Integer termine;
    private Integer sinCategorieId;
    private String categorie;

    private Integer demande;

    private String conducteur;

    private String nomConducteur;

    private String prenomConducteur;

    private String adresseConducteur;
    private Date dnConducteur;
    private String nopermis;
    private String noduplicata;
    private String catPermis;
    private String catValidees;
    private Date debValidite;
    private Date finValidite;
    private Date dateDelivrance;
    private String lieuDelivrance;
    private String capObtenu;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSouscriptionProduitId() {
        return souscriptionProduitId;
    }

    public void setSouscriptionProduitId(Integer souscriptionProduitId) {
        this.souscriptionProduitId = souscriptionProduitId;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getTermine() {
        return termine;
    }

    public void setTermine(Integer termine) {
        this.termine = termine;
    }

    public Integer getSinCategorieId() {
        return sinCategorieId;
    }

    public void setSinCategorieId(Integer sinCategorieId) {
        this.sinCategorieId = sinCategorieId;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Integer getDemande() {
        return demande;
    }

    public void setDemande(Integer demande) {
        this.demande = demande;
    }

    public String getConducteur() {
        return conducteur;
    }

    public void setConducteur(String conducteur) {
        this.conducteur = conducteur;
    }

    public String getNomConducteur() {
        return nomConducteur;
    }

    public void setNomConducteur(String nomConducteur) {
        this.nomConducteur = nomConducteur;
    }

    public String getPrenomConducteur() {
        return prenomConducteur;
    }

    public void setPrenomConducteur(String prenomConducteur) {
        this.prenomConducteur = prenomConducteur;
    }

    public String getAdresseConducteur() {
        return adresseConducteur;
    }

    public void setAdresseConducteur(String adresseConducteur) {
        this.adresseConducteur = adresseConducteur;
    }

    public Date getDnConducteur() {
        return dnConducteur;
    }

    public void setDnConducteur(Date dnConducteur) {
        this.dnConducteur = dnConducteur;
    }

    public String getNopermis() {
        return nopermis;
    }

    public void setNopermis(String nopermis) {
        this.nopermis = nopermis;
    }

    public String getNoduplicata() {
        return noduplicata;
    }

    public void setNoduplicata(String noduplicata) {
        this.noduplicata = noduplicata;
    }

    public String getCatPermis() {
        return catPermis;
    }

    public void setCatPermis(String catPermis) {
        this.catPermis = catPermis;
    }

    public String getCatValidees() {
        return catValidees;
    }

    public void setCatValidees(String catValidees) {
        this.catValidees = catValidees;
    }

    public Date getDebValidite() {
        return debValidite;
    }

    public void setDebValidite(Date debValidite) {
        this.debValidite = debValidite;
    }

    public Date getFinValidite() {
        return finValidite;
    }

    public void setFinValidite(Date finValidite) {
        this.finValidite = finValidite;
    }

    public Date getDateDelivrance() {
        return dateDelivrance;
    }

    public void setDateDelivrance(Date dateDelivrance) {
        this.dateDelivrance = dateDelivrance;
    }

    public String getLieuDelivrance() {
        return lieuDelivrance;
    }

    public void setLieuDelivrance(String lieuDelivrance) {
        this.lieuDelivrance = lieuDelivrance;
    }

    public String getCapObtenu() {
        return capObtenu;
    }

    public void setCapObtenu(String capObtenu) {
        this.capObtenu = capObtenu;
    }
}
