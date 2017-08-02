package modeles.souscription;

import java.util.Date;

import modeles.BaseModele;

/**
 * Created by LENOVO on 7/28/2017.
 */

public class Souscription extends BaseModele {
    private Integer id;
    private Integer idClient;
    private Integer idClientSouscripteur;
    private Date dateSouscription;
    private Double primenet;
    private Double primetotal;
    private Double reduction;
    private String noPolice;
    private Integer valide;
    private Integer idProduit;
    private Integer duree;
    private String nomClient;
    private String prenomClient;
    private String nomSouscripteur;
    private String prenomSouscripteur;
    private String nomProduit;

    public Souscription() {
    }

    public Integer getIdClientSouscripteur() {
        return idClientSouscripteur;
    }

    public void setIdClientSouscripteur(Integer idClientSouscripteur) {
        this.idClientSouscripteur = idClientSouscripteur;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public String getNomSouscripteur() {
        return nomSouscripteur;
    }

    public void setNomSouscripteur(String nomSouscripteur) {
        this.nomSouscripteur = nomSouscripteur;
    }

    public String getPrenomSouscripteur() {
        return prenomSouscripteur;
    }

    public void setPrenomSouscripteur(String prenomSouscripteur) {
        this.prenomSouscripteur = prenomSouscripteur;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }

    public Date getDateSouscription() {
        return dateSouscription;
    }

    public void setDateSouscription(Date dateSouscription) {
        this.dateSouscription = dateSouscription;
    }

    public Double getPrimenet() {
        return primenet;
    }

    public void setPrimenet(Double primenet) {
        this.primenet = primenet;
    }

    public Double getPrimetotal() {
        return primetotal;
    }

    public void setPrimetotal(Double primetotal) {
        this.primetotal = primetotal;
    }

    public Double getReduction() {
        return reduction;
    }

    public void setReduction(Double reduction) {
        this.reduction = reduction;
    }

    public String getNoPolice() {
        return noPolice;
    }

    public void setNoPolice(String noPolice) {
        this.noPolice = noPolice;
    }

    public Integer getValide() {
        return valide;
    }

    public void setValide(Integer valide) {
        this.valide = valide;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }
}
